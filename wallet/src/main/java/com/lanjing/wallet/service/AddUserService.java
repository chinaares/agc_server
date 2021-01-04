package com.lanjing.wallet.service;

import com.lanjing.wallet.model.AddUser;

public interface AddUserService {
    int deleteByPrimaryKey(Integer fid);

    int insert(AddUser record);

    int insertSelective(AddUser record);

    AddUser selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(AddUser record);

    int updateByPrimaryKey(AddUser record);

    int updateByUserKey( String userkey);
}
