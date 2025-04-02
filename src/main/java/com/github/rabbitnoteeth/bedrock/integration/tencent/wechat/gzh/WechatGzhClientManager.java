package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WechatGzhClientManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatGzhClientManager.class);
    private static final String DEFAULT_CLIENT_ID = "DEFAULT";
    private static final Map<String, WechatGzhClient> CLIENT_MAP = new ConcurrentHashMap<>();

    private WechatGzhClientManager() {}

    public static void registerClient(WechatGzhClientConfig config) {
        registerClient(DEFAULT_CLIENT_ID, config);
    }

    public static void registerClient(String id, WechatGzhClientConfig config) {
        CLIENT_MAP.put(id, new WechatGzhClient(config));
        LOGGER.info("register wechat gzh client, id={}, config={}", id, config);
    }

    public static WechatGzhClient getClient() {
        return getClient(DEFAULT_CLIENT_ID);
    }

    public static WechatGzhClient getClient(String id) {
        return CLIENT_MAP.get(id);
    }

}
