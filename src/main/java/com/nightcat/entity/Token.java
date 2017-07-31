package com.nightcat.entity;

import com.nightcat.common.utility.Util;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_users_token", schema = "nightcat", catalog = "")
public class Token {
    private String id;
    private String uid;
    private Timestamp last_active_time;

    public Token() {
    }

    public Token(String id, String uid) {
        this.id = id;
        this.uid = uid;
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
    @Column(name = "last_active_time")
    public Timestamp getLast_active_time() {
        return last_active_time;
    }

    public void setLast_active_time(Timestamp last_active_time) {
        this.last_active_time = last_active_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token that = (Token) o;

        if (!id.equals(that.id)) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (last_active_time != null ?
                !last_active_time.equals(that.last_active_time)
                : that.last_active_time != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (last_active_time != null ? last_active_time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Util.toJson(this);
    }
}
