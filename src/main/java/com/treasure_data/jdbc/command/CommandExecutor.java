package com.treasure_data.jdbc.command;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hsqldb.result.ResultConstants;

import com.treasure_data.jdbc.compiler.expr.DateValue;
import com.treasure_data.jdbc.compiler.expr.DoubleValue;
import com.treasure_data.jdbc.compiler.expr.Expression;
import com.treasure_data.jdbc.compiler.expr.JdbcParameter;
import com.treasure_data.jdbc.compiler.expr.LongValue;
import com.treasure_data.jdbc.compiler.expr.NullValue;
import com.treasure_data.jdbc.compiler.expr.StringValue;
import com.treasure_data.jdbc.compiler.expr.TimeValue;
import com.treasure_data.jdbc.compiler.expr.ops.ExpressionList;
import com.treasure_data.jdbc.compiler.expr.ops.ItemsList;
import com.treasure_data.jdbc.compiler.parser.CCSQLParser;
import com.treasure_data.jdbc.compiler.parser.ParseException;
import com.treasure_data.jdbc.compiler.schema.Column;
import com.treasure_data.jdbc.compiler.schema.Table;
import com.treasure_data.jdbc.compiler.stat.ColumnDefinition;
import com.treasure_data.jdbc.compiler.stat.CreateTable;
import com.treasure_data.jdbc.compiler.stat.Index;
import com.treasure_data.jdbc.compiler.stat.Insert;
import com.treasure_data.jdbc.compiler.stat.Select;

/**
 * @see org.hsqldb.Session
 * @see org.hsqldb.SessionInterface
 */
public class CommandExecutor {
    private ClientAdaptor clientAdaptor;

    public CommandExecutor(ClientAdaptor clientAdaptor) {
        this.clientAdaptor = clientAdaptor;
    }

    public ClientAdaptor getClientAdaptor() {
        return clientAdaptor;
    }

    public synchronized Wrapper execute(Wrapper w)
            throws SQLException {
        switch (w.mode) {
        case ResultConstants.LARGE_OBJECT_OP:
        case ResultConstants.EXECUTE:
        case ResultConstants.BATCHEXECUTE:
            throw new UnsupportedOperationException();

        case ResultConstants.EXECDIRECT:
            return executeDirect(w);

        case ResultConstants.BATCHEXECDIRECT:
        case ResultConstants.PREPARE:
            return executePrepare(w);

        case ResultConstants.CLOSE_RESULT:
        case ResultConstants.UPDATE_RESULT:
        case ResultConstants.FREESTMT:
        case ResultConstants.GETSESSIONATTR:
        case ResultConstants.SETSESSIONATTR:
        case ResultConstants.ENDTRAN:
        case ResultConstants.SETCONNECTATTR:
        case ResultConstants.REQUESTDATA:
        case ResultConstants.DISCONNECT:
        default:
            throw new UnsupportedOperationException();
        }
    }

