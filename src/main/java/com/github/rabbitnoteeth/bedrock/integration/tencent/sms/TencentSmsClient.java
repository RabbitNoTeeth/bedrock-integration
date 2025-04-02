package com.github.rabbitnoteeth.bedrock.integration.tencent.sms;

import com.github.rabbitnoteeth.bedrock.integration.tencent.sms.exception.TencentSmsException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;

import java.util.Random;

public class TencentSmsClient {

    private final TencentSmsClientConfig config;

    TencentSmsClient(TencentSmsClientConfig config) {
        this.config = config;
    }


    public String generateVerificationCode() {
        return generateVerificationCode(6);
    }

    public String generateVerificationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }

    public void send(String templateId, String phone, String[] templateParams) throws TencentSmsException {
        try {
            String secretId = this.config.getSecretId();
            String secretKey = this.config.getSecretKey();
            String region = this.config.getRegion();
            String smsSdkAppId = this.config.getSmsSdkAppId();
            String signName = this.config.getSignName();
            Credential cred = new Credential(secretId, secretKey);
            SmsClient client = new SmsClient(cred, region);
            SendSmsRequest req = new SendSmsRequest();
            req.setPhoneNumberSet(new String[]{phone});
            req.setSmsSdkAppId(smsSdkAppId);
            req.setSignName(signName);
            req.setTemplateId(templateId);
            req.setTemplateParamSet(templateParams);
            SendSmsResponse resp = client.SendSms(req);
            SendStatus status = resp.getSendStatusSet()[0];
            String statusCode = status.getCode();
            if (!"Ok".equals(statusCode)) {
                throw new TencentSmsException(statusCode, status.getMessage());
            }
        } catch (TencentSmsException e) {
            throw e;
        } catch (Exception e) {
            throw new TencentSmsException(e);
        }
    }

    public TencentSmsClientConfig getConfig() {
        return config;
    }
}
