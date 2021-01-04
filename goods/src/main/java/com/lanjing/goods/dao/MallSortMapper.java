package com.lanjing.goods.dao;

import com.lanjing.goods.model.MallSort;
import com.lanjing.goods.model.MallSortExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
public interface MallSortMapper {
    long countByExample(MallSortExample example);

    int deleteByExample(MallSortExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallSort record);

    int insertSelective(MallSort record);

    List<MallSort> selectByExample(MallSortExample example);

    MallSort selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallSort record, @Param("example") MallSortExample example);

    int updateByExample(@Param("record") MallSort record, @Param("example") MallSortExample example);

    int updateByPrimaryKeySelective(MallSort record);

    int updateByPrimaryKey(MallSort record);

    List<MallSort> selectAll();
}