package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model;

public enum WechatGzhLangEnum {
    ZH_CN("zh_CN"),
    ZH_TW("zh_TW"),
    EN("en");

    private final String value;

    WechatGzhLangEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
