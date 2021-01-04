package com.lanjing.goods.enums;

public enum MallSortEnum {
    ENABLE(1, "使用"),
    DISABLE(2, "禁用"),
    DEL(-1, "删除");

    int code;
    String msg;

    MallSortEnum(int code, String msg) {
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
