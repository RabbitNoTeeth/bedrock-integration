package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.GsonUtil;
import com.wechat.pay.java.core.util.StringUtil;

import java.util.List;

public class PrepayRequest {

    @SerializedName("appid")
    private String appid;
    @SerializedName("mchid")
    private String mchid;
    @SerializedName("description")
    private String description;
    @SerializedName("out_trade_no")
    private String outTradeNo;
    @SerializedName("time_expire")
    private String timeExpire;
    @SerializedName("attach")
    private String attach;
    @SerializedName("notify_url")
    private String notifyUrl;
    @SerializedName("goods_tag")
    private String goodsTag;
    @SerializedName("limit_pay")
    private List<LimitPayEnum> limitPay;
    @SerializedName("support_fapiao")
    private Boolean supportFapiao;
    @SerializedName("amount")
    private Amount amount;
    @SerializedName("payer")
    private Payer payer;
    @SerializedName("detail")
    private Detail detail;
    @SerializedName("scene_info")
    private SceneInfo sceneInfo;
    @SerializedName("settle_info")
    private SettleInfo settleInfo;

    public PrepayRequest() {
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return this.mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTradeNo() {
        return this.outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeExpire() {
        return this.timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAttach() {
        return this.attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsTag() {
        return this.goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public List<LimitPayEnum> getLimitPay() {
        return this.limitPay;
    }

    public void setLimitPay(List<LimitPayEnum> limitPay) {
        this.limitPay = limitPay;
    }

    public Boolean getSupportFapiao() {
        return this.supportFapiao;
    }

    public void setSupportFapiao(Boolean supportFapiao) {
        this.supportFapiao = supportFapiao;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Payer getPayer() {
        return this.payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Detail getDetail() {
        return this.detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public SceneInfo getSceneInfo() {
        return this.sceneInfo;
    }

    public void setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public SettleInfo getSettleInfo() {
        return this.settleInfo;
    }

    public void setSettleInfo(SettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PrepayRequest {\n");
        sb.append("    appid: ").append(StringUtil.toIndentedString(this.appid)).append("\n");
        sb.append("    mchid: ").append(StringUtil.toIndentedString(this.mchid)).append("\n");
        sb.append("    description: ").append(StringUtil.toIndentedString(this.description)).append("\n");
        sb.append("    outTradeNo: ").append(StringUtil.toIndentedString(this.outTradeNo)).append("\n");
        sb.append("    timeExpire: ").append(StringUtil.toIndentedString(this.timeExpire)).append("\n");
        sb.append("    attach: ").append(StringUtil.toIndentedString(this.attach)).append("\n");
        sb.append("    notifyUrl: ").append(StringUtil.toIndentedString(this.notifyUrl)).append("\n");
        sb.append("    goodsTag: ").append(StringUtil.toIndentedString(this.goodsTag)).append("\n");
        sb.append("    limitPay: ").append(StringUtil.toIndentedString(this.limitPay)).append("\n");
        sb.append("    supportFapiao: ").append(StringUtil.toIndentedString(this.supportFapiao)).append("\n");
        sb.append("    amount: ").append(StringUtil.toIndentedString(this.amount)).append("\n");
        sb.append("    payer: ").append(StringUtil.toIndentedString(this.payer)).append("\n");
        sb.append("    detail: ").append(StringUtil.toIndentedString(this.detail)).append("\n");
        sb.append("    sceneInfo: ").append(StringUtil.toIndentedString(this.sceneInfo)).append("\n");
        sb.append("    settleInfo: ").append(StringUtil.toIndentedString(this.settleInfo)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public static enum LimitPayEnum {
        @SerializedName("no_balance")
        NO_BALANCE,
        @SerializedName("no_credit")
        NO_CREDIT,
        @SerializedName("no_debit")
        NO_DEBIT,
        @SerializedName("balance_only")
        BALANCE_ONLY;

        private LimitPayEnum() {
        }
    }

    public com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest toJsapiRequest() {
        Gson gson = GsonUtil.getGson();
        return gson.fromJson(gson.toJson(this), com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest.class);
    }

    public com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest toNativeRequest() {
        Gson gson = GsonUtil.getGson();
        return gson.fromJson(gson.toJson(this), com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest.class);
    }

}
