package com.github.rabbitnoteeth.bedrock.integration.tencent.sms;

import com.github.rabbitnoteeth.bedrock.integration.tencent.sms.exception.TencentSmsException;
import com.github.rabbitnoteeth.bedrock.util.validation.ValidationUtils;
import com.github.rabbitnoteeth.bedrock.util.validation.annotation.Validate;
import com.github.rabbitnoteeth.bedrock.util.validation.entity.Rule;

public class TencentSmsClientConfig {

    private final String secretId;
    private final String secretKey;
    private final String region;
    private final String smsSdkAppId;
    private final String signName;

    private TencentSmsClientConfig(String secretId, String secretKey, String region, String smsSdkAppId, String signName) {
        this.secretId = secretId;
        this.secretKey = secretKey;
        this.region = region;
        this.smsSdkAppId = smsSdkAppId;
        this.signName = signName;
    }

    public String getSecretId() {
        return secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getRegion() {
        return region;
    }

    public String getSmsSdkAppId() {
        return smsSdkAppId;
    }

    public String getSignName() {
        return signName;
    }

    public static class Builder {

        @Validate(rule = Rule.NOT_NULL, message = "secretId is required")
        private String secretId;
        @Validate(rule = Rule.NOT_NULL, message = "secretKey is required")
        private String secretKey;
        @Validate(rule = Rule.NOT_NULL, message = "region is required")
        private String region;
        @Validate(rule = Rule.NOT_NULL, message = "smsSdkAppId is required")
        private String smsSdkAppId;
        @Validate(rule = Rule.NOT_NULL, message = "signName is required")
        private String signName;

        public Builder setSecretId(String secretId) {
            this.secretId = secretId;
            return this;
        }

        public Builder setSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        public Builder setSmsSdkAppId(String smsSdkAppId) {
            this.smsSdkAppId = smsSdkAppId;
            return this;
        }

        public Builder setSignName(String signName) {
            this.signName = signName;
            return this;
        }

        public TencentSmsClientConfig build() throws TencentSmsException {
            try {
                ValidationUtils.validate(this);
                return new TencentSmsClientConfig(secretId, secretKey, region, smsSdkAppId, signName);
            } catch (Throwable e) {
                throw new TencentSmsException(e);
            }
        }

    }

    @Override
    public String toString() {
        return "TencentSmsClientConfig{" +
            "secretId='" + secretId + '\'' +
            ", secretKey='" + secretKey + '\'' +
            ", region='" + region + '\'' +
            ", smsSdkAppId='" + smsSdkAppId + '\'' +
            ", signName='" + signName + '\'' +
            '}';
    }
}
