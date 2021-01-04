package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Promotion;

import java.util.List;

public interface PromotionService {
    int deleteByPrimaryKey(Integer id);

    int insert(Promotion record);

    int insertSelective(Promotion record);

    Promotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Promotion record);

    int updateByPrimaryKey(Promotion record);

    List<Promotion> selectAll();

    void calculate(Promotion promotion);
}