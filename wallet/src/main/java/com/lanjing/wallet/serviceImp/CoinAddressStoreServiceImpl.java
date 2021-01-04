package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.CoinAddressStoreMapper;
import com.lanjing.wallet.model.CoinAddressStoreEntity;
import com.lanjing.wallet.service.CoinAddressStoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CoinAddressStoreServiceImpl implements CoinAddressStoreService {

    @Resource
    CoinAddressStoreMapper coinAddressStoreMapper;

    @Override
    public int insert(CoinAddressStoreEntity coinAddressStoreEntity) {
        return coinAddressStoreMapper.insert(coinAddressStoreEntity);
    }

    @Override
    public CoinAddressStoreEntity queryUsedAddress(String address) {
        return coinAddressStoreMapper.queryUsedAddress(address);
    }

    @Override
    public CoinAddressStoreEntity queryNoUsedAddress() {
        return coinAddressStoreMapper.queryNoUsedAddress();
    }

    @Override
    public int updateByPrimaryKeySelective(CoinAddressStoreEntity coinAddressStoreEntity) {
        return coinAddressStoreMapper.updateByPrimaryKeySelective(coinAddressStoreEntity);
    }
}
