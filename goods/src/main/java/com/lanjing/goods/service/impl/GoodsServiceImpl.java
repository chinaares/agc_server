package com.lanjing.goods.service.impl;

import com.lanjing.goods.dao.GoodsMapper;
import com.lanjing.goods.model.Goods;
import com.lanjing.goods.model.GoodsExample;
import com.lanjing.goods.model.po.Cargo;
import com.lanjing.goods.model.po.SalesVolume;
import com.lanjing.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsMapper goodsMapper;

    public int deleteByPrimaryKey(Integer id) {
        return this.goodsMapper.deleteByPrimaryKey(id);
    }

    public int insert(Goods record) {
        return this.goodsMapper.insert(record);
    }

    public int insertSelective(Goods record) {
        return this.goodsMapper.insertSelective(record);
    }

    public Goods selectByPrimaryKey(Integer id) {
        return this.goodsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Goods record) {
        return this.goodsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Goods record) {
        return this.goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int delGoods(Integer id, Integer status) {
        return this.goodsMapper.delGoods(id, status);
    }

    @Override
    public List<Goods> getGoods(Long shopCode, String name, Integer status) {
        return this.goodsMapper.getGoods(shopCode, name, status);
    }

    @Override
    public List<Goods> getGoodsBypage(Long shopCode, String name, Integer status, Integer page, Integer size) {
        return this.goodsMapper.getGoodsBypage(shopCode, name, status,page,size);
    }

    @Override
    public int getGoodsBycount(Long shopCode, String name, Integer status) {
        return goodsMapper.getGoodsBycount(shopCode,name,status);
    }


    @Override
    public List<Cargo> getGoods() {
        return this.goodsMapper.selectGoods();
    }

    @Override
    public List<Cargo> getGoods(String key) {
        return this.goodsMapper.findGoods(key);
    }

    @Override
    public List<Cargo> getGoodsByCode(Long code, Integer type) {
        return this.goodsMapper.getGoodsByCode(code, type);
    }

    @Override
    public long countByExample(GoodsExample example) {
        return goodsMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(GoodsExample example) {
        return goodsMapper.deleteByExample(example);
    }

    @Override
    public List<Goods> selectByExample(GoodsExample example) {
        return goodsMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(Goods record, GoodsExample example) {
        return goodsMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Goods record, GoodsExample example) {
        return goodsMapper.updateByExample(record, example);
    }

    @Override
    public Integer sumBySalesVolume(Long code) {
        return goodsMapper.sumBySalesVolume(code);
    }

    @Override
    public List<Cargo> brushSelection(Long code, Double max, Double min, String key, Integer type) {
        return goodsMapper.brushSelection(code, max, min, key, type);
    }

    @Override
    public Goods selectByCode(Long code) {
        return goodsMapper.selectByCode(code);
    }

    @Override
    public List<SalesVolume> salesList(Long code) {
        return goodsMapper.salesList(code);
    }

    @Override
    public List<Cargo> sortGoods(Long code) {
        return goodsMapper.sortGoods(code);
    }

    @Override
    public List<Cargo> goodsByTypeList(Integer type) {
        return goodsMapper.goodsByTypeList(type);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();
    }
}





