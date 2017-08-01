package com.nightcat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_project_feature", schema = "nightcat", catalog = "")
public class FeaturedProject {

    private String id;
    private String proj_id;

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

    public void setProj_id(String proj_id) {
        this.proj_id = proj_id;
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

        FeaturedProject that = (FeaturedProject) o;

        if (!id.equals(that.id)) return false;
        return proj_id.equals(that.proj_id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + proj_id.hashCode();
        return result;
    }
}
