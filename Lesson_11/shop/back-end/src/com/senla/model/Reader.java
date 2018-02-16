package com.senla.model;

import com.senla.api.model.IReader;

public class Reader extends Entity implements IReader {
    private static final long serialVersionUID = -561916297036215555L;
    private String name;
    private int balance;


    public Reader(int id, String name) {
        super(id);
        this.name = name;
    }

    public Reader(int id, String name, int balance) {
        super(id);
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return getId() + "," + name;
    }

    @Override
    public Reader clone() throws CloneNotSupportedException {
        return (Reader) super.clone();
    }
}
