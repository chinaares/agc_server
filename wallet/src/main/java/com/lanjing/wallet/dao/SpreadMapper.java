package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Spread;
import com.lanjing.wallet.model.SpreadExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SpreadMapper {
    long countByExample(SpreadExample example);

    int deleteByExample(SpreadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Spread record);

    int insertSelective(Spread record);

    List<Spread> selectByExample(SpreadExample example);

    Spread selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Spread record, @Param("example") SpreadExample example);

    int updateByExample(@Param("record") Spread record, @Param("example") SpreadExample example);

    int updateByPrimaryKeySelective(Spread record);

    int updateByPrimaryKey(Spread record);

    Spread selectByUserId(Integer userId);
}