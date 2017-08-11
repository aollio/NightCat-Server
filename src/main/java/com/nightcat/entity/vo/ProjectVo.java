package com.nightcat.entity.vo;

import com.nightcat.common.utility.Util;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.Project;
import com.nightcat.entity.ProjectImage;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ProjectVo {


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
    private Project.Depth depth;
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
    private Project.Status status = Project.Status.Publish;
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
     * 抢单数量
     */
    private int grab_count;
    /**
     * 竞标人 竞标设计师
     */
    private String bidder;
    /**
     * 竞标人 竞标时间
     */
    private Timestamp bid_time;

    private String cancel_reason;


    private List<String> img_urls = new LinkedList<>();

    private ProjectVo() {
    }

    public static ProjectVo from(Project project, List<ProjectImage> img_urls) {
        ProjectVo projectVo = new ProjectVo();
        Util.less2more(project, projectVo);
        if (img_urls != null) {
            img_urls.forEach(e -> {
                if (e.getProj_id().equals(project.getId()))
                    projectVo.img_urls.add(e.getImg_url());
            });
        }
        return projectVo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DesignType getType() {
        return type;
    }

    public void setType(DesignType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getArea_count() {
        return area_count;
    }

    public void setArea_count(int area_count) {
        this.area_count = area_count;
    }

    public Project.Depth getDepth() {
        return depth;
    }

    public void setDepth(Project.Depth depth) {
        this.depth = depth;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public Project.Status getStatus() {
        return status;
    }

    public void setStatus(Project.Status status) {
        this.status = status;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public String getModify_by() {
        return modify_by;
    }

    public void setModify_by(String modify_by) {
        this.modify_by = modify_by;
    }

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    public String getModify_mark() {
        return modify_mark;
    }

    public void setModify_mark(String modify_mark) {
        this.modify_mark = modify_mark;
    }

    public Timestamp getDue_time() {
        return due_time;
    }

    public void setDue_time(Timestamp due_time) {
        this.due_time = due_time;
    }

    public int getFav_count() {
        return fav_count;
    }

    public void setFav_count(int fav_count) {
        this.fav_count = fav_count;
    }

    public int getGrab_count() {
        return grab_count;
    }

    public void setGrab_count(int grab_count) {
        this.grab_count = grab_count;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public Timestamp getBid_time() {
        return bid_time;
    }

    public void setBid_time(Timestamp bid_time) {
        this.bid_time = bid_time;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    public List<String> getImg_urls() {
        return img_urls;
    }

    public void setImg_urls(List<String> img_urls) {
        this.img_urls = img_urls;
    }
}
