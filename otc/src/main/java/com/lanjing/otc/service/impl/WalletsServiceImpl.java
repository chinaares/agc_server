package com.lanjing.otc.service.impl;

import com.lanjing.otc.dao.WalletsMapper;
import com.lanjing.otc.model.Wallets;
import com.lanjing.otc.model.WalletsExample;
import com.lanjing.otc.model.po.Wallet;
import com.lanjing.otc.service.WalletsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 11:58
 * @version version 1.0.0
 */
@Service
public class WalletsServiceImpl implements WalletsService{

    @Resource
    private WalletsMapper walletsMapper;

    @Override
    public long countByExample(WalletsExample example) {
        return walletsMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(WalletsExample example) {
        return walletsMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return walletsMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Wallets record) {
        return walletsMapper.insert(record);
    }

    @Override
    public int insertSelective(Wallets record) {
        return walletsMapper.insertSelective(record);
    }

    @Override
    public List<Wallets> selectByExample(WalletsExample example) {
        return walletsMapper.selectByExample(example);
    }

    @Override
    public Wallets selectByPrimaryKey(Integer fid) {
        return walletsMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByExampleSelective(Wallets record,WalletsExample example) {
        return walletsMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Wallets record,WalletsExample example) {
        return walletsMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Wallets record) {
        return walletsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Wallets record) {
        return walletsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Wallet> selectByUserId(String userKey) {
        return walletsMapper.selectByUserId(userKey);
    }

    @Override
    public Wallets selectByUserandcoin(String userkey, Integer coinid) {
        return walletsMapper.selectByUserKeyAndCoinId(userkey,coinid);
    }
}
