package com.nightcat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_pay_orders")
public class PayOrder extends EntityModel {

    private String id;
    private String uid;


    private int amount;
    private int price;
    private Timestamp create_time;
    private String event;
    private String status;
    private String type;
    private String object_id;
    private String order_id;
    private String pay_type;
    private int del;

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int AMOUNT_TOPAY) {
        this.amount = AMOUNT_TOPAY;
    }
    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int PRICE) {
        this.price = PRICE;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String ID) {
        this.id = ID;
    }

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp DATE) {
        this.create_time = DATE;
    }

    @Basic
    @Column(name = "event")
    public String getEvent() {
        return event;
    }

    public void setEvent(String EVENT) {
        this.event = EVENT;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String STATUS) {
        this.status = STATUS;
    }
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String TYPE) {
        this.type = TYPE;
    }

    @Basic
    @Column(name = "object_id")
    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String OBJECT_ID) {
        this.object_id = OBJECT_ID;
    }

    @Basic
    @Column(name = "order_id")
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String ORDER_NO) {
        this.order_id = ORDER_NO;
    }

    @Basic
    @Column(name = "pay_type")
    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String PAY_TYPE) {
        this.pay_type = PAY_TYPE;
    }

    @Basic
    @Column(name = "is_del")
    public int getDel() {
        return del;
    }

    public void setDel(int ISDEL) {
        this.del = ISDEL;
    }




}
