package com.lanjing.goods.enums;

public enum GoodsEnum {
    DISABLE(0, "下架"),
    ENABLE(1, "上架"),
    DELETE(2, "删除");

    int code;
    String msg;

    GoodsEnum(int code, String msg) {
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
