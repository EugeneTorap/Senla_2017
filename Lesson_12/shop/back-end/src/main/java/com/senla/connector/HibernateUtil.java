package com.senla.connector;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static HibernateUtil instance;
    private SessionFactory sessionFactory;
    private final static Logger LOGGER = Logger.getLogger(HibernateUtil.class);


    private HibernateUtil(){
        connect();
    }

    public static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    private void connect() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public SessionFactory getSessionFactory(){
        if (sessionFactory == null || sessionFactory.isClosed()){
            connect();
        }
        return sessionFactory;
    }
}
