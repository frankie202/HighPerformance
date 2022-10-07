package com.frankie.pay.count;

import com.frankie.pay.bean.OrderDetail;
import com.frankie.pay.bean.PromotionType;
import com.frankie.pay.bean.SupportPromotions;

import java.math.BigDecimal;
import java.util.Map;

/**
 * PromotionFactory
 * Description:
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class PromotionFactory {

    public static BigDecimal getPayMoney(OrderDetail orderDetail) {
        Map<PromotionType, SupportPromotions> supportPromotions = orderDetail.getMerchandise().getSupportPromotions();

        IBaseCount baseCount = new BaseCount();

        if (supportPromotions != null && supportPromotions.size() > 0) {
            for (PromotionType type : supportPromotions.keySet()) {
                baseCount = promotion(supportPromotions.get(type),baseCount);
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }

    private static IBaseCount promotion(SupportPromotions supportPromotions,
                                        IBaseCount baseCount) {
        if (supportPromotions.getPromotionType() == PromotionType.COUPON) {
            baseCount = new CouponDecorator(baseCount);
        } else if (supportPromotions.getPromotionType() == PromotionType.REDPACKET) {
            baseCount = new RedPacketDecorator(baseCount);
        }
        return baseCount;
    }
}
