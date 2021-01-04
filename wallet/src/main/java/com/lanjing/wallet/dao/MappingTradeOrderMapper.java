package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.MappingTradeOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MappingTradeOrderMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(MappingTradeOrder record);

    int insertSelective(MappingTradeOrder record);

    MappingTradeOrder selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(MappingTradeOrder record);

    int updateByPrimaryKey(MappingTradeOrder record);

    //List<MappingTradeOrder> selectBy(@Param("record") MappingTradeOrder record);

    List<MappingTradeOrder> selectBy(@Param("record") MappingTradeOrder record,Integer begin,Integer end);

    List<MappingTradeOrder> selectByUserKey(Integer symbol,String user,Integer begin ,Integer end);
}