package com.lanjing.goods.dao;

import com.lanjing.goods.model.Admin;
import com.lanjing.goods.model.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-09-09 17:22
 */
public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin findByAname(@Param("aname") String aname);

    Admin findByCode(@Param("code") Long code);
}