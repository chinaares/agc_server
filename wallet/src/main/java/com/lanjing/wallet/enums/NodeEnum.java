package com.lanjing.wallet.enums;

public enum NodeEnum {
    SMALL(1, "小节点"),//小节点
    MEDIUM(2, "中节点"),//中节点
    BIG(3, "大节点");//大节点

    int code;
    String msg;

    NodeEnum(int code, String msg) {
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
