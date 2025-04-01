package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay;

import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.exception.WechatPayException;
import com.gitee.rabbitnoteeth.bedrock.util.BeanUtils;
import com.gitee.rabbitnoteeth.bedrock.util.annotation.NotNull;
import com.gitee.rabbitnoteeth.bedrock.util.exception.BeanValidException;

import java.lang.reflect.Field;

public class WechatPayClientConfig {

    private final String merchantId;
    private final String privateKeyPath;
    private final String merchantSerialNumber;
    private final String platformCertPath;
    private final String notifyUrl;
    private final String apiV3Key;

    private WechatPayClientConfig(String merchantId, String privateKeyPath, String merchantSerialNumber, String platformCertPath, String notifyUrl, String apiV3Key) {
        this.merchantId = merchantId;
        this.privateKeyPath = privateKeyPath;
        this.merchantSerialNumber = merchantSerialNumber;
        this.platformCertPath = platformCertPath;
        this.notifyUrl = notifyUrl;
        this.apiV3Key = apiV3Key;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public String getMerchantSerialNumber() {
        return merchantSerialNumber;
    }

    public String getPlatformCertPath() {
        return platformCertPath;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getApiV3Key() {
        return apiV3Key;
    }

    public static class Builder {

        @NotNull
        private String merchantId;
        @NotNull
        private String privateKeyPath;
        @NotNull
        private String merchantSerialNumber;
        @NotNull
        private String platformCertPath;
        @NotNull
        private String notifyUrl;
        @NotNull
        private String apiV3Key;

        public Builder setMerchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder setPrivateKeyPath(String privateKeyPath) {
            this.privateKeyPath = privateKeyPath;
            return this;
        }

        public Builder setMerchantSerialNumber(String merchantSerialNumber) {
            this.merchantSerialNumber = merchantSerialNumber;
            return this;
        }

        public Builder setPlatformCertPath(String platformCertPath) {
            this.platformCertPath = platformCertPath;
            return this;
        }

        public Builder setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public Builder setApiV3Key(String apiV3Key) {
            this.apiV3Key = apiV3Key;
            return this;
        }

        public WechatPayClientConfig build() throws WechatPayException {
            try {
                BeanUtils.validFields(this);
                return new WechatPayClientConfig(this.merchantId, this.privateKeyPath, this.merchantSerialNumber, this.platformCertPath, this.notifyUrl, this.apiV3Key);
            } catch (Throwable e) {
                throw new WechatPayException(e);
            }

        }

    }

    @Override
    public String toString() {
        return "WechatPayConfig{" + "merchantId='" + merchantId + '\'' + ", privateKeyPath='" + privateKeyPath + '\'' + ", merchantSerialNumber='" + merchantSerialNumber + '\'' + ", platformCertPath='" + platformCertPath + '\'' + ", notifyUrl='" + notifyUrl + '\'' + ", apiV3Key='" + apiV3Key + '\'' + '}';
    }
}
