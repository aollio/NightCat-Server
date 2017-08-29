package com.yemao.vo.model;

import com.yemao.utility.Util;

import java.sql.Timestamp;

public class RelationVo {


    private String id;
    /**
     * 关系的发起者, 比如雇主关注设计师, 雇主是发起者
     */
    private UserVo from;
    /**
     *
     */
    private UserVo to;
    /**
     *
     */
    private Timestamp create_time;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserVo getFrom() {
        return from;
    }

    public void setFrom(UserVo from) {
        this.from = from;
    }

    public UserVo getTo() {
        return to;
    }

    public void setTo(UserVo to) {
        this.to = to;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return Util.toJson(this);
    }
}
