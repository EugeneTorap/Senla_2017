package entity;

import util.IdGenerator;

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
