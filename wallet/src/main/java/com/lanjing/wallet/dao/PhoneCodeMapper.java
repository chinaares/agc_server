package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.PhoneCode;
import org.apache.ibatis.annotations.Param;

public interface PhoneCodeMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(PhoneCode record);

    int insertSelective(PhoneCode record);

    PhoneCode selectByPrimaryKey(Integer fid);

    PhoneCode selectByCode(String phonenum,String code);

    int updateByPrimaryKeySelective(PhoneCode record);

    int updateByPrimaryKey(PhoneCode record);

    int selectByPhone(@Param("phonenum") String phonenum);
}