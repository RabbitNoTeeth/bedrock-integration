package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.util;

import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.exception.WechatGzhException;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.message.WechatGzhMessage;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.response.WechatGzhResponse;
import com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.gzh.model.response.WechatGzhTextResponse;
import com.gitee.rabbitnoteeth.bedrock.util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WechatGzhParser {

    private WechatGzhParser() {
    }

    public static WechatGzhMessage decodeMessage(String data) throws WechatGzhException {
        try {
            Document document = XmlUtils.parse(data);
            Element root = document.getDocumentElement();
            WechatGzhMessage res = new WechatGzhMessage();
            NodeList toUserNameTags = root.getElementsByTagName("ToUserName");
            if (toUserNameTags.getLength() > 0) {
                res.setToUserName(toUserNameTags.item(0).getTextContent());
            }
            NodeList fromUserNameTags = root.getElementsByTagName("FromUserName");
            if (fromUserNameTags.getLength() > 0) {
                res.setFromUserName(fromUserNameTags.item(0).getTextContent());
            }
            NodeList createTimeTags = root.getElementsByTagName("CreateTime");
            if (createTimeTags.getLength() > 0) {
                res.setCreateTime(createTimeTags.item(0).getTextContent());
            }
            NodeList msgTypeTags = root.getElementsByTagName("MsgType");
            if (msgTypeTags.getLength() > 0) {
                res.setMsgType(msgTypeTags.item(0).getTextContent());
            }
            NodeList eventTags = root.getElementsByTagName("Event");
            if (eventTags.getLength() > 0) {
                res.setEvent(eventTags.item(0).getTextContent());
            }
            NodeList eventKeyTags = root.getElementsByTagName("EventKey");
            if (eventKeyTags.getLength() > 0) {
                res.setEventKey(eventKeyTags.item(0).getTextContent());
            }
            NodeList ticketTags = root.getElementsByTagName("Ticket");
            if (ticketTags.getLength() > 0) {
                res.setTicket(ticketTags.item(0).getTextContent());
            }
            return res;
        } catch (Exception e) {
            throw new WechatGzhException(e);
        }
    }

    public static String encodeResponse(WechatGzhResponse response) throws WechatGzhException {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<xml>");
            sb.append("<ToUserName><![CDATA[").append(response.getToUserName()).append("]]></ToUserName>");
            sb.append("<FromUserName><![CDATA[").append(response.getFromUserName()).append("]]></FromUserName>");
            sb.append("<CreateTime>").append(response.getCreateTime()).append("</CreateTime>");
            if (response instanceof WechatGzhTextResponse) {
                WechatGzhTextResponse textResponse = (WechatGzhTextResponse)response;
                sb.append("<MsgType><![CDATA[").append(textResponse.getMsgType()).append("]]></MsgType>");
                sb.append("<Content><![CDATA[").append(textResponse.getContent()).append("]]></Content>");
            }
            sb.append("</xml>");
            return sb.toString();
        } catch (Exception e) {
            throw new WechatGzhException(e);
        }
    }

}
