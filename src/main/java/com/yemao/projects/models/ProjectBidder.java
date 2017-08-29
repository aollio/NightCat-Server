package com.yemao.projects.models;


import com.yemao.entity.EntityModel;

import javax.persistence.*;
import java.sql.Timestamp;

import static com.yemao.utility.Util.*;

/**
 * 项目抢单表
 */
@Entity
@Table(name = "ym_project_bidders")
public class ProjectBidder implements EntityModel {
    private String id = uuid();
    private String proj_id;
    private String uid;
    private Timestamp create_time = now();

    private boolean close = false;

    /**
     * 报价
     */
    private double price;
    /**
     * 任务周期
     */
    private String cycle;
    /**
     * 方案说明
     */
    private String description;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "proj_id")
    public String getProj_id() {
        return proj_id;
    }

    public void setProj_id(String proj_id) {
        this.proj_id = proj_id;
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

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
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
    @Column(name = "cycle")
    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Basic
    @Column(name = "is_del")
    public boolean isClose() {
        return close;
    }

    public void setClose(Boolean close) {
        if (close == null) {
            this.close = false;
            return;
        }
        this.close = close;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectBidder that = (ProjectBidder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (proj_id != null ? !proj_id.equals(that.proj_id) : that.proj_id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (proj_id != null ? proj_id.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }
}
