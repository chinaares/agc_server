package com.lanjing.otc.dao;

import com.lanjing.otc.model.Parameters;
import com.lanjing.otc.model.ParametersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-11 10:46
 * @version version 1.0.0
 */
public interface ParametersMapper {
    long countByExample(ParametersExample example);

    int deleteByExample(ParametersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Parameters record);

    int insertSelective(Parameters record);

    Parameters selectByKey(String keyname);

    List<Parameters> selectByExample(ParametersExample example);

    Parameters selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Parameters record, @Param("example") ParametersExample example);

    int updateByExample(@Param("record") Parameters record, @Param("example") ParametersExample example);

    int updateByPrimaryKeySelective(Parameters record);

    int updateByPrimaryKey(Parameters record);
}