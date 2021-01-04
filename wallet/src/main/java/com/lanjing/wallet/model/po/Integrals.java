package com.lanjing.wallet.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-29 17:16
 */
@Data
public class Integrals {

    /**
     * id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 赠送积分数
     */
    private Integer amount;

    /**
     * 参与时间
     */
    private Date createTime;
}
