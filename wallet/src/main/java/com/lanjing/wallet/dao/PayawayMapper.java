package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Payaway;
import com.lanjing.wallet.model.PayawayExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PayawayMapper {
    long countByExample(PayawayExample example);

    int deleteByExample(PayawayExample example);

    int insert(Payaway record);

    int insertSelective(Payaway record);

    int updateByPrimaryKeySelective(Payaway record);

    List<Payaway> selectByExample(PayawayExample example);

    int updateByExampleSelective(@Param("record") Payaway record, @Param("example") PayawayExample example);

    int updateByExample(@Param("record") Payaway record, @Param("example") PayawayExample example);
}