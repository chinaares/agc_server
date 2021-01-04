package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Income;
import com.lanjing.wallet.model.IncomeExample;
import com.lanjing.wallet.model.po.EmptyRecord;
import com.lanjing.wallet.model.po.RewardBonus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IncomeMapper {
    long countByExample(IncomeExample example);

    int deleteByExample(IncomeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    List<Income> selectByExample(IncomeExample example);

    Income selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByExample(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);

    List<Income> queryBytodayAndUser(Integer userId,Integer type);

    List<Income> queryByUser(Integer userId,Integer type,Integer begin ,Integer size);

    Double sumReward(@Param("type") int type);

    List<Income> selectByUserId(@Param("userKey") String userKey,@Param("type") Integer type);

    List<RewardBonus> selectFinancialReward(@Param("key") String key);

    List<RewardBonus> selectPromotionReward(@Param("key") String key);

    List<EmptyRecord> selectEmptyRecord(@Param("key") String key, @Param("type") Integer type, @Param("count") Integer count, @Param("start") Date start, @Param("end") Date end);
}