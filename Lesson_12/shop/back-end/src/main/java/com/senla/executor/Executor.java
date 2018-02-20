package com.senla.executor;

import com.senla.connector.DBConnector;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Executor {

    private final static Logger LOGGER = Logger.getLogger(Executor.class);

    public static <T> T transact(Command<T> command){
        Transaction transaction = null;
        try(Session session = DBConnector.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T t = command.process(session);
            transaction.commit();
            return t;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            transaction.rollback();
        }
        return null;
    }
}
