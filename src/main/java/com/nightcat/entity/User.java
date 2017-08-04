package com.nightcat.entity;

import com.nightcat.common.utility.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 夜猫用户表
 */
@Entity
@Table(name = "ym_user", schema = "nightcat", catalog = "")
public class User {
    private String uid;
    private String password;
    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 身份证号码
     */
    private String id_card;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像url
     */
    private String img_url;
    /**
     * 手机号
     */
    private String phone;
    private Timestamp create_time;
    /**
     * 上次登录时间
     */
    private Timestamp last_login_time;
    /**
     * 用户角色. 0: 设计师; 1: 雇员
     */
    private Role role;
    private String wechat;
    private String qq;
    private String weibo;
    private String platform;
    private String client_id;
    private String imtoken;
    private boolean del;

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }


    public enum Role {
        DESIGNER, EMPLOYER
    }

    @Id
    @Column(name = "id")
    public String getUid() {
        return uid;
    }

    public void setUid(String id) {
        this.uid = id;
    }


    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Basic
    @Column(name = "realname")
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realName) {
        this.realname = realName;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

    @Basic
    @Column(name = "img_url")
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img) {
        this.img_url = img;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp register_time) {
        this.create_time = register_time;
    }

    @Basic
    @Column(name = "last_login_time")
    public Timestamp getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Timestamp last_login_time) {
        this.last_login_time = last_login_time;
    }

    @Basic
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role platformRole) {
        this.role = platformRole;
    }

    @Basic
    @Column(name = "wechat")
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String webchat) {
        this.wechat = webchat;
    }

    @Basic
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "weibo")
    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String webo) {
        this.weibo = webo;
    }

    @Basic
    @Column(name = "platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Basic
    @Column(name = "client_id")
    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }


    @Basic
    @Column(name = "im_token")
    public String getImtoken() {
        return imtoken;
    }

    public void setImtoken(String imtoken) {
        this.imtoken = imtoken;
    }

    @Basic
    @Column(name = "del")
    public boolean isDel() {
        return del;
    }

    public void setDel(boolean isdel) {
        this.del = isdel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (uid != null ? !uid.equals(user.uid) : user.uid != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (realname != null ? !realname.equals(user.realname) : user.realname != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (img_url != null ? !img_url.equals(user.img_url) : user.img_url != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (create_time != null ? !create_time.equals(user.create_time) : user.create_time != null)
            return false;
        if (last_login_time != null ? !last_login_time.equals(user.last_login_time) : user.last_login_time != null)
            return false;
        if (role != null ? !role.equals(user.role) : user.role != null)
            return false;
        if (wechat != null ? !wechat.equals(user.wechat) : user.wechat != null) return false;
        if (qq != null ? !qq.equals(user.qq) : user.qq != null) return false;
        if (weibo != null ? !weibo.equals(user.weibo) : user.weibo != null) return false;
        if (platform != null ? !platform.equals(user.platform) : user.platform != null) return false;
        if (client_id != null ? !client_id.equals(user.client_id) : user.client_id != null) return false;
        if (imtoken != null ? !imtoken.equals(user.imtoken) : user.imtoken != null) return false;
        if (isDel() != user.isDel()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (img_url != null ? img_url.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (last_login_time != null ? last_login_time.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (wechat != null ? wechat.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (weibo != null ? weibo.hashCode() : 0);
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (client_id != null ? client_id.hashCode() : 0);
        result = 31 * result + (imtoken != null ? imtoken.hashCode() : 0);
        result = 31 * result + (del ? 1 : 0);
        return result;
    }


    @Override
    public String toString() {
        return Util.toJson(this);
    }
}
