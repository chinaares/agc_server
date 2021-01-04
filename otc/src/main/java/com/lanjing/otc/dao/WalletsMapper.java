package com.lanjing.otc.dao;

import com.lanjing.otc.model.Wallets;
import com.lanjing.otc.model.WalletsExample;
import com.lanjing.otc.model.po.Wallet;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 11:58
 * @version version 1.0.0
 */
public interface WalletsMapper {
    long countByExample(WalletsExample example);

    int deleteByExample(WalletsExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Wallets record);

    int insertSelective(Wallets record);

    Wallets selectByUserandcoin(String userkey,Integer coinid);

    List<Wallets> selectByExample(WalletsExample example);

    Wallets selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Wallets record, @Param("example") WalletsExample example);

    int updateByExample(@Param("record") Wallets record, @Param("example") WalletsExample example);

    int updateByPrimaryKeySelective(Wallets record);

    int updateByPrimaryKey(Wallets record);

    Wallets selectByUserKeyAndCoinId(@Param("userKey") String userKey,@Param("coinId") Integer coinId);

    int updateByVersionAndFreeze(@Param("num") BigDecimal num,@Param("id") Integer id,@Param("version") Integer version);

    int updateByVersionAndCoinNum(@Param("num") BigDecimal num,@Param("id") Integer id,@Param("version") Integer version);

    List<Wallet> selectByUserId(@Param("userKey") String userKey);
}