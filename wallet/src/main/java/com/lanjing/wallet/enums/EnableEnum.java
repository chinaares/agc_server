package com.lanjing.wallet.enums;

public enum EnableEnum {
    OFF(0),//删除
    ON(1);//启用
    private Integer num;

    EnableEnum(Integer num) {
        this.num = num;
    }

    public Integer getVal() {
        return this.num.intValue();

    }
}
