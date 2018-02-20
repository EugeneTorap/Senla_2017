package com.senla.connector;

import com.senla.dao.JDBCProperty;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static DBConnector instance = null;
    private Connection connection;
    private final static Logger LOGGER = Logger.getLogger(DBConnector.class);


    private DBConnector() throws Exception {
        connect();
    }

    public static DBConnector getInstance() throws Exception {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    private void connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = JDBCProperty.getInstance().getProperty("url");
            String username = JDBCProperty.getInstance().getProperty("user");
            String password = JDBCProperty.getInstance().getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            LOGGER.error("Database Connection Creation Failed: ", e);
            throw new Exception("Connection is failed", e);
        }
    }
    
    public Connection getConnection() throws Exception {
        try {
            if (connection == null || connection.isClosed()){
                connect();
            }
        }
        catch (SQLException e) {
            LOGGER.error("Failed close", e);
        }
        return connection;
    }
}
