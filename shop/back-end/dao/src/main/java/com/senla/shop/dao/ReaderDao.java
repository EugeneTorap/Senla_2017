package com.senla.shop.dao;

import com.senla.shop.api.dao.IReaderDao;
import com.senla.shop.model.Reader;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ReaderDao extends GenericDao<Reader> implements IReaderDao {

    public ReaderDao() {
        super(Reader.class);
    }

    @Override
    public Reader getByToken(Session session, String token) {
        Query query = session.createQuery("FROM Reader WHERE Reader.token = token");
        query.setParameter("token", token);
        return (Reader) query.getResultList().get(0);
    }

    @Override
    public Reader getByLogin(Session session, String name) {
        Query query = session.createQuery("FROM Reader WHERE Reader.name = name");
        query.setParameter("name", name);
        return (Reader) query.getResultList().get(0);
    }
}
