package com.nightcat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_comments", schema = "nightcat", catalog = "")
public class ProjComments {
    private String id;
    private String proj_id;
    private String uid;
    private boolean employer;
    private Integer score;
    private String content;
    private Timestamp comment_time;
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
    @Column(name = "proj_id")
    public String getProj_id() {
        return proj_id;
    }

    public void setProj_id(String proj_id) {
        this.proj_id = proj_id;
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
    @Column(name = "employer")
    public boolean isEmployer() {
        return employer;
    }

    public void setEmployer(boolean employer) {
        this.employer = employer;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

        ProjComments that = (ProjComments) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (proj_id != null ? !proj_id.equals(that.proj_id) : that.proj_id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (employer != that.employer) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (comment_time != null ? !comment_time.equals(that.comment_time) : that.comment_time != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (proj_id != null ? proj_id.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (employer ? 1 : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (comment_time != null ? comment_time.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
