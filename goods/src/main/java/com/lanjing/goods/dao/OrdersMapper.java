package com.lanjing.goods.dao;

import com.lanjing.goods.model.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}