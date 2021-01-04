package com.lanjing.otc.service;

import com.lanjing.otc.model.Users;
import com.lanjing.otc.model.UsersExample;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 11:46
 */
public interface UsersService {


    long countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(Users record, UsersExample example);

    int updateByExample(Users record, UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

}



