package com.nightcat.entity.vo;

import com.nightcat.utility.Util;
import com.nightcat.entity.DesignType;
import com.nightcat.entity.DesignerProfile;
import com.nightcat.entity.User;
import com.nightcat.entity.User.Sex;

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
    private Sex sex = Sex.UNDEFINED;
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
    private User.Role role;

    private String client_id;

    private String imtoken;

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
    private boolean official;
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
    private String position;

    /**
     * 职称是否认证, 认证后用户无法修改学校信息
     */
    private boolean official_position;

    /**
     * 学校
     */
    private String school;

    /**
     * 学校是否认证, 认证后用户无法修改学校信息
     */
    private boolean official_school;

    private UserVo() {
    }

    public static UserVo from(User user, DesignerProfile profile) {
        UserVo userVo = new UserVo();
        Util.less2more(user, userVo);
        Util.less2more(profile, userVo);
        return userVo;
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

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getImtoken() {
        return imtoken;
    }

    public void setImtoken(String imtoken) {
        this.imtoken = imtoken;
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

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isOfficial_position() {
        return official_position;
    }

    public void setOfficial_position(boolean official_position) {
        this.official_position = official_position;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean isOfficial_school() {
        return official_school;
    }

    public void setOfficial_school(boolean official_school) {
        this.official_school = official_school;
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
