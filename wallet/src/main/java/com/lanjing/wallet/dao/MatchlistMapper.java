package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.Matchlist;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MatchlistMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Matchlist record);

    int insertSelective(Matchlist record);

    Matchlist selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Matchlist record);

    int updateByPrimaryKey(Matchlist record);

    List<Matchlist> selectByTimeOrId(Integer fid, Date createtime,Date beginTime,int begin,int end);

    int selectByTimeOrIdCount(Integer fid, Date createtime,Date beginTime);

    List<Map> selectByNostart();

    int updatestate(Integer fid);

    int updatenum(Integer fid,double restnum);

}