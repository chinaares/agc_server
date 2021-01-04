package com.lanjing.wallet.enums;

public enum InvestEnum {
    INPUT(1),//投入
    COMPLETE(2),//完成
    REFUSE(3),//提前出仓审核不通过
    PASS(4),//提前出仓审核通过
    ABORT(5);//提前出仓
    private Integer num;

    InvestEnum(Integer num) {
        this.num = num;
    }

    public int getVal() {
        return this.num.intValue();

    }
}
