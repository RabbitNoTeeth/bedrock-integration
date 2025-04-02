package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.mp;

import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.common.store.WechatAccessTokenStore;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.common.store.impl.DefaultWechatAccessTokenStoreImpl;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.mp.exception.WechatMpException;
import com.github.rabbitnoteeth.bedrock.util.validation.ValidationUtils;
import com.github.rabbitnoteeth.bedrock.util.validation.annotation.Validate;
import com.github.rabbitnoteeth.bedrock.util.validation.entity.Rule;

public class WechatMpClientConfig {

    private final String appId;
    private final String appSecret;
    private final boolean useStableAccessToken;
    private final long accessTokenIdleSeconds;
    private final WechatAccessTokenStore accessTokenStore;

    private WechatMpClientConfig(String appId, String appSecret, boolean useStableAccessToken, long accessTokenIdleSeconds, WechatAccessTokenStore accessTokenStore) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.useStableAccessToken = useStableAccessToken;
        this.accessTokenIdleSeconds = accessTokenIdleSeconds;
        this.accessTokenStore = accessTokenStore;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public boolean isUseStableAccessToken() {
        return useStableAccessToken;
    }

    public long getAccessTokenIdleSeconds() {
        return accessTokenIdleSeconds;
    }

    public WechatAccessTokenStore getAccessTokenStore() {
        return accessTokenStore;
    }

    public static class Builder {

        private static final boolean DEFAULT_USE_STABLE_ACCESS_TOKEN = true;
        private static final long DEFAULT_ACCESS_TOKEN_IDLE_SECONDS = 300;

        @Validate(rule = Rule.NOT_NULL, message = "appId is required")
        private String appId;
        @Validate(rule = Rule.NOT_NULL, message = "appSecret is required")
        private String appSecret;
        @Validate(rule = Rule.NOT_NULL, message = "useStableAccessToken is required")
        private Boolean useStableAccessToken = DEFAULT_USE_STABLE_ACCESS_TOKEN;
        @Validate(rule = Rule.NOT_NULL, message = "accessTokenIdleSeconds is required")
        private Long accessTokenIdleSeconds = DEFAULT_ACCESS_TOKEN_IDLE_SECONDS;

        private WechatAccessTokenStore accessTokenStore;

        public Builder setAppId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder setAppSecret(String appSecret) {
            this.appSecret = appSecret;
            return this;
        }

        public Builder setUseStableAccessToken(Boolean useStableAccessToken) {
            this.useStableAccessToken = useStableAccessToken;
            return this;
        }

        public Builder setAccessTokenIdleSeconds(Long accessTokenIdleSeconds) {
            this.accessTokenIdleSeconds = accessTokenIdleSeconds;
            return this;
        }

        public Builder setAccessTokenStore(WechatAccessTokenStore accessTokenStore) {
            this.accessTokenStore = accessTokenStore;
            return this;
        }

        public WechatMpClientConfig build() throws WechatMpException {
            try {
                ValidationUtils.validate(this);
                if (accessTokenStore == null) {
                    accessTokenStore = new DefaultWechatAccessTokenStoreImpl();
                }
                return new WechatMpClientConfig(appId, appSecret, useStableAccessToken, accessTokenIdleSeconds, accessTokenStore);
            } catch (Throwable e) {
                throw new WechatMpException(e);
            }
        }

    }

    @Override
    public String toString() {
        return "WechatMpClientConfig{" +
            "appId='" + appId + '\'' +
            ", appSecret='" + appSecret + '\'' +
            ", useStableAccessToken='" + useStableAccessToken + '\'' +
            ", accessTokenIdleSeconds='" + accessTokenIdleSeconds + '\'' +
            '}';
    }
}
