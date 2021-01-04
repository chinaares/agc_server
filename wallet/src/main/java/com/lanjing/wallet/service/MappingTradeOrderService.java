package com.lanjing.wallet.service;

import com.lanjing.wallet.model.MappingTradeOrder;

import java.util.List;

public interface MappingTradeOrderService {

    int deleteByPrimaryKey(Integer fid);

    int insert(MappingTradeOrder record);

    int insertSelective(MappingTradeOrder record);

    MappingTradeOrder selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(MappingTradeOrder record);

    int updateByPrimaryKey(MappingTradeOrder record);

    List<MappingTradeOrder> selectByUserKey(String user,Integer page ,Integer size);

    List<MappingTradeOrder> selectBy(MappingTradeOrder record,Integer page,Integer size);
}
