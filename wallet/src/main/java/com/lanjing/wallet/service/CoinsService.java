package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Coins;

import java.math.BigDecimal;
import java.util.List;

public interface CoinsService {

    int deleteByPrimaryKey(Integer fid);

    int insert(Coins record);

    int insertSelective(Coins record);

    Coins selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Coins record);

    int updateByPrimaryKey(Coins record);

    List<Coins> selectAll();

    Double selectRate(String coinName);

    BigDecimal findPrice();
}
