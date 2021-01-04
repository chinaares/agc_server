package com.lanjing.wallet.dao;

import java.util.List;

import com.lanjing.wallet.model.UserGredeLog;

public interface UserGredeLogMapper {
	
    public int insert(UserGredeLog log);
    
    public List<UserGredeLog> findUserGredeLogByPage(String userName,String admin, int page,int pagesize);//用户名，手机号，管理员，页数，行数 降序返回
    public int findUserGredeLogByPageCount(String userName,String admin);
}
