package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.CoinAddressStoreEntity;
import org.apache.ibatis.annotations.Param;

public interface CoinAddressStoreMapper {

    int insert(CoinAddressStoreEntity coinAddressStoreEntity);

    CoinAddressStoreEntity queryUsedAddress(@Param("address") String address);

    CoinAddressStoreEntity queryNoUsedAddress();

    int updateByPrimaryKeySelective(CoinAddressStoreEntity coinAddressStoreEntity);
}