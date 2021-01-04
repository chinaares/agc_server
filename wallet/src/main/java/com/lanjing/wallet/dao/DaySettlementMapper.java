package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.DaySettlement;

import java.util.List;
import java.util.Map;

public interface DaySettlementMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(DaySettlement record);

    int insertSelective(DaySettlement record);

    DaySettlement selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(DaySettlement record);

    int updateByPrimaryKeyWithBLOBs(DaySettlement record);

    int updateByPrimaryKey(DaySettlement record);

    Map selectByLevCode(Integer lev, String cover_code);

    List<DaySettlement> selectByLevCode1(Integer lev, String cover_code);
}