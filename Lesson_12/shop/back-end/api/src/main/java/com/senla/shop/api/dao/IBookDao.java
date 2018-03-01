package com.senla.shop.api.dao;

import com.senla.shop.model.Book;
import org.hibernate.Session;

import java.util.List;

public interface IBookDao extends IGenericDao<Book> {
    List<Book> getAllUnsold(Session session, String sort);
}
