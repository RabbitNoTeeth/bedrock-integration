package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.response;


import com.gitee.rabbitnoteeth.bedrock.util.DateUtils;

public class WechatGzhResponse {
    protected final String toUserName;
    protected final String fromUserName;
    protected final long createTime;
    protected final String MsgType;

    protected WechatGzhResponse(String fromUserName, String toUserName, String msgType) {
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.createTime = DateUtils.getSecondTimestamp();
        MsgType = msgType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

}
