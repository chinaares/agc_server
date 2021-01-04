package com.lanjing.goods.dao;

import com.lanjing.goods.model.Shopcat;

import java.util.List;

public interface ShopcatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shopcat record);

    int insertSelective(Shopcat record);

    Shopcat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shopcat record);

    int updateByPrimaryKey(Shopcat record);

    List<Shopcat> selectByUserid(Integer userid);

    Shopcat selectByGoodsidAndGoodsskuid(Integer userid, Integer goodsid, Integer goodsskuid);
}