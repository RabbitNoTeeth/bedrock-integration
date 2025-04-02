package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh;

import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.common.WechatAccessToken;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.WechatGzhLangEnum;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.menu.WechatGzhCreateMenu;
import com.github.rabbitnoteeth.bedrock.util.HexUtils;
import com.github.rabbitnoteeth.bedrock.util.HttpClientUtils;
import com.github.rabbitnoteeth.bedrock.util.JsonUtils;
import com.github.rabbitnoteeth.bedrock.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.exception.WechatGzhException;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.*;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.qrcode.WechatGzhScene;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.qrcode.WechatGzhSceneQrcode;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.qrcode.WechatGzhSceneQrcodeTicket;
import com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.user.WechatGzhUserInfo;
import com.wechat.pay.java.core.util.GsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class WechatGzhClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatGzhClient.class);
    private static final long DEFAULT_TEMPORARY_SCENE_QRCODE_TICKET_EXPIRE_SECONDS = 60L;
    private final WechatGzhClientConfig config;
    private LocalDateTime accessTokenNextRequestTime;

    public WechatGzhClient(WechatGzhClientConfig config) {
        this.config = config;
    }

    private WechatAccessToken requestUnstableAccessToken() throws WechatGzhException {
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            Map<String, Object> params = new HashMap<>();
            params.put("grant_type", "client_credential");
            params.put("appid", this.config.getAppId());
            params.put("secret", this.config.getAppSecret());
            HttpResponse response = HttpClientUtils.get(url, params, null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatGzhException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatAccessToken token = gson.fromJson(body, WechatAccessToken.class);
            Integer errCode = token.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatGzhException(body);
            }
            LOGGER.info("Succeed in requesting access token, {}", token);
            return token;
        } catch (WechatGzhException e) {
            LOGGER.error("Failed to request access token", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Failed to request access token", e);
            throw new WechatGzhException(e);
        }
    }

    private WechatAccessToken requestStableAccessToken() throws WechatGzhException {
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/stable_token";
            JsonObject json = new JsonObject();
            json.addProperty("grant_type", "client_credential");
            json.addProperty("appid", this.config.getAppId());
            json.addProperty("secret", this.config.getAppSecret());
            json.addProperty("force_refresh", true);
            HttpResponse response = HttpClientUtils.post(url, new StringEntity(json.toString(), ContentType.APPLICATION_JSON), null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatGzhException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatAccessToken token = gson.fromJson(body, WechatAccessToken.class);
            Integer errCode = token.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatGzhException(body);
            }
            LOGGER.info("Succeed in requesting access token, {}", token);
            return token;
        } catch (WechatGzhException e) {
            LOGGER.error("Failed to request access token", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Failed to request access token", e);
            throw new WechatGzhException(e);
        }
    }

    public String getAccessToken() throws WechatGzhException {
        try {
            String accessToken = config.getAccessTokenStore().get();
            if (StringUtils.isNotBlank(accessToken) && (accessTokenNextRequestTime == null || accessTokenNextRequestTime.isAfter(LocalDateTime.now()))) {
                return accessToken;
            }
            synchronized (this) {
                WechatAccessToken newToken = config.isUseStableAccessToken() ? requestStableAccessToken() : requestUnstableAccessToken();
                accessToken = newToken.getAccessToken();
                Long expiresIn = newToken.getExpiresIn();
                accessTokenNextRequestTime = LocalDateTime.now().plusSeconds(expiresIn).minusSeconds(config.getAccessTokenIdleSeconds());
                config.getAccessTokenStore().set(accessToken, expiresIn);
                return accessToken;
            }
        } catch (WechatGzhException e) {
            throw e;
        } catch (Throwable e) {
            throw new WechatGzhException(e);
        }
    }

    public boolean validSignature(String signature, String token, String timestamp, String nonce) throws WechatGzhException {
        try {
            // 将token、timestamp、nonce三个参数进行字典序排序
            String[] arr = {token, timestamp, nonce};
            Arrays.sort(arr);
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            String concat = String.join("", arr);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(concat.getBytes());
            String mySignature = HexUtils.parseByte2HexStr(digest);
            return signature.equalsIgnoreCase(mySignature);
        } catch (Exception e) {
            throw new WechatGzhException(e);
        }
    }

    public WechatGzhSceneQrcodeTicket createTemporarySceneQrcodeTicket(WechatGzhScene wechatGzhScene) throws WechatGzhException {
        return createTemporarySceneQrcodeTicket(DEFAULT_TEMPORARY_SCENE_QRCODE_TICKET_EXPIRE_SECONDS, wechatGzhScene);
    }

    public WechatGzhSceneQrcodeTicket createTemporarySceneQrcodeTicket(long expireSeconds, WechatGzhScene wechatGzhScene) throws WechatGzhException {
        try {
            if (expireSeconds < 60) {
                expireSeconds = 60;
            }
            String accessToken = getAccessToken();
            if (StringUtils.isBlank(accessToken)) {
                throw new IllegalStateException("there's no access token");
            }
            String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
            JsonObject json = new JsonObject();
            json.addProperty("expire_seconds", expireSeconds);
            json.addProperty("action_name", "QR_SCENE");
            JsonObject actionInfo = new JsonObject();
            JsonObject scene = new JsonObject();
            int sceneId = wechatGzhScene.getSceneId();
            scene.addProperty("scene_id", sceneId);
            String sceneStr = wechatGzhScene.getSceneStr();
            if (StringUtils.isNotBlank(sceneStr)) {
                scene.addProperty("scene_str", sceneStr);
            }
            actionInfo.add("scene", scene);
            json.add("action_info", actionInfo);
            HttpResponse response = HttpClientUtils.post(url, new StringEntity(json.toString(), ContentType.APPLICATION_JSON), null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatGzhException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatGzhSceneQrcodeTicket ticket = gson.fromJson(body, WechatGzhSceneQrcodeTicket.class);
            Integer errCode = ticket.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatGzhException(body);
            }
            return ticket;
        } catch (WechatGzhException e) {
            throw e;
        } catch (Exception e) {
            throw new WechatGzhException(e);
        }
    }

    public WechatGzhSceneQrcode getTemporarySceneQrcode(WechatGzhSceneQrcodeTicket ticket) throws WechatGzhException {
        try {
            String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
            Map<String, Object> params = new HashMap<>();
            params.put("ticket", URLEncoder.encode(ticket.getTicket(), StandardCharsets.UTF_8));
            HttpResponse response = HttpClientUtils.get(url, params, null);
            byte[] body = EntityUtils.toByteArray(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBodySize=%s", statusCode, body.length);
            if (statusCode != 200) {
                throw new WechatGzhException(errMsg);
            }
            WechatGzhSceneQrcode qrcode = new WechatGzhSceneQrcode();
            qrcode.setBase64(Base64.getEncoder().encodeToString(body));
            qrcode.setExpireSeconds(ticket.getExpireSeconds());
            return qrcode;
        } catch (WechatGzhException e) {
            throw e;
        } catch (Exception e) {
            throw new WechatGzhException(e);
        }
    }

    public WechatGzhSceneQrcode createTemporarySceneQrcode(WechatGzhScene wechatGzhScene) throws WechatGzhException {
        return getTemporarySceneQrcode(createTemporarySceneQrcodeTicket(DEFAULT_TEMPORARY_SCENE_QRCODE_TICKET_EXPIRE_SECONDS, wechatGzhScene));
    }

    public WechatGzhSceneQrcode createTemporarySceneQrcode(long expireSeconds, WechatGzhScene wechatGzhScene) throws WechatGzhException {
        return getTemporarySceneQrcode(createTemporarySceneQrcodeTicket(expireSeconds, wechatGzhScene));
    }

    public WechatGzhUserInfo getUserInfo(String openId) throws WechatGzhException {
        return getUserInfo(openId, WechatGzhLangEnum.ZH_CN);
    }

    public WechatGzhUserInfo getUserInfo(String openId, WechatGzhLangEnum lang) throws WechatGzhException {
        try {
            String accessToken = getAccessToken();
            if (StringUtils.isBlank(accessToken)) {
                throw new IllegalStateException("there's no access token");
            }
            String url = "https://api.weixin.qq.com/cgi-bin/user/info";
            Map<String, Object> params = new HashMap<>();
            params.put("access_token", accessToken);
            params.put("openid", openId);
            if (lang != null) {
                params.put("lang", lang.getValue());
            }
            HttpResponse response = HttpClientUtils.get(url, params, null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatGzhException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatGzhUserInfo userInfo = gson.fromJson(body, WechatGzhUserInfo.class);
            Integer errCode = userInfo.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatGzhException(body);
            }
            return userInfo;
        } catch (WechatGzhException e) {
            throw e;
        } catch (Exception e) {
            throw new WechatGzhException(e);
        }
    }

    public void createMenu(WechatGzhCreateMenu menu) throws WechatGzhException {
        try {
            String accessToken = getAccessToken();
            if (StringUtils.isBlank(accessToken)) {
                throw new IllegalStateException("there's no access token");
            }
            String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
            HttpResponse response = HttpClientUtils.post(url, new StringEntity(JsonUtils.encode(menu), ContentType.APPLICATION_JSON), null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatGzhException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            JsonObject data = gson.fromJson(body, JsonObject.class);
            int errCode = data.get("errcode").getAsInt();
            if (errCode != 0) {
                throw new WechatGzhException(body);
            }
        } catch (WechatGzhException e) {
            throw e;
        } catch (Exception e) {
            throw new WechatGzhException(e);
        }
    }

    public WechatGzhClientConfig getConfig() {
        return config;
    }
}
