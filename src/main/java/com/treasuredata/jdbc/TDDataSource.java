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

import javax.sql.DataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class TDDataSource
        implements DataSource
{
    private String password;

    private String user;

    private int loginTimeout;

    private PrintWriter printer;

    public TDDataSource()
    {
    }

    public Connection getConnection()
            throws SQLException
    {
        return getConnection(getUser(), getPassword());
    }

    public Connection getConnection(String user, String password)
            throws SQLException
    {
        throw new SQLException(new UnsupportedOperationException(
                "TDDataSource#getConection(String, String)"));
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getUser()
    {
        return user;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public PrintWriter getLogWriter()
            throws SQLException
    {
        return printer;
    }

    public int getLoginTimeout()
            throws SQLException
    {
        return loginTimeout;
    }

    public void setLogWriter(PrintWriter out)
            throws SQLException
    {
        printer = out;
    }

    public void setLoginTimeout(int seconds)
            throws SQLException
    {
        loginTimeout = seconds;
    }

    /**
     * Returns false unless <code>interfaces</code> is implemented
     *
     * @param interfaces a Class defining an interface.
     * @return true                   if this implements the interface or
     * directly or indirectly wraps an object
     * that does.
     * @throws java.sql.SQLException if an error occurs while determining
     * whether this is a wrapper for an object
     * with the given interface.
     */
    public boolean isWrapperFor(Class<?> interfaces)
            throws SQLException
    {
        return interfaces.isInstance(this);
    }

    /**
     * Returns <code>this</code> if this class implements the interface
     *
     * @param interfaces a Class defining an interface
     * @return an object that implements the interface
     * @throws java.sql.SQLExption if no object if found that implements the
     * interface
     */
    public <T> T unwrap(Class<T> interfaces)
            throws SQLException
    {
        //does not implement non-standard methods on JDBC objects 
        //hence return this if this class implements the interface 
        //or throw an SQLException
        try {
            return interfaces.cast(this);
        }
        catch (ClassCastException e) {
            throw new SQLException(e);
        }
    }
}
