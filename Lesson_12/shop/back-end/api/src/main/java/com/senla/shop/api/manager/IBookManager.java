package com.senla.shop.api.manager;

import com.senla.shop.model.Book;

import java.util.List;

public interface IBookManager extends IManager<Book> {
    List<Book> getAllUnsold(String sort) throws Exception;
}
