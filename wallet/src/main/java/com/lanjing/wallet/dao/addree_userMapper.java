package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.addree_user;

import java.util.List;

public interface addree_userMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(addree_user record);

    int insertSelective(addree_user record);

    addree_user selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(addree_user record);

    int updateByPrimaryKey(addree_user record);

    int updateByUserKeySelective(addree_user record);

    addree_user selectByUserKey(String userkey);

    addree_user selectByKey(addree_user addree);

    addree_user selectByHelps(addree_user addree);

    List<addree_user> selectAll();
}