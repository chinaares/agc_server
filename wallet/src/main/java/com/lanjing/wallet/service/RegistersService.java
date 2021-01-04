package com.lanjing.wallet.service;


import com.lanjing.wallet.model.Registers;

public interface RegistersService {
    int deleteByPrimaryKey(Integer fid);

    int insert(Registers record);

    int insertSelective(Registers record);

    Registers selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Registers record);

    int updateByPrimaryKey(Registers record);

    Registers selectByTime(String time);
}