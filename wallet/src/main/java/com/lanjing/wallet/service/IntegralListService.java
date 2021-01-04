package com.lanjing.wallet.service;

import com.lanjing.wallet.model.IntegralList;
import com.lanjing.wallet.model.IntegralListExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-19 11:40
 */
public interface IntegralListService {


    long countByExample(IntegralListExample example);

    int deleteByExample(IntegralListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralList record);

    int insertSelective(IntegralList record);

    List<IntegralList> selectByExample(IntegralListExample example);

    IntegralList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(IntegralList record, IntegralListExample example);

    int updateByExample(IntegralList record, IntegralListExample example);

    int updateByPrimaryKeySelective(IntegralList record);

    int updateByPrimaryKey(IntegralList record);

    long countByUserIdAndIId(Integer id, Integer userId);
}
