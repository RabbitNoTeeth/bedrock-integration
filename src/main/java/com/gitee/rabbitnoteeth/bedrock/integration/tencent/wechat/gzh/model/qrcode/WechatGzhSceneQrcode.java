package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.qrcode;

public class WechatGzhSceneQrcode {

    private String base64;

    private Long expireSeconds;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
