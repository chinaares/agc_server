package com.lanjing.wallet.util;

public class TradeAirdropUtil {
    public static String getMsg(int code){
        switch (code){
            case 1 : return "购物赠送释放";
            case 2 : return "配额激活释放";
            case 3 : return "后台添加配额激活释放";
            default:return "未知记录释放";
        }
    }

    public static int getCode(int code){
        switch (code){
            case 1 :
            case 2 :
            case 3 : return 4;
            default:return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(TradeAirdropUtil.getCode(1));
    }
}