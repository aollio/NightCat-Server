package com.yemao.entity;

import javax.persistence.*;

@Table(name = "system_ids")
@Entity
public class SystemIds implements EntityModel{

    private String id;
    private int value = 0;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }


    @Basic
    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
