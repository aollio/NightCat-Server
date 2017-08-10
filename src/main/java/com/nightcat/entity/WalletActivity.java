package com.nightcat.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_wallet_activity")
public class WalletActivity {


    private String id;
    private String uid;
    private Type type;
    private BigDecimal amount;
    private Timestamp create_time;
    private String payer_id;
    private String order_id;
    private String event;


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
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Basic
    @Column(name = "payer_id")
    public String getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(String payer_id) {
        this.payer_id = payer_id;
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
    @Column(name = "event")
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WalletActivity walletActivity = (WalletActivity) o;

        if (id != null ? !id.equals(walletActivity.id) : walletActivity.id != null) return false;
        if (uid != null ? !uid.equals(walletActivity.uid) : walletActivity.uid != null) return false;
        if (type != null ? !type.equals(walletActivity.type) : walletActivity.type != null) return false;
        if (amount != null ? !amount.equals(walletActivity.amount) : walletActivity.amount != null) return false;
        if (create_time != null ? !create_time.equals(walletActivity.create_time) : walletActivity.create_time != null)
            return false;
        if (payer_id != null ? !payer_id.equals(walletActivity.payer_id) : walletActivity.payer_id != null)
            return false;
        if (order_id != null ? !order_id.equals(walletActivity.order_id) : walletActivity.order_id != null)
            return false;
        if (event != null ? !event.equals(walletActivity.event) : walletActivity.event != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (payer_id != null ? payer_id.hashCode() : 0);
        result = 31 * result + (order_id != null ? order_id.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
}
