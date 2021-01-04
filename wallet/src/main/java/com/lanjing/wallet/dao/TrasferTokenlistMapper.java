package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.Trasferlist;

import java.util.List;

public interface TrasferTokenlistMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Trasferlist record);

    int insertSelective(Trasferlist record);

    Trasferlist selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Trasferlist record);

    int updateByPrimaryKeyWithBLOBs(Trasferlist record);

    int updateByPrimaryKey(Trasferlist record);

    int selectBymaxId(Integer coin);

    List<Trasferlist> selectBystate(Integer state);

    List<Trasferlist> selectByTransferState(Integer state);

    int updateByfId(Integer fid);

    int updateByfId2(Integer fid);

    int updateByfId3(Integer fid);

}