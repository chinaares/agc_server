package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Spread;
import com.lanjing.wallet.model.Users;

public interface SpreadService {

    /**
     * 通过用户id查询
     * @param userId
     * @return
     */
    Spread selectByUserId(Integer userId);

    /**
     * 发放奖励
     * @param size
     * @param bonus
     * @param user
     */
    void calculate(int size, double bonus, Users user);
}
