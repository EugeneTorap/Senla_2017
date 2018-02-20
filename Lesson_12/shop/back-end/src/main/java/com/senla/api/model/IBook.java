package com.senla.api.model;

import java.util.Date;

public interface IBook extends IEntity {
    String getTitle();

    Boolean getIsStore();

    Date getDateReceipted();

    Date getDatePublished();

    Integer getPrice();

    String toString();

    IBook clone() throws CloneNotSupportedException;

    boolean equals(Object o);

    int hashCode();
}
