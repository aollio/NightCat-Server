package com.nightcat.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_pay", schema = "nightcat", catalog = "")
public class Pay {
    private String id;
    private String order_id;
    private String uid;
    private BigDecimal price;
    private String status;
    private String description;
    private Timestamp pay_time;
    private Type type;
    private boolean del;

    public enum Type {
        INCOME, EXPENDITURE
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_id")
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "pay_time")
    public Timestamp getPay_time() {
        return pay_time;
    }

    public void setPay_time(Timestamp pay_time) {
        this.pay_time = pay_time;
    }

    @Basic
    @Column(name = "type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Basic
    @Column(name = "del")
    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pay pay = (Pay) o;

        if (id != null ? !id.equals(pay.id) : pay.id != null) return false;
        if (order_id != null ? !order_id.equals(pay.order_id) : pay.order_id != null) return false;
        if (uid != null ? !uid.equals(pay.uid) : pay.uid != null) return false;
        if (price != null ? !price.equals(pay.price) : pay.price != null) return false;
        if (status != null ? !status.equals(pay.status) : pay.status != null) return false;
        if (description != null ? !description.equals(pay.description) : pay.description != null) return false;
        if (pay_time != null ? !pay_time.equals(pay.pay_time) : pay.pay_time != null) return false;
        if (type != null ? !type.equals(pay.type) : pay.type != null) return false;
        if (del != pay.del) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (order_id != null ? order_id.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pay_time != null ? pay_time.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (del ? 1 : 0);
        return result;
    }
}
