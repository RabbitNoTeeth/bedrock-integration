package com.github.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.StringUtil;

public class GoodsDetail {
    @SerializedName("merchant_goods_id")
    private String merchantGoodsId;
    @SerializedName("wechatpay_goods_id")
    private String wechatpayGoodsId;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("quantity")
    private Integer quantity;
    @SerializedName("unit_price")
    private Integer unitPrice;

    public GoodsDetail() {
    }

    public String getMerchantGoodsId() {
        return this.merchantGoodsId;
    }

    public void setMerchantGoodsId(String merchantGoodsId) {
        this.merchantGoodsId = merchantGoodsId;
    }

    public String getWechatpayGoodsId() {
        return this.wechatpayGoodsId;
    }

    public void setWechatpayGoodsId(String wechatpayGoodsId) {
        this.wechatpayGoodsId = wechatpayGoodsId;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GoodsDetail {\n");
        sb.append("    merchantGoodsId: ").append(StringUtil.toIndentedString(this.merchantGoodsId)).append("\n");
        sb.append("    wechatpayGoodsId: ").append(StringUtil.toIndentedString(this.wechatpayGoodsId)).append("\n");
        sb.append("    goodsName: ").append(StringUtil.toIndentedString(this.goodsName)).append("\n");
        sb.append("    quantity: ").append(StringUtil.toIndentedString(this.quantity)).append("\n");
        sb.append("    unitPrice: ").append(StringUtil.toIndentedString(this.unitPrice)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
