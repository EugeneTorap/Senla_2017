package com.senla.executor;

import com.senla.dao.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final static Logger LOGGER = Logger.getLogger(Executor.class);

    public static void transact(Connection connection, String[] sql) throws DaoException {
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
            throw new DaoException("Commit is failed in method transact", e);
        }
    }

    public static void transact(Connection connection, String sql) throws DaoException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e){
            LOGGER.error("Can't close statement", e);
            throw new DaoException("Can't close statement in generic method transact", e);
        }
    }

    public static <T> T execQuery(Connection connection, String sql, ResultHandler<T> handler) throws DaoException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            try (ResultSet result = statement.getResultSet()){
                return handler.handle(result);
            }
            catch (SQLException e){
                LOGGER.error("Can't close resultSet", e);
                throw new DaoException("Can't close resultSet in generic method execQuery", e);
            }
        } catch (SQLException e){
            LOGGER.error("Can't close statement", e);
            throw new DaoException("Can't close statement in generic method execQuery", e);
        }
    }
}
