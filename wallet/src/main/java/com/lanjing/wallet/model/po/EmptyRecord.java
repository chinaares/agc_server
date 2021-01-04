package com.lanjing.wallet.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-08 17:37
 * @version version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmptyRecord {
    private Integer id;
    private Integer userId;
    private String phone;
    //配额激活 0 未激活，>0激活
    private Integer count;
    //1购物，2配额
    private Integer type;
    //锁仓总量
    private Double amount;
    //锁仓余额
    private Double lockBalance;
    //释放累计
    private Double total;
    //周期
    private Integer cycle;
    //空投时间
    private Date time;
}
