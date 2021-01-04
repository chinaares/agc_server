package com.lanjing.wallet.service;

import com.lanjing.wallet.model.PhoneCode;

public interface PhoneCodeService {

    int deleteByPrimaryKey(Integer fid);

    int insertSelective(PhoneCode record);

    int sendCode(String phonenum);

    int sendmsg(String phonenum,String text);

    PhoneCode selectByPrimaryKey(Integer fid);

    PhoneCode selectByUserKey(Integer fid);

    int selectByCode(String phonenum,String code);

    int updateByPrimaryKeySelective(PhoneCode record);

    int updateByPrimaryKey(PhoneCode record);

    int selectByPhone( String phonenum);

    int checkByCode(String phonenum,String code);

    int expireByCode(String phonenum,String code);
}
