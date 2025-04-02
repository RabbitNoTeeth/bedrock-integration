package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.exception;

public class WechatGzhException extends Exception {

    private String code;

    public WechatGzhException(String message) {
        super(message);
    }

    public WechatGzhException(String code, String message) {
        super(message);
        this.code = code;
    }

    public WechatGzhException(Throwable e) {
        super(e);
    }

    public WechatGzhException(String message, Throwable e) {
        super(message, e);
    }

    public String getCode() {
        return code;
    }
}
