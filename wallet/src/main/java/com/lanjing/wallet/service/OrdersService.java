package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Orders;

import java.util.List;

public interface OrdersService {
    int deleteByPrimaryKey(Integer fid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> selectBy(Orders record, Integer begin, Integer end);
}