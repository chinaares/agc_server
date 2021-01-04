package com.lanjing.otc.model.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.BigDecimalUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-14 14:20
 * @version version 1.0.0
 */
@Data
public class Wallet {
    private Integer id;

    private Integer coinId;

    private String coinName;

    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal coinNum;
}
