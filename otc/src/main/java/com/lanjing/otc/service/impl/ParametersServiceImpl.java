package com.lanjing.otc.service.impl;

import com.lanjing.otc.dao.ParametersMapper;
import com.lanjing.otc.model.Parameters;
import com.lanjing.otc.model.ParametersExample;
import com.lanjing.otc.service.ParametersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-11 10:46
 * @version version 1.0.0
 */
@Service
public class ParametersServiceImpl implements ParametersService{

    @Resource
    private ParametersMapper parametersMapper;

    @Override
    public long countByExample(ParametersExample example) {
        return parametersMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ParametersExample example) {
        return parametersMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return parametersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Parameters record) {
        return parametersMapper.insert(record);
    }

    @Override
    public int insertSelective(Parameters record) {
        return parametersMapper.insertSelective(record);
    }

    @Override
    public Parameters selectByKey(String keyname) {
        return parametersMapper.selectByKey(keyname);
    }

    @Override
    public List<Parameters> selectByExample(ParametersExample example) {
        return parametersMapper.selectByExample(example);
    }

    @Override
    public Parameters selectByPrimaryKey(Integer id) {
        return parametersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Parameters record,ParametersExample example) {
        return parametersMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Parameters record,ParametersExample example) {
        return parametersMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Parameters record) {
        return parametersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Parameters record) {
        return parametersMapper.updateByPrimaryKey(record);
    }

}
