package com.lanjing.goods.enums;

public enum AddressEnum {
    DELETE(0, "删除"),
    DISABLE(1, "正常"),
    ENABLE(2, "默认");

    int code;
    String msg;

    AddressEnum(int code, String msg) {
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
