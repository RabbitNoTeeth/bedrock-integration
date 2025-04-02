package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.mp.exception;

public class WechatMpException extends Exception {

    private String code;

    public WechatMpException(String message) {
        super(message);
    }

    public WechatMpException(String code, String message) {
        super(message);
        this.code = code;
    }

    public WechatMpException(Throwable e) {
        super(e);
    }

    public WechatMpException(String message, Throwable e) {
        super(message, e);
    }

    public String getCode() {
        return code;
    }
}
