package com.frankie.pay.count;

import com.frankie.pay.bean.OrderDetail;

import java.math.BigDecimal;

/**
 * BaseCountDecorator
 * Description:
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class BaseCountDecorator implements IBaseCount{

    private IBaseCount count;

    public BaseCountDecorator(IBaseCount count) {
        this.count = count;
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal("0");

        if (count != null) {
            payTotalMoney = count.countPayMoney(orderDetail);
        }

        return payTotalMoney;
    }
}
