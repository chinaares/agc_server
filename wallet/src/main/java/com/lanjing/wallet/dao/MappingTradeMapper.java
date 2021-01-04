package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.MappingTrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MappingTradeMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(MappingTrade record);

    int insertSelective(MappingTrade record);

    MappingTrade selectByPrimaryKey(Integer fid);

    List<MappingTrade> selectBy(@Param("record") MappingTrade record, Integer begin , Integer end);

    int updateByPrimaryKeySelective(MappingTrade record);

    int updateByPrimaryKey(MappingTrade record);

    List<MappingTrade> selectByUserKey(Integer symbol,String user,Integer begin ,Integer end);


    List<MappingTrade> selectByType(Integer type);

    List<Map> selectAll(String likes,Integer type,Integer state,Integer Isreal,String createtime,String updatetime,Integer begin,Integer end);

    int selectAllCount(String likes,Integer type,Integer state,Integer Isreal,String createtime,String updatetime);


    List<MappingTrade> selectByType1(Integer type);
}