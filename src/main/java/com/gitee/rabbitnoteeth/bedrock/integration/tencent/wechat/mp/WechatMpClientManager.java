package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.mp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WechatMpClientManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatMpClientManager.class);
    private static final String DEFAULT_CLIENT_ID = "DEFAULT";
    private static final Map<String, WechatMpClient> CLIENT_MAP = new ConcurrentHashMap<>();

    private WechatMpClientManager() {}

    public static void registerClient(WechatMpClientConfig config) {
        registerClient(DEFAULT_CLIENT_ID, config);
    }

    public static void registerClient(String id, WechatMpClientConfig config) {
        CLIENT_MAP.put(id, new WechatMpClient(config));
        LOGGER.info("register wechat mp client, id={}, config={}", id, config);
    }

    public static WechatMpClient getClient() {
        return getClient(DEFAULT_CLIENT_ID);
    }

    public static WechatMpClient getClient(String id) {
        return CLIENT_MAP.get(id);
    }

}
