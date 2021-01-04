package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Income;
import com.lanjing.wallet.model.po.EmptyRecord;
import com.lanjing.wallet.model.po.RewardBonus;

import java.util.Date;
import java.util.List;

public interface IncomeService {

    /**
     * 获取释放记录
     *
     * @param investId
     * @return
     */
    List<Income> selectByInvestId(Integer investId, Integer type);

    Double reward(int type);

    public List<Income> selectAllOrByPrimaryKeyOrStatusOrUserId(Integer id, Integer type, Integer userId);

    List<RewardBonus> selectFinancialReward(String key);

    List<RewardBonus> selectPromotionReward(String key);

    List<EmptyRecord>  selectEmptyRecord(String key, Integer type, Integer count, Date start, Date end);
}
