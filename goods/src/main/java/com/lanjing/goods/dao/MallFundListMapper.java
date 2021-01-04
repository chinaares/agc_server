package com.lanjing.goods.dao;

import com.lanjing.goods.model.MallFundList;
import com.lanjing.goods.model.MallFundListExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-22 15:57
 */
public interface MallFundListMapper {
    long countByExample(MallFundListExample example);

    int deleteByExample(MallFundListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallFundList record);

    int insertSelective(MallFundList record);

    List<MallFundList> selectByExample(MallFundListExample example);

    MallFundList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallFundList record, @Param("example") MallFundListExample example);

    int updateByExample(@Param("record") MallFundList record, @Param("example") MallFundListExample example);

    int updateByPrimaryKeySelective(MallFundList record);

    int updateByPrimaryKey(MallFundList record);

    List<MallFundList> findByCode(@Param("code") Long code);
}