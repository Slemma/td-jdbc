/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.treasuredata.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TDStatement
        extends TDStatementBase
        implements Statement, Constants
{
    private int fetchSize = 50;

    public TDStatement(TDConnection conn)
    {
        super(conn);
    }

    public void addBatch(String sql)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#addBatch()"));
    }

    public void cancel()
            throws SQLException
    {
    }

    public void clearBatch()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#clearBatch()"));
    }

    public boolean execute(String sql)
            throws SQLException
    {
        // TODO: this should really check if there are results, but there's no easy
        // way to do that without calling rs.next();
        return executeQuery(sql) != null;
    }

    public boolean execute(String sql, int autoGeneratedKeys)
            throws SQLException
    {
        return execute(sql);
    }

    public boolean execute(String sql, int[] columnIndexes)
            throws SQLException
    {
        return execute(sql);
    }

    public boolean execute(String sql, String[] columnNames)
            throws SQLException
    {
        return execute(sql);
    }

    public int[] executeBatch()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#executeBatch()"));
    }

    public synchronized ResultSet executeQuery(String sql)
            throws SQLException
    {
        fetchResult(sql);
        TDResultSetBase rs = getResultSet();
        return rs;
    }

    public int executeUpdate(String sql)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException());
    }

    public int executeUpdate(String sql, int autoGeneratedKeys)
            throws SQLException
    {
        return executeUpdate(sql);
    }

    public int executeUpdate(String sql, int[] columnIndexes)
            throws SQLException
    {
        return executeUpdate(sql);
    }

    public int executeUpdate(String sql, String[] columnNames)
            throws SQLException
    {
        return executeUpdate(sql);
    }

    public int getFetchDirection()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#getFetchDirection()"));
    }

    public int getFetchSize()
            throws SQLException
    {
        return fetchSize;
    }

    public ResultSet getGeneratedKeys()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#getGeneratedKeys()"));
    }

    public int getMaxFieldSize()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#getMaxFieldSize()"));
    }

    public boolean getMoreResults()
            throws SQLException
    {
        return getMoreResults(CLOSE_CURRENT_RESULT);
    }

    public boolean getMoreResults(int current)
            throws SQLException
    {
        return true;
    }

    public int getResultSetConcurrency()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#getResultSetConcurrency()"));
    }

    public int getResultSetHoldability()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#getResultSetHoldability()"));
    }

    public int getResultSetType()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#getResultSetType()"));
    }

    public boolean isPoolable()
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#isPoolable()"));
    }

    public void setCursorName(String name)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#setCursorName(String)"));
    }

    public void setFetchDirection(int direction)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#setFetchDirection(int)"));
    }

    public void setFetchSize(int rows)
            throws SQLException
    {
        fetchSize = rows;
    }

    public void setMaxFieldSize(int max)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#setMaxFieldSize(int)"));
    }

    public void setPoolable(boolean poolable)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#setPoolable(boolean)"));
    }

    public boolean isWrapperFor(Class<?> iface)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#isWrapperFor(Class)"));
    }

    public <T> T unwrap(Class<T> iface)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException("TDStatement#unwrap(Class)"));
    }
}
