package com.lanjing.goods.dao;

import com.lanjing.goods.model.Parameters;
import com.lanjing.goods.model.ParametersWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParametersMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(ParametersWithBLOBs record);

    int insertSelective(ParametersWithBLOBs record);

    ParametersWithBLOBs selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(ParametersWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ParametersWithBLOBs record);

    ParametersWithBLOBs selectByKey(String keyname);

    int updateByPrimaryKey(Parameters record);

    List<Parameters> selectByType(@Param("type") Integer type);

    int updateByKeyName(@Param("bannerKey") String bannerKey, @Param("filePath") String filePath);
}