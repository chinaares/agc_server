package com.lanjing.wallet.util;

public class SpreadUtil {
    public static double get(int num) {
        if (num >= 200)
            return 20000;
        if (num >= 100)
            return 8000;
        if (num >= 50)
            return  5000;
        if (num >= 20)
            return  1200;
        if (num >= 10)
            return  600;
        return 0;
    }
}