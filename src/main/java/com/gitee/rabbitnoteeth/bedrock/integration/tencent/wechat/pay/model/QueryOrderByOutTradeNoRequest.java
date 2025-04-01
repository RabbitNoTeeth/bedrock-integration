package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.GsonUtil;
import com.wechat.pay.java.core.util.StringUtil;

public class QueryOrderByOutTradeNoRequest {
    @SerializedName("out_trade_no")
    @Expose(
        serialize = false
    )
    private String outTradeNo;
    @SerializedName("mchid")
    @Expose(
        serialize = false
    )
    private String mchid;

    public QueryOrderByOutTradeNoRequest() {
    }

    public String getOutTradeNo() {
        return this.outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getMchid() {
        return this.mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QueryOrderByOutTradeNoRequest {\n");
        sb.append("    outTradeNo: ").append(StringUtil.toIndentedString(this.outTradeNo)).append("\n");
        sb.append("    mchid: ").append(StringUtil.toIndentedString(this.mchid)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByOutTradeNoRequest toJsapiRequest() {
        Gson gson = GsonUtil.getGson();
        return gson.fromJson(gson.toJson(this), com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByOutTradeNoRequest.class);
    }

    public com.wechat.pay.java.service.payments.nativepay.model.QueryOrderByOutTradeNoRequest toNativeRequest() {
        Gson gson = GsonUtil.getGson();
        return gson.fromJson(gson.toJson(this), com.wechat.pay.java.service.payments.nativepay.model.QueryOrderByOutTradeNoRequest.class);
    }
}
