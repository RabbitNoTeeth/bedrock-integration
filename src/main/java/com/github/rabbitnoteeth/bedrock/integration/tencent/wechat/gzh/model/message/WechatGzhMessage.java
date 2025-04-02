package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.message;

public class WechatGzhMessage {

    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String msgType;
    private String event;
    private String eventKey;
    private String ticket;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "WechatGzhEventMsg{" +
            "toUserName='" + toUserName + '\'' +
            ", fromUserName='" + fromUserName + '\'' +
            ", createTime='" + createTime + '\'' +
            ", msgType='" + msgType + '\'' +
            ", event='" + event + '\'' +
            ", eventKey='" + eventKey + '\'' +
            ", ticket='" + ticket + '\'' +
            '}';
    }
}
