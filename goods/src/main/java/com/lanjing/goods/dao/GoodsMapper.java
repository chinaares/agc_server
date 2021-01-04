package com.lanjing.goods.dao;

import com.lanjing.goods.model.Goods;
import com.lanjing.goods.model.GoodsExample;
import com.lanjing.goods.model.po.Cargo;
import com.lanjing.goods.model.po.SalesVolume;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-23 16:35
 */
public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int delGoods(@Param("id") Integer id, @Param("status") Integer status);

    List<Goods> getGoods(@Param("shopCode") Long shopCode, @Param("name") String name, @Param("status") Integer status);

    List<Goods> getGoodsBypage(@Param("shopCode") Long shopCode, @Param("name") String name, @Param("status") Integer status, @Param("page") Integer page, @Param("size") Integer size);

    int getGoodsBycount(@Param("shopCode") Long shopCode, @Param("name") String name, @Param("status") Integer status);

    List<Cargo> selectGoods();

    List<Cargo> findGoods(@Param("key") String key);

    List<Cargo> getGoodsByCode(@Param("code") Long code, @Param("type") Integer type);

    Integer sumBySalesVolume(@Param("code") Long code);

    Goods selectByCode(@Param("code") Long code);

    List<Cargo> brushSelection(@Param("code") Long code, @Param("max") Double max, @Param("min") Double min, @Param("key") String key, @Param("type") Integer type);

    List<SalesVolume> salesList(@Param("code") Long code);

    List<Cargo> sortGoods(Long code);

    List<Goods> selectByShopCode(@Param("shopCode") Long shopCode);

    List<Cargo> goodsByTypeList(@Param("type") Integer type);

    List<Goods> selectAll();
}