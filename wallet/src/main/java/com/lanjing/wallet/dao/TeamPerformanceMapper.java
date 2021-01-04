package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.TeamPerformance;
import com.lanjing.wallet.model.TeamPerformanceExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TeamPerformanceMapper {
    long countByExample(TeamPerformanceExample example);

    int deleteByExample(TeamPerformanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TeamPerformance record);

    int insertSelective(TeamPerformance record);

    List<TeamPerformance> selectByExample(TeamPerformanceExample example);

    TeamPerformance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TeamPerformance record, @Param("example") TeamPerformanceExample example);

    int updateByExample(@Param("record") TeamPerformance record, @Param("example") TeamPerformanceExample example);

    int updateByPrimaryKeySelective(TeamPerformance record);

    int updateByPrimaryKey(TeamPerformance record);
}