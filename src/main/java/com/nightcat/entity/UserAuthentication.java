package com.nightcat.entity;

import com.nightcat.common.utility.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 用户认证信息审核表
 */
@Entity
@Table(name = "ym_user_authentication", schema = "nightcat", catalog = "")
public class UserAuthentication {
    private String id;
    /**
     * 用户uid
     */
    private String uid;
    /**
     * 认证类型
     */
    private Type type;
    /**
     * 证件级别. 人工审核填写
     */
    private String level;
    /**
     * 证件名称, 用户上传用于审核
     */
    private String name;
    /**
     * 证件号码. 人工审核填写
     */
    private String id_number;
    /**
     * 证件图片, 用户上传
     */
    private String img_url;
    /**
     * 审核状态, 默认审核中
     */
    private Status status = Status.Comfirming;
    /**
     * 创建时间, 默认创建时时间
     */
    private Timestamp create_time = Util.now();

    public enum Type {
        /**
         * 职称认证
         */
        Position,
        /**
         * 学历认证
         */
        School
    }

    public enum Status {
        Comfirming, Success, Fail
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
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "id_number")
    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    @Basic
    @Column(name = "img_url")
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Basic
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthentication that = (UserAuthentication) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (id_number != null ? !id_number.equals(that.id_number) : that.id_number != null) return false;
        if (img_url != null ? !img_url.equals(that.img_url) : that.img_url != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (id_number != null ? id_number.hashCode() : 0);
        result = 31 * result + (img_url != null ? img_url.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }
}
