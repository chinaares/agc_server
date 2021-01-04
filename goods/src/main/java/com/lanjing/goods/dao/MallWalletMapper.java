package com.lanjing.goods.dao;

import com.lanjing.goods.model.MallWallet;
import com.lanjing.goods.model.MallWalletExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 * @version version 1.0.0
 */
public interface MallWalletMapper {
    long countByExample(MallWalletExample example);

    int deleteByExample(MallWalletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallWallet record);

    int insertSelective(MallWallet record);

    List<MallWallet> selectByExample(MallWalletExample example);

    MallWallet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallWallet record, @Param("example") MallWalletExample example);

    int updateByExample(@Param("record") MallWallet record, @Param("example") MallWalletExample example);

    int updateByPrimaryKeySelective(MallWallet record);

    int updateByPrimaryKey(MallWallet record);

    List<MallWallet> findByCode(@Param("code")Long code);

    MallWallet findByCodeAndCoinId(@Param("code")Long code,@Param("coinId")Integer coinId);

	int updateByCodeAndCoinIdAndVersion(@Param("amount") BigDecimal amount, @Param("code")Long code, @Param("coinId")Integer coinId, @Param("version")Integer version);

	int updateAddressByCodeAndCoinId(@Param("updatedAddress")String updatedAddress,@Param("code")Long code,@Param("coinId")Integer coinId);
}