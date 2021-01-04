package com.lanjing.goods.dao;

import com.lanjing.goods.model.Goodssku;

import java.util.List;

public interface GoodsskuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goodssku record);

    int insertSelective(Goodssku record);

    Goodssku selectByPrimaryKey(Integer id);

    List<Goodssku> selectBygoodsCode(String goodsCode);

    int updateByPrimaryKeySelective(Goodssku record);

    int updateByPrimaryKey(Goodssku record);

    int uptstateByPrimaryKey(Integer id,Integer state);
}