package com.lanjing.otc.model.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.BigDecimalUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-16 10:53
 * @version version 1.0.0
 */
@Data
public class AdminAds {

    //id
    private Integer id;

    //手机号码
    private String phone;

    //发布数量
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal num;

    // 剩余数量
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal numBalance;

    //币种id
    private Integer coinId;

    //币种name
    private String coin;

    //状态1 上架,0 下架
    private Integer status;

    //审核状态 0 待审核，-1 审核未通过，1 审核通过 ，2冻结
    private Integer reviewStatus;

    //类型 1 买入，2卖出
    private Integer type;

    //发布时间
    private Date createTime;
}
