package com.senla.connector;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnector {

    private static DBConnector instance;
    private SessionFactory sessionFactory;


    private DBConnector(){
        connect();
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    private void connect() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    
    public SessionFactory getSessionFactory(){
        if (sessionFactory == null || sessionFactory.isClosed()){
            connect();
        }
        return sessionFactory;
    }
}
