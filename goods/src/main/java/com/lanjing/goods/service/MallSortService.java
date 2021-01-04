package com.lanjing.goods.service;

import com.lanjing.goods.model.MallSort;
import com.lanjing.goods.model.MallSortExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 10:13
 */
public interface MallSortService {


    long countByExample(MallSortExample example);

    int deleteByExample(MallSortExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallSort record);

    int insertSelective(MallSort record);

    List<MallSort> selectByExample(MallSortExample example);

    MallSort selectByPrimaryKey(Integer id);

    int updateByExampleSelective(MallSort record, MallSortExample example);

    int updateByExample(MallSort record, MallSortExample example);

    int updateByPrimaryKeySelective(MallSort record);

    int updateByPrimaryKey(MallSort record);

    List selectAll();
}


