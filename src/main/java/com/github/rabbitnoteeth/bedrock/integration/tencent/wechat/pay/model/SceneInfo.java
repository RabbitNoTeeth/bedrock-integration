package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.StringUtil;

public class SceneInfo {

    @SerializedName("payer_client_ip")
    private String payerClientIp;
    @SerializedName("device_id")
    private String deviceId;
    @SerializedName("store_info")
    private StoreInfo storeInfo;

    public SceneInfo() {
    }

    public String getPayerClientIp() {
        return this.payerClientIp;
    }

    public void setPayerClientIp(String payerClientIp) {
        this.payerClientIp = payerClientIp;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public StoreInfo getStoreInfo() {
        return this.storeInfo;
    }

    public void setStoreInfo(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SceneInfo {\n");
        sb.append("    payerClientIp: ").append(StringUtil.toIndentedString(this.payerClientIp)).append("\n");
        sb.append("    deviceId: ").append(StringUtil.toIndentedString(this.deviceId)).append("\n");
        sb.append("    storeInfo: ").append(StringUtil.toIndentedString(this.storeInfo)).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
