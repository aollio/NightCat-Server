package com.yemao.entity;

import com.yemao.utility.Util;

import javax.persistence.*;


/**
 * 以下为一般情况下必有的字段：
 * eventType、convType、to、fromAccount、msgTimestamp、msgType、msgidClient、msgidServer
 */
@Entity
@Table(name = "im_message")
public class ImMessage implements EntityModel {


    private String id;

    private String toUid;

    private String fromUid;

    /**
     * 值为1，表示是会话类型的消息
     */
    private String eventType;
    /**
     * 会话具体类型：
     * PERSON（二人会话数据）、TEAM（群聊数据）、
     * CUSTOM_PERSON（个人自定义系统通知）、CUSTOM_TEAM（群组自定义系统通知），字符串类型
     */
    private String convType;
    /**
     * 若convType为PERSON或CUSTOM_PERSON，则to为消息接收者的用户账号，字符串类型；
     * 若convType为TEAM或CUSTOM_TEAM，则to为tid，即群id，可转为Long型数据
     */
    private String to;
    /**
     * 消息发送者的用户账号，字符串类型
     */
    private String fromAccount;
    /**
     * 发送客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST，字符串类型
     */
    private String fromClientType;
    /**
     * 发送设备id，字符串类型
     */
    private String fromDeviceId;
    /**
     * 发送方昵称，字符串类型
     */
    private String fromNick;
    /**
     * 消息发送时间，字符串类型
     */
    private String msgTimestamp;
    /**
     * 会话具体类型PERSON、TEAM对应的通知消息类型：
     * TEXT、
     * PICTURE、
     * AUDIO、
     * VIDEO、
     * LOCATION 、
     * NOTIFICATION、
     * FILE、 //文件消息
     * NETCALL_AUDIO、 //网络电话音频聊天
     * NETCALL_VEDIO、 //网络电话视频聊天
     * DATATUNNEL_NEW、 //新的数据通道请求通知
     * TIPS、 //提示类型消息
     * CUSTOM //自定义消息
     * <p>
     * 会话具体类型CUSTOM_PERSON对应的通知消息类型：
     * FRIEND_ADD、 //加好友
     * FRIEND_DELETE、 //删除好友
     * CUSTOM_P2P_MSG、 //个人自定义系统通知
     * <p>
     * 会话具体类型CUSTOM_TEAM对应的通知消息类型：
     * TEAM_APPLY、 //申请入群
     * TEAM_APPLY_REJECT、 //拒绝入群申请
     * TEAM_INVITE、 //邀请进群
     * TEAM_INVITE_REJECT、 //拒绝邀请
     * TLIST_UPDATE、 //群信息更新
     * CUSTOM_TEAM_MSG、 //群组自定义系统通知
     */
    private String msgType;
    /**
     * 消息内容，字符串类型
     */
    private String body;
    /**
     * 附加消息，字符串类型
     */
    private String attach;
    /**
     * 客户端生成的消息id，仅在convType为PERSON或TEAM含此字段，字符串类型
     */
    private String msgidClient;
    /**
     * 服务端生成的消息id，可转为Long型数据
     */
    private String msgidServer;
    /**
     * 重发标记：0不是重发, 1是重发。仅在convType为PERSON或TEAM时含此字段，可转为Integer类型数据
     */
    private String resendFlag;
    /**
     * 自定义系统通知消息是否存离线:0：不存，1：存。
     * 仅在convType为CUSTOM_PERSON或CUSTOM_TEAM时含此字段，可转为Integer类型数据
     */
    private String customSafeFlag;
    /**
     * 自定义系统通知消息推送文本。仅在convType为CUSTOM_PERSON或CUSTOM_TEAM时含此字段，字符串类型
     */
    private String customApnsText;
    /**
     * 跟本次群操作有关的用户accid，仅在convType为TEAM或CUSTOM_TEAM时含此字段，字符串类型。
     * tMembers格式举例：
     * {
     * ... // 其他字段
     * "tMembers":"[123, 456]" //相关的accid为 123 和 456
     * }
     */
    private String tMembers;
    /**
     * 消息扩展字段
     */
    private String ext;

    /**
     * 标识是否被反垃圾，仅在被反垃圾时才有此字段，可转为Boolean类型数据
     */
    private String antispam;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getConvType() {
        return convType;
    }

    public void setConvType(String convType) {
        this.convType = convType;
    }

    @Basic
    @Column(name = "to_im")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getFromDeviceId() {
        return fromDeviceId;
    }

