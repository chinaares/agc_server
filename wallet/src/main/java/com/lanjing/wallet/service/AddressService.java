package com.lanjing.wallet.service;

import com.lanjing.wallet.model.addree_user;

public interface AddressService {
    int deleteByPrimaryKey(Integer fid);

    int insertSelective(addree_user record);

    addree_user selectByPrimaryKey(Integer fid);

    addree_user selectByUserKey(String userkey);

    int updateByPrimaryKeySelective(addree_user record);

    int updateByUserKeySelective(addree_user record);

    addree_user updateByUserIdSelective(String userid,String help);

    addree_user selectByhelpkey(String help,String key);

    addree_user createWellet(String userkey,String helps);

    addree_user importwellet(String userkey,String helps);

    addree_user getaddress(String userkey,String tradepwd);


}
