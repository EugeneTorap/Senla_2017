package com.senla.api.model;

import com.senla.enums.Status;
import com.senla.model.Reader;

import java.util.Date;
import java.util.List;

public interface IOrder extends IEntity {
    Status getStatus();

    Integer getPrice();

    Date getDateExecuted();

    String toString();

    IOrder clone() throws CloneNotSupportedException;
}
