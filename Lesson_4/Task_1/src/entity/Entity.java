package entity;

import util.IdGenerator;

public abstract class Entity {
    private int id;


    public Entity() {
        this.id = IdGenerator.generateId();
    }

    public int getId() {
        return id;
    }
}
