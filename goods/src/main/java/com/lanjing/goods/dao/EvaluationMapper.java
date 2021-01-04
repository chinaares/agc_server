package com.lanjing.goods.dao;

import com.lanjing.goods.model.Evaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer id);

    List<Evaluation> selectByGoodsid(@Param("goodsId")Integer goodsId);

    int selectByGoodsidcount(@Param("goodsId")Integer goodsId);

    List<Evaluation> queryByGoodsidPage(@Param("goodsId") Integer goodsId,@Param("page") Integer page,@Param("size") Integer size);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);

    List<Evaluation> queryAll(Integer state,Long shopcode,Integer goodsId,Integer page,Integer size);

    int queryAllcount(Integer state,Long shopcode,Integer goodsId);
}