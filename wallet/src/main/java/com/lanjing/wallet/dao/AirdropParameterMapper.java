package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.AirdropParameter;
import com.lanjing.wallet.model.AirdropParameterExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AirdropParameterMapper {
    long countByExample(AirdropParameterExample example);

    int deleteByExample(AirdropParameterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AirdropParameter record);

    int insertSelective(AirdropParameter record);

    List<AirdropParameter> selectByExample(AirdropParameterExample example);

    AirdropParameter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AirdropParameter record, @Param("example") AirdropParameterExample example);

    int updateByExample(@Param("record") AirdropParameter record, @Param("example") AirdropParameterExample example);

    int updateByPrimaryKeySelective(AirdropParameter record);

    int updateByPrimaryKey(AirdropParameter record);
}