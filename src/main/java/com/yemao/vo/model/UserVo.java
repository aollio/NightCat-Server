package com.yemao.vo.model;

import com.yemao.users.models.*;
import com.yemao.users.models.User.*;
import com.yemao.users.models.Profile.*;
import com.yemao.utility.Util;
import com.yemao.entity.DesignType;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserVo {

    /**
     *
     */
    private String uid;

    private String accid;


    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private User.Sex sex = User.Sex.UNDEFINED;
    /**
     * 头像url
     */
    private String img_url;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 上次登录时间
     */
    private Timestamp last_login_time;
    /**
     * 用户角色. 0: 设计师; 1: 雇员
     */
    private Role role;

    private String client_id;

    private String im_token;

    /**
     * 用户简介
     */
    private String summary;
    /**
     * 设计师类别
     */
    private DesignType type;


    /**
     * 工龄, 单位年
     * e.g. 3.5年工龄
     */
    private double service_length;
    /**
     * 接单数
     */
    private int bid_count;
    /**
     * 时薪
     */
    private BigDecimal hourly_wage;
    /**
     * 是否官方认证
     */
    private Official official;
    /**
     * 综合指标
     */
    private String overall_target;
    /**
     * 总工时
     */
    private int total_works;
    /**
     * 接单成功数
     */
    private int bid_success_count;
    /**
     * 星级
     */
    private int star_level;
    /**
     * 职称
     */
    private Position position;

    /**
     * 学校
     */
    private String school;

    public UserVo() {
    }

    public Official getOfficial() {
        return official;
    }

    public Position getPosition() {
        return position;
    }

    public void setOfficial(Official official) {
        this.official = official;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Timestamp last_login_time) {
        this.last_login_time = last_login_time;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }


    public String getIm_token() {
        return im_token;
    }

    public void setIm_token(String im_token) {
        this.im_token = im_token;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public DesignType getType() {
        return type;
    }

    public void setType(DesignType type) {
        this.type = type;
    }

    public double getService_length() {
        return service_length;
    }

    public void setService_length(double service_length) {
        this.service_length = service_length;
    }

    public int getBid_count() {
        return bid_count;
    }

    public void setBid_count(int bid_count) {
        this.bid_count = bid_count;
    }

    public BigDecimal getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(BigDecimal hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public String getOverall_target() {
        return overall_target;
    }

    public void setOverall_target(String overall_target) {
        this.overall_target = overall_target;
    }

    public int getTotal_works() {
        return total_works;
    }

    public void setTotal_works(int total_works) {
        this.total_works = total_works;
    }

    public int getBid_success_count() {
        return bid_success_count;
    }

    public void setBid_success_count(int bid_success_count) {
        this.bid_success_count = bid_success_count;
    }

    public int getStar_level() {
        return star_level;
    }

    public void setStar_level(int star_level) {
        this.star_level = star_level;
    }


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }


    @Override
    public String toString() {
        return Util.toJson(this);
    }
}
