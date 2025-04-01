package com.gitee.rabbitnoteeth.bedrock.integration.tencent.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TencentSmsClientManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TencentSmsClientManager.class);
    private static final String DEFAULT_CLIENT_ID = "DEFAULT";
    private static final Map<String, TencentSmsClient> CLIENT_MAP = new ConcurrentHashMap<>();

    private TencentSmsClientManager() {}

    public static void registerClient(TencentSmsClientConfig config) {
        registerClient(DEFAULT_CLIENT_ID, config);
    }

    public static void registerClient(String id, TencentSmsClientConfig config) {
        CLIENT_MAP.put(id, new TencentSmsClient(config));
        LOGGER.info("register tencent sms client, id={}, config={}", id, config);
    }

    public static TencentSmsClient getClient() {
        return getClient(DEFAULT_CLIENT_ID);
    }

    public static TencentSmsClient getClient(String id) {
        return CLIENT_MAP.get(id);
    }

}
