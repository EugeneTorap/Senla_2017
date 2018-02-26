package com.senla.model;

import com.senla.api.model.IReader;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "reader", schema = "book_shop")
public class Reader extends AbstractEntity implements IReader {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Basic
    @Column(name = "balance")
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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
