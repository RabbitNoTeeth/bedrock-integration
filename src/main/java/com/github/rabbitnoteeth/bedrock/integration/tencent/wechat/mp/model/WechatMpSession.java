package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.mp.model;

import com.google.gson.annotations.SerializedName;

public class WechatMpSession {

    @SerializedName("openid")
    private String openId;

    @SerializedName("session_key")
    private String sessionKey;

    @SerializedName("unionid")
    private String unionId;

    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WechatMpSession{" +
            "openId='" + openId + '\'' +
            ", sessionKey='" + sessionKey + '\'' +
            ", unionId='" + unionId + '\'' +
            ", errCode=" + errCode +
            ", errMsg='" + errMsg + '\'' +
            '}';
    }
}
