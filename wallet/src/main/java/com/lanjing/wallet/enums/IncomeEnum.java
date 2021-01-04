package com.lanjing.wallet.enums;

public enum IncomeEnum {
    RELEASE(1, "理财锁仓释放"),//理财锁仓释放
    PROMOTION(2, "直推分红"),//直推分红
    TEAM(3, "团队分红"),//团队分红
    SHARE(4, "商场购物"),//商场购物
    COMMUNITY(5, "推广奖励"),//推广奖励,用户购物锁仓的释放分红
    SUPER(6, "超级奖励"),//超级奖励
    AIRDROP(7, "首次理财奖励"),//首次理财奖励
    REGISTER(8, "注册成为有效用户"),//注册成为有效用户
    FREED(9, "首次理财奖励释放");//首次理财奖励

    int code;
    String msg;

    IncomeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
