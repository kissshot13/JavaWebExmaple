package com.kissshot.servletDemo.service;

import com.mysql.jdbc.Connection;

/**
 *  假代码
 */
public class DBConnectionManager {

    private String dbUrl;
    private String user;
    private String password;
    private Connection connection;

    public DBConnectionManager(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection(){
        return this.connection;
    }

    public void closeConnection(){
        //close DB connection here
    }
}
