package com.nightcat.vo.model;

import com.nightcat.utility.Util;

import java.sql.Timestamp;

public class ExpCommentVo {



    /**
     * 评论id
     */
    private String id;
    /**
     * 用户经历id
     */
    private String exp_id;
    /**
     * 发出评论的用户id
     */
    private String uid;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Timestamp comment_time = Util.now();
    /**
     * 评论类型
     */
    private String type;

    private UserVo creator;

    private ExpVo exp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExp_id() {
        return exp_id;
    }

    public void setExp_id(String exp_id) {
        this.exp_id = exp_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getComment_time() {
        return comment_time;
    }

    public void setComment_time(Timestamp comment_time) {
        this.comment_time = comment_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserVo getCreator() {
        return creator;
    }

    public void setCreator(UserVo creator) {
        this.creator = creator;
    }

    public ExpVo getExp() {
        return exp;
    }

    public void setExp(ExpVo exp) {
        this.exp = exp;
    }
}
