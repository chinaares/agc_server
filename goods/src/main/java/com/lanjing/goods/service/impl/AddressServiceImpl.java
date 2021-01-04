package com.lanjing.goods.service.impl;

import com.lanjing.goods.dao.AddressMapper;
import com.lanjing.goods.model.Address;
import com.lanjing.goods.service.AddressService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AddressMapper addressMapper;

    public int deleteByPrimaryKey(Integer fid) {
        return this.addressMapper.deleteByPrimaryKey(fid);
    }

    public int insert(Address record) {
        return this.addressMapper.insert(record);
    }

    public int insertSelective(Address record) {
        return this.addressMapper.insertSelective(record);
    }

    public Address selectByPrimaryKey(Integer fid) {
        return this.addressMapper.selectByPrimaryKey(fid);
    }

    public int updateByPrimaryKeySelective(Address record) {
        return this.addressMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Address record) {
        return this.addressMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateAddressStatus(Integer id, String userKey) {
        return this.addressMapper.updateAddressStatus(id, userKey);
    }

    @Override
    public List<Address> getAddress(Integer id, String userKey) {
        return this.addressMapper.getAddress(id,userKey);
    }

    @Override
    public int resetDefaultAddress(Integer id, String userKey, int code) {
        return this.addressMapper.resetDefaultAddress(id,userKey,code);
    }

    @Override
    public Address getDefaultAddress(String userKey) {
        return this.addressMapper.getDefaultAddress(userKey);
    }
}