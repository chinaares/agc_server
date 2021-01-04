package com.lanjing.wallet.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserInputEntity {
    private String name;//用户名
    private String phone;//手机号
    private String createTime;//注册时间
    private BigDecimal agcNumber;//AGC资产
    private BigDecimal tbNumber;//TB资产
}
