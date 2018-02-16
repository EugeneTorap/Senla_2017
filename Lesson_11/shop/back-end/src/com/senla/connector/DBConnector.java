package com.senla.connector;

import com.senla.controller.dao.JDBCProperty;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static DBConnector instance = null;
    private static Connection connection;
    private final static Logger LOGGER = Logger.getLogger(DBConnector.class);


    private DBConnector(){
        connect();
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    private static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = JDBCProperty.getInstance().getProperty("url");
            String username = JDBCProperty.getInstance().getProperty("user");
            String password = JDBCProperty.getInstance().getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            LOGGER.error("Database Connection Creation Failed: ", e);
        }
    }
    
    public Connection getConnection(){
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
