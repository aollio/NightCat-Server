package com.nightcat.raw;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_pro_picture", schema = "nightcat", catalog = "")
public class YmProPicture {
    private String id;
    private String proId;
    private String imgPath;
    private String imgW;
    private String imgH;
    private Integer orderBy;
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
    @Column(name = "PRO_ID")
    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
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
    @Column(name = "IMG_W")
    public String getImgW() {
        return imgW;
    }

    public void setImgW(String imgW) {
        this.imgW = imgW;
    }

    @Basic
    @Column(name = "IMG_H")
    public String getImgH() {
        return imgH;
    }

    public void setImgH(String imgH) {
        this.imgH = imgH;
    }

    @Basic
    @Column(name = "ORDER_BY")
    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
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

        YmProPicture that = (YmProPicture) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (proId != null ? !proId.equals(that.proId) : that.proId != null) return false;
        if (imgPath != null ? !imgPath.equals(that.imgPath) : that.imgPath != null) return false;
        if (imgW != null ? !imgW.equals(that.imgW) : that.imgW != null) return false;
        if (imgH != null ? !imgH.equals(that.imgH) : that.imgH != null) return false;
        if (orderBy != null ? !orderBy.equals(that.orderBy) : that.orderBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (proId != null ? proId.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + (imgW != null ? imgW.hashCode() : 0);
        result = 31 * result + (imgH != null ? imgH.hashCode() : 0);
        result = 31 * result + (orderBy != null ? orderBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
