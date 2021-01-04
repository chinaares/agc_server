package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Wellets;

import java.math.BigDecimal;
import java.util.Map;

public interface WelletService {
    int deleteByPrimaryKey(Integer fid);

    int insert(Wellets record);

    int insertSelective(Wellets record);

    Wellets selectByPrimaryKey(Integer fid);

    Map<String, String> selectByUserKey(String userkey);

    Wellets selectByUserKey(String userkey, Integer coin);

    int updateByPrimaryKeySelective(Wellets record);

    int updateByPrimaryKey(Wellets record);

    Wellets selectByUserandcoin(String keyes, int coinId);

    int withdraw(String userkey, String pwd, int coin, double amount, double gas_price, String to);

    void withdraw1(String userkey, String pwd, int coin, BigDecimal amount, BigDecimal gas, String to);

    int transfer(int coin, String userkey, double num);

    /**
     * 理财资产转出
     *
     * @param num
     * @param amount
     * @param userKey
     * @param id
     * @param version
     * @return
     */
    int transfer(double num, Double amount, String userKey, Integer id, Integer version);
}
