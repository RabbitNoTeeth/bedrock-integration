package com.github.rabbitnoteeth.bedrock.integration.tencent.sms.exception;

public class TencentSmsException extends Exception {

    private String code;

    public TencentSmsException(String message) {
        super(message);
    }

    public TencentSmsException(String code, String message) {
        super(message);
        this.code = code;
    }

    public TencentSmsException(Throwable e) {
        super(e);
    }

    public TencentSmsException(String message, Throwable e) {
        super(message, e);
    }

    public String getCode() {
        return code;
    }
}
