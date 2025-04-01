package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.StringUtil;

public class Amount {

    @SerializedName("total")
    private Integer total;
    @SerializedName("currency")
    private String currency;

    public Amount() {
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Amount {\n");
        sb.append("    total: ").append(StringUtil.toIndentedString(this.total)).append("\n");
        sb.append("    currency: ").append(StringUtil.toIndentedString(this.currency)).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
