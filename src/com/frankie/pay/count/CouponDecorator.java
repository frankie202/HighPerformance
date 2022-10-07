package com.frankie.pay.count;

import com.frankie.pay.bean.OrderDetail;
import com.frankie.pay.bean.PromotionType;

import java.math.BigDecimal;

/**
 * CouponDecorator
 * Description:
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class CouponDecorator extends BaseCountDecorator{
    public CouponDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        payTotalMoney = super.countPayMoney(orderDetail);

        payTotalMoney = countCouponPayMoney(orderDetail);

        return payTotalMoney;
    }

    public BigDecimal countCouponPayMoney(OrderDetail orderDetail) {
        BigDecimal coupon = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.COUPON).getUserCoupon()
                .getCoupon();

        System.out.println("优惠券金额： " + coupon);
        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(coupon));

        return orderDetail.getPayMoney();
    }
}
