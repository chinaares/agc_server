package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Market;

import java.util.List;

public interface MarketService {

    List<Market> selectNew();

    List<Market> selectBy(Market record);

    Market selectById(Integer fId);

}
