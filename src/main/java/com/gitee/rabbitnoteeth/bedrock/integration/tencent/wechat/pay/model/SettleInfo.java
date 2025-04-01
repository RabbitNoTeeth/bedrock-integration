package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.StringUtil;

public class SettleInfo {
    @SerializedName("profit_sharing")
    private Boolean profitSharing;

    public SettleInfo() {
    }

    public Boolean getProfitSharing() {
        return this.profitSharing;
    }

    public void setProfitSharing(Boolean profitSharing) {
        this.profitSharing = profitSharing;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SettleInfo {\n");
        sb.append("    profitSharing: ").append(StringUtil.toIndentedString(this.profitSharing)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
