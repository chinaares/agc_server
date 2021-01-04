package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.ShopOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopOrderMapper {

    List<ShopOrder> queryYesterdaySalesList();

    List<ShopOrder> selectAll();
}