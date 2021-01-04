package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Coins;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface CoinsMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Coins record);

    int insertSelective(Coins record);

    Coins selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Coins record);

    int updateByPrimaryKey(Coins record);

    List<Coins> selectAll();

    Double selectRate(@Param("coinName") String coinName);

    Coins findByCoinname(@Param("coinname")String coinname);

    @Select("select cast((select price from coins where fId=3) * (select price from coins where fId=6) as decimal(16,8))")
    BigDecimal findPrice();
}