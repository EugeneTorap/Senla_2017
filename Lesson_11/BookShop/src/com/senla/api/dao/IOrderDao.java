package com.senla.api.dao;

import com.senla.model.entity.Order;

public interface IOrderDao {
    void create(Order order);
    Order findById(int id);
    void update(int id);
    void clone(int id);
}
