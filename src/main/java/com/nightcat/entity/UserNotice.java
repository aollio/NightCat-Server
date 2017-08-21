package com.nightcat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_user_notice")
public class UserNotice extends EntityModel {


    public enum Type {
        SYSTEM("系统消息"),
        NOTICE("推送消息"),
        PROJECT_GRABBED("订单消息"),
        PROJECT_CHOOSE("订单消息");

        String title;

        Type(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    private String id;
    private String content;
    private String uid;
    private Timestamp create_time;


    private boolean read;
    private Type type;
    private boolean del;

    public UserNotice() {
    }

    public UserNotice(String id, String content, String uid, Timestamp create_time, boolean read, Type type, boolean del) {
        this.id = id;
        this.content = content;
        this.uid = uid;
        this.create_time = create_time;
        this.read = read;
        this.type = type;
        this.del = del;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "is_read", length = 2)
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }


    @Basic
    @Column(name = "notice_type", length = 2)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Basic
    @Column(name = "is_del", length = 2)
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

        UserNotice that = (UserNotice) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (read != that.read) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (del != that.del) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (read ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (del ? 1 : 0);
        return result;
    }
}
