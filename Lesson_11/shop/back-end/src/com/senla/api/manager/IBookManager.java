package com.senla.api.manager;

import com.senla.api.model.IBook;

import java.util.List;

public interface IBookManager extends IManager<IBook> {
    List<IBook> getAllUnsold(String sort);
}
