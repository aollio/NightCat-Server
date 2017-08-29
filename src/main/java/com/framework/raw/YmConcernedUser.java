package com.framework.raw;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_concerned_user", schema = "yemao", catalog = "")
public class YmConcernedUser {
    private String id;
    private String userId;
    private String concernedId;
    private Timestamp createDate;

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
    @Column(name = "CONCERNED_ID")
    public String getConcernedId() {
        return concernedId;
    }

    public void setConcernedId(String concernedId) {
        this.concernedId = concernedId;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YmConcernedUser that = (YmConcernedUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (concernedId != null ? !concernedId.equals(that.concernedId) : that.concernedId != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (concernedId != null ? concernedId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
