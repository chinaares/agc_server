package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.ParametersMapper;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.model.MappingTrade;
import com.lanjing.wallet.model.WellethistoryState;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.*;
import com.lanjing.wallet.systemService.systemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
public class MappingTradeController extends BaseContrller {

    @Autowired
    private systemUtil systemutil;

    @Resource(name = "MappingTradeService")
    private MappingTradeService mappingTradeService;

    @Resource(name = "MarketService")
    private MarketService marketService;

    @Resource(name = "MappingTradeOrderService")
    private MappingTradeOrderService mappingTradeOrderService;

    @Resource(name = "WelletService")
    private WelletService welletService;

    @Resource(name = "CoinsService")
    private CoinsService coinsService;

    @Resource(name = "RecommendService")
    private RecommendService recommendService;

    @Resource(name = "WellethistoryStateService")
    private WellethistoryStateService wellethistoryStateService;

    @Autowired
    private ParametersMapper parametersMapper;

    @Resource(name = "ChangelogService")
    private ChangelogService changelogService;


    @RequestMapping("/app/getmappingtrade")
    public String getmappingTradelist(){
        JSONObject json = new JSONObject();
        Integer page1 = 1;
        Integer size1 = 6;
        MappingTrade mappingTrade = new MappingTrade();
        try {
            mappingTrade.setCoinid(1);
            mappingTrade.setType(1);
            mappingTrade.setState(1);
            List<MappingTrade> list = mappingTradeService.selectBy(mappingTrade,page1,size1);
            List<JSONObject> mapList =  new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (MappingTrade mappingTrade1 : list){
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(mappingTrade1));
                jsonObject.put("createtime",format.format(mappingTrade1.getCreatetime()));
                jsonObject.put("updatetime",format.format(mappingTrade1.getUpdatetime()));
                mapList.add(jsonObject);
            }
            mappingTrade.setCoinid(1);
            mappingTrade.setType(2);
            mappingTrade.setState(1);
            List<MappingTrade> list1 = mappingTradeService.selectBy(mappingTrade,page1,size1);
            List<JSONObject> mapList1 =  new ArrayList<>();
            for (MappingTrade mappingTrade1 : list1){
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(mappingTrade1));
                jsonObject.put("createtime",format.format(mappingTrade1.getCreatetime()));
                jsonObject.put("updatetime",format.format(mappingTrade1.getUpdatetime()));
                mapList1.add(jsonObject);
            }

