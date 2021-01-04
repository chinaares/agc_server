package com.lanjing.otc.enums;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 14:57
 */
public enum ReviewEnum {
    UN_REVIEWED(0, "未审核"),
    PASS(1, "通过"),
    FAIL(-1, "不通过");

    int code;
    String msg;

    ReviewEnum(int code, String msg) {
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
