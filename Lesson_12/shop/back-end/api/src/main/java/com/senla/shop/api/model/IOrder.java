package com.senla.shop.api.model;

import com.senla.shop.enums.Status;

import java.util.Date;

public interface IOrder extends IEntity {

    Status getStatus();

    Integer getPrice();

    Date getDateExecuted();

    String toString();

    IOrder clone() throws CloneNotSupportedException;
}
