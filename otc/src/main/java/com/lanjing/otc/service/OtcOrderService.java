package com.lanjing.otc.service;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.otc.model.OtcOrder;
import com.lanjing.otc.model.OtcOrderExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 14:38
 */
public interface OtcOrderService {


    long countByExample(OtcOrderExample example);

    int deleteByExample(OtcOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OtcOrder record);

    int insertSelective(OtcOrder record);

    List<OtcOrder> selectByExample(OtcOrderExample example);

    OtcOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(OtcOrder record, OtcOrderExample example);

    int updateByExample(OtcOrder record, OtcOrderExample example);

    int updateByPrimaryKeySelective(OtcOrder record);

    JSONObject otcsurePay(OtcOrder record);

    int updateByPrimaryKey(OtcOrder record);

    JSONObject otctrade(Integer adId, Integer userkey, Double coinnum, String remark, String tradepwd);

    JSONObject otcrelease(Integer id,Integer userkey,Integer state,String backContent);

    List<OtcOrder> selectByuser(Integer trade_user,Integer state,Integer begin ,Integer pagesize);

}

