package com.lanjing.wallet.enums;

public enum RewardEnum {
    FIRST(1),//1 首次理财奖励
    PROMOTION(2),//2 推广奖励
    TEAM(3),//3 团队奖励
    REGISTER(4);//4 注册成为有效用户
    private Integer num;

    RewardEnum(Integer num) {
        this.num = num;
    }

    public int getVal() {
        return this.num.intValue();

    }
}
