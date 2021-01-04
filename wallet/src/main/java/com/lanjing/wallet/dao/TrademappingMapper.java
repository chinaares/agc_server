package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Trademapping;

public interface TrademappingMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Trademapping record);

    int insertSelective(Trademapping record);

    Trademapping selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Trademapping record);

    int updateByPrimaryKey(Trademapping record);
}