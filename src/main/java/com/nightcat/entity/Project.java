package com.nightcat.entity;

import com.nightcat.common.utility.Util;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ym_projects")
public class Project {


    /**
     * 订单的状态流程图
     * http://image.aollio.com/nightcat-project-status-process.png
     */
    public enum Status {
        /**
         * 发布项目后, 项目处于发布状态. 这时候设计师可以进行抢单.
         * 如果没有设计师抢单, 并且项目到达截止时间后会进入'Cancel'状态
         */
        Publish,
        /**
         * 雇主选择一位设计师后, 等待设计师确认
         */
        ConfirmDesigner_WaitDesignerConfitm,
        /**
         * 设计时确认后(双方确认), 等待雇主支付. 这时会生成一个对应的支付订单
         */
        BothConfirm_WaitEmployerPay,
        /**
         * 支付完成后, 等待设计师设计
         */
        PayComplete_WaitDesign,
        /**
         * 设计完成后, 由雇主进行确认设计完成. 等待评价项目. 这时将款项转给设计师账户
         */
        DesignComplete_WaitComment,
        /**
         * 若在设计过程中, 雇主和设计师之间发生了无法私下协调的问题. 由平台介入协调. 称为会审
         */
        Platform_InterPose,
        /**
         * 订单正常完成. 评价后状态为完成
         */
        Complete,
        /**
         * 订单非正常完成. e.g. 项目超时未抢单; 会审失败; 雇主没有支付; 等等
         */
        Cancel;


    }

    public enum Depth {
        Schema("方案"),
        Expansion("扩出"),
        Construction("施工");

        String text;

        Depth(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    private String id;
    private String title;
    /**
     * 项目类型
     */
    private DesignType type;
    /**
     * 项目内容
     */
    private String content;
    /**
     * 项目预算
     */
    private BigDecimal budget;
    /**
     * 设计面积
     */
    private int area;

    /**
     * 设计面积个数
     */
    private int area_count;

    /**
     * 项目深度
     */
    private Depth depth;
    /**
     * 项目需要的工时
     */
    private int period;
    /**
     * 项目开始时间
     */
    private Timestamp start_time;
    /**
     * 项目截止时间
     */
    private Timestamp end_time;

    /**
     * 项目状态
     */
    private Status status = Status.Publish;
    /**
     * 项目创建人uid
     */
    private String create_by;
    /**
     * 项目的创建时间
     */
    private Timestamp create_time;

    /**
     * 项目是否优秀
     */
    private boolean good;

    /**
     * 修改人uid
     */
    private String modify_by;
    /**
     * 修改人修改项目时间
     */
    private Timestamp modify_time;
    /**
     * 项目备注
     */
    private String modify_mark;
    /**
     * 抢单截止时间
     */
    private Timestamp due_time;
    /**
     * 项目喜欢数
     */
    private int fav_count;
    /**
     * 竞标人 竞标设计师
     */
    private String bidder;
    /**
     * 竞标人 竞标时间
     */
    private Timestamp bid_time;

    /**
     * 项目是否被删除
     */
    private boolean del;

    private String cancel_reason;


    @Basic
    @Column(name = "cancel_reason")
    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
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
    @Column(name = "title", length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type", length = 2)
    public DesignType getType() {
        return type;
    }

    public void setType(DesignType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "content", length = 200)
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
    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Basic
    @Column(name = "depth")
    public Depth getDepth() {
        return depth;
    }

    public void setDepth(Depth depth) {
        this.depth = depth;
    }


    @Basic
    @Column(name = "area_count")
    public int getArea_count() {
        return area_count;
    }

    public void setArea_count(int area_count) {
        this.area_count = area_count;
    }

    @Basic
    @Column(name = "period")
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
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
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
    @Column(name = "is_good", length = 2)
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
    @Column(name = "modify_marl", length = 300)
    public String getModify_mark() {
        return modify_mark;
    }

    public void setModify_mark(String description) {
        this.modify_mark = description;
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
    @Column(name = "fav_count")
    public int getFav_count() {
        return fav_count;
    }

    public void setFav_count(int fav_count) {
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
    @Column(name = "del", length = 2)
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
        if (area != that.area) return false;
        if (depth != null ? !depth.equals(that.depth) : that.depth != null) return false;
        if (period != that.period) return false;
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
        if (modify_mark != null ? !modify_mark.equals(that.modify_mark) : that.modify_mark != null) return false;
        if (due_time != null ? !due_time.equals(that.due_time) : that.due_time != null) return false;
        if (fav_count != that.fav_count) return false;
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
        result = 31 * result + area;
        result = 31 * result + (depth != null ? depth.hashCode() : 0);
        result = 31 * result + period;
        result = 31 * result + (start_time != null ? start_time.hashCode() : 0);
        result = 31 * result + (end_time != null ? end_time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (create_by != null ? create_by.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (good ? 1 : 0);
        result = 31 * result + (modify_by != null ? modify_by.hashCode() : 0);
        result = 31 * result + (modify_time != null ? modify_time.hashCode() : 0);
        result = 31 * result + (modify_mark != null ? modify_mark.hashCode() : 0);
        result = 31 * result + (due_time != null ? due_time.hashCode() : 0);
        result = 31 * result + fav_count;
        result = 31 * result + (bidder != null ? bidder.hashCode() : 0);
        result = 31 * result + (bid_time != null ? bid_time.hashCode() : 0);
        result = 31 * result + (del ? 1 : 0);
        return result;
    }
}
