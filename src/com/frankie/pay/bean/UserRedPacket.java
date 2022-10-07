package com.frankie.pay.bean;

import java.math.BigDecimal;

/**
 * UserRedPacket
 * Description:
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class UserRedPacket {
//    红包id
    private Integer id;
    //    领取红包用户id
    private Integer userId;
    //    商品sku
    private String sku;
//    红包金额
    private BigDecimal redPacket;

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

    public BigDecimal getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(BigDecimal redPacket) {
        this.redPacket = redPacket;
    }
}
