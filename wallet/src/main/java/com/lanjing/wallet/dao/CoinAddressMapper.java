package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.CoinAddress;
import com.lanjing.wallet.model.CoinAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-06 10:29
 */
public interface CoinAddressMapper {
    long countByExample(CoinAddressExample example);

    int deleteByExample(CoinAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoinAddress record);

    int insertSelective(CoinAddress record);

    List<CoinAddress> selectByExample(CoinAddressExample example);

    CoinAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoinAddress record, @Param("example") CoinAddressExample example);

    int updateByExample(@Param("record") CoinAddress record, @Param("example") CoinAddressExample example);

    int updateByPrimaryKeySelective(CoinAddress record);

    int updateByPrimaryKey(CoinAddress record);

    CoinAddress findByUserIdAndAddress(@Param("userId") Integer userId, @Param("address") String address);

    Integer countByIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);
}