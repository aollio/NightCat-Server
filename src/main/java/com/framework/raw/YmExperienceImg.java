package com.framework.raw;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_experience_img", schema = "yemao", catalog = "")
public class YmExperienceImg {
    private String id;
    private String expeId;
    private String imgPath;
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
    @Column(name = "EXPE_ID")
    public String getExpeId() {
        return expeId;
    }

    public void setExpeId(String expeId) {
        this.expeId = expeId;
    }

    @Basic
    @Column(name = "IMG_PATH")
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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

        YmExperienceImg that = (YmExperienceImg) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (expeId != null ? !expeId.equals(that.expeId) : that.expeId != null) return false;
        if (imgPath != null ? !imgPath.equals(that.imgPath) : that.imgPath != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (expeId != null ? expeId.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
