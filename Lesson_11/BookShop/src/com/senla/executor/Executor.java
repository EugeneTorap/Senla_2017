package com.senla.executor;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final static Logger LOGGER = Logger.getLogger(Executor.class);

    public static void execUpdate(Connection connection, String[] sql) {
        try {
            connection.setAutoCommit(false);
            for (String str: sql){
                try (Statement statement = connection.createStatement()) {
                    statement.execute(str);
                }
                catch (SQLException e){
                    LOGGER.error("Can't close statement", e);
                }
            }
            connection.commit();
        }
        catch (SQLException e){
            LOGGER.error("Commit is failed", e);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            catch (SQLException ex){
                LOGGER.error("Rollback is failed", ex);
            }
        }
    }

    public static void execUpdate(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e){
            LOGGER.error("Can't close statement", e);
        }
    }

    public static <T> T execQuery(Connection connection, String sql, ResultHandler<T> handler) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            try (ResultSet result = statement.getResultSet()){
                return handler.handle(result);
            }
            catch (SQLException e){
                LOGGER.error("Can't close resultSet", e);
            }
        } catch (SQLException e){
            LOGGER.error("Can't close statement", e);
        }
        return null;
    }
}
