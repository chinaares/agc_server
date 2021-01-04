package com.lanjing.goods.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lanjing.goods.dao.EvaluationMapper;
import com.lanjing.goods.model.Evaluation;
import com.lanjing.goods.service.EvaluationService;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService{

    @Resource
    private EvaluationMapper evaluationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return evaluationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Evaluation record) {
        return evaluationMapper.insert(record);
    }

    @Override
    public int insertSelective(Evaluation record) {
        return evaluationMapper.insert(record);
    }

    @Override
    public Evaluation selectByPrimaryKey(Integer id) {
        return evaluationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Evaluation record) {
        return evaluationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Evaluation record) {
        return evaluationMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Evaluation> selectByGoodsid(Integer goodsId) {
        return evaluationMapper.selectByGoodsid(goodsId);
    }

    @Override
    public int selectByGoodsidcount(Integer goodsId) {
        return evaluationMapper.selectByGoodsidcount(goodsId);
    }

    @Override
    public List<Evaluation> queryByGoodsidPage(Integer goodsId, Integer page, Integer size) {
        return evaluationMapper.queryByGoodsidPage(goodsId,page,size);
    }

    @Override
    public List<Evaluation> queryAll(Integer state,Long shopcode,Integer goodsId,Integer page,Integer size) {
        return evaluationMapper.queryAll(state,shopcode,goodsId,page,size);
    }

    @Override
    public int queryAllcount(Integer state,Long shopcode,Integer goodsId) {
        return evaluationMapper.queryAllcount(state,shopcode,goodsId);
    }

}
