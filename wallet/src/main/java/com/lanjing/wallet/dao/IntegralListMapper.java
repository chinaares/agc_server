package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.IntegralList;
import com.lanjing.wallet.model.IntegralListExample;
import com.lanjing.wallet.model.po.Integrals;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-19 11:40
 * @version version 1.0.0
 */
public interface IntegralListMapper {
    long countByExample(IntegralListExample example);

    int deleteByExample(IntegralListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralList record);

    int insertSelective(IntegralList record);

    List<IntegralList> selectByExample(IntegralListExample example);

    IntegralList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntegralList record, @Param("example") IntegralListExample example);

    int updateByExample(@Param("record") IntegralList record, @Param("example") IntegralListExample example);

    int updateByPrimaryKeySelective(IntegralList record);

    int updateByPrimaryKey(IntegralList record);

    List<Integrals> findByUserId(@Param("key")String key,@Param("start")String start,@Param("end")String end);
}