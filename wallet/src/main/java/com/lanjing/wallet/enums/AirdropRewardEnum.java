package com.lanjing.wallet.enums;

public enum AirdropRewardEnum {
    INPUT(1),//投入
    ABORT(2),//中途退出
    COMPLETE(3);//结束完成
    private Integer num;

    AirdropRewardEnum(Integer num) {
        this.num = num;
    }

    public int getVal() {
        return this.num.intValue();

    }
}
