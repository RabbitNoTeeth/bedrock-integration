package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh;

import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.common.store.WechatAccessTokenStore;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.common.store.impl.DefaultWechatAccessTokenStoreImpl;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.exception.WechatGzhException;
import com.gitee.rabbitnoteeth.bedrock.util.BeanUtils;
import com.gitee.rabbitnoteeth.bedrock.util.annotation.NotNull;

public class WechatGzhClientConfig {

    private final String appId;
    private final String appSecret;
    private final String token;
    private final String encodingAESKey;
    private final boolean useStableAccessToken;
    private final long accessTokenIdleSeconds;
    private final WechatAccessTokenStore accessTokenStore;

    private WechatGzhClientConfig(String appId, String appSecret, String token, String encodingAESKey, boolean useStableAccessToken, long accessTokenIdleSeconds, WechatAccessTokenStore accessTokenStore) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.token = token;
        this.encodingAESKey = encodingAESKey;
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

    public String getToken() {
        return token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
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

        @NotNull
        private String appId;
        @NotNull
        private String appSecret;
        @NotNull
        private String token;
        @NotNull
        private String encodingAESKey;
        @NotNull
        private Boolean useStableAccessToken = DEFAULT_USE_STABLE_ACCESS_TOKEN;
        @NotNull
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

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setEncodingAESKey(String encodingAESKey) {
            this.encodingAESKey = encodingAESKey;
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

        public WechatGzhClientConfig build() throws WechatGzhException {
            try {
                BeanUtils.validFields(this);
                if (accessTokenStore == null) {
                    accessTokenStore = new DefaultWechatAccessTokenStoreImpl();
                }
                return new WechatGzhClientConfig(appId, appSecret, token, encodingAESKey, useStableAccessToken, accessTokenIdleSeconds, accessTokenStore);
            } catch (Throwable e) {
                throw new WechatGzhException(e);
            }
        }

    }

    @Override
    public String toString() {
        return "WechatGzhClientConfig{" +
            "appId='" + appId + '\'' +
            ", appSecret='" + appSecret + '\'' +
            ", token='" + token + '\'' +
            ", encodingAESKey='" + encodingAESKey + '\'' +
            ", useStableAccessToken='" + useStableAccessToken + '\'' +
            ", accessTokenIdleSeconds='" + accessTokenIdleSeconds + '\'' +
            '}';
    }
}
