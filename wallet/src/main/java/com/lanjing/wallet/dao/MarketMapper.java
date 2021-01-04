package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.Market;

import java.util.List;

public interface MarketMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Market record);

    int insertSelective(Market record);

    Market selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Market record);

    int updateByPrimaryKey(Market record);

    List<Market> selectNew();

    List<Market> selectBy(Market record);
}