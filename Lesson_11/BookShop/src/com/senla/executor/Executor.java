package com.senla.executor;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final static Logger LOGGER = Logger.getLogger(Executor.class);

    public static int execUpdate(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            return statement.getUpdateCount();
        } catch (SQLException e){
            LOGGER.error("Can't close statement", e);
        }
        return 0;
    }

    public static <T> T execQuery(Connection connection, String sql, ResultHandler<T> handler) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            try (ResultSet result = statement.getResultSet()){
                return handler.handle(result);
            } catch (SQLException e){
                LOGGER.error("Can't close result", e);
            }
        } catch (SQLException e){
            LOGGER.error("Can't close statement", e);
        }
        return null;
    }
}
