package com.senla.shop.api.manager;

import com.senla.shop.api.model.IBook;

import java.util.List;

public interface IBookManager extends IManager<IBook> {
    List<IBook> getAllUnsold(String sort) throws Exception;
}
