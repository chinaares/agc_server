package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Integral;
import com.lanjing.wallet.model.IntegralExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-29 12:15
 */
public interface IntegralMapper {
    long countByExample(IntegralExample example);

    int deleteByExample(IntegralExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Integral record);

    int insertSelective(Integral record);

    List<Integral> selectByExample(IntegralExample example);

    Integral selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Integral record, @Param("example") IntegralExample example);

    int updateByExample(@Param("record") Integral record, @Param("example") IntegralExample example);

    int updateByPrimaryKeySelective(Integral record);

    int updateByPrimaryKey(Integral record);

    List<Integral> findAllByCode(@Param("code") Long code);

    int updateStatusById(@Param("updatedStatus") Integer updatedStatus, @Param("id") Integer id);

    Integral findByCode(@Param("code") Long code);
}