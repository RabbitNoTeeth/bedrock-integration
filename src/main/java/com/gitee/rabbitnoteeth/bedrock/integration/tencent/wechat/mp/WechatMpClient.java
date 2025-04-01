package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.mp;

import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.common.WechatAccessToken;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.mp.model.WechatMpPhoneNum;
import com.gitee.rabbitnoteeth.bedrock.util.HttpClientUtils;
import com.gitee.rabbitnoteeth.bedrock.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.mp.exception.WechatMpException;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.mp.model.WechatMpSession;
import com.wechat.pay.java.core.util.GsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WechatMpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatMpClient.class);
    private final WechatMpClientConfig config;
    private LocalDateTime accessTokenNextRequestTime;

    WechatMpClient(WechatMpClientConfig config) {
        this.config = config;
    }

    private WechatAccessToken requestUnstableAccessToken() throws WechatMpException {
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
                throw new WechatMpException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatAccessToken token = gson.fromJson(body, WechatAccessToken.class);
            Integer errCode = token.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatMpException(body);
            }
            LOGGER.info("Succeed in requesting access token, {}", token);
            return token;
        } catch (WechatMpException e) {
            LOGGER.error("Failed to request access token", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Failed to request access token", e);
            throw new WechatMpException(e);
        }
    }

    private WechatAccessToken requestStableAccessToken() throws WechatMpException {
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/stable_token";
            JsonObject json = new JsonObject();
            json.addProperty("grant_type", "client_credential");
            json.addProperty("appid", this.config.getAppId());
            json.addProperty("secret", this.config.getAppSecret());
            json.addProperty("force_refresh", true);
            HttpResponse response;
            response = HttpClientUtils.post(url, new StringEntity(json.toString(), ContentType.APPLICATION_JSON), null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatMpException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatAccessToken token = gson.fromJson(body, WechatAccessToken.class);
            Integer errCode = token.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatMpException(body);
            }
            LOGGER.info("Succeed in requesting access token, {}", token);
            return token;
        } catch (WechatMpException e) {
            LOGGER.error("Failed to request access token", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Failed to request access token", e);
            throw new WechatMpException(e);
        }
    }

    public String getAccessToken() throws WechatMpException {
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
        } catch (WechatMpException e) {
            throw e;
        } catch (Throwable e) {
            throw new WechatMpException(e);
        }
    }

    public WechatMpSession getSession(String jsCode) throws WechatMpException {
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            Map<String, Object> params = new HashMap<>();
            params.put("appid", this.config.getAppId());
            params.put("secret", this.config.getAppSecret());
            params.put("js_code", jsCode);
            params.put("grant_type", "authorization_code");
            HttpResponse response;
            response = HttpClientUtils.get(url, params, null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatMpException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatMpSession session = gson.fromJson(body, WechatMpSession.class);
            Integer errCode = session.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatMpException(body);
            }
            return session;
        } catch (WechatMpException e) {
            throw e;
        } catch (Exception e) {
            throw new WechatMpException(e);
        }
    }

    public WechatMpPhoneNum getPhoneNum(String code) throws WechatMpException {
        try {
            String accessToken = getAccessToken();
            if (StringUtils.isBlank(accessToken)) {
                throw new IllegalStateException("there's no access token");
            }
            String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
            JsonObject json = new JsonObject();
            json.addProperty("code", code);
            HttpResponse response;
            response = HttpClientUtils.post(url, new StringEntity(json.toString(), ContentType.APPLICATION_JSON), null);
            String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int statusCode = response.getStatusLine().getStatusCode();
            String errMsg = String.format("statusCode=%d, responseBody=%s", statusCode, body);
            if (statusCode != 200) {
                throw new WechatMpException(errMsg);
            }
            Gson gson = GsonUtil.getGson();
            WechatMpPhoneNum phoneNum = gson.fromJson(body, WechatMpPhoneNum.class);
            Integer errCode = phoneNum.getErrCode();
            if (errCode != null && errCode != 0) {
                throw new WechatMpException(body);
            }
            return phoneNum;
        } catch (WechatMpException e) {
            throw e;
        } catch (Exception e) {
            throw new WechatMpException(e);
        }
    }

    public WechatMpClientConfig getConfig() {
        return config;
    }
}
