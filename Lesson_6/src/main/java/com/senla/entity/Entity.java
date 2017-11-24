package com.senla.entity;

import com.senla.util.IdGenerator;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private int id;


    public Entity() {
        this.id = IdGenerator.generateId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract String toStringWithSign();
}
