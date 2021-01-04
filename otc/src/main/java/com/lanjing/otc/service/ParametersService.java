package com.lanjing.otc.service;

import com.lanjing.otc.model.Parameters;
import com.lanjing.otc.model.ParametersExample;

import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-11 10:46
 * @version version 1.0.0
 */
public interface ParametersService{


    long countByExample(ParametersExample example);

    int deleteByExample(ParametersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Parameters record);

    int insertSelective(Parameters record);

    Parameters selectByKey(String keyname);

    List<Parameters> selectByExample(ParametersExample example);

    Parameters selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Parameters record,ParametersExample example);

    int updateByExample(Parameters record,ParametersExample example);

    int updateByPrimaryKeySelective(Parameters record);

    int updateByPrimaryKey(Parameters record);

}
