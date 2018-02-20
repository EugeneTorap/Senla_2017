package com.senla.api.model;

public interface IReader extends IEntity {
    String getName();

    Integer getBalance();

    String toString();

    IReader clone() throws CloneNotSupportedException;

    boolean equals(Object o);

    int hashCode();
}
