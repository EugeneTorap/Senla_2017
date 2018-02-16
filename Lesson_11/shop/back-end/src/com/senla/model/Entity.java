package com.senla.model;

import com.senla.api.model.IEntity;

import java.io.Serializable;

public abstract class Entity implements IEntity, Serializable, Cloneable {
    private int id;


    public Entity(int id) {
        this.id = id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
