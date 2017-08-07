package com.framework.raw;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_orders", schema = "nightcat", catalog = "")
public class YmOrders {
    private String id;
    private String userId;
    private BigDecimal price;
    private Timestamp createDate;
    private String event;
    private String status;
    private String orderNo;
    private String objectId;
    private String isdel;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "EVENT")
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ORDER_NO")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "OBJECT_ID")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "ISDEL")
    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YmOrders ymOrders = (YmOrders) o;

        if (id != null ? !id.equals(ymOrders.id) : ymOrders.id != null) return false;
        if (userId != null ? !userId.equals(ymOrders.userId) : ymOrders.userId != null) return false;
        if (price != null ? !price.equals(ymOrders.price) : ymOrders.price != null) return false;
        if (createDate != null ? !createDate.equals(ymOrders.createDate) : ymOrders.createDate != null) return false;
        if (event != null ? !event.equals(ymOrders.event) : ymOrders.event != null) return false;
        if (status != null ? !status.equals(ymOrders.status) : ymOrders.status != null) return false;
        if (orderNo != null ? !orderNo.equals(ymOrders.orderNo) : ymOrders.orderNo != null) return false;
        if (objectId != null ? !objectId.equals(ymOrders.objectId) : ymOrders.objectId != null) return false;
        if (isdel != null ? !isdel.equals(ymOrders.isdel) : ymOrders.isdel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (isdel != null ? isdel.hashCode() : 0);
        return result;
    }
}
