package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.menu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WechatGzhCreateMenu {

    @SerializedName("button")
    private List<Button> buttons;

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public static class Button {
        @SerializedName("type")
        private String type;
        @SerializedName("name")
        private String name;
        @SerializedName("key")
        private String key;
        @SerializedName("url")
        private String url;
        @SerializedName("media_id")
        private String mediaId;
        @SerializedName("appid")
        private String appId;
        @SerializedName("pagepath")
        private String pagePath;
        @SerializedName("article_id")
        private String articleId;
        @SerializedName("sub_button")
        private List<Button> subButtons;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPagePath() {
            return pagePath;
        }

        public void setPagePath(String pagePath) {
            this.pagePath = pagePath;
        }

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public List<Button> getSubButtons() {
            return subButtons;
        }

        public void setSubButtons(List<Button> subButtons) {
            this.subButtons = subButtons;
        }
    }

}
