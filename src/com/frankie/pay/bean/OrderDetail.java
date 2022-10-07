package com.frankie.pay.bean;

import java.math.BigDecimal;

/**
 * OrderDetail
 * Description:
 *      详细订单
 * @Author Frankie
 * @Date 2022/10/5
 */
public class OrderDetail {
//    详细订单
    private Integer id;
//    主订单号
    private Integer orderId;
//    商品详情
    private Merchandise merchandise;
//    支付单价
    private BigDecimal payMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }
}
