package com.frankie.pay.count;

import com.frankie.pay.bean.OrderDetail;

import java.math.BigDecimal;

/**
 * BaseCount
 * Description:
 *  支付基本类
 * @Author Frankie
 * @Date 2022/10/5
 */
public class BaseCount implements IBaseCount{
    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
         orderDetail.setPayMoney(
                orderDetail.getMerchandise().getPrice()
        );

        System.out.println("商品的原单价： " + orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }
}
