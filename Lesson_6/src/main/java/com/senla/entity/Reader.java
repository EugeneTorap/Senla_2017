package com.senla.entity;

public class Reader extends Entity {
    private static final long serialVersionUID = -561916297036215555L;
    private String name;


    public Reader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getId() + " " + name;
    }

    public String toStringWithSign() {
        return getId() + "," + name;
    }

    public Reader clone() throws CloneNotSupportedException {
        return (Reader) super.clone();
    }
}
