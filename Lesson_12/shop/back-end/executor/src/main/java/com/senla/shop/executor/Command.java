package com.senla.shop.executor;

import org.hibernate.Session;

public interface Command<T> {
    T process(Session session) throws CloneNotSupportedException;
}
