package com.yemao.projects.models;

import com.yemao.entity.EntityModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_project_images")
public class ProjectImage implements EntityModel {

    private String id;
    private String proj_id;
    private String img_url;
    private Timestamp create_time;

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

    public void setProj_id(String proId) {
        this.proj_id = proId;
    }

    @Basic
    @Column(name = "img_url")
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String imgPath) {
        this.img_url = imgPath;
    }


    @Basic
    @Column(name = "create_time")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp createDate) {
        this.create_time = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectImage that = (ProjectImage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (proj_id != null ? !proj_id.equals(that.proj_id) : that.proj_id != null) return false;
        if (img_url != null ? !img_url.equals(that.img_url) : that.img_url != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (proj_id != null ? proj_id.hashCode() : 0);
        result = 31 * result + (img_url != null ? img_url.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }
}
