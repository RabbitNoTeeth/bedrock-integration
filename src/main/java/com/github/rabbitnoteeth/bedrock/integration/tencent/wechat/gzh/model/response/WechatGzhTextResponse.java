package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.response;

public final class WechatGzhTextResponse extends WechatGzhResponse {

    private String content;

    public WechatGzhTextResponse(String fromUserName, String toUserName) {
        super(fromUserName, toUserName, "text");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
