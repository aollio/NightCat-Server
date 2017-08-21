package com.nightcat.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_pay")
public class Pay extends EntityModel {

    /**
     * 支付订单id
     */
    private String id;
    /**
     * 项目id
     */
    private String order_id;
    private String uid;
    private BigDecimal price;
    private String status;
    private String content;
    private String type;
    private Timestamp create_time;
    private int is_del;


    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String ID) {
        this.id = ID;
    }

    @Basic
    @Column(name = "order_id")
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String ORDER_ID) {
        this.order_id = ORDER_ID;
    }
    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String USER_ID) {
        this.uid = USER_ID;
    }
    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal PRICE) {
        this.price = PRICE;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String DESCRIPTION) {
        this.content = DESCRIPTION;
    }
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String PAY_TYPE) {
        this.type = PAY_TYPE;
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
    @Column(name = "is_del")
    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int ISDEL) {
        this.is_del = ISDEL;
    }


}
