package com.lanjing.wallet.service;

import com.lanjing.wallet.model.CoinAddressStoreEntity;

public interface CoinAddressStoreService {

    int insert(CoinAddressStoreEntity coinAddressStoreEntity);

    CoinAddressStoreEntity queryUsedAddress(String address);

    CoinAddressStoreEntity queryNoUsedAddress();

    int updateByPrimaryKeySelective(CoinAddressStoreEntity coinAddressStoreEntity);
}
