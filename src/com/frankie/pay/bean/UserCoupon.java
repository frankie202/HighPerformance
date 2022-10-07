package com.frankie.pay.bean;

import java.math.BigDecimal;

/**
 * UserCoupon
 * Description:
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class UserCoupon {
//    优惠券id
    private Integer id;
//    领取优惠券用户id
    private Integer userId;
//    商品sku
    private String sku;
//    优惠金额
    private BigDecimal coupon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }
}
