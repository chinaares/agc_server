package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Btctrasferlist;
import com.lanjing.wallet.model.Trasferlist;

import java.util.List;

public interface BtctrasferlistMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Btctrasferlist record);

    int insertSelective(Btctrasferlist record);

    Btctrasferlist selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Btctrasferlist record);

    int updateByPrimaryKey(Btctrasferlist record);

    int selectBymaxId();

    List<Btctrasferlist> selectBystate(Integer state);

    int updateByfId(Integer fid);

    int updateByfId3(Integer fid);
}