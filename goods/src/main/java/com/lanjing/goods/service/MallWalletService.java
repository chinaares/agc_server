package com.lanjing.goods.service;

import com.lanjing.goods.model.MallWallet;
import com.lanjing.goods.model.MallWalletExample;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
public interface MallWalletService {


    long countByExample(MallWalletExample example);

    int deleteByExample(MallWalletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallWallet record);

    int insertSelective(MallWallet record);

    List<MallWallet> selectByExample(MallWalletExample example);

    MallWallet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(MallWallet record, MallWalletExample example);

    int updateByExample(MallWallet record, MallWalletExample example);

    int updateByPrimaryKeySelective(MallWallet record);

    int updateByPrimaryKey(MallWallet record);


    MallWallet findByCodeAndCoinId(Long code, Integer coinId);

    int updateByCodeAndCoinIdAndVersion(BigDecimal amount, Long code, Integer coinId, Integer version);

    int withdraw1(Long mallcode, int coin, double amount);
}
