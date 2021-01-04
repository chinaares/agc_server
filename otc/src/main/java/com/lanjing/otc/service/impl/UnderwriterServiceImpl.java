package com.lanjing.otc.service.impl;

import com.lanjing.otc.dao.UnderwriterMapper;
import com.lanjing.otc.model.Underwriter;
import com.lanjing.otc.model.UnderwriterExample;
import com.lanjing.otc.service.UnderwriterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 16:22
 */
@Service
public class UnderwriterServiceImpl implements UnderwriterService {

    @Resource
    private UnderwriterMapper underwriterMapper;

    @Override
    public long countByExample(UnderwriterExample example) {
        return underwriterMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UnderwriterExample example) {
        return underwriterMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return underwriterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Underwriter record) {
        return underwriterMapper.insert(record);
    }

    @Override
    public int insertSelective(Underwriter record) {
        return underwriterMapper.insertSelective(record);
    }

    @Override
    public List<Underwriter> selectByExample(UnderwriterExample example) {
        return underwriterMapper.selectByExample(example);
    }

    @Override
    public Underwriter selectByPrimaryKey(Integer id) {
        return underwriterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Underwriter record, UnderwriterExample example) {
        return underwriterMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Underwriter record, UnderwriterExample example) {
        return underwriterMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Underwriter record) {
        return underwriterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Underwriter record) {
        return underwriterMapper.updateByPrimaryKey(record);
    }

    @Override
    public Underwriter findByUserId(Integer userId) {
        return underwriterMapper.findByUserId(userId);
    }

    @Override
    public List<Underwriter> findAllByStatusAndPhone(Integer status, String key) {
        return underwriterMapper.findAllByStatusAndPhone(status,key);
    }
}


