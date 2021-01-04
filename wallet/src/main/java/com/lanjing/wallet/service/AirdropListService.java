package com.lanjing.wallet.service;

import com.lanjing.wallet.model.AirdropList;
import com.lanjing.wallet.model.AirdropListExample;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 11:44
 */
public interface AirdropListService {


    long countByExample(AirdropListExample example);

    int deleteByExample(AirdropListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AirdropList record);

    int insertSelective(AirdropList record);

    List<AirdropList> selectByExample(AirdropListExample example);

    AirdropList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(AirdropList record, AirdropListExample example);

    int updateByExample(AirdropList record, AirdropListExample example);

    int updateByPrimaryKeySelective(AirdropList record);

    int updateByPrimaryKey(AirdropList record);

    List<AirdropList> findByUserId(Integer userId, Integer id);

    /**
     * 参加理财
     * @param airdropList
     * @param userKey
     * @return
     */
    int invest(AirdropList airdropList, String userKey);

    List<AirdropList> findByStatus(Integer status);

    void financialRelease(AirdropList airdropList, Boolean status);

    BigDecimal sumFreeYyb(Integer userId, Integer id);

    BigDecimal sumFreeBtc(Integer userId, Integer id);

    BigDecimal sumFreezeYyb(Integer userId, Integer id);

    BigDecimal sumFreezeBtc(Integer userId, Integer id);

    int minusFreeBtc(Integer id, Double amount);

    int minusFreeYyb(Integer id, Double amount);
}
