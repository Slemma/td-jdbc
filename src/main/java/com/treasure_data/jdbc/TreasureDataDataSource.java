package com.treasure_data.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TreasureDataDataSource implements DataSource {
    private String password;

    private String user;

    private int loginTimeout;

    private PrintWriter printer;

    public TreasureDataDataSource() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.DataSource#getConnection()
     */
    public Connection getConnection() throws SQLException {
        return getConnection(getUser(), getPassword());
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.DataSource#getConnection(java.lang.String,
     * java.lang.String)
     */
    public Connection getConnection(String username, String password)
            throws SQLException {
        // TODO #MN
        try {
            return new JDBCConnection("", null);
        } catch (Exception e) {
            throw new SQLException(
                    "Error in getting TreasureDataConnection", e);
        }
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#getLogWriter()
     */
    public PrintWriter getLogWriter() throws SQLException {
        return printer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#getLoginTimeout()
     */
    public int getLoginTimeout() throws SQLException {
        return loginTimeout;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
     */
    public void setLogWriter(PrintWriter out) throws SQLException {
        printer = out;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#setLoginTimeout(int)
     */
    public void setLoginTimeout(int seconds) throws SQLException {
        loginTimeout = seconds;
    }

    /**
     * Returns false unless <code>interfaces</code> is implemented 
     * 
     * @param  interfaces             a Class defining an interface.
     * @return true                   if this implements the interface or 
     *                                directly or indirectly wraps an object 
     *                                that does.
     * @throws java.sql.SQLException  if an error occurs while determining 
     *                                whether this is a wrapper for an object 
     *                                with the given interface.
     */
    public boolean isWrapperFor(Class<?> interfaces) throws SQLException {
        return interfaces.isInstance(this);
    }

    /**
     * Returns <code>this</code> if this class implements the interface
     *
     * @param  interfaces a Class defining an interface
     * @return an object that implements the interface
     * @throws java.sql.SQLExption if no object if found that implements the 
     * interface
     */
    public <T> T unwrap(Class<T> interfaces) throws SQLException {
        //does not implement non-standard methods on JDBC objects 
        //hence return this if this class implements the interface 
        //or throw an SQLException
        try {
            return interfaces.cast(this);
        } catch (ClassCastException e) {
            throw new SQLException(e);
        }
    }

}
