package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.Registers;
import org.apache.ibatis.annotations.Param;

public interface RegistersMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Registers record);

    int insertSelective(Registers record);

    Registers selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Registers record);

    int updateByPrimaryKey(Registers record);

    Registers selectByTime(@Param("time") String time);
}