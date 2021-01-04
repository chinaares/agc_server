package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.TradeAirdrop;
import com.lanjing.wallet.model.TradeAirdropExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TradeAirdropMapper {
    long countByExample(TradeAirdropExample example);

    int deleteByExample(TradeAirdropExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeAirdrop record);

    int insertSelective(TradeAirdrop record);

    List<TradeAirdrop> selectByExample(TradeAirdropExample example);

    TradeAirdrop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeAirdrop record, @Param("example") TradeAirdropExample example);

    int updateByExample(@Param("record") TradeAirdrop record, @Param("example") TradeAirdropExample example);

    int updateByPrimaryKeySelective(TradeAirdrop record);

    int updateByPrimaryKey(TradeAirdrop record);

    List<TradeAirdrop> selectByUserKey(String userKey);

    List<TradeAirdrop> selectAll();

    double sumActivation(@Param("userKey") String userKey);

    double sumFreed(@Param("userKey") String userKey);

    double sumConsume(@Param("userKey") String userKey);
}