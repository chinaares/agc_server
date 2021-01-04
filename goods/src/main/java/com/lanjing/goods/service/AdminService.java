package com.lanjing.goods.service;

import com.lanjing.goods.model.Admin;
import com.lanjing.goods.model.AdminExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
public interface AdminService {


    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(Admin record, AdminExample example);

    int updateByExample(Admin record, AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin findByAname(String phone);

    Admin findByCode(Long code);
}


