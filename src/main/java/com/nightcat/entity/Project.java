package com.nightcat.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_project", schema = "nightcat", catalog = "")
public class Project {
    private String id;
    private String title;
    private String type;
    private String content;
    private BigDecimal budget;
    private Integer area;
    private String depth;
    private Integer period;
    private Timestamp start_time;
    private Timestamp end_time;
    private String status;
    private String create_by;
    private Timestamp create_time;

    private boolean good;

    private String modify_by;
    private Timestamp modify_time;
    private String description;
    private Timestamp due_time;
    private Integer view_count;
    private Integer fav_count;
    private String bidder;
    private Timestamp bid_time;

    private boolean del;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
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
    @Column(name = "budget")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Basic
    @Column(name = "area")
    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Basic
    @Column(name = "depth")
    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Basic
    @Column(name = "period")
    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    @Basic
    @Column(name = "status", length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
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
    @Column(name = "good",length = 2)
    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    @Basic
    @Column(name = "modify_by")
    public String getModify_by() {
        return modify_by;
    }

    public void setModify_by(String modify_by) {
        this.modify_by = modify_by;
    }

    @Basic
    @Column(name = "modify_time")
    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    @Basic
    @Column(name = "description",length = 300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "due_time")
    public Timestamp getDue_time() {
        return due_time;
    }

    public void setDue_time(Timestamp due_time) {
        this.due_time = due_time;
    }

    @Basic
    @Column(name = "view_count")
    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    @Basic
    @Column(name = "fav_count")
    public Integer getFav_count() {
        return fav_count;
    }

    public void setFav_count(Integer fav_count) {
        this.fav_count = fav_count;
    }

    @Basic
    @Column(name = "bidder")
    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    @Basic
    @Column(name = "bid_time")
    public Timestamp getBid_time() {
        return bid_time;
    }

    public void setBid_time(Timestamp bid_time) {
        this.bid_time = bid_time;
    }

    @Basic
    @Column(name = "del",length = 2)
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

        Project that = (Project) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (budget != null ? !budget.equals(that.budget) : that.budget != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (depth != null ? !depth.equals(that.depth) : that.depth != null) return false;
        if (period != null ? !period.equals(that.period) : that.period != null) return false;
        if (start_time != null ? !start_time.equals(that.start_time) : that.start_time != null) return false;
        if (end_time != null ? !end_time.equals(that.end_time) : that.end_time != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (create_by != null ? !create_by.equals(that.create_by) : that.create_by != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (good != that.good) {
            return false;
        }
        if (modify_by != null ? !modify_by.equals(that.modify_by) : that.modify_by != null) return false;
        if (modify_time != null ? !modify_time.equals(that.modify_time) : that.modify_time != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (due_time != null ? !due_time.equals(that.due_time) : that.due_time != null) return false;
        if (view_count != null ? !view_count.equals(that.view_count) : that.view_count != null) return false;
        if (fav_count != null ? !fav_count.equals(that.fav_count) : that.fav_count != null) return false;
        if (bidder != null ? !bidder.equals(that.bidder) : that.bidder != null) return false;
        if (bid_time != null ? !bid_time.equals(that.bid_time) : that.bid_time != null) return false;
        if (del != that.del) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (budget != null ? budget.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (depth != null ? depth.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (start_time != null ? start_time.hashCode() : 0);
        result = 31 * result + (end_time != null ? end_time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (create_by != null ? create_by.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (good ? 1 : 0);
        result = 31 * result + (modify_by != null ? modify_by.hashCode() : 0);
        result = 31 * result + (modify_time != null ? modify_time.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (due_time != null ? due_time.hashCode() : 0);
        result = 31 * result + (view_count != null ? view_count.hashCode() : 0);
        result = 31 * result + (fav_count != null ? fav_count.hashCode() : 0);
        result = 31 * result + (bidder != null ? bidder.hashCode() : 0);
        result = 31 * result + (bid_time != null ? bid_time.hashCode() : 0);
        result = 31 * result + (del ? 1 : 0);
        return result;
    }
}