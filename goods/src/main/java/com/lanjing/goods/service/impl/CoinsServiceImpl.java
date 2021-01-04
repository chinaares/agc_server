package com.lanjing.goods.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.lanjing.goods.model.CoinsExample;
import com.lanjing.goods.dao.CoinsMapper;
import com.lanjing.goods.model.Coins;
import com.lanjing.goods.service.CoinsService;
/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 * @version version 1.0.0
 */
@Service
public class CoinsServiceImpl implements CoinsService{

    @Resource
    private CoinsMapper coinsMapper;

    @Override
    public long countByExample(CoinsExample example) {
        return coinsMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CoinsExample example) {
        return coinsMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return coinsMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Coins record) {
        return coinsMapper.insert(record);
    }

    @Override
    public int insertSelective(Coins record) {
        return coinsMapper.insertSelective(record);
    }

    @Override
    public List<Coins> selectByExample(CoinsExample example) {
        return coinsMapper.selectByExample(example);
    }

    @Override
    public Coins selectByPrimaryKey(Integer fid) {
        return coinsMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByExampleSelective(Coins record,CoinsExample example) {
        return coinsMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Coins record,CoinsExample example) {
        return coinsMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Coins record) {
        return coinsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Coins record) {
        return coinsMapper.updateByPrimaryKey(record);
    }

}
