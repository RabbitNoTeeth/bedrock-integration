package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.mp.model;

import com.google.gson.annotations.SerializedName;

public class WechatMpPhoneNum {

    @SerializedName("phone_info")
    private PhoneInfo phoneInfo;

    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
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
        return "WechatMpPhoneNum{" +
            "phoneInfo=" + phoneInfo +
            ", errCode=" + errCode +
            ", errMsg='" + errMsg + '\'' +
            '}';
    }

    public static class PhoneInfo {

        @SerializedName("phoneNumber")
        private String phoneNumber;

        @SerializedName("purePhoneNumber")
        private String purePhoneNumber;

        @SerializedName("countryCode")
        private String countryCode;

        @SerializedName("watermark")
        private Watermark watermark;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPurePhoneNumber() {
            return purePhoneNumber;
        }

        public void setPurePhoneNumber(String purePhoneNumber) {
            this.purePhoneNumber = purePhoneNumber;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public Watermark getWatermark() {
            return watermark;
        }

        public void setWatermark(Watermark watermark) {
            this.watermark = watermark;
        }

        @Override
        public String toString() {
            return "PhoneInfo{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", purePhoneNumber='" + purePhoneNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", watermark=" + watermark +
                '}';
        }
    }

    public static class Watermark {

        @SerializedName("timestamp")
        private Long timestamp;

        @SerializedName("appid")
        private String appid;

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        @Override
        public String toString() {
            return "Watermark{" +
                "timestamp=" + timestamp +
                ", appid='" + appid + '\'' +
                '}';
        }
    }

}
