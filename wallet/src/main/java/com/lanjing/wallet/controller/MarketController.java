package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.service.MarketService;
import com.lanjing.wallet.model.Market;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MarketController {

    @Resource(name = "MarketService")
    private MarketService marketService;


    @RequestMapping("/app/getmarket")
    public String getMarketList(){
        JSONObject json = new JSONObject();
        List<Market> list = marketService.selectNew();
        List<Map<String,String>> markets = new ArrayList<>();
        for (Market market :list){
            Map<String,String> map = new HashMap<>();
            map.put("symbol",market.getSymbol());
            map.put("amount",market.getAmount()+"");
            map.put("price",market.getPrice()+"");
            map.put("CNY",market.getCnyprice()+"");
            double change = (market.getClose().doubleValue()-market.getOpen().doubleValue())/market.getOpen().doubleValue();
            map.put("change",change+"");
            markets.add(map);
        }
        json.put("code",200);
        json.put("msg","ok");
        json.put("data",markets);
        return json.toJSONString();
    }

    @RequestMapping("/app/getmarket2")
    public String getMarketList2(){
        JSONObject json = new JSONObject();
        List<Market> list = new ArrayList<>();
        Market market3 = new Market();
        Market market = new Market();
        market.setSymbol("BTC/USDT");
        market3 = marketService.selectBy(market).get(0);
        list.add(market3);
        market.setSymbol("ETH/USDT");
        market3 = marketService.selectBy(market).get(0);
        list.add(market3);
        market.setSymbol("EOS/USDT");
        market3 = marketService.selectBy(market).get(0);
        list.add(market3);
        Map<String,Map> markets = new HashMap<>();
        for (Market market1 :list){
            Map<String,String> map = new HashMap<>();
            map.put("symbol",market1.getSymbol());
            map.put("amount",market1.getAmount()+"");
            map.put("price",market1.getPrice()+"");
            map.put("CNY",market1.getCnyprice()+"");
            double change = (market1.getClose().doubleValue()-market1.getOpen().doubleValue())/market1.getOpen().doubleValue();
            map.put("change",change+"");
            markets.put(market1.getSymbol().replace("/USDT",""),map);
        }
        json.put("code",200);
        json.put("msg","ok");
        json.put("data",markets);
        return json.toJSONString();
    }
}
