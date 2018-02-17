package com.senla.api.model;

import com.senla.enums.Status;

import java.util.Date;

public interface IOrder extends IEntity {
    Status getStatus();

    IReader getReader();

    int getPrice();

    Date getDateExecuted();

    String toString();

    IOrder clone() throws CloneNotSupportedException;
}
