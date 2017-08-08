package com.nightcat.entity;

import com.nightcat.common.utility.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 用户经历评论表
 */
@Entity
@Table(name = "ym_user_experience_comments")
public class ExpComment {

    /**
     * 评论id
     */
    private String id;
    /**
     * 用户经历id
     */
    private String exp_id;
    /**
     * 发出评论的用户id
     */
    private String uid;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Timestamp comment_time = Util.now();
    /**
     * 评论类型
     */
    private String type;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "exp_id")
    public String getExp_id() {
        return exp_id;
    }

    public void setExp_id(String exp_id) {
        this.exp_id = exp_id;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "comment_time")
    public Timestamp getComment_time() {
        return comment_time;
    }

    public void setComment_time(Timestamp comment_time) {
        this.comment_time = comment_time;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpComment that = (ExpComment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (comment_time != null ? !comment_time.equals(that.comment_time) : that.comment_time != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (comment_time != null ? comment_time.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

}
