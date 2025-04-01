package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.qrcode;

public class WechatGzhScene {


    private int sceneId;

    private String sceneStr;

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneStr() {
        return sceneStr;
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
    }

    @Override
    public String toString() {
        return "WechatGzhScene{" +
            "sceneId=" + sceneId +
            ", sceneStr='" + sceneStr + '\'' +
            '}';
    }
}
