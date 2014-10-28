package com.treasure_data.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.treasure_data.jdbc.command.CommandContext;

public class TDPreparedStatement extends TDStatement implements PreparedStatement {
    private static Logger LOG = Logger.getLogger(
            TDPreparedStatement.class.getName());

    private CommandContext w;

    private final HashMap<Integer, Object> params = new HashMap<Integer, Object>();

    public TDPreparedStatement(TDConnection conn, String sql)
            throws SQLException {
        super(conn);
        w = createCommandContext(sql);
    }

    public void clearParameters() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#clearParameters()"));
    }

    public void addBatch() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#clearBatch()"));
    }

    public void clearBatch() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#clearBatch()"));
    }

    public boolean execute() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#execute()"));
    }

    public synchronized ResultSet executeQuery() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#executeQuery()"));
    }

    @Override
    public int[] executeBatch() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#executeBatch()"));
    }

    public int executeUpdate() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#executeUpdate()"));
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#getMetaData()"));
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#getParameterMetaData()"));
    }

    public void setArray(int i, Array x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setArray(int, Array)"));
    }

    public void setAsciiStream(int i, InputStream in) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setAsciiStream(int, InputStream)"));
    }

    public void setAsciiStream(int i, InputStream in, int length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setAsciiStream(int, InputStream, int)"));
    }

    public void setAsciiStream(int i, InputStream in, long length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setAsciiStream(int, InputStream, long)"));
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x)
            throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBigDecimal(int, BigDecimal)"));
    }

    public void setBinaryStream(int i, InputStream x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBinaryStream(int, InputStream)"));
    }

    public void setBinaryStream(int i, InputStream in, int length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBinaryStream(int, InputStream, int)"));
    }

    public void setBinaryStream(int i, InputStream in, long length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBinaryStream(int, InputStream, long)"));
    }

    public void setBlob(int i, Blob x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBlob(int, Blob)"));
    }

    public void setBlob(int i, InputStream inputStream) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBlob(int, InputStream)"));
    }

    public void setBlob(int i, InputStream inputStream, long length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBlob(int, InputStream, long)"));
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBoolean(int, boolean)"));
    }

    public void setByte(int i, byte x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setByte(int, byte)"));
    }

    public void setBytes(int i, byte[] x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setBytes(int, byte[])"));
    }

    public void setCharacterStream(int i, Reader reader) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setCharacterStream(int, Reader)"));
    }

    public void setCharacterStream(int i, Reader reader, int length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setCharacterStream(int, Reader, int)"));
    }

    public void setCharacterStream(int i, Reader reader, long length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setCharacterStream(int, Reader, long)"));
    }

    public void setClob(int i, Clob x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setClob(int, Clob)"));
    }

    public void setClob(int i, Reader reader) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setClob(int, Reader)"));
    }

    public void setClob(int i, Reader reader, long length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setClob(int, Reader, long)"));
    }

    public void setDate(int i, Date x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setDate(int, Date)"));
    }

    public void setDate(int i, Date x, Calendar cal) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setDate(int, Date, Calender)"));
    }

    public void setDouble(int i, double x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setDouble(int, double)"));
    }

    public void setFloat(int i, float x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setFloat(int, float)"));
    }

    public void setInt(int i, int x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setInt(int, int)"));
    }

    public void setLong(int i, long x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setLong(int, long)"));
    }

    public void setNCharacterStream(int i, Reader value) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNCharacterStream(int, Reader)"));
    }

    public void setNCharacterStream(int i, Reader value, long length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNCharacterStream(int, Reader, long)"));
    }

    public void setNClob(int i, NClob value) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNClob(int, NClob)"));
    }

    public void setNClob(int i, Reader reader) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNClob(int, Reader)"));
    }

    public void setNClob(int i, Reader reader, long length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNClob(int, Reader, long)"));
    }

    public void setNString(int i, String value) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNString(int, String)"));
    }

    public void setNull(int i, int sqlType) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNull(int, int)"));
    }

    public void setNull(int i, int sqlType, String typeName) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setNull(int, int, String)"));
    }

    public void setObject(int i, Object x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setObject(int, Object)"));
    }

    public void setObject(int i, Object x, int targetSqlType) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setObject(int, Object, int)"));
    }

    public void setObject(int i, Object x, int targetSqlType, int scale) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setObject(int, Object, int, int)"));
    }

    public void setRef(int i, Ref x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setRef(int, Ref)"));
    }

    public void setRowId(int i, RowId x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setRowId(int, RowId)"));
    }

    public void setSQLXML(int i, SQLXML xmlObject) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setSQLXML(int, SQLXML)"));
    }

    public void setShort(int i, short x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setShort(int, short)"));
    }

    public void setString(int i, String x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setString(int, String)"));
    }

    public void setTime(int i, Time x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setTime(int, Time)"));
    }

    public void setTime(int i, Time x, Calendar cal) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setTime(int, Time, Calendar)"));
    }

    public void setTimestamp(int i, Timestamp x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setTimestamp(int, Timestamp)"));
    }

    public void setTimestamp(int i, Timestamp x, Calendar cal) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setTimestamp(int, Timestamp, Calendar)"));
    }

    public void setURL(int i, URL x) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setURL(int, URL)"));
    }

    public void setUnicodeStream(int i, InputStream x, int length) throws SQLException {
        throw new SQLException(new UnsupportedOperationException("TDPreparedStatement#setUnicodeStream(int, InputStream, int)"));
    }

}
