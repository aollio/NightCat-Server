package com.yemao.users.models;

import com.yemao.entity.DesignType;
import com.yemao.entity.EntityModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 设计师用户详情
 */
@Entity
@Table(name = "ym_designer_profile")
public class Profile implements EntityModel {

    public enum Official {
        /**
         * 未认证
         */
        NOT,
        /**
         * 职称是否认证, 认证后用户无法修改学校信息
         */
        POSITION_ONLY,
        /**
         * 学历是否认证, 认证后用户无法修改学校信息
         */
        SCHOOL_ONLY,
        /**
         * 全部都认证
         */
        ALL
    }

    public enum Position {
        ALL, LOW, MIDDLE, HIGH
    }

    /**
     * 用户标识
     */
    private String uid;

    /**
     * 用户简介
     */
    private String summary;
    /**
     * 设计师类别
     */
    private DesignType type = DesignType.UNDEFINDED;


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
    private Integer total_works;
    /**
     * 接单成功数
     */
    private Integer bid_success_count;
    /**
     * 账户余额
     */
    private BigDecimal account_balance;
    /**
     * 用户创建时间
     */
    private Timestamp create_time;
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



    @Id
    @Column(name = "uid", nullable = false)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public DesignType getType() {
        return type;
    }

    public void setType(DesignType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "service_length")
    public double getService_length() {
        return service_length;
    }

    public void setService_length(double service_length) {
        this.service_length = service_length;
    }

    @Basic
    @Column(name = "bid_count")
    public int getBid_count() {
        return bid_count;
    }

    public void setBid_count(int bid_count) {
        this.bid_count = bid_count;
    }

    @Basic
    @Column(name = "hourly_wage")
    public BigDecimal getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(BigDecimal hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    @Basic
    @Column(name = "official")
    public Official getOfficial() {
        return official;
    }

    public void setOfficial(Official official) {
        this.official = official;
    }

    @Basic
    @Column(name = "overall_target")
    public String getOverall_target() {
        return overall_target;
    }

    public void setOverall_target(String overall_target) {
        this.overall_target = overall_target;
    }

    @Basic
    @Column(name = "total_works")
    public Integer getTotal_works() {
        return total_works;
    }

    public void setTotal_works(Integer total_works) {
        this.total_works = total_works;
    }

    @Basic
    @Column(name = "bid_success_count")
    public Integer getBid_success_count() {
        return bid_success_count;
    }

    public void setBid_success_count(Integer bid_success_count) {
        this.bid_success_count = bid_success_count;
    }

    @Basic
    @Column(name = "account_balance")
    public BigDecimal getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(BigDecimal account_balance) {
        this.account_balance = account_balance;
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
    @Column(name = "star_level")
    public int getStar_level() {
        return star_level;
    }

    public void setStar_level(int star_level) {
        this.star_level = star_level;
    }

    @Basic
    @Column(name = "position")
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile that = (Profile) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (service_length != that.service_length)
            return false;
        if (bid_count != that.bid_count) return false;
        if (hourly_wage != null ? !hourly_wage.equals(that.hourly_wage) : that.hourly_wage != null) return false;
        if (official != that.official) return false;
        if (overall_target != null ? !overall_target.equals(that.overall_target) : that.overall_target != null)
            return false;
        if (total_works != that.total_works) return false;
        if (bid_success_count != that.bid_success_count)
            return false;
        if (account_balance != null ? !account_balance.equals(that.account_balance) : that.account_balance != null)
            return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (star_level != that.star_level) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + Double.hashCode(service_length);
        result = 31 * result + bid_count;
        result = 31 * result + (hourly_wage != null ? hourly_wage.hashCode() : 0);
        result = 31 * result + (overall_target != null ? overall_target.hashCode() : 0);
        result = 31 * result + total_works;
        result = 31 * result + bid_success_count;
        result = 31 * result + (account_balance != null ? account_balance.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + star_level;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);


        return result;
    }

}
