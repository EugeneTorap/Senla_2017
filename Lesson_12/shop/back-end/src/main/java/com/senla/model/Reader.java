package com.senla.model;

import com.senla.api.model.IReader;

public class Reader extends Entity implements IReader {
    private static final long serialVersionUID = -561916297036215555L;
    private String name;
    private Integer balance;


    public Reader(){

    }

    public Reader(int id, String name) {
        super(id);
        this.name = name;
    }

    public Reader(int id, String name, int balance) {
        super(id);
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return getId() + "," + name;
    }

    @Override
    public Reader clone() throws CloneNotSupportedException {
        return (Reader) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader that = (Reader) o;

        if (id != that.id) return false;
        if (!balance.equals(that.balance)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + balance;
        return result;
    }
}
