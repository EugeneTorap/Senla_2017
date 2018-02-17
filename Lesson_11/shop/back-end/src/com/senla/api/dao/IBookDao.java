package com.senla.api.dao;

import com.senla.api.model.IBook;
import com.senla.controller.dao.DaoException;

import java.util.List;

public interface IBookDao extends GenericDao<IBook> {
    List<IBook> getAllUnsold(String sort) throws DaoException;
}
