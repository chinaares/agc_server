package com.lanjing.otc.service;

import com.lanjing.otc.model.PayWay;
import com.lanjing.otc.model.PayWayExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 15:10
 */
public interface PayWayService {


    long countByExample(PayWayExample example);

    int deleteByExample(PayWayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PayWay record);

    int insertSelective(PayWay record);

    List<PayWay> selectByExample(PayWayExample example);

    PayWay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(PayWay record, PayWayExample example);

    int updateByExample(PayWay record, PayWayExample example);

    int updateByPrimaryKeySelective(PayWay record);

    int updateByPrimaryKey(PayWay record);

    int countByUserId(Integer userId);
}


