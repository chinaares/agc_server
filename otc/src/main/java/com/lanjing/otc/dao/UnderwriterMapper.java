package com.lanjing.otc.dao;

import com.lanjing.otc.model.Underwriter;
import com.lanjing.otc.model.UnderwriterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 16:30
 */
public interface UnderwriterMapper {
    long countByExample(UnderwriterExample example);

    int deleteByExample(UnderwriterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Underwriter record);

    int insertSelective(Underwriter record);

    List<Underwriter> selectByExample(UnderwriterExample example);

    Underwriter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Underwriter record, @Param("example") UnderwriterExample example);

    int updateByExample(@Param("record") Underwriter record, @Param("example") UnderwriterExample example);

    int updateByPrimaryKeySelective(Underwriter record);

    int updateByPrimaryKey(Underwriter record);

    Underwriter findByUserId(@Param("userId")Integer userId);

    List<Underwriter> findAllByStatusAndPhone(@Param("status")Integer status,@Param("phone")String phone);


}