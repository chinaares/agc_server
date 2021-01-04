package com.lanjing.goods.dao;

import com.lanjing.goods.model.OrderExample;
import com.lanjing.goods.model.ShopOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopOrderMapper2 {


    ShopOrder queryByOrderidAndGoodsskuId(@Param("orderId") String orderId, @Param("goodsskuId") Integer goodsskuId);


}