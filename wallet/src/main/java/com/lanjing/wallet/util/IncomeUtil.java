package com.lanjing.wallet.util;

/**
 * RELEASE(1),//理财锁仓释放
 * PROMOTION(2),//推广奖励
 * AIRDROP(3),//理财奖励
 * SHARE(4),//分享奖励
 * COMMUNITY(5),//社区奖励
 * SUPER(6),//超级奖励
 * TEAM(7);//团队奖励
 *
 * 1 首次理财奖励，2 推广奖励，3 团队奖励, 4 用户注册(有效用户)
 */
public class IncomeUtil {

    public static Integer getType(int type) {
        switch (type) {
            case 1:
                return 9;
            case 2:
                return 2;
            case 3:
                return 7;
            case 4:
                return 8;
            default:
                return -1;
        }

    }

    public static String getMsg(int type) {
        switch (type) {
            case 1:
                return "理财奖励释放";
            case 2:
                return "推广奖励";
            case 3:
                return "团队奖励";
            case 4:
                return "注册成为有效用户";
            default:
                return String.valueOf(type);
        }
    }
}
