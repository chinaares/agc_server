package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.ReleaseRecord;

public interface ReleaseRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReleaseRecord record);

    int insertSelective(ReleaseRecord record);

    ReleaseRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReleaseRecord record);

    int updateByPrimaryKey(ReleaseRecord record);
}