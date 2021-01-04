package com.lanjing.wallet.service;

import com.lanjing.wallet.model.WithdrawLog;
import java.util.List;
import com.lanjing.wallet.model.WithdrawLogExample;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-24 17:14
 */
public interface WithdrawLogService {


    long countByExample(WithdrawLogExample example);

    int deleteByExample(WithdrawLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawLog record);

    int insertSelective(WithdrawLog record);

    List<WithdrawLog> selectByExample(WithdrawLogExample example);

    WithdrawLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(WithdrawLog record, WithdrawLogExample example);

    int updateByExample(WithdrawLog record, WithdrawLogExample example);

    int updateByPrimaryKeySelective(WithdrawLog record);

    int updateByPrimaryKey(WithdrawLog record);

}


