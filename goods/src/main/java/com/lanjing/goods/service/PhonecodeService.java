package com.lanjing.goods.service;

import com.lanjing.goods.model.Phonecode;
import com.lanjing.goods.model.PhonecodeExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-22 16:56
 */
public interface PhonecodeService {


    long countByExample(PhonecodeExample example);

    int deleteByExample(PhonecodeExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Phonecode record);

    int insertSelective(Phonecode record);

    List<Phonecode> selectByExample(PhonecodeExample example);

    Phonecode selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(Phonecode record, PhonecodeExample example);

    int updateByExample(Phonecode record, PhonecodeExample example);

    int updateByPrimaryKeySelective(Phonecode record);

    int updateByPrimaryKey(Phonecode record);

    int selectByCode(String phonenum, String code);
}
