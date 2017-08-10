package com.nightcat.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_pay_orders", schema = "nightcat", catalog = "")
public class PayOrder {

    private String id;
    private String uid;

    private BigDecimal AMOUNT_TOPAY;
    private BigDecimal PRICE;
    private Timestamp DATE;
    private String EVENT;
    private String STATUS;
    private String TYPE;
    private String OBJECT_ID;
    private String ORDER_NO;
    private String PAY_TYPE;
    private int ISDEL;

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

    public BigDecimal getAMOUNT_TOPAY() {
        return AMOUNT_TOPAY;
    }

    public void setAMOUNT_TOPAY(BigDecimal AMOUNT_TOPAY) {
        this.AMOUNT_TOPAY = AMOUNT_TOPAY;
    }

    public BigDecimal getPRICE() {
        return PRICE;
    }

    public void setPRICE(BigDecimal PRICE) {
        this.PRICE = PRICE;
    }

    public Timestamp getDATE() {
        return DATE;
    }

    public void setDATE(Timestamp DATE) {
        this.DATE = DATE;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(String OBJECT_ID) {
        this.OBJECT_ID = OBJECT_ID;
    }

    public String getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(String ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public String getPAY_TYPE() {
        return PAY_TYPE;
    }

    public void setPAY_TYPE(String PAY_TYPE) {
        this.PAY_TYPE = PAY_TYPE;
    }

    public int getISDEL() {
        return ISDEL;
    }

    public void setISDEL(int ISDEL) {
        this.ISDEL = ISDEL;
    }


}
