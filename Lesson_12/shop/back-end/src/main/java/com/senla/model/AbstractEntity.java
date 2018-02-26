package com.senla.model;

import com.senla.api.model.IEntity;

import java.io.Serializable;

public abstract class AbstractEntity implements IEntity, Serializable, Cloneable {
    protected int id;

    public AbstractEntity() {

    }

    protected AbstractEntity(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
