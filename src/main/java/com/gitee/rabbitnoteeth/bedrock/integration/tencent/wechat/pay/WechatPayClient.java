package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay;

import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.consts.WechatPayType;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.exception.WechatPayException;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model.PrepayRequest;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model.QueryOrderByIdRequest;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model.QueryOrderByOutTradeNoRequest;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAConfig;
import com.wechat.pay.java.core.cipher.Signer;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RSANotificationConfig;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;

import java.time.Instant;
import java.util.UUID;

public class WechatPayClient {

    protected final WechatPayClientConfig payConfig;
    protected final Config config;
    protected final Signer signer;
    private final NotificationParser notificationParser;
    private final JsapiService jsapiService;
    private final NativePayService nativePayService;

    WechatPayClient(WechatPayClientConfig payConfig) {
        this.payConfig = payConfig;
        // init config
        this.config = new RSAConfig.Builder().merchantId(payConfig.getMerchantId()).privateKeyFromPath(payConfig.getPrivateKeyPath()).merchantSerialNumber(payConfig.getMerchantSerialNumber()).wechatPayCertificatesFromPath(payConfig.getPlatformCertPath()).build();
        // init singer
        this.signer = config.createSigner();
        // init notificationParser
        RSANotificationConfig notificationConfig = new RSANotificationConfig.Builder().certificatesFromPath(payConfig.getPlatformCertPath()).apiV3Key(payConfig.getApiV3Key()).build();
        this.notificationParser = new NotificationParser(notificationConfig);
        // init service by type
        this.jsapiService = new JsapiService.Builder().config(config).build();
        this.nativePayService = new NativePayService.Builder().config(config).build();
    }

    public final String generateTimeStamp() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

    public final String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16).toUpperCase();
    }

    public final String generateSign(String data) {
        return signer.sign(data).getSign();
    }

    public final String prepay(WechatPayType type, PrepayRequest request) throws WechatPayException {
        try {
            checkTypeValue(type);
            if (type == WechatPayType.JSAPI) {
                return jsapiService.prepay(request.toJsapiRequest()).getPrepayId();
            }
            if (type == WechatPayType.NATIVE) {
                return nativePayService.prepay(request.toNativeRequest()).getCodeUrl();
            }
            throw new IllegalArgumentException("invalid type value");
        } catch (Throwable e) {
            throw new WechatPayException(e);
        }
    }

    public final Transaction parseNotification(String timestamp, String nonce, String serial, String signType, String signature, String data) throws WechatPayException {
        try {
            RequestParam requestParam = new RequestParam.Builder().timestamp(timestamp).nonce(nonce).serialNumber(serial).signType(signType).signature(signature).body(data).build();
            return notificationParser.parse(requestParam, Transaction.class);
        } catch (Throwable e) {
            throw new WechatPayException(e);
        }
    }

    public final Transaction queryOrderById(WechatPayType type, String transactionId, String mchid) throws WechatPayException {
        try {
            checkTypeValue(type);
            QueryOrderByIdRequest request = new QueryOrderByIdRequest();
            request.setTransactionId(transactionId);
            request.setMchid(mchid);
            if (type == WechatPayType.JSAPI) {
                return jsapiService.queryOrderById(request.toJsapiRequest());
            }
            if (type == WechatPayType.NATIVE) {
                return nativePayService.queryOrderById(request.toNativeRequest());
            }
            throw new IllegalArgumentException("invalid type value");
        } catch (Throwable e) {
            throw new WechatPayException(e);
        }
    }

    public final Transaction queryOrderByOutTradeNo(WechatPayType type, String outTradeNo, String mchid) throws WechatPayException {
        try {
            checkTypeValue(type);
            QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
            request.setOutTradeNo(outTradeNo);
            request.setMchid(mchid);
            if (type == WechatPayType.JSAPI) {
                return jsapiService.queryOrderByOutTradeNo(request.toJsapiRequest());
            }
            if (type == WechatPayType.NATIVE) {
                return nativePayService.queryOrderByOutTradeNo(request.toNativeRequest());
            }
            throw new IllegalArgumentException("invalid type value");
        } catch (Throwable e) {
            throw new WechatPayException(e);
        }
    }

    private void checkTypeValue(WechatPayType type) {
        if (type == null) {
            throw new IllegalArgumentException("type value is required");
        }
    }

    public WechatPayClientConfig getConfig() {
        return payConfig;
    }
}
