package com.senla.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "audit")
public class Audit implements IEntity, Serializable{
    private Integer id;
    private Long time;
    private Reader reader;

    public Audit(){

    }

    public Audit(Reader reader) {
        time = new Date().getTime();
        this.reader = reader;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time")
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id")
    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
