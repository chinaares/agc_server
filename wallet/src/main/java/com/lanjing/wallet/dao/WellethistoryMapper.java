package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Wellethistory;

public interface WellethistoryMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Wellethistory record);

    int insertSelective(Wellethistory record);

    Wellethistory selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Wellethistory record);

    int updateByPrimaryKey(Wellethistory record);
}