package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Avail;
import com.lanjing.wallet.model.AvailExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 19:04
 */
public interface AvailService {


    long countByExample(AvailExample example);

    int deleteByExample(AvailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Avail record);

    int insertSelective(Avail record);

    List<Avail> selectByExample(AvailExample example);

    Avail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Avail record, AvailExample example);

    int updateByExample(Avail record, AvailExample example);

    int updateByPrimaryKeySelective(Avail record);

    int updateByPrimaryKey(Avail record);

    List<Avail> findByAIdAndUserIdAndType(Integer id, Integer userId, Integer type);
}


