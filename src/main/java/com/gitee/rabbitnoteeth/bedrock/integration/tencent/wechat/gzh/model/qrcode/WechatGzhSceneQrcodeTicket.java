package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.qrcode;

import com.google.gson.annotations.SerializedName;

public class WechatGzhSceneQrcodeTicket {

    @SerializedName("ticket")
    private String ticket;

    @SerializedName("expire_seconds")
    private Long expireSeconds;

    @SerializedName("url")
    private String url;

    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "WechatGzhSceneQrcodeTicket{" +
            "ticket='" + ticket + '\'' +
            ", expireSeconds=" + expireSeconds +
            ", url='" + url + '\'' +
            ", errCode=" + errCode +
            ", errMsg='" + errMsg + '\'' +
            '}';
    }
}
