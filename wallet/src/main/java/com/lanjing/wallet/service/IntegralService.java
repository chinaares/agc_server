package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Integral;
import com.lanjing.wallet.model.IntegralExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-19 10:20
 */
public interface IntegralService {


    long countByExample(IntegralExample example);

    int deleteByExample(IntegralExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Integral record);

    int insertSelective(Integral record);

    List<Integral> selectByExample(IntegralExample example);

    Integral selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Integral record, IntegralExample example);

    int updateByExample(Integral record, IntegralExample example);

    int updateByPrimaryKeySelective(Integral record);

    int updateByPrimaryKey(Integral record);

    List<Integral> findAllByCode(Long code);

    int updateStatusById(Integer status, Integer id);

    Integral findByCode(Long code);

    /**
     * 参加活动获取积分
     *
     * @param integral
     * @param userId
     * @return
     */
    int earnPoints(Integral integral, Integer userId);
}


