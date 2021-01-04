package com.lanjing.goods.dao;

import com.lanjing.goods.model.MallShop;
import com.lanjing.goods.model.MallShopExample;
import com.lanjing.goods.model.po.MallShops;
import com.lanjing.goods.model.po.Merchant;
import com.lanjing.goods.model.po.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-31 17:10
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

    List<Merchant> getMerchant(@Param("key") String key);

    MallShop findByCode(@Param("code") Long code);

    MallShops findByCodes(Long code);

    List<Shop> getShop(@Param("key") String key);

    List<Shop> findBySort();

    Shop selectByCode(@Param("code") Long code);

    MallShop findByPhone(@Param("phone") String phone);
}