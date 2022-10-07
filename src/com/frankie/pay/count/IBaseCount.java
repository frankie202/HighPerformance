package com.frankie.pay.count;

import com.frankie.pay.bean.OrderDetail;

import java.math.BigDecimal;

/**
 * IBaseCount
 * Description:
 *  计算支付金额接口类
 * @Author Frankie
 * @Date 2022/10/5
 */
public interface IBaseCount {

    BigDecimal countPayMoney(OrderDetail orderDetail);
}
