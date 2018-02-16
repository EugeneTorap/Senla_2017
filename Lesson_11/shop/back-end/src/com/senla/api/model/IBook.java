package com.senla.api.model;

import java.util.Date;

public interface IBook {
    Boolean getTheBookInStore();

    String getTitle();

    int getPrice();

    Date getDatePublished();

    Date getDateReceipted();

    String toString();

    IBook clone() throws CloneNotSupportedException;
}
