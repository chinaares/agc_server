package com.lanjing.otc.service;

import com.lanjing.otc.model.Underwriter;
import com.lanjing.otc.model.UnderwriterExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 16:22
 */
public interface UnderwriterService {


    long countByExample(UnderwriterExample example);

    int deleteByExample(UnderwriterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Underwriter record);

    int insertSelective(Underwriter record);

    List<Underwriter> selectByExample(UnderwriterExample example);

    Underwriter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Underwriter record, UnderwriterExample example);

    int updateByExample(Underwriter record, UnderwriterExample example);

    int updateByPrimaryKeySelective(Underwriter record);

    int updateByPrimaryKey(Underwriter record);

    Underwriter findByUserId(Integer userId);

    List<Underwriter> findAllByStatusAndPhone(Integer status, String key);
}


