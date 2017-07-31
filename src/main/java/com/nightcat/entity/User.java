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
    private String user_name;
    private String password;
    private String realname;
    private String nickname;
    private String img_url;
    private String phone;
    private Timestamp register_time;
    private Timestamp last_login_time;
    private Role role;
    private String wechat;
    private String qq;
    private String weibo;
    private String platform;
    private String clientId;
    private String status;
    private String imtoken;
    private boolean del;


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
    @Column(name = "username")
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String userName) {
        this.user_name = userName;
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
    @Column(name = "register_time")
    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
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
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (user_name != null ? !user_name.equals(user.user_name) : user.user_name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (realname != null ? !realname.equals(user.realname) : user.realname != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (img_url != null ? !img_url.equals(user.img_url) : user.img_url != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (register_time != null ? !register_time.equals(user.register_time) : user.register_time != null)
            return false;
        if (last_login_time != null ? !last_login_time.equals(user.last_login_time) : user.last_login_time != null)
            return false;
        if (role != null ? !role.equals(user.role) : user.role != null)
            return false;
        if (wechat != null ? !wechat.equals(user.wechat) : user.wechat != null) return false;
        if (qq != null ? !qq.equals(user.qq) : user.qq != null) return false;
        if (weibo != null ? !weibo.equals(user.weibo) : user.weibo != null) return false;
        if (platform != null ? !platform.equals(user.platform) : user.platform != null) return false;
        if (clientId != null ? !clientId.equals(user.clientId) : user.clientId != null) return false;
        if (status != null ? !status.equals(user.status) : user.status != null) return false;
        if (imtoken != null ? !imtoken.equals(user.imtoken) : user.imtoken != null) return false;
        if (isDel() != user.isDel()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (user_name != null ? user_name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (img_url != null ? img_url.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (register_time != null ? register_time.hashCode() : 0);
        result = 31 * result + (last_login_time != null ? last_login_time.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (wechat != null ? wechat.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (weibo != null ? weibo.hashCode() : 0);
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (imtoken != null ? imtoken.hashCode() : 0);
        result = 31 * result + (del ? 1 : 0);
        return result;
    }


    @Override
    public String toString() {
        return Util.toJson(this);
    }
}
