package com.yemao.vo.model;

import com.yemao.notice.models.Notice;

import java.sql.Timestamp;

public class NoticeVo {

    private String id;
    private String content;
    private String uid;
    private Timestamp create_time;


    private Notice.Type type;

    private String title;

    public static NoticeVo from(Notice userNotice) {
        NoticeVo vo = new NoticeVo();
        vo.setId(userNotice.getId());
        vo.setUid(userNotice.getUid());
        vo.setContent(userNotice.getContent());
        vo.setCreate_time(userNotice.getCreate_time());
        vo.setType(userNotice.getType());
        vo.setTitle(userNotice.getType().getTitle());
        return vo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Notice.Type getType() {
        return type;
    }

    public void setType(Notice.Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
