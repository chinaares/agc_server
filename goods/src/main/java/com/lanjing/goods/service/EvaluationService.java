package com.lanjing.goods.service;

import com.lanjing.goods.dao.EvaluationMapper;
import com.lanjing.goods.model.Evaluation;

import java.util.List;

public interface EvaluationService{


    int deleteByPrimaryKey(Integer id);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);

    List<Evaluation> selectByGoodsid(Integer goodsId);

    int selectByGoodsidcount(Integer goodsId);

    List<Evaluation> queryByGoodsidPage(Integer goodsId,Integer page,Integer size);

    List<Evaluation> queryAll(Integer state,Long shopcode,Integer goodsId,Integer page,Integer size);

    int queryAllcount(Integer state,Long shopcode,Integer goodsId);

}
