package com.senla.api.dao;

import com.senla.api.model.IBook;
import com.senla.dao.DaoException;

import java.util.List;

public interface IBookDao extends IGenericDao<IBook> {
    List<IBook> getAllUnsold(String sort) throws Exception;
}
