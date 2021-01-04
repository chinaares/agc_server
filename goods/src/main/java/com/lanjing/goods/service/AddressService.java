package com.lanjing.goods.service;

import com.lanjing.goods.model.Address;
import java.util.List;

public interface AddressService {
    int deleteByPrimaryKey(Integer fid);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    int updateAddressStatus(Integer id, String userKey);

    List<Address> getAddress(Integer id, String userKey);

    int resetDefaultAddress(Integer id, String userKey, int code);

    Address getDefaultAddress(String userKey);
}