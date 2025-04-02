package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay;

import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.exception.WechatPayException;
import com.github.rabbitnoteeth.bedrock.util.validation.ValidationUtils;
import com.github.rabbitnoteeth.bedrock.util.validation.annotation.Validate;
import com.github.rabbitnoteeth.bedrock.util.validation.entity.Rule;

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

        @Validate(rule = Rule.NOT_NULL, message = "merchantId is required")
        private String merchantId;
        @Validate(rule = Rule.NOT_NULL, message = "privateKeyPath is required")
        private String privateKeyPath;
        @Validate(rule = Rule.NOT_NULL, message = "merchantSerialNumber is required")
        private String merchantSerialNumber;
        @Validate(rule = Rule.NOT_NULL, message = "platformCertPath is required")
        private String platformCertPath;
        @Validate(rule = Rule.NOT_NULL, message = "notifyUrl is required")
        private String notifyUrl;
        @Validate(rule = Rule.NOT_NULL, message = "apiV3Key is required")
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
                ValidationUtils.validate(this);
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
