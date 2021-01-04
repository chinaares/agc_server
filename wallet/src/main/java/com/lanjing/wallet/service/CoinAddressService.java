package com.lanjing.wallet.service;

import com.lanjing.wallet.model.CoinAddressExample;
import com.lanjing.wallet.model.CoinAddress;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-05 16:54
 */
public interface CoinAddressService {


    long countByExample(CoinAddressExample example);

    int deleteByExample(CoinAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoinAddress record);

    int insertSelective(CoinAddress record);

    List<CoinAddress> selectByExample(CoinAddressExample example);

    CoinAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(CoinAddress record, CoinAddressExample example);

    int updateByExample(CoinAddress record, CoinAddressExample example);

    int updateByPrimaryKeySelective(CoinAddress record);

    int updateByPrimaryKey(CoinAddress record);

}


