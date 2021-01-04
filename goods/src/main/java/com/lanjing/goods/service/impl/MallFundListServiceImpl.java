package com.lanjing.goods.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lanjing.goods.model.MallFundList;
import com.lanjing.goods.model.MallFundListExample;
import com.lanjing.goods.dao.MallFundListMapper;
import java.util.List;
import com.lanjing.goods.service.MallFundListService;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
@Service
public class MallFundListServiceImpl implements MallFundListService {

    @Resource
    private MallFundListMapper mallFundListMapper;

    @Override
    public long countByExample(MallFundListExample example) {
        return mallFundListMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(MallFundListExample example) {
        return mallFundListMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mallFundListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MallFundList record) {
        return mallFundListMapper.insert(record);
    }

    @Override
    public int insertSelective(MallFundList record) {
        return mallFundListMapper.insertSelective(record);
    }

    @Override
    public List<MallFundList> selectByExample(MallFundListExample example) {
        return mallFundListMapper.selectByExample(example);
    }

    @Override
    public MallFundList selectByPrimaryKey(Integer id) {
        return mallFundListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(MallFundList record, MallFundListExample example) {
        return mallFundListMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(MallFundList record, MallFundListExample example) {
        return mallFundListMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(MallFundList record) {
        return mallFundListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MallFundList record) {
        return mallFundListMapper.updateByPrimaryKey(record);
    }

}

