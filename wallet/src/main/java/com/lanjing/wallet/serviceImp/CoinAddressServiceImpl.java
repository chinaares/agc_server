package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.CoinAddressMapper;
import com.lanjing.wallet.model.CoinAddress;
import com.lanjing.wallet.model.CoinAddressExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.lanjing.wallet.service.CoinAddressService;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-05 16:54
 */
@Service
public class CoinAddressServiceImpl implements CoinAddressService {

    @Resource
    private CoinAddressMapper coinAddressMapper;

    @Override
    public long countByExample(CoinAddressExample example) {
        return coinAddressMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CoinAddressExample example) {
        return coinAddressMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return coinAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CoinAddress record) {
        return coinAddressMapper.insert(record);
    }

    @Override
    public int insertSelective(CoinAddress record) {
        return coinAddressMapper.insertSelective(record);
    }

    @Override
    public List<CoinAddress> selectByExample(CoinAddressExample example) {
        return coinAddressMapper.selectByExample(example);
    }

    @Override
    public CoinAddress selectByPrimaryKey(Integer id) {
        return coinAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(CoinAddress record, CoinAddressExample example) {
        return coinAddressMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(CoinAddress record, CoinAddressExample example) {
        return coinAddressMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(CoinAddress record) {
        return coinAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CoinAddress record) {
        return coinAddressMapper.updateByPrimaryKey(record);
    }

}


