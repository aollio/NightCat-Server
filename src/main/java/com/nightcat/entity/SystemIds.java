package com.nightcat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "system_ids")
@Entity
public class SystemIds {

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
