package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.StringUtil;

public class StoreInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("area_code")
    private String areaCode;
    @SerializedName("address")
    private String address;

    public StoreInfo() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StoreInfo {\n");
        sb.append("    id: ").append(StringUtil.toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(StringUtil.toIndentedString(this.name)).append("\n");
        sb.append("    areaCode: ").append(StringUtil.toIndentedString(this.areaCode)).append("\n");
        sb.append("    address: ").append(StringUtil.toIndentedString(this.address)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
