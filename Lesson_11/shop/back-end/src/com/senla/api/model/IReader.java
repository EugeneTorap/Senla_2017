package com.senla.api.model;

public interface IReader extends IEntity {
    String getName();

    int getBalance();

    String toString();

    IReader clone() throws CloneNotSupportedException;
}
