package com.frankie.pay.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Order
 * Description: 主订单
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class Order {
//    订单ID
    private Integer id;
//    订单号
    private String orderNo;
//    支付金额
    private BigDecimal totalMoney;
//    详细订单列表
    private List<OrderDetail> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<OrderDetail> getList() {
        return list;
    }

    public void setList(List<OrderDetail> list) {
        this.list = list;
    }
}
