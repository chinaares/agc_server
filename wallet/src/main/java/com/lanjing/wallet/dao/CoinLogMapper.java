package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.CoinLog;

import java.util.List;
import java.util.Map;

public interface CoinLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoinLog record);

    int insertSelective(CoinLog record);

    CoinLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoinLog record);

    int updateByPrimaryKey(CoinLog record);

    List<Map> selectByUserpage(Integer coinId,String userkey,Integer begin,Integer end);

    List<Map> selectByUserpage1(String userkey,Integer begin,Integer end);

}