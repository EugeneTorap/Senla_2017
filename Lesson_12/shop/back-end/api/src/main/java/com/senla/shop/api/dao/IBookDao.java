package com.senla.shop.api.dao;

import com.senla.shop.api.model.IBook;
import org.hibernate.Session;

import java.util.List;

public interface IBookDao extends IGenericDao<IBook> {
    List<IBook> getAllUnsold(Session session, String sort);
}
