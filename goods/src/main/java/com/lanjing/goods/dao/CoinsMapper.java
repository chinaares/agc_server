package com.lanjing.goods.dao;

import com.lanjing.goods.model.Coins;
import com.lanjing.goods.model.CoinsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 * @version version 1.0.0
 */
public interface CoinsMapper {
    long countByExample(CoinsExample example);

    int deleteByExample(CoinsExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Coins record);

    int insertSelective(Coins record);

    List<Coins> selectByExample(CoinsExample example);

    Coins selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Coins record, @Param("example") CoinsExample example);

    int updateByExample(@Param("record") Coins record, @Param("example") CoinsExample example);

    int updateByPrimaryKeySelective(Coins record);

    int updateByPrimaryKey(Coins record);

    List<Coins> findAllByFid(@Param("fid")Integer fid);
}