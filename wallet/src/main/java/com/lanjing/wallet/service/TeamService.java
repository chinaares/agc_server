package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Team;

public interface TeamService {

    /**
     * 通过用户id查询
     * @param userId
     * @return
     */
    Team selectByUserId(Integer userId);

    /**
     * 团队分红
     * @param release
     * @param userKey
     */
    void calculate(double release, String userKey);
}
