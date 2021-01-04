package com.lanjing.goods.enums;

public enum SwitchEnum {
    ON(1, "正常"),
    OFF(0, "删除");

    int code;
    String msg;

    SwitchEnum(int code, String msg) {
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
