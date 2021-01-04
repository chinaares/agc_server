package com.lanjing.wallet.model.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-14 10:29
 * @version version 1.0.0
 */
@Data
public class FinancialRecord {
    private Integer id;
    /**
     * 项目名称
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 1待释放，2释放完成，-1 删除
     */
    private Integer status;

    /**
     * 已释放天数
     */
    private Integer days;

    /**
     * 总释放天数
     */
    private Integer cycle;

    /**
     * 理财金额
     */
    private BigDecimal amount;

    /**
     * 冻结BTC金额
     */
    private BigDecimal freezeBtc;

    /**
     * 冻结YYB金额
     */
    private BigDecimal freezeYyb;

    /**
     * 自由BTC金额
     */
    private BigDecimal freeBtc;

    /**
     * 自由YYB金额
     */
    private BigDecimal freeYyb;

    /**
     * 每日释放BTC
     */
    private BigDecimal freedBtc;

    /**
     * 每日释放YYB
     */
    private BigDecimal freedYyb;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
