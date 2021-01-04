package com.lanjing.goods.service;

import com.lanjing.goods.model.Goods;
import com.lanjing.goods.model.GoodsExample;
import com.lanjing.goods.model.po.Cargo;
import com.lanjing.goods.model.po.SalesVolume;

import java.util.List;

public interface GoodsService {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int delGoods(Integer id, Integer status);

    List<Goods> getGoods(Long mallshopCode, String name, Integer status);

    List<Goods> getGoodsBypage(Long shopCode, String name, Integer status, Integer page, Integer size);

    int getGoodsBycount(Long shopCode, String name, Integer status);

    List<Cargo> getGoods();

    List<Cargo> getGoods(String key);

    List<Cargo> getGoodsByCode(Long code, Integer type);

    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    int updateByExampleSelective(Goods record, GoodsExample example);

    int updateByExample(Goods record, GoodsExample example);

    /**
     * 获取店铺销售量
     *
     * @param code
     * @return
     */
    Integer sumBySalesVolume(Long code);

    /**
     * 刷选商品
     *
     * @param code
     * @param max
     * @param min
     * @param key
     * @param type
     * @return
     */
    List<Cargo> brushSelection(Long code, Double max, Double min, String key, Integer type);


    Goods selectByCode(Long code);
    /**
     * 销量榜
     * @param code
     * @return
     */
    List<SalesVolume> salesList(Long code);

    List<Cargo> sortGoods(Long code);

    List<Cargo> goodsByTypeList(Integer type);

    List<Goods> selectAll();
}




