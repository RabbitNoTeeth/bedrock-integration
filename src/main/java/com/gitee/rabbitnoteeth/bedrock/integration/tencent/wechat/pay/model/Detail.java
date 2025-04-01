package com.gitee.rabbitnoteeth.bedrock.integration.tencent.wechat.pay.model;

import com.google.gson.annotations.SerializedName;
import com.wechat.pay.java.core.util.StringUtil;

import java.util.List;

public class Detail {

    @SerializedName("cost_price")
    private Integer costPrice;
    @SerializedName("invoice_id")
    private String invoiceId;
    @SerializedName("goods_detail")
    private List<GoodsDetail> goodsDetail;

    public Detail() {
    }

    public Integer getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<GoodsDetail> getGoodsDetail() {
        return this.goodsDetail;
    }

    public void setGoodsDetail(List<GoodsDetail> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Detail {\n");
        sb.append("    costPrice: ").append(StringUtil.toIndentedString(this.costPrice)).append("\n");
        sb.append("    invoiceId: ").append(StringUtil.toIndentedString(this.invoiceId)).append("\n");
        sb.append("    goodsDetail: ").append(StringUtil.toIndentedString(this.goodsDetail)).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