    public Wrapper executeDirect(Wrapper w) throws SQLException {
        try {
            String sql = w.sql;
            InputStream in = new ByteArrayInputStream(sql.getBytes());
            CCSQLParser p = new CCSQLParser(in);
            w.compiledSql = p.Statement();
            extractJdbcParameters(w);
            if (w.paramList.size() != 0) {
                throw new ParseException("sql includes some jdbcParameters");
            }
            return executeCompiledStatement(w);
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    public Wrapper executePrepare(Wrapper w) throws SQLException {
        if (w.compiledSql == null) {
            try {
                String sql = w.sql;
                InputStream in = new ByteArrayInputStream(sql.getBytes());
                CCSQLParser p = new CCSQLParser(in);
                w.compiledSql = p.Statement();
                extractJdbcParameters(w);
                return w;
            } catch (ParseException e) {
                throw new SQLException(e);
            }
        } else {
            return executeCompiledPreparedStatement(w);
        }
    }

    private Wrapper extractJdbcParameters(Wrapper w) throws SQLException {
        w.paramList = new ArrayList<String>();
        com.treasure_data.jdbc.compiler.stat.Statement stat = w.compiledSql;
        if (stat instanceof Insert) {
            return extractJdbcParameters(w, (Insert) stat);
        } else if (stat instanceof CreateTable) {
            return extractJdbcParameters(w, (CreateTable) stat);
        } else if (stat instanceof Select) {
            return extractJdbcParameters(w, (Select) stat);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public Wrapper executeCompiledStatement(Wrapper w) throws SQLException {
        com.treasure_data.jdbc.compiler.stat.Statement stat = w.compiledSql;
        if (stat instanceof Insert) {
            return executeCompiledStatement(w, (Insert) stat);
        } else if (stat instanceof CreateTable) {
            return executeCompiledStatement(w, (CreateTable) stat);
        } else if (stat instanceof Select) {
            return executeCompiledStatement(w, (Select) stat);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public Wrapper executeCompiledPreparedStatement(Wrapper w) throws SQLException {
        com.treasure_data.jdbc.compiler.stat.Statement stat = w.compiledSql;
        if (stat instanceof Insert) {
            return executeCompiledPreparedStatement(w, (Insert) stat);
        } else if (stat instanceof CreateTable) {
            return executeCompiledPreparedStatement(w, (CreateTable) stat);
        } else if (stat instanceof Select) {
            return executeCompiledPreparedStatement(w, (Select) stat);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public Wrapper executeCompiledStatement(Wrapper w, Select stat)
            throws SQLException {
        String sql = stat.toString();
        w.resultSet = clientAdaptor.select(sql);
        return w;
    }

    public Wrapper executeCompiledPreparedStatement(Wrapper w, Select stat)
            throws SQLException {
        return this.executeCompiledStatement(w, stat);
    }

    public Wrapper extractJdbcParameters(Wrapper w, Select stat) {
        return w;
    }

    public Wrapper executeCompiledStatement(Wrapper w, Insert stat) {
        /**
         * SQL:
         * insert into table02 (k1, k2, k3) values (2, 'muga', 'nishizawa')
         *
         * ret:
         * table => table02
         * cols  => [k1, k2, k3]
         * items => (2, 'muga', 'nishizawa')
         */

        Table table = stat.getTable();
        // table validation
        if (table == null
                || table.getName() == null
                || table.getName().isEmpty()) {
            throw new UnsupportedOperationException();
        }

        // columns validation
        List<Column> cols = stat.getColumns();
        if (cols == null || cols.size() <= 0) {
            throw new UnsupportedOperationException();
        }

        // items validation
        List<Expression> exprs;
        {
            ItemsList items = stat.getItemsList();
            if (items == null) {
                throw new UnsupportedOperationException();
            }
            try {
                exprs = ((ExpressionList) items).getExpressions();
            } catch (Throwable t) {
                throw new UnsupportedOperationException();
            }
        }

        // other validations
        if (cols.size() != exprs.size()) {
            throw new UnsupportedOperationException();
        }

        try {
            Map<String, Object> record = new HashMap<String, Object>();
            Iterator<Column> col_iter = cols.iterator();
            Iterator<Expression> expr_iter = exprs.iterator();
            while (col_iter.hasNext()) {
                Column col = col_iter.next();
                Expression expr = expr_iter.next();
                record.put(col.getColumnName(), toValue(expr));
            }
            clientAdaptor.insertData(table.getName(), record);
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }

        return w;
    }

    public Wrapper executeCompiledPreparedStatement(Wrapper w, Insert stat) {
        /**
         * SQL:
         * insert into table02 (k1, k2, k3) values (?, ?, 'nishizawa')
         *
         * ret:
         * table => table02
         * cols  => [k1, k2, k3]
         * items => (?, ?, 'nishizawa')
         */

        Table table = stat.getTable();
        // table validation
        if (table == null
                || table.getName() == null
                || table.getName().isEmpty()) {
            throw new UnsupportedOperationException();
        }

        // columns validation
        List<Column> cols = stat.getColumns();
        if (cols == null || cols.size() <= 0) {
            throw new UnsupportedOperationException();
        }

        // items validation
        List<Expression> exprs;
        {
            ItemsList items = stat.getItemsList();
            if (items == null) {
                throw new UnsupportedOperationException();
            }
            try {
                exprs = ((ExpressionList) items).getExpressions();
            } catch (Throwable t) {
                throw new UnsupportedOperationException();
            }
        }

        // other validations
        if (cols.size() != exprs.size()) {
            throw new UnsupportedOperationException();
        }


        List<String> paramList = w.paramList;
        Map<Integer, Object> params = w.params;

        try {
            Map<String, Object> record = new HashMap<String, Object>();
            Iterator<Column> col_iter = cols.iterator();
            Iterator<Expression> expr_iter = exprs.iterator();
            while (col_iter.hasNext()) {
                Column col = col_iter.next();
                String colName = col.getColumnName();
                int i = getIndex(paramList, colName);
                if (i >= 0) {
                    record.put(colName, params.get(new Integer(i + 1)));
                } else {
                    Expression expr = expr_iter.next();
                    record.put(colName, toValue(expr));
                }
            }
            clientAdaptor.insertData(table.getName(), record);
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }

        return w;
    }

    public Wrapper extractJdbcParameters(Wrapper w, Insert stat) {
        List<Column> cols = stat.getColumns();
        List<Expression> exprs =
            ((ExpressionList) stat.getItemsList()).getExpressions();
        int len = cols.size();
        for (int i = 0; i < len; i++) {
            Expression expr = exprs.get(i);
            if (! (expr instanceof JdbcParameter)) {
                continue;
            }

            String colName = cols.get(i).getColumnName();
            w.paramList.add(colName);
        }
        return w;
    }

    public Wrapper executeCompiledStatement(Wrapper w, CreateTable stat) {
        /**
         * SQL:
         * create table table01(c0 varchar(255), c1 int)
         *
         * ret:
         * table => table02
         */

        // table validation
        Table table = stat.getTable();
        if (table == null
                || table.getName() == null
                || table.getName().isEmpty()) {
            throw new UnsupportedOperationException();
        }

        // column definition validation
        List<ColumnDefinition> def = stat.getColumnDefinitions();
        if (def == null || def.size() == 0) {
            throw new UnsupportedOperationException();
        }

        // this variable is not used
        List<Index> indexes = stat.getIndexes();

        try {
            clientAdaptor.createTable(table.getName());
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }

        return w;
    }

    public Wrapper executeCompiledPreparedStatement(Wrapper w, CreateTable stat)
            throws SQLException {
        return this.executeCompiledStatement(w, stat);
    }

    public Wrapper extractJdbcParameters(Wrapper w, CreateTable stat) {
        return w;
    }

    private static int getIndex(List<String> list, String data) {
        for (int i = 0; i < list.size(); i++) {
            String d = list.get(i);
            if (d.equals(data)) {
                return i;
            }
        }
        return -1;
    }

    private static Object toValue(Expression expr) {
        if (expr instanceof DateValue) {
            DateValue v = (DateValue) expr;
            return v.getValue().getTime() / 1000;
        } else if (expr instanceof DoubleValue) {
            DoubleValue v = (DoubleValue) expr;
            return v.getValue();
        } else if (expr instanceof LongValue) {
            LongValue v = (LongValue) expr;
            return v.getValue();
        } else if (expr instanceof NullValue) {
            return null;
        } else if (expr instanceof StringValue) {
            StringValue v = (StringValue) expr;
            return v.getValue();
        } else if (expr instanceof TimeValue) {
            TimeValue v = (TimeValue) expr;
            return v.getValue().getTime() / 1000;
        } else {
            return org.hsqldb.result.Result.newErrorResult(
                    new UnsupportedOperationException(),
                    expr.toString());
        }
    }
}