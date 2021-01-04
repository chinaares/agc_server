package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Parameters;
import com.lanjing.wallet.model.ParametersWithBLOBs;

import java.util.List;

public interface ParametersService {
    int deleteByPrimaryKey(Integer fid);

    int insert(ParametersWithBLOBs record);

    int insertSelective(ParametersWithBLOBs record);

    ParametersWithBLOBs selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(ParametersWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ParametersWithBLOBs record);

    ParametersWithBLOBs selectByKey(String keyname);

    ParametersWithBLOBs findByKey(String key);

    int updateByPrimaryKey(Parameters record);

    List<Parameters> selectByType(Integer type);

    int updateByKeyName(String bannerKey, String filePath);
}
