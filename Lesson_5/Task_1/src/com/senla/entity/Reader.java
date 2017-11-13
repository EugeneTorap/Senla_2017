package com.senla.entity;

public class Reader extends Entity {
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
}
