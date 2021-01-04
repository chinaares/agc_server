package com.lanjing.wallet.service;

import com.lanjing.wallet.model.MappingTrade;

import java.util.List;

public interface MappingTradeService {
    int deleteByPrimaryKey(Integer fid);

    int insert(MappingTrade record);

    int insertSelective(MappingTrade record);

    MappingTrade selectByPrimaryKey(Integer fid);

    List<MappingTrade> selectBy(MappingTrade record,Integer page,Integer size);

    int updateByPrimaryKeySelective(MappingTrade record);

    int updateByPrimaryKey(MappingTrade record);

    List<MappingTrade> selectByUserKey(Integer symbol,String user,Integer begin ,Integer end);
}