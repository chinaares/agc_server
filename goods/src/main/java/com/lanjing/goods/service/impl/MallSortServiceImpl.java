package com.lanjing.goods.service.impl;

import com.lanjing.goods.dao.MallSortMapper;
import com.lanjing.goods.enums.MallSortEnum;
import com.lanjing.goods.model.MallSort;
import com.lanjing.goods.model.MallSortExample;
import com.lanjing.goods.service.MallSortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 10:13
 */
@Service
public class MallSortServiceImpl implements MallSortService {

    @Resource
    private MallSortMapper mallSortMapper;

    @Override
    public long countByExample(MallSortExample example) {
        return mallSortMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(MallSortExample example) {
        return mallSortMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mallSortMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MallSort record) {
        return mallSortMapper.insert(record);
    }

    @Override
    public int insertSelective(MallSort record) {
        return mallSortMapper.insertSelective(record);
    }

    @Override
    public List<MallSort> selectByExample(MallSortExample example) {
        return mallSortMapper.selectByExample(example);
    }

    @Override
    public MallSort selectByPrimaryKey(Integer id) {
        return mallSortMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(MallSort record, MallSortExample example) {
        return mallSortMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(MallSort record, MallSortExample example) {
        return mallSortMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(MallSort record) {
        return mallSortMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MallSort record) {
        return mallSortMapper.updateByPrimaryKey(record);
    }

    @Override
    public List selectAll() {
        return mallSortMapper.selectAll();
    }
}