            Coins coin = coinsService.selectByPrimaryKey(1);
            json.put("CNYprice",coin.getPrice()*7);
            json.put("price",coin.getPrice());
            json.put("buydata",mapList);
            json.put("selldata",mapList1);
            json.put("msg","ok");
            json.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/getusermaptradelist")
    private String getUsermaptradelist(@RequestBody Map<String,String> map){
        JSONObject json = new JSONObject();
        String page = map.get("page");
        String size = map.get("size");
        String state = map.get("state");
        Integer page1 = null;
        Integer size1 = null;
        try{
            if(page != null && !"".equals(page)){
                page1 = Integer.parseInt(page);
            }
            if(size != null && !"".equals(size)){
                size1 = Integer.parseInt(size);
            }
            String userkey = getUserKey();
            MappingTrade mappingtrade = new MappingTrade();
            mappingtrade.setCoinid(1);
            mappingtrade.setFuser(userkey);
            if("1".equals(state)){
                mappingtrade.setState(1);
            }
            List<MappingTrade> list = mappingTradeService.selectBy(mappingtrade,page1,size1);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<JSONObject> orderList1 =  new ArrayList<>();
            for (MappingTrade mappingTrade : list){
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(mappingTrade));
                jsonObject.put("createtime",format.format(mappingTrade.getCreatetime()));
                jsonObject.put("updatetime",format.format(mappingTrade.getUpdatetime()));
                if (mappingTrade.getType()==1){
                    jsonObject.put("rest",(mappingTrade.getRest().doubleValue())/mappingTrade.getPrice().doubleValue()+mappingTrade.getFee().doubleValue());
                }else if(mappingTrade.getType()==2){
                    jsonObject.put("rest",mappingTrade.getRest().doubleValue()+mappingTrade.getFee().doubleValue());
                    jsonObject.put("tradenum",mappingTrade.getTradenum().doubleValue()/mappingTrade.getPrice().doubleValue());
                }
                jsonObject.put("type",mappingTrade.getType());
                orderList1.add(jsonObject);
            }
            json.put("data",orderList1);
            json.put("msg","ok");
            json.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/recall")
    public  String recall(@RequestBody Map<String,String> map){
        JSONObject json = new JSONObject();
        String fId = map.get("Id");
        if(fId == null || "".equals(fId)){
            json.put("msg","参数缺失！");
            json.put("code",300);
            return json.toJSONString();
        }
        try {
            MappingTrade mappingTrade = mappingTradeService.selectByPrimaryKey(Integer.parseInt(fId));
            mappingTrade.setState(4);
            int i = mappingTradeService.updateByPrimaryKeySelective(mappingTrade);
            if(i>0){
                json.put("msg","ok");
                json.put("code",200);
            }else{
                json.put("msg","no");
                json.put("code",204);
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }

    //trade_buy_switch trade_sell_switch

    @RequestMapping("/app/trade")
    public String trade(@RequestBody Map<String,String> map){
        JSONObject json = new JSONObject();
        String coinNum = map.get("coinNum");
        String type = map.get("type");
        String trade_buy_switch =  parametersMapper.selectByKey("trade_buy_switch").getKeyvalue();
        String trade_sell_switch = parametersMapper.selectByKey("trade_sell_switch").getKeyvalue();

        System.out.println("------------type："+type+";-----trade_buy_switch:"+trade_buy_switch+";trade_sell_switch:"+trade_sell_switch);
        if("1".equals(type) && "0".equals(trade_buy_switch)){
            json.put("msg","停止买入挂单！");
            json.put("code",300);
            return json.toJSONString();
        }else if("2".equals(type) && "0".equals(trade_sell_switch)){
            json.put("msg","停止卖出挂单！");
            json.put("code",300);
            return json.toJSONString();
        }
        String userkey = getUserKey();
        double price = coinsService.selectByPrimaryKey(1).getPrice();
        if(coinNum == null || "".equals(coinNum)||type == null || "".equals(type)){
            json.put("msg","参数缺失！");
            json.put("code",300);
            return json.toJSONString();
        }

        if(Double.parseDouble(coinNum) <= 0){
            json.put("msg","交易数量必须大于0！");
            json.put("code",301);
            return json.toJSONString();
        }

        MappingTrade mappingTrade1 = new MappingTrade();
        mappingTrade1.setFuser(userkey);
        mappingTrade1.setState(1);
        mappingTrade1.setType(Integer.parseInt(type));
        List<MappingTrade>  list = mappingTradeService.selectBy(mappingTrade1,1,10);
        System.out.print("---------"+list.size());
        if(list.size()>=1){
            json.put("msg","目前存在挂单！");
            json.put("code",302);
            return json.toJSONString();
        }
        try {
            MappingTrade mappingTrade = new MappingTrade();
            mappingTrade.setFuser(getUserKey());
            mappingTrade.setType(Integer.parseInt(type));
            Wellets Titan = welletService.selectByUserKey(userkey,1);
            double num = Double.parseDouble(coinNum);
            if(num<0){
                num = - num;
            }
            //卖出记录
            WellethistoryState wellethistoryState2 = new WellethistoryState();
            wellethistoryState2.setChangenum(BigDecimal.valueOf(-num*0.998));
            Date time = new Date();
            wellethistoryState2.setCreatetime(time);
            wellethistoryState2.setUpdatetime(time);
            wellethistoryState2.setState(1);

            if("1".equals(type)){

                wellethistoryState2.setType(35);
                wellethistoryState2.setRemark("卖出");
                wellethistoryState2.setWelletid(Titan.getFid());

                if(Titan.getCoinnum().doubleValue() <= 0){
                    json.put("msg","余额不足");
                    json.put("code",303);
                    return json.toJSONString();
                }

                if(Titan.getCoinnum().doubleValue()<num){
                    json.put("msg","余额不足");
                    json.put("code",303);
                    return json.toJSONString();
                }
                Titan.setFrozennum(BigDecimal.valueOf(Titan.getFrozennum().doubleValue()+num*0.998));
                Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()-num));
                welletService.updateByPrimaryKeySelective(Titan);
                //systemutil.addcoinLog(userkey,Titan.getCoinid(),num,27,(int) (System.currentTimeMillis() / 1000),"买入",null);
                /*systemutil.uptWellet(userkey,1,num*0.05/price1,1,1);
                systemutil.uptWellet(userkey,2,num*0.05/price1,0,1);*/
            }else if("2".equals(type)){

                Wellets USD = welletService.selectByUserKey(userkey,3);
                if(USD.getCoinnum().doubleValue() <= 0){
                    json.put("msg","余额不足");
                    json.put("code",303);
                    return json.toJSONString();
                }
                if(USD.getCoinnum().doubleValue()<num){
                    json.put("msg","余额不足");
                    json.put("code",303);
                    return json.toJSONString();
                }

                wellethistoryState2.setType(35);
                wellethistoryState2.setRemark("卖出");
                wellethistoryState2.setWelletid(USD.getFid());


                USD.setFrozennum(BigDecimal.valueOf(USD.getFrozennum().doubleValue()+num*0.998));
                USD.setCoinnum(BigDecimal.valueOf(USD.getCoinnum().doubleValue()-num));
                welletService.updateByPrimaryKeySelective(USD);
                //systemutil.addcoinLog(userkey,USD.getCoinid(),num,27,(int) (System.currentTimeMillis() / 1000),"卖出",null);
                /*double releaseNum = changelogService.userTitanKT1(userkey);
                if(releaseNum <= 0){
                    Wellets Titanc = welletService.selectByUserKey(userkey,2);
                    double coincoinnum1 = 0;
                    if(num*0.998/price>Titan.getShiftnum().doubleValue()){
                        double ago = Titanc.getCoinnum().doubleValue();
                        double shift = Titan.getShiftnum().doubleValue();
                        if(Titanc.getCoinnum().doubleValue()>=shift*0.05 && Titanc.getCoinnum().doubleValue()>=0){
                            Titanc.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()-shift*0.05));
                            Titan.setShiftnum(BigDecimal.valueOf(0));
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+shift*0.05));
                            coincoinnum1 = shift*0.05;
                            mappingTrade.setReleasenum(BigDecimal.valueOf(shift*0.05));
                        }else if(Titanc.getCoinnum().doubleValue()<shift*0.05 && Titanc.getCoinnum().doubleValue()>=0){
                            coincoinnum1 = Titanc.getCoinnum().doubleValue();
                            Titan.setShiftnum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue()-Titanc.getCoinnum().doubleValue()*20));
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+Titanc.getCoinnum().doubleValue()));
                            mappingTrade.setReleasenum(Titanc.getCoinnum());
                            Titanc.setCoinnum(BigDecimal.valueOf(0));
                        }
                        welletService.updateByPrimaryKeySelective(Titanc);
                        welletService.updateByPrimaryKeySelective(Titan);
                        systemutil.insertReleaseLog(userkey,recommendService.selectByUserKey1(userkey).getUsergrede(),num*0.998/price,2,coincoinnum1,ago,Titanc.getCoinnum().doubleValue(),1,coincoinnum1,2);
                    }else {
                        double ago = Titanc.getCoinnum().doubleValue();
                        if(Titanc.getCoinnum().doubleValue()>=num*0.998/price*0.05 && Titanc.getCoinnum().doubleValue()>=0){
                            coincoinnum1 = num*0.998/price*0.05;
                            Titanc.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()-num*0.998/price*0.05));
                            Titan.setShiftnum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue() - num/price));
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+num*0.998/price*0.05));
                            mappingTrade.setReleasenum(BigDecimal.valueOf(num*0.998/price*0.05));
                        }else if(Titanc.getCoinnum().doubleValue()<num*0.998/price*0.05 && Titanc.getCoinnum().doubleValue()>=0){
                            coincoinnum1 = Titanc.getCoinnum().doubleValue();
                            Titan.setShiftnum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue() - Titanc.getCoinnum().doubleValue()*20));
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+Titanc.getCoinnum().doubleValue()));
                            mappingTrade.setReleasenum(Titanc.getCoinnum());
                            Titanc.setCoinnum(BigDecimal.valueOf(0));
                        }
                        welletService.updateByPrimaryKeySelective(Titanc);
                        welletService.updateByPrimaryKeySelective(Titan);
                        systemutil.insertReleaseLog(userkey,recommendService.selectByUserKey1(userkey).getUsergrede(),num*0.998/price,2,coincoinnum1,ago,Titanc.getCoinnum().doubleValue(),1,coincoinnum1,2);
                    }
                }*/
            }
            wellethistoryStateService.insertSelective(wellethistoryState2);
            wellethistoryState2.setType(30);
            wellethistoryState2.setRemark("手续费");
            wellethistoryState2.setChangenum(BigDecimal.valueOf(-num*0.002));
            wellethistoryStateService.insertSelective(wellethistoryState2);

            mappingTrade.setCoinid(1);
            mappingTrade.setCointype("titanusd");
            if("1".equals(type)){
                mappingTrade.setCoinnum(BigDecimal.valueOf(price*num*0.998));
                mappingTrade.setRest(BigDecimal.valueOf(price*num*0.998));
            }else if("2".equals(type)){
                mappingTrade.setCoinnum(BigDecimal.valueOf(num*0.998));
                mappingTrade.setRest(BigDecimal.valueOf(num*0.998));
            }
            mappingTrade.setFee(BigDecimal.valueOf(num*0.002));
            mappingTrade.setTradenum(BigDecimal.valueOf(0));
            mappingTrade.setPrice(BigDecimal.valueOf(price));
            mappingTrade.setState(1);
            mappingTrade.setCreatetime(time);
            mappingTrade.setUpdatetime(time);
            int i = mappingTradeService.insertSelective(mappingTrade);
            if(i>0){
                json.put("msg","ok");
                json.put("code",200);
            }else {
                json.put("msg","no");
                json.put("code",400);
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }

}
