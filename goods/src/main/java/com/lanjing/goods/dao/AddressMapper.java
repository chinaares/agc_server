package com.lanjing.goods.dao;

import com.lanjing.goods.model.Address;
import com.lanjing.goods.model.AddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    int updateAddressStatus(@Param("id") Integer id, @Param("userKey") String userKey);

    List<Address> getAddress(@Param("id") Integer id,@Param("userKey") String userKey);

    int resetDefaultAddress(@Param("id") Integer id, @Param("userKey") String userKey,@Param("code") int code);

    Address getDefaultAddress(@Param("userKey") String userKey);
}