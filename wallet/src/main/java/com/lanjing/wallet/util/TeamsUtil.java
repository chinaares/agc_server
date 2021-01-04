package com.lanjing.wallet.util;

public class TeamsUtil {
    /** 自身持币锁仓数量 */
    public static double claim1(int level) {
        if (level == 1)
            return 10000;
        if (level == 2)
            return 50000;
        if (level == 3)
            return 200000;
        return 0;
    }

    /** 直推1万枚或以上的用户 */
    public static double claim2(int level) {
        if (level == 1)
            return 10;
        if (level == 2)
            return 10;
        if (level == 3)
            return 20;
        return 0;
    }

    /** 锁仓数量总/节点数 */
    public static double claim3(int level) {
        if (level == 1)
            return 410000;
        if (level == 2)
            return 3;
        if (level == 3)
            return 3;
        return 0;
    }
}
