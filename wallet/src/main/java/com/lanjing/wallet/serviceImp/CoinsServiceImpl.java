package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.service.CoinsService;
import com.lanjing.wallet.dao.CoinsMapper;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Service("CoinsService")
@RestController
public class CoinsServiceImpl implements CoinsService {

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    public int insert(Coins record) {
        return 0;
    }

    @Override
    public int insertSelective(Coins record) {
        return 0;
    }

    @Override
    public Coins selectByPrimaryKey(Integer fid) {
        Coins coin = null;
        try {
            String str = redisDao.getValue("coin"+fid);
            coin = JSONObject.parseObject(str,Coins.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        coin = coinsMapper.selectByPrimaryKey(fid);
        redisDao.setKey("coin"+fid,JSONObject.toJSONString(coin));
        return coin;
    }

    @RequestMapping("/zuul/getcoin")
    public Coins selectByPrimaryKey1(Integer fid) {
        Coins coin = coinsMapper.selectByPrimaryKey(fid);
        return coin;
    }

    @RequestMapping("/zuul/getcoincnyprice")
    public String selectByCoincnyprice(Integer fid){
        JSONObject json = new JSONObject();
        if(fid != 6){
            Coins coin = coinsMapper.selectByPrimaryKey(fid);
            double coinprice = coin.getPrice()*coinsMapper.selectByPrimaryKey(6).getPrice();
            json.put("price",coinprice);
        }else {
            json.put("price",coinsMapper.selectByPrimaryKey(6).getPrice());
        }
        return json.toJSONString();
    }

    @Override
    public int updateByPrimaryKeySelective(Coins record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Coins record) {
        return 0;
    }

    @Override
    public List<Coins> selectAll() {
        return coinsMapper.selectAll();
    }

    @Override
    public Double selectRate(String coinName) {
        return coinsMapper.selectRate(coinName);
    }

    @Override
    public BigDecimal findPrice() {
        return coinsMapper.findPrice();
    }
}
