package com.senla.executor;

import org.hibernate.Session;

public interface Command<T> {
    T process(Session session);
}
