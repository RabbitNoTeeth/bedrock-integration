package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.exception;

public class WechatPayException extends Exception {

    public WechatPayException(String message) {
        super(message);
    }

    public WechatPayException(Throwable e) {
        super(e);
    }

    public WechatPayException(String message, Throwable e) {
        super(message, e);
    }

}
