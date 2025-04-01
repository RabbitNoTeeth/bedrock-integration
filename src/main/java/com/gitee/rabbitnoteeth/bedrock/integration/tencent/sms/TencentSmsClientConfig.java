package com.gitee.rabbitnoteeth.bedrock.integration.tencent.sms;

import com.gitee.rabbitnoteeth.bedrock.integration.tencent.sms.exception.TencentSmsException;
import com.gitee.rabbitnoteeth.bedrock.util.BeanUtils;
import com.gitee.rabbitnoteeth.bedrock.util.annotation.NotNull;

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

        @NotNull
        private String secretId;
        @NotNull
        private String secretKey;
        @NotNull
        private String region;
        @NotNull
        private String smsSdkAppId;
        @NotNull
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
                BeanUtils.validFields(this);
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
