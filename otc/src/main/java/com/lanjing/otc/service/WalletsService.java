package com.lanjing.otc.service;

import com.lanjing.otc.model.Wallets;
import com.lanjing.otc.model.WalletsExample;
import com.lanjing.otc.model.po.Wallet;

import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 11:58
 * @version version 1.0.0
 */
public interface WalletsService{


    long countByExample(WalletsExample example);

    int deleteByExample(WalletsExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Wallets record);

    int insertSelective(Wallets record);

    List<Wallets> selectByExample(WalletsExample example);

    Wallets selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(Wallets record,WalletsExample example);

    int updateByExample(Wallets record,WalletsExample example);

    int updateByPrimaryKeySelective(Wallets record);

    int updateByPrimaryKey(Wallets record);

    List<Wallet> selectByUserId(String userKey);

    Wallets selectByUserandcoin(String userkey,Integer coinid);
}
