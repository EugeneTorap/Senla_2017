package com.senla.model.entity;

public class Reader extends Entity {
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

    public String getName() {
        return name;
    }

    public String toString() {
        return getId() + "," + name;
    }

    public Reader clone() throws CloneNotSupportedException {
        return (Reader) super.clone();
    }
}