    public void setFromDeviceId(String fromDeviceId) {
        this.fromDeviceId = fromDeviceId;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public String getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(String msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMsgidClient() {
        return msgidClient;
    }

    public void setMsgidClient(String msgidClient) {
        this.msgidClient = msgidClient;
    }

    public String getMsgidServer() {
        return msgidServer;
    }

    public void setMsgidServer(String msgidServer) {
        this.msgidServer = msgidServer;
    }

    public String getResendFlag() {
        return resendFlag;
    }

    public void setResendFlag(String resendFlag) {
        this.resendFlag = resendFlag;
    }

    public String getCustomSafeFlag() {
        return customSafeFlag;
    }

    public void setCustomSafeFlag(String customSafeFlag) {
        this.customSafeFlag = customSafeFlag;
    }

    public String getCustomApnsText() {
        return customApnsText;
    }

    public void setCustomApnsText(String customApnsText) {
        this.customApnsText = customApnsText;
    }

    public String gettMembers() {
        return tMembers;
    }

    public void settMembers(String tMembers) {
        this.tMembers = tMembers;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getAntispam() {
        return antispam;
    }

    public void setAntispam(String antispam) {
        this.antispam = antispam;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImMessage imMessage = (ImMessage) o;

        if (id != null ? !id.equals(imMessage.id) : imMessage.id != null) return false;
        if (toUid != null ? !toUid.equals(imMessage.toUid) : imMessage.toUid != null) return false;
        if (fromUid != null ? !fromUid.equals(imMessage.fromUid) : imMessage.fromUid != null) return false;
        if (eventType != null ? !eventType.equals(imMessage.eventType) : imMessage.eventType != null) return false;
        if (convType != null ? !convType.equals(imMessage.convType) : imMessage.convType != null) return false;
        if (to != null ? !to.equals(imMessage.to) : imMessage.to != null) return false;
        if (fromAccount != null ? !fromAccount.equals(imMessage.fromAccount) : imMessage.fromAccount != null)
            return false;
        if (fromClientType != null ? !fromClientType.equals(imMessage.fromClientType) : imMessage.fromClientType != null)
            return false;
        if (fromDeviceId != null ? !fromDeviceId.equals(imMessage.fromDeviceId) : imMessage.fromDeviceId != null)
            return false;
        if (fromNick != null ? !fromNick.equals(imMessage.fromNick) : imMessage.fromNick != null) return false;
        if (msgTimestamp != null ? !msgTimestamp.equals(imMessage.msgTimestamp) : imMessage.msgTimestamp != null)
            return false;
        if (msgType != null ? !msgType.equals(imMessage.msgType) : imMessage.msgType != null) return false;
        if (body != null ? !body.equals(imMessage.body) : imMessage.body != null) return false;
        if (attach != null ? !attach.equals(imMessage.attach) : imMessage.attach != null) return false;
        if (msgidClient != null ? !msgidClient.equals(imMessage.msgidClient) : imMessage.msgidClient != null)
            return false;
        if (msgidServer != null ? !msgidServer.equals(imMessage.msgidServer) : imMessage.msgidServer != null)
            return false;
        if (resendFlag != null ? !resendFlag.equals(imMessage.resendFlag) : imMessage.resendFlag != null) return false;
        if (customSafeFlag != null ? !customSafeFlag.equals(imMessage.customSafeFlag) : imMessage.customSafeFlag != null)
            return false;
        if (customApnsText != null ? !customApnsText.equals(imMessage.customApnsText) : imMessage.customApnsText != null)
            return false;
        if (tMembers != null ? !tMembers.equals(imMessage.tMembers) : imMessage.tMembers != null) return false;
        if (ext != null ? !ext.equals(imMessage.ext) : imMessage.ext != null) return false;
        return antispam != null ? antispam.equals(imMessage.antispam) : imMessage.antispam == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (toUid != null ? toUid.hashCode() : 0);
        result = 31 * result + (fromUid != null ? fromUid.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (convType != null ? convType.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (fromAccount != null ? fromAccount.hashCode() : 0);
        result = 31 * result + (fromClientType != null ? fromClientType.hashCode() : 0);
        result = 31 * result + (fromDeviceId != null ? fromDeviceId.hashCode() : 0);
        result = 31 * result + (fromNick != null ? fromNick.hashCode() : 0);
        result = 31 * result + (msgTimestamp != null ? msgTimestamp.hashCode() : 0);
        result = 31 * result + (msgType != null ? msgType.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (attach != null ? attach.hashCode() : 0);
        result = 31 * result + (msgidClient != null ? msgidClient.hashCode() : 0);
        result = 31 * result + (msgidServer != null ? msgidServer.hashCode() : 0);
        result = 31 * result + (resendFlag != null ? resendFlag.hashCode() : 0);
        result = 31 * result + (customSafeFlag != null ? customSafeFlag.hashCode() : 0);
        result = 31 * result + (customApnsText != null ? customApnsText.hashCode() : 0);
        result = 31 * result + (tMembers != null ? tMembers.hashCode() : 0);
        result = 31 * result + (ext != null ? ext.hashCode() : 0);
        result = 31 * result + (antispam != null ? antispam.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return Util.toJson(this);
    }
}
