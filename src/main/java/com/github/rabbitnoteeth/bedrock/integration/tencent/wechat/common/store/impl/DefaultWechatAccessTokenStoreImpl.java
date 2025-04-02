package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.common.store.impl;

import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.common.store.WechatAccessTokenStore;
import com.github.rabbitnoteeth.bedrock.util.collection.TimedMap;

public class DefaultWechatAccessTokenStoreImpl implements WechatAccessTokenStore {

    private final TimedMap<String, String> CACHE = new TimedMap<>();
    private final String KEY = "ACCESS_TOKEN";

    @Override
    public void set(String accessToken, long expireSeconds) {
        CACHE.put(KEY, accessToken, expireSeconds);
    }

    @Override
    public String get() {
        return CACHE.get(KEY);
    }
}
