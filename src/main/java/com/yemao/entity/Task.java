package com.yemao.entity;

import com.yemao.common.timing.TaskType;
import com.yemao.utility.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 系统定时器 消息表
 */
@Entity
@Table(name = "system_timing_message")
public class Task implements EntityModel {


    private String id;
    /**
     * 消息类型. 以及每条消息对应的执行器
     */
    private TaskType type;

    /**
     * 执行状态
     */
    private Status status = Status.Wait_execute;


    private Timestamp create_time = Util.now();

    /**
     * 消息执行时间
     */
    private Timestamp execute_time;

    /**
     * 消息携带的数据
     */
    private String data;

    /**
     * 消息执行完成时间(不论失败还是成功)
     */
    private Timestamp finish_time;

    public enum Status {
        Wait_execute,
        Success,
        Fail
    }


    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "timing_type")
    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
    @Column(name = "execute_time")
    public Timestamp getExecute_time() {
        return execute_time;
    }

    public void setExecute_time(Timestamp execute_time) {
        this.execute_time = execute_time;
    }

    @Basic
    @Column(name = "timing_data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Basic
    @Column(name = "finish_time")
    public Timestamp getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Timestamp finish_time) {
        this.finish_time = finish_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task that = (Task) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != that.type) return false;
        if (status != that.status) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (execute_time != null ? !execute_time.equals(that.execute_time) : that.execute_time != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return finish_time != null ? finish_time.equals(that.finish_time) : that.finish_time == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (execute_time != null ? execute_time.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (finish_time != null ? finish_time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", create_time=" + create_time +
                ", execute_time=" + execute_time +
                ", data='" + data + '\'' +
                ", finish_time=" + finish_time +
                '}';
    }
}
