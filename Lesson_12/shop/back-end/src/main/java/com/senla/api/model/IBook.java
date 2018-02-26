package com.senla.api.model;

import com.senla.model.Book;

import java.util.Date;
import java.util.List;

public interface IBook extends IEntity {

    void setId(int id);

    int getId();

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

    Book clone() throws CloneNotSupportedException;

    boolean equals(Object o);

    int hashCode();
}
