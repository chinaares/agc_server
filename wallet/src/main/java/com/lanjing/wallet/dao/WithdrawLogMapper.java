package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.WithdrawLog;
import com.lanjing.wallet.model.WithdrawLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-24 17:16
 */
public interface WithdrawLogMapper {
    long countByExample(WithdrawLogExample example);

    int deleteByExample(WithdrawLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawLog record);

    int insertSelective(WithdrawLog record);

    List<WithdrawLog> selectByExample(WithdrawLogExample example);

    WithdrawLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WithdrawLog record, @Param("example") WithdrawLogExample example);

    int updateByExample(@Param("record") WithdrawLog record, @Param("example") WithdrawLogExample example);

    int updateByPrimaryKeySelective(WithdrawLog record);

    int updateByPrimaryKey(WithdrawLog record);
}