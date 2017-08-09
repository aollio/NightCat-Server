package com.nightcat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

import static com.nightcat.common.utility.Util.now;

/**
 * 项目动态表
 */
@Entity
@Table(name = "ym_project_dynamic")
public class ProjectDynamic {
    private String id;
    private String proj_id;
    private String uid;
    /**
     * 是否是项目发布者发出的动态
     */
    private boolean publisher;
    private Type type;
    private String content;
    private String img_url;

    private Timestamp create_time = now();

    public ProjectDynamic() {
    }

    public ProjectDynamic(String id, String proj_id, String uid, boolean publisher, Type type, String content, String img_url, Timestamp create_time) {
        this.id = id;
        this.proj_id = proj_id;
        this.uid = uid;
        this.publisher = publisher;
        this.type = type;
        this.content = content;
        this.img_url = img_url;
        this.create_time = create_time;
    }


    public enum Type {
        Publish,
        /**
         * 被设计师抢单
         */
        Grabbed,
        /**
         * 设计师确认
         */
        Designer_Confirm
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
    @Column(name = "proj_id")
    public String getProj_id() {
        return proj_id;
    }

    public void setProj_id(String proj_id) {
        this.proj_id = proj_id;
    }

    @Basic
    @Column(name = "user_id")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "publisher")
    public boolean isPublisher() {
        return publisher;
    }

    public void setPublisher(boolean publisher) {
        this.publisher = publisher;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        ProjectDynamic projectDynamic = (ProjectDynamic) o;

        if (id != null ? !id.equals(projectDynamic.id) : projectDynamic.id != null) return false;
        if (proj_id != null ? !proj_id.equals(projectDynamic.proj_id) : projectDynamic.proj_id != null) return false;
        if (uid != null ? !uid.equals(projectDynamic.uid) : projectDynamic.uid != null) return false;
        if (publisher != projectDynamic.publisher)
            return false;
        if (type != null ? !type.equals(projectDynamic.type) : projectDynamic.type != null) return false;
        if (content != null ? !content.equals(projectDynamic.content) : projectDynamic.content != null) return false;
        if (img_url != null ? !img_url.equals(projectDynamic.img_url) : projectDynamic.img_url != null) return false;
        if (create_time != null ? !create_time.equals(projectDynamic.create_time) : projectDynamic.create_time != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (proj_id != null ? proj_id.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (publisher ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (img_url != null ? img_url.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }
}
