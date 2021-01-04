package com.lanjing.wallet.service;

import com.lanjing.wallet.model.TradeAirdrop;
import com.lanjing.wallet.model.Users;

import java.util.List;

public interface TradeAirdropService {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeAirdrop record);

    int insertSelective(TradeAirdrop record);

    TradeAirdrop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeAirdrop record);

    int updateByPrimaryKey(TradeAirdrop record);

    List<TradeAirdrop> selectByUserKey(String userKey);

    List<TradeAirdrop> selectAll();

    int dailyRelease(double release, TradeAirdrop tradeAirdrop, boolean status);

    double sumActivation(String userKey);

    double sumFreed(String userKey);

    double sumConsume(String userKey);

    int addActivation(Users user, Double amount);
}