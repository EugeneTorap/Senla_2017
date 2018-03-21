package com.senla.shop.api.dao;

import com.senla.shop.model.Reader;
import org.hibernate.Session;

public interface IReaderDao extends IGenericDao<Reader> {
    Reader getByToken(Session session, String token);
}
