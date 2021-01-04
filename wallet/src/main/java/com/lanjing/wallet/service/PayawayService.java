package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Payaway;

import java.util.List;

public interface PayawayService {

    int insertSelective(Payaway record);

    int updateByPrimaryKeySelective(Payaway record);

    List<Payaway> selectByUser(String userkey,Integer type);

}
