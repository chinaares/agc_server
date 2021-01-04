package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.MallShop;
import com.lanjing.wallet.model.MallShopExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-09-04 16:46
 * @version version 1.0.0
 */
public interface MallShopMapper {
    long countByExample(MallShopExample example);

    int deleteByExample(MallShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallShop record);

    int insertSelective(MallShop record);

    List<MallShop> selectByExample(MallShopExample example);

    MallShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallShop record, @Param("example") MallShopExample example);

    int updateByExample(@Param("record") MallShop record, @Param("example") MallShopExample example);

    int updateByPrimaryKeySelective(MallShop record);

    int updateByPrimaryKey(MallShop record);

    MallShop findByCode(@Param("code")Long code);
}