package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.GsonUtil;
import com.wechat.pay.java.core.util.StringUtil;

public class QueryOrderByIdRequest {
    @SerializedName("transaction_id")
    @Expose(
        serialize = false
    )
    private String transactionId;
    @SerializedName("mchid")
    @Expose(
        serialize = false
    )
    private String mchid;

    public QueryOrderByIdRequest() {
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMchid() {
        return this.mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QueryOrderByIdRequest {\n");
        sb.append("    transactionId: ").append(StringUtil.toIndentedString(this.transactionId)).append("\n");
        sb.append("    mchid: ").append(StringUtil.toIndentedString(this.mchid)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByIdRequest toJsapiRequest() {
        Gson gson = GsonUtil.getGson();
        return gson.fromJson(gson.toJson(this), com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByIdRequest.class);
    }

    public com.wechat.pay.java.service.payments.nativepay.model.QueryOrderByIdRequest toNativeRequest() {
        Gson gson = GsonUtil.getGson();
        return gson.fromJson(gson.toJson(this), com.wechat.pay.java.service.payments.nativepay.model.QueryOrderByIdRequest.class);
    }
}
