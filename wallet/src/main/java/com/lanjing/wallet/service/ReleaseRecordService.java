package com.lanjing.wallet.service;

import com.lanjing.wallet.model.ReleaseRecord;

public interface ReleaseRecordService {

    int deleteByPrimaryKey(Long id);

    int insert(ReleaseRecord record);

    int insertSelective(ReleaseRecord record);

    ReleaseRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReleaseRecord record);

    int updateByPrimaryKey(ReleaseRecord record);
}
