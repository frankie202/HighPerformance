package com.frankie.pay.count;

import com.frankie.pay.bean.OrderDetail;
import com.frankie.pay.bean.PromotionType;

import java.math.BigDecimal;

/**
 * RedPacketDecorator
 * Description:
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class RedPacketDecorator extends BaseCountDecorator{
    public RedPacketDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        payTotalMoney = super.countPayMoney(orderDetail);

        payTotalMoney = countRedPacketPayMoney(orderDetail);

        return payTotalMoney;
    }

    public BigDecimal countRedPacketPayMoney(OrderDetail orderDetail) {
        BigDecimal redPacket = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.REDPACKET).getUserRedPacket().getRedPacket();


        System.out.println("红包金额： " + redPacket);
        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(redPacket));

        return orderDetail.getPayMoney();
    }
}
