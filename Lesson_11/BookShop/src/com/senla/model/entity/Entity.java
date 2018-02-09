package com.senla.model.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    private int id;


    public Entity(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
