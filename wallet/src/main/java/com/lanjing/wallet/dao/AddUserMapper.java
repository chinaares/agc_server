package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.AddUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddUserMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(AddUser record);

    int insertSelective(AddUser record);

    AddUser selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(AddUser record);

    int updateByPrimaryKey(AddUser record);

    int updateByUserKey(@Param("userkey") String userkey);

    List<AddUser> selectAll(String likes,Integer state,Integer begin ,Integer end);

    int selectAllCount(String likes,Integer state);

    int updateByfids(List<String> list,Integer state);
}