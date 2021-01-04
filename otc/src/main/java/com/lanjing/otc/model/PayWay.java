package com.lanjing.otc.model;

import lombok.Data;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 16:08
 */
@Data
public class PayWay {
    private Integer fid;

    /**
     * 用户Id
     */
    private Integer userid;

    /**
     * 账号
     */
    private String accounts;

    /**
     * 账号类型    1，支付宝    2，微信    3，银行卡
     */
    private Integer type;

    /**
     * 姓名
     */
    private String username;

    /**
     * 开户银行
     */
    private String bankaccount;

    /**
     * 开户支行
     */
    private String accountOpeningBranch;

    /**
     * 二维码图片
     */
    private String qrCode;
}