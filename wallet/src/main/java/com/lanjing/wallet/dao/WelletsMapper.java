package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Wellets;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface WelletsMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Wellets record);

    int insertSelective(Wellets record);

    Wellets selectByPrimaryKey(Integer fid);

    List<Wellets> selectByUserKey(String userkey);

    int updateByPrimaryKeySelective(Wellets record);

    int updateByPrimaryKey(Wellets record);

    List<Wellets> selectByCointype(Integer coinid);

    List<Wellets> selectByCointype2(Integer coinid);

    Wellets selectByUserandcoin(String userkey, Integer coinid);

    List<Wellets> selectByUsersandcoin(List list, Integer coinid);

    List<Map> Adminwelletlist(String likes, Integer begin, Integer end);

    int AdminwelletlistCount(@Param("likes") String likes);

    Map AdminwelletByuser(@Param("userkey") String userkey);

    int uptWelletByuserandcoin(double num, int coinId, String userkey);

    int uptWelletRelease(double num, int coinId, String userkey);

    int uptWelletLockNum(double num, int coinId, String userkey);

    List<Wellets> queryWelletByAdress(@Param("address") String address);

    Wellets selectByAddressAndCoin(String address, Integer coinId);

    int updateRewardRelease(@Param("lockNum") Double lockNum, @Param("releaseNum") Double releaseNum, @Param("coinId") int coinId, @Param("keyes") String keyes);

    int upLockFinances(@Param("principal") Double principal, @Param("coinId") int coinId, @Param("keyes") String keyes, @Param("version") Integer version);

    int incomeInterest(@Param("release") Double release, @Param("coinId") int coinId, @Param("keyes") String keyes, @Param("version") Integer version);

    int updateCoinNumByVersion(@Param("release") double release, @Param("coinId") int coinId, @Param("keyes") String keyes, @Param("version") Integer version);

    int transfer(@Param("num") Double num, @Param("amount") Double amount, @Param("userKey") String userKey, @Param("id") Integer id, @Param("version") Integer version);

    int invest(@Param("num") BigDecimal num, @Param("freezeBtc") BigDecimal freezeBtc, @Param("freeBtc") BigDecimal freeBtc, @Param("userKey") String userKey, @Param("coinId") Integer coinId, @Param("version") Integer version);

    int financialRelease(@Param("num") BigDecimal num, @Param("userKey") String userKey, @Param("coinId") Integer coinId, @Param("version") Integer version);

    int lockFinances(@Param("num") BigDecimal num, @Param("userKey") String userKey, @Param("coinId") Integer coinId, @Param("version") Integer version);

    @Select("select ifnull(sum(coinNum),0) from wellets where coinId=#{coinId} ")
    BigDecimal sumTotalCoinNum(@Param("coinId") Integer coinId);
}