package com.nightcat.entity.vo;

import com.nightcat.entity.Notice;

import java.sql.Timestamp;

public class NoticeVo {

    private String id;
    private String content;
    private String uid;
    private Timestamp create_time;


    private String type;

    public static NoticeVo from(Notice notice) {
        NoticeVo vo = new NoticeVo();
        vo.setId(notice.getId());
        vo.setUid(notice.getUid());
        vo.setContent(notice.getContent());
        vo.setCreate_time(notice.getCreate_time());
        vo.setType(notice.getType().getTitle());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
