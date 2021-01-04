package com.lanjing.wallet.enums;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-17 11:17
 */
public enum AvailEnum {
    BTC(1, "BTC释放"),//BTC
    YYB(2, "YYB释放");//YYB

    int code;
    String msg;

    AvailEnum(int code, String msg) {
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
