package com.lanjing.wallet.service;

import com.lanjing.wallet.model.AirdropReward;

import java.util.List;

public interface AirdropRewardService {

    /**
     * 通过id、状态、用户id、类型查询
     *
     * @param id
     * @param status
     * @param userId
     * @param type
     * @return
     */
    List<AirdropReward> selectAllOrByPrimaryKeyOrStatusOrUserIdOrType(Integer id, Integer status, Integer userId, Integer type);

    /**
     * EXC奖励释放
     * @param todayRelease
     * @param airdropReward
     * @param status
     */
    void rewardRelease(double todayRelease, AirdropReward airdropReward, boolean status);

    /**
     * 获取EXC总奖励数
     * @return
     */
    Double sumTotalRewards(String key);
}
