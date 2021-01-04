package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Promotion;
import com.lanjing.wallet.model.PromotionExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PromotionMapper {
    long countByExample(PromotionExample example);

    int deleteByExample(PromotionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Promotion record);

    int insertSelective(Promotion record);

    List<Promotion> selectByExample(PromotionExample example);

    Promotion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Promotion record, @Param("example") PromotionExample example);

    int updateByExample(@Param("record") Promotion record, @Param("example") PromotionExample example);

    int updateByPrimaryKeySelective(Promotion record);

    int updateByPrimaryKey(Promotion record);

    List<Promotion> selectAll();
}