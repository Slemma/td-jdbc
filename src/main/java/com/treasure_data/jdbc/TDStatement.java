package com.treasure_data.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import org.hsqldb.result.ResultConstants;

import com.treasure_data.jdbc.internal.CommandExecutor;
import com.treasure_data.jdbc.internal.TreasureDataClientAdaptor;

public class TDStatement implements Statement {
    private CommandExecutor exec;

    private int fetchSize = 50;

    /**
     * We need to keep a reference to the result set to support the following:
     * <code>
     * statement.execute(String sql);
     * statement.getResultSet();
     * </code>.
     */
    private ResultSet currentResultSet = null;

    /**
     * The maximum number of rows this statement should return (0 => all rows).
     */
    private int maxRows = 0;

    /**
     * Add SQLWarnings to the warningChain if needed.
     */
    private SQLWarning warningChain = null;

    /**
     * Keep state so we can fail certain calls made after close().
     */
    private boolean isClosed = false;

    public TDStatement(TDConnection conn) {
        this.exec = new CommandExecutor(new TreasureDataClientAdaptor(conn));
    }

    public void addBatch(String sql) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void cancel() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void clearBatch() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void clearWarnings() throws SQLException {
        warningChain = null;
    }

    public void close() throws SQLException {
        currentResultSet = null;
        isClosed = true;
    }

    public boolean execute(String sql) throws SQLException {
        ResultSet rs = executeQuery(sql);

        // TODO: this should really check if there are results, but there's no easy
        // way to do that without calling rs.next();
        return rs != null;
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return execute(sql);
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return execute(sql);
    }

    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return execute(sql);
    }

    public int[] executeBatch() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public synchronized ResultSet executeQuery(String sql) throws SQLException {
        fetchResult(sql);
        return getResultSet();
    }

    private void fetchResult(String sql) throws SQLException {
        try {
            currentResultSet = exec.execute(ResultConstants.EXECDIRECT, sql);
        } catch (Throwable t) {
            throw new SQLException(t);
        }
    }

    public int executeUpdate(String sql) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public Connection getConnection() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int getFetchDirection() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int getFetchSize() throws SQLException {
        return fetchSize;
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int getMaxFieldSize() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int getMaxRows() throws SQLException {
        return maxRows;
    }

    public boolean getMoreResults() throws SQLException {
        return getMoreResults(CLOSE_CURRENT_RESULT);
    }

    public boolean getMoreResults(int current) throws SQLException {
        return true;
    }

    public int getQueryTimeout() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public ResultSet getResultSet() throws SQLException {
        ResultSet tmp = currentResultSet;
        currentResultSet = null;
        return tmp;
    }

    public int getResultSetConcurrency() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int getResultSetHoldability() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int getResultSetType() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int getUpdateCount() throws SQLException {
        return 0;
    }

    public SQLWarning getWarnings() throws SQLException {
        return warningChain;
    }

    public boolean isClosed() throws SQLException {
        return isClosed;
    }

    public boolean isPoolable() throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void setCursorName(String name) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void setFetchDirection(int direction) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void setFetchSize(int rows) throws SQLException {
        fetchSize = rows;
    }

    public void setMaxFieldSize(int max) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void setMaxRows(int max) throws SQLException {
        if (max < 0) {
            throw new SQLException("max must be >= 0");
        }
        maxRows = max;
    }

    public void setPoolable(boolean poolable) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException(new UnsupportedOperationException());
    }
}
