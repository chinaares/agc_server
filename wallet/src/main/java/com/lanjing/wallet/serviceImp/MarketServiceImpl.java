package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.service.MarketService;
import com.lanjing.wallet.dao.MarketMapper;
import com.lanjing.wallet.model.Market;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MarketService")
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketMapper marketMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public List<Market> selectNew() {
        List<Market> markets = null;
        try {
            if (redisDao.hasKey("newmarket")){
                markets = JSONObject.parseArray(redisDao.getValue("newmarket"),Market.class);
            }else {
                markets = marketMapper.selectNew();
                redisDao.setKey("newmarket",JSONObject.toJSONString(markets),10);
            }
        } catch (Exception e){
            markets = marketMapper.selectNew();
            System.out.println(e.getMessage());
        }
        return markets;
    }

    @Override
    public List<Market> selectBy(Market record) {
        return marketMapper.selectBy(record);
    }

    @Override
    public Market selectById(Integer fId) {
        return marketMapper.selectByPrimaryKey(fId);
    }
}
