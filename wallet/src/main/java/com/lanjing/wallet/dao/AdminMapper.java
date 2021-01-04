package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.Admin;
import com.lanjing.wallet.model.po.Administrator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByLogin(String name, String password);

    List<Admin> findAdminByPage(int pageNo,int pagesize,String adminName);

    int findAdminByPagCount(String adminName);

    int updateAdminStatus(@Param("status") int status,@Param("ids") String[] ids);

    List<Administrator> selectAll();

    int updatePurviewByPrimaryKey(@Param("id") Integer id,@Param("purview") Integer purview);

    List<Admin> selectList();
}