package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.CoinLogService;
import com.lanjing.wallet.dao.CoinLogMapper;
import com.lanjing.wallet.model.CoinLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CoinLogService")
public class CoinLogServiceImpl implements CoinLogService {

    @Autowired
    private CoinLogMapper coinLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return coinLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CoinLog record) {
        return coinLogMapper.insert(record);
    }

    @Override
    public int insertSelective(CoinLog record) {
        return coinLogMapper.insertSelective(record);
    }

    @Override
    public CoinLog selectByPrimaryKey(Long id) {
        return coinLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CoinLog record) {
        return coinLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CoinLog record) {
        return coinLogMapper.updateByPrimaryKey(record);
    }
}
