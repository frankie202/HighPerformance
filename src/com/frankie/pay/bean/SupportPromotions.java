package com.frankie.pay.bean;

/**
 * SupportPromotions
 * Description:
 *
 * @Author Frankie
 * @Date 2022/10/5
 */
public class SupportPromotions implements Cloneable{

    private Integer id;

    private PromotionType promotionType;

    private Integer priority;

    private UserCoupon userCoupon;

    private UserRedPacket userRedPacket;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public UserCoupon getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(UserCoupon userCoupon) {
        this.userCoupon = userCoupon;
    }

    public UserRedPacket getUserRedPacket() {
        return userRedPacket;
    }

    public void setUserRedPacket(UserRedPacket userRedPacket) {
        this.userRedPacket = userRedPacket;
    }

    @Override
    protected Object clone() {
        SupportPromotions supportPromotions = null;

        try {
            supportPromotions = (SupportPromotions) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return supportPromotions;

    }
}
