package com.nightcat.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_pay", schema = "nightcat", catalog = "")
public class Pay {
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public BigDecimal getPRICE() {
        return PRICE;
    }

    public void setPRICE(BigDecimal PRICE) {
        this.PRICE = PRICE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPAY_TYPE() {
        return PAY_TYPE;
    }

    public void setPAY_TYPE(String PAY_TYPE) {
        this.PAY_TYPE = PAY_TYPE;
    }

    public Timestamp getDATE() {
        return DATE;
    }

    public void setDATE(Timestamp DATE) {
        this.DATE = DATE;
    }

    public int getISDEL() {
        return ISDEL;
    }

    public void setISDEL(int ISDEL) {
        this.ISDEL = ISDEL;
    }

    /**
    * 支付订单id
    */
    private String ID;
    /**
    * 项目id
    */
    private String ORDER_ID;
    private String USER_ID;
    private BigDecimal PRICE;
    private String STATUS;
    private String DESCRIPTION;
    private String PAY_TYPE;
    private Timestamp DATE;
    private int ISDEL;



}
