package com.senla.controller.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static DaoFactory instance = null;
    private Connection connection;
    private final static Logger LOGGER = Logger.getLogger(DaoFactory.class);

    public static DaoFactory getInstance() {
        try {
            if (instance == null) {
                instance = new DaoFactory();
            } else if (instance.getConnection().isClosed()) {
                instance = new DaoFactory();
            }
        }
        catch (SQLException e){
            LOGGER.error("Failed close", e);
        }
        return instance;
    }

    private DaoFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = JDBCProperty.getInstance().getProperty("url");
            String username = JDBCProperty.getInstance().getProperty("user");
            String password = JDBCProperty.getInstance().getProperty("password");

            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            LOGGER.error("Database Connection Creation Failed: ", e);
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
}
