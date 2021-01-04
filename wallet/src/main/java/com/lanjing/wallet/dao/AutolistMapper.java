package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.Autolist;

import java.util.List;
import java.util.Map;

public interface AutolistMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Autolist record);

    int insertSelective(Autolist record);

    Autolist selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Autolist record);

    int updateByPrimaryKey(Autolist record);

    List<Autolist> selectAll(Integer begin , Integer end);

    int selectAllCount();

    List<Map> selectByNostart();

    int updatestate(Integer fid);

    int updatestate2(Integer fid);

}