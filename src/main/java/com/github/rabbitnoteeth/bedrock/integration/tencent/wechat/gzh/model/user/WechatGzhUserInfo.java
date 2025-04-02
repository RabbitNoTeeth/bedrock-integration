package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WechatGzhUserInfo {

    @SerializedName("subscribe")
    private Integer subscribe;

    @SerializedName("openid")
    private String openid;

    @SerializedName("language")
    private String language;

    @SerializedName("subscribe_time")
    private Long subscribeTime;

    @SerializedName("unionid")
    private String unionid;

    @SerializedName("remark")
    private String remark;

    @SerializedName("groupid")
    private Integer groupid;

    @SerializedName("tagid_list")
    private List<Integer> tagidList;

    @SerializedName("subscribe_scene")
    private String subscribeScene;

    @SerializedName("qr_scene")
    private Integer qrScene;

    @SerializedName("qr_scene_str")
    private String qrSceneStr;

    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;


    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public List<Integer> getTagidList() {
        return tagidList;
    }

    public void setTagidList(List<Integer> tagidList) {
        this.tagidList = tagidList;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public Integer getQrScene() {
        return qrScene;
    }

    public void setQrScene(Integer qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
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
        return "WechatGzhUserInfo{" +
            "subscribe=" + subscribe +
            ", openid='" + openid + '\'' +
            ", language='" + language + '\'' +
            ", subscribeTime=" + subscribeTime +
            ", unionid='" + unionid + '\'' +
            ", remark='" + remark + '\'' +
            ", groupid=" + groupid +
            ", tagidList=" + tagidList +
            ", subscribeScene='" + subscribeScene + '\'' +
            ", qrScene=" + qrScene +
            ", qrSceneStr='" + qrSceneStr + '\'' +
            ", errCode=" + errCode +
            ", errMsg='" + errMsg + '\'' +
            '}';
    }
}
