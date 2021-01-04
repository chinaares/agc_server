package com.lanjing.goods.dao;

import com.lanjing.goods.model.Phonecode;
import com.lanjing.goods.model.PhonecodeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-22 16:56
 */
public interface PhonecodeMapper {
    long countByExample(PhonecodeExample example);

    int deleteByExample(PhonecodeExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Phonecode record);

    int insertSelective(Phonecode record);

    List<Phonecode> selectByExample(PhonecodeExample example);

    Phonecode selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Phonecode record, @Param("example") PhonecodeExample example);

    int updateByExample(@Param("record") Phonecode record, @Param("example") PhonecodeExample example);

    int updateByPrimaryKeySelective(Phonecode record);

    int updateByPrimaryKey(Phonecode record);

    Phonecode selectByCode(@Param("phonenum")String phonenum,@Param("code") String code);
}