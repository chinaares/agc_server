package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Avail;
import com.lanjing.wallet.model.AvailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-17 11:11
 */
public interface AvailMapper {
    long countByExample(AvailExample example);

    int deleteByExample(AvailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Avail record);

    int insertSelective(Avail record);

    List<Avail> selectByExample(AvailExample example);

    Avail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Avail record, @Param("example") AvailExample example);

    int updateByExample(@Param("record") Avail record, @Param("example") AvailExample example);

    int updateByPrimaryKeySelective(Avail record);

    int updateByPrimaryKey(Avail record);

    List<Avail> findByAIdAndUserIdAndType(@Param("aId") Integer aId, @Param("userId") Integer userId, @Param("type") Integer type);
}