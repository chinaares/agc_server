package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.WelletsuptLog;

import java.util.List;

public interface WelletsuptLogMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(WelletsuptLog record);

    int insertSelective(WelletsuptLog record);

    WelletsuptLog selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(WelletsuptLog record);

    int updateByPrimaryKey(WelletsuptLog record);

    List<WelletsuptLog> selectAllBypage(String likes, String times, Integer begin, Integer end);

    int selectAllCount(String likes, String times);
}