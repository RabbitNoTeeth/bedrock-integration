package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WechatPayClientManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatPayClientManager.class);
    private static final String DEFAULT_CLIENT_ID = "DEFAULT";
    private static final Map<String, WechatPayClient> CLIENT_MAP = new ConcurrentHashMap<>();

    private WechatPayClientManager() {}

    public static void registerClient(WechatPayClientConfig config) {
        registerClient(DEFAULT_CLIENT_ID, config);
    }

    public static void registerClient(String id, WechatPayClientConfig config) {
        CLIENT_MAP.put(id, new WechatPayClient(config));
        LOGGER.info("register wechat pay client, id={}, config={}", id, config);
    }

    public static WechatPayClient getClient() {
        return getClient(DEFAULT_CLIENT_ID);
    }

    public static WechatPayClient getClient(String id) {
        return CLIENT_MAP.get(id);
    }

}
