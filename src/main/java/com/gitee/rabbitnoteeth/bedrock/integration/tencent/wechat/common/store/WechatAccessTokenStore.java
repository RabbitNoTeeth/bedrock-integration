package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.common.store;

public interface WechatAccessTokenStore {

    void set(String accessToken, long expireSeconds);

    String get();

}
