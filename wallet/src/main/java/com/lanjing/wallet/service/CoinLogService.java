package com.lanjing.wallet.service;

import com.lanjing.wallet.model.CoinLog;

public interface CoinLogService {
    int deleteByPrimaryKey(Long id);

    int insert(CoinLog record);

    int insertSelective(CoinLog record);

    CoinLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoinLog record);

    int updateByPrimaryKey(CoinLog record);
}
