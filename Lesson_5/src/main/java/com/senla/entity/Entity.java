package com.senla.entity;

import com.senla.util.IdGenerator;

public abstract class Entity {
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
}
