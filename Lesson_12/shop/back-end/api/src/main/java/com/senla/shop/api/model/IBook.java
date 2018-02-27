package com.senla.shop.api.model;

import java.util.Date;

public interface IBook extends IEntity {

    String getTitle();

    void setTitle(String title);

    Integer getPrice();

    void setPrice(Integer price);

    Boolean getIsStore();

    void setIsStore(Boolean store);

    Date getDateReceipted();

    void setDateReceipted(Date dateReceipted);

    Date getDatePublished();

    void setDatePublished(Date datePublished);

    String toString();

    IBook clone() throws CloneNotSupportedException;

    boolean equals(Object o);

    int hashCode();
}
