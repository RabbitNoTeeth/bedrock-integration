package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.StringUtil;

public class Payer {

    @SerializedName("openid")
    private String openid;

    public Payer() {
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Payer {\n");
        sb.append("    openid: ").append(StringUtil.toIndentedString(this.openid)).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
