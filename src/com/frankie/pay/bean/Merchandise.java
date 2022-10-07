package com.frankie.pay.bean;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Merchandise
 * Description:
 *      商品详情
 * @Author Frankie
 * @Date 2022/10/5
 */
public class Merchandise {
//    商品sku
    private String sku;
//    商品名称
    private String name;
//    商品单价
    private BigDecimal price;
//    支付促销类型
    private Map<PromotionType,SupportPromotions> supportPromotions;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<PromotionType, SupportPromotions> getSupportPromotions() {
        return supportPromotions;
    }

    public void setSupportPromotions(Map<PromotionType, SupportPromotions> supportPromotions) {
        this.supportPromotions = supportPromotions;
    }
}
