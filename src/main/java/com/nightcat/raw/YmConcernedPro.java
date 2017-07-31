package com.nightcat.raw;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_concerned_pro", schema = "nightcat", catalog = "")
public class YmConcernedPro {
    private String id;
    private String userId;
    private String proId;
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
    @Column(name = "PRO_ID")
    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
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

        YmConcernedPro that = (YmConcernedPro) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (proId != null ? !proId.equals(that.proId) : that.proId != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (proId != null ? proId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
