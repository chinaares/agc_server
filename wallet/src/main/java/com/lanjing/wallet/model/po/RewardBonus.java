package com.lanjing.wallet.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-08 16:57
 * @version version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardBonus {
    private Integer id;
    private Integer userId;
    private String phone;
    private Integer type;
    private double amount;
    private Date time;
}
