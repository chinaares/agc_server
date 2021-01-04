package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.AirdropReward;
import com.lanjing.wallet.model.AirdropRewardExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AirdropRewardMapper {
    long countByExample(AirdropRewardExample example);

    int deleteByExample(AirdropRewardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AirdropReward record);

    int insertSelective(AirdropReward record);

    List<AirdropReward> selectByExample(AirdropRewardExample example);

    AirdropReward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AirdropReward record, @Param("example") AirdropRewardExample example);

    int updateByExample(@Param("record") AirdropReward record, @Param("example") AirdropRewardExample example);

    int updateByPrimaryKeySelective(AirdropReward record);

    int updateByPrimaryKey(AirdropReward record);

    Double sumTotalRewards(@Param("key") String key);
}