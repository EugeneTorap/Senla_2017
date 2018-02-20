package com.senla.model;

import com.senla.api.model.IEntity;

import java.io.Serializable;

public abstract class Entity implements IEntity, Serializable, Cloneable {
    protected int id;

    public Entity() {

    }

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
