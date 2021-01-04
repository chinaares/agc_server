package com.lanjing.wallet.systemService;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.ChangelogService;
import com.lanjing.wallet.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("systemUtil")
@RestController
public class systemUtil {

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private MappingTradeMapper mappingTradeMapper;

    @Autowired
    private ChangelogMapper changelogMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private ParametersMapper parametersMapper;

    @Autowired
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Autowired
    private ReleaseRecordMapper releaseRecordMapper;

    @Autowired
    private CoinLogMapper coinLogMapper;

    @Autowired
    private CoinsMapper coinsMapper;


    @Autowired
    private WelletsMapper welletService;

    @Resource(name = "ChangelogService")
    private ChangelogService changelogService;

    @Autowired
    private RecommendMapper recommendService;

    /***
     * 修改钱包单数据
     * userkey    用户key
     * coinId     币种Id
     * coinNum    数量
     * type1      0减少   1增加
     * type2      1,coinNum  2,frozenNum 3,lockNum  4,shiftNum
     * */
    public int uptWellet(String userkey,Integer coinId,double coinNum,Integer type1,Integer type2){
        if(coinNum < 0){
            coinNum = -coinNum;
        }
        Wellets wellet = welletsMapper.selectByUserandcoin(userkey,coinId);
        if(wellet != null){
            if(type2 == 1){
                if(type1 == 0){
                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue()-coinNum));
                }else if(type1 == 1){
                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue()+coinNum));
                }
            }else if(type2 == 2){
                if(type1 == 0){
                    wellet.setFrozennum(BigDecimal.valueOf(wellet.getFrozennum().doubleValue()-coinNum));
                }else if(type1 == 1){
                    wellet.setFrozennum(BigDecimal.valueOf(wellet.getFrozennum().doubleValue()+coinNum));
                }
            }else if(type2 == 3){
                if(type1 == 0){
                    wellet.setLocknum(BigDecimal.valueOf(wellet.getLocknum().doubleValue()-coinNum));
                }else if(type1 == 1){
                    wellet.setLocknum(BigDecimal.valueOf(wellet.getLocknum().doubleValue()+coinNum));
                }
            }else if(type2 == 4){
                if(type1 == 0){
                    wellet.setShiftnum(BigDecimal.valueOf(wellet.getShiftnum().doubleValue()-coinNum));
                }else if(type1 == 1){
                    wellet.setShiftnum(BigDecimal.valueOf(wellet.getShiftnum().doubleValue()+coinNum));
                }
            }
            return welletsMapper.updateByPrimaryKeySelective(wellet);
        }
        return 0;
    }


    /***
     * 修改钱包多数据
     * userkey    用户key
     * coinId     币种Id
     * coinNum    可提现数量
     * frozenNum      冻结数量
     * lockNum      锁仓数量
     * shiftNum      转入数量
     * */
    public int uptWellet(String userkey,Integer coinId,double coinNum,double frozenNum,double lockNum,double shiftNum){
        Wellets wellet = welletsMapper.selectByUserandcoin(userkey,coinId);
        if(wellet != null){
            wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + coinNum));
            wellet.setFrozennum(BigDecimal.valueOf(wellet.getFrozennum().doubleValue() + frozenNum));
            wellet.setLocknum(BigDecimal.valueOf(wellet.getLocknum().doubleValue() + lockNum));
            wellet.setShiftnum(BigDecimal.valueOf(wellet.getShiftnum().doubleValue() + shiftNum));
            return welletsMapper.updateByPrimaryKeySelective(wellet);
        }
        return 0;
    }


    /***
     * 添加委托列表数据
     *
     * userkey  用户
     * coinId   交易对id
     * cointype  交易对名称
     * type    1,买   2,卖
     * price   价格 usd
     * coinNum    挂单数量
     * */
    public void addMappingTradeLog(String userkey,Integer coinId ,String cointype ,Integer type ,double price,double coinNum){
        MappingTrade mappingTrade = new MappingTrade();
        mappingTrade.setFuser(userkey);
        mappingTrade.setCoinid(coinId);
        mappingTrade.setCointype(cointype);
        mappingTrade.setType(type);
        mappingTrade.setPrice(BigDecimal.valueOf(price));
        mappingTrade.setCoinnum(BigDecimal.valueOf(coinNum));
        mappingTrade.setRest(BigDecimal.valueOf(coinNum));
        mappingTrade.setTradenum(BigDecimal.valueOf(0));
        Date time = new Date();
        mappingTrade.setCreatetime(time);
        mappingTrade.setUpdatetime(time);
        mappingTrade.setState(1);
        mappingTradeMapper.insertSelective(mappingTrade);
    }


    //添加钱包记录
    public void addWellethistoryState(Integer type,String remark, double changenum ,Integer welletId){
        WellethistoryState wellethistoryState2 = new WellethistoryState();
        Date time = new Date();
        wellethistoryState2.setCreatetime(time);
        wellethistoryState2.setUpdatetime(time);
        wellethistoryState2.setState(1);
        wellethistoryState2.setType(type);
        wellethistoryState2.setRemark(remark);
        wellethistoryState2.setChangenum(BigDecimal.valueOf(changenum));
        wellethistoryState2.setWelletid(welletId);
        wellethistoryStateMapper.insertSelective(wellethistoryState2);
    }



    public Wellets selectByUserKey(String userkey, Integer coin) {
        List<Wellets> list = welletsMapper.selectByUserKey(userkey);
        Wellets wellet = null;
        for (Wellets wellet1 : list){
            if (wellet1.getCoinid() == coin){
                wellet = wellet1;
            }
        }
        return wellet;
    }
    /***
     * 修改委托列表数据
     *
     * userkey  用户
     * coinId   交易对id
     * rest       剩余数量
     * tradeNum   交易数量
     * */
    public void uptMappingTradeLog(Integer fId,double tradeNum){
        MappingTrade mappingTrade = mappingTradeMapper.selectByPrimaryKey(fId);

        double price = coinsMapper.selectByPrimaryKey(1).getPrice();

        WellethistoryState wellethistoryState2 = new WellethistoryState();
        Date time = new Date();
        wellethistoryState2.setCreatetime(time);
        wellethistoryState2.setUpdatetime(time);
        wellethistoryState2.setState(1);

        if(mappingTrade.getType() == 1){
            uptWellet(mappingTrade.getFuser(),3,tradeNum,1,1);
            uptWellet(mappingTrade.getFuser(),1,0,-tradeNum/mappingTrade.getPrice().doubleValue(),0,0);
            wellethistoryState2.setChangenum(BigDecimal.valueOf(tradeNum));
            wellethistoryState2.setWelletid(welletsMapper.selectByUserandcoin(mappingTrade.getFuser(),3).getFid());
        }else if(mappingTrade.getType() == 2) {
            mappingTrade.setPrice(BigDecimal.valueOf(price));
            uptWellet(mappingTrade.getFuser(),1,tradeNum/price,1,1);
            uptWellet(mappingTrade.getFuser(),3,0,-tradeNum,0,0);
            wellethistoryState2.setChangenum(BigDecimal.valueOf(tradeNum/price));
            wellethistoryState2.setWelletid(welletsMapper.selectByUserandcoin(mappingTrade.getFuser(),1).getFid());
            double releaseNum = changelogService.userTitanKT1(mappingTrade.getFuser());
            if(releaseNum <= 0){
                Wellets Titan = selectByUserKey(mappingTrade.getFuser(),1);
                Wellets Titanc = selectByUserKey(mappingTrade.getFuser(),2);
                double coincoinnum1 = 0;
                if(tradeNum/price>Titan.getShiftnum().doubleValue()){
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
                    if(coincoinnum1 >0){
                        welletService.updateByPrimaryKeySelective(Titanc);
                        welletService.updateByPrimaryKeySelective(Titan);
                        insertReleaseLog(mappingTrade.getFuser(),recommendService.selectByUserKey1(mappingTrade.getFuser()).getUsergrede(),tradeNum/price,2,coincoinnum1,ago,Titanc.getCoinnum().doubleValue(),1,coincoinnum1,2);
                    }
                }else {
                    double ago = Titanc.getCoinnum().doubleValue();
                    if(Titanc.getCoinnum().doubleValue()>=tradeNum/price*0.05 && Titanc.getCoinnum().doubleValue()>=0){
                        coincoinnum1 = tradeNum/price*0.05;
                        Titanc.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()-tradeNum/price*0.05));
                        Titan.setShiftnum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue() - tradeNum/price));
                        Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+tradeNum/price*0.05));
                        mappingTrade.setReleasenum(BigDecimal.valueOf(tradeNum/price*0.05));
                    }else if(Titanc.getCoinnum().doubleValue()<tradeNum/price*0.05 && Titanc.getCoinnum().doubleValue()>=0){
                        coincoinnum1 = Titanc.getCoinnum().doubleValue();
                        Titan.setShiftnum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue() - Titanc.getCoinnum().doubleValue()*20));
                        Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+Titanc.getCoinnum().doubleValue()));
                        mappingTrade.setReleasenum(Titanc.getCoinnum());
                        Titanc.setCoinnum(BigDecimal.valueOf(0));
                    }
                    if(coincoinnum1 >0){
                        welletService.updateByPrimaryKeySelective(Titanc);
                        welletService.updateByPrimaryKeySelective(Titan);
                        insertReleaseLog(mappingTrade.getFuser(),recommendService.selectByUserKey1(mappingTrade.getFuser()).getUsergrede(),tradeNum/price,2,coincoinnum1,ago,Titanc.getCoinnum().doubleValue(),1,coincoinnum1,2);
                    }
                }
            }
        }
        wellethistoryState2.setType(34);
        wellethistoryState2.setRemark("买入");
        wellethistoryStateMapper.insertSelective(wellethistoryState2);
        mappingTrade.setRest(BigDecimal.valueOf(mappingTrade.getRest().doubleValue()-tradeNum));
        mappingTrade.setTradenum(BigDecimal.valueOf(mappingTrade.getTradenum().doubleValue()+tradeNum));
        //mappingTrade.setFee(BigDecimal.valueOf(tradeNum*0.002));
        /*if(mappingTrade.getType() == 2){
            *//*Wellets Titan = welletsMapper.selectByUserandcoin(mappingTrade.getFuser(),1);
            Wellets Titanc = welletsMapper.selectByUserandcoin(mappingTrade.getFuser(),2);
            uptWellet(mappingTrade.getFuser(),2,tradeNum*0.05,0,1);
            uptWellet(mappingTrade.getFuser(),1,tradeNum*0.05,1,1);
            insertReleaseLog(mappingTrade.getFuser(),0,tradeNum*0.05,2,tradeNum*0.05,Titanc.getCoinnum().doubleValue(),Titanc.getCoinnum().doubleValue()-(tradeNum*0.05),1,tradeNum*0.05,2);*//*
            mappingTrade.setReleasenum(BigDecimal.valueOf(tradeNum*0.05));
        }*/
        mappingTrade.setUpdatetime(time);
        if(mappingTrade.getCoinnum().doubleValue() == mappingTrade.getTradenum().doubleValue()){
            mappingTrade.setState(2);
        }
        mappingTradeMapper.updateByPrimaryKeySelective(mappingTrade);
    }


    /***
     * 添加理财释放记录记录
     *
     * userkey  用户
     * usergrede   用户等级
     * cointype1   减少的币种
     * coincoinnum1       减少数量
     * coinnumago    减少前数量
     * coinnumafter   减少后的数量
     * cointype2    增加币种
     * coinnum2     增加数量
     * type     1,日理财   3,理财等级加权    4,理财分享加权  5,释放等级加权     6,释放分享加权
     * */
    public void insertAirdropLog(String userkey,Integer usergrede ,Integer cointype1,double coincoinnum1,double coinnumago,double coinnumafter,Integer cointype2,double coinnum2,Integer type){
        Changelog changelog = new Changelog();
        changelog.setUserkey(userkey);
        changelog.setCointype1(cointype1);
        changelog.setCoinnum1(BigDecimal.valueOf(-coincoinnum1));
        changelog.setCoinnumago(BigDecimal.valueOf(coinnumago));
        changelog.setCoinnumafter(BigDecimal.valueOf(coinnumafter));
        changelog.setCointype2(cointype2);
        changelog.setCoinnum2(BigDecimal.valueOf(coinnum2));
        changelog.setType(type);
        changelog.setCraetetime(new Date());
        changelogMapper.insertSelective(changelog);
    }

    /***
     * 添加交易释放记录
     *
     * userkey  用户
     * usergrede   用户等级
     * buynum   交易量
     * cointype1   减少的币种
     * coincoinnum1       减少数量
     * coinnumago    减少前数量
     * coinnumafter   减少后的数量
     * cointype2    增加币种
     * coinnum2     增加数量
     * type     2,交易释放
     * */
    public void insertReleaseLog(String userkey ,Integer usergrede ,double buynum, Integer cointype1,double coincoinnum1,double coinnumago,double coinnumafter,Integer cointype2,double coinnum2,Integer type){
        /*Changelog changelog = new Changelog();
        changelog.setUserkey(userkey);
        changelog.setBuynum(BigDecimal.valueOf(buynum));
        changelog.setCointype1(cointype1);
        changelog.setCoinnum1(BigDecimal.valueOf(-coincoinnum1));
        changelog.setCoinnumago(BigDecimal.valueOf(coinnumago));
        changelog.setCoinnumafter(BigDecimal.valueOf(coinnumafter));
        changelog.setCointype2(cointype2);
        changelog.setCoinnum2(BigDecimal.valueOf(coinnum2));
        changelog.setType(type);
        changelog.setCraetetime(new Date());
        //Titan释放记录
        WellethistoryState wellethistoryState = new WellethistoryState();
        Date time = new Date();
        wellethistoryState.setCreatetime(time);
        wellethistoryState.setUpdatetime(time);
        wellethistoryState.setState(1);
        wellethistoryState.setChangenum(BigDecimal.valueOf(coinnum2));
        wellethistoryState.setWelletid(welletsMapper.selectByUserandcoin(userkey,1).getFid());
        wellethistoryState.setType(31);
        wellethistoryState.setRemark("交易释放");
        wellethistoryStateMapper.insertSelective(wellethistoryState);*/

        Changelog changelog = new Changelog();
        changelog.setUserkey(userkey);
        changelog.setBuynum(BigDecimal.valueOf(buynum));
        changelog.setCointype1(cointype1);
        changelog.setCoinnum1(BigDecimal.valueOf(-coincoinnum1));
        changelog.setCoinnumago(BigDecimal.valueOf(coinnumago));
        changelog.setCoinnumafter(BigDecimal.valueOf(coinnumafter));
        changelog.setCointype2(cointype2);
        changelog.setCoinnum2(BigDecimal.valueOf(coinnum2));
        changelog.setType(type);
        changelog.setCraetetime(new Date());
        changelogMapper.insertSelective(changelog);
        insertReleaseRecord(userkey,cointype1,coincoinnum1,0.05,cointype2,coinnum2);
        addcoinLog(userkey,cointype1,coincoinnum1,22,(int) (System.currentTimeMillis() / 1000),"释放减少",null);
        addcoinLog(userkey,cointype2,coinnum2,2,(int) (System.currentTimeMillis() / 1000),"卖出释放",null);
    }

    public void insertReleaseRecord(String userkey,int fromcoin,double fromcoinNum,double rate,int tocoin,double tocoinNum){
        ReleaseRecord releaseRecord = new ReleaseRecord();
        releaseRecord.setUserKey(userkey);
        releaseRecord.setReleaseToCoin(tocoin);
        releaseRecord.setReleaseFromAmount(fromcoinNum);
        releaseRecord.setReleaseFromCoin(fromcoin);
        releaseRecord.setReleaseRate(rate);
        releaseRecord.setReleaseTime((int) (System.currentTimeMillis() / 1000));
        releaseRecord.setReleaseToAmount(tocoinNum);
        releaseRecordMapper.insertSelective(releaseRecord);
    }

    public void addcoinLog(String userkey,int coinId,double changeNum,int changetype,int time,String remark,Long orderId){
        CoinLog coinLog = new CoinLog();
        coinLog.setUserKey(userkey);
        coinLog.setChangeCoin(coinId);
        coinLog.setChangeAmount(changeNum);
        coinLog.setChangeDesc(remark);
        coinLog.setChangeTime(time);
        coinLog.setChangeType(changetype);
        if(orderId != null){
            coinLog.setOrderId(orderId);
        }
        coinLogMapper.insertSelective(coinLog);
    }




    /***
     * 判断用户n代内 某等级用户的数量
     *
     * usergrede    用户等级
     * beginLev     开始层级
     * endLev       结束层级
     * cover_code   标识码
     * */
    public int checkUsergredesize(Integer usergrede,Integer beginLev,Integer endLev,String cover_code){
        Map map = new HashMap();
        map.put("usergrede",usergrede);
        map.put("beginLev",beginLev);
        map.put("endLev",endLev);
        map.put("cover_code",cover_code);
        int size = recommendMapper.selectBycover_code(map);
        return size;
    }

    @RequestMapping("/sys/checkUsergrede")
    public void checkUsergrede(String userkey){
        Recommend recommend = recommendMapper.selectByUserKey1(userkey);
        int usergrede = recommend.getUsergrede();
        String cover_code = recommend.getCoverCode();
        int lev = recommend.getLev();
        for(int i = 1;i<4;i++){
            if ((lev-i)>=1){
                String cover_code1 = cover_code.substring(0,cover_code.length()-(4*i));
                int size = checkUsergredesize(usergrede,lev-i+1,lev-i+3,cover_code1);
                if (size>=2){
                    Recommend recommend1 = recommendMapper.selectBycover_code1(cover_code1);
                    if(recommend1.getUsergrede()<=usergrede && recommend1.getUsergrede() <7){
                        recommend1.setUsergrede(usergrede+1);
                        recommendMapper.updateByPrimaryKeySelective(recommend1);
                        checkUsergrede(recommend1.getUserkey1());
                    }
                }
            }else {
                break;
            }
        }
    }


    public int checkgrede(String userkey){
        List<Recommend> recommendList = recommendMapper.selectByUserKey21(userkey);
        Recommend re = recommendMapper.selectByUserKey1(userkey);
        if(re.getNum() != recommendList.size()){
            re.setNum(recommendList.size());
            recommendMapper.updateByPrimaryKeySelective(re);
        }
        int grede1 = 0;
        int grede2 = 0;
        int grede3 = 0;
        int grede4 = 0;
        int grede5 = 0;
        int grede6 = 0;
        int grede7 = 0;
        for (Recommend recommend : recommendList){
            if(recommend.getUsergrede()>0){
                grede1++;
            }
            if(recommend.getUsergrede()>1){
                grede2++;
            }
            if(recommend.getUsergrede()>2){
                grede3++;
            }
            if(recommend.getUsergrede()>3){
                grede4++;
            }
            if(recommend.getUsergrede()>4){
                grede5++;
            }
            if(recommend.getUsergrede()>5){
                grede6++;
            }
            if(recommend.getUsergrede()>6){
                grede7++;
            }
            List<Recommend> recommendList2 = recommendMapper.selectByUserKey21(recommend.getUserkey1());
            for (Recommend recommend2 : recommendList2){
                if(recommend2.getUsergrede()>0){
                    grede1++;
                }
                if(recommend2.getUsergrede()>1){
                    grede2++;
                }
                if(recommend2.getUsergrede()>2){
                    grede3++;
                }
                if(recommend2.getUsergrede()>3){
                    grede4++;
                }
                if(recommend2.getUsergrede()>4){
                    grede5++;
                }
                if(recommend2.getUsergrede()>5){
                    grede6++;
                }
                if(recommend2.getUsergrede()>6){
                    grede7++;
                }
                List<Recommend> recommendList3 = recommendMapper.selectByUserKey21(recommend2.getUserkey1());
                for (Recommend recommend3 : recommendList3){
                    if(recommend3.getUsergrede()>0){
                        grede1++;
                    }
                    if(recommend3.getUsergrede()>1){
                        grede2++;
                    }
                    if(recommend3.getUsergrede()>2){
                        grede3++;
                    }
                    if(recommend3.getUsergrede()>3){
                        grede4++;
                    }
                    if(recommend3.getUsergrede()>4){
                        grede5++;
                    }
                    if(recommend3.getUsergrede()>5){
                        grede6++;
                    }
                    if(recommend3.getUsergrede()>6){
                        grede7++;
                    }
                }
            }
        }
        if (grede6>=2){
            return 7;
        }else if (grede5>=2){
            return 6;
        }else if (grede4>=2){
            return 5;
        }else if (grede3>=2){
            return 4;
        }else if (grede2>=2){
            return 3;
        }else if (grede1>=2){
            return 2;
        }else if(recommendList.size()>=20){
            return 1;
        }
        return 0;
    }

    @Scheduled(cron = "0 0 20 * * ? ")
    public void gredeup(){
        int max = recommendMapper.selectMaxlev();
        for (int i =max;i>0;i--){
            List<Recommend> list = recommendMapper.selectByLev(i);
            for (Recommend recommend : list){
                int grede = checkgrede(recommend.getUserkey1());
                if(recommend.getUsergrede() < grede){
                    recommend.setUsergrede(grede);
                    recommendMapper.updateByPrimaryKeySelective(recommend);
                }
            }
        }
    }


    public String unlock(int code){
        String nodeurl = "";
        String pwd = "";
        String back = null;
        if(code == 1){
            nodeurl = parametersMapper.selectByKey("nodeurl").getKeyvalue();
            pwd = parametersMapper.selectByKey("nodepwd1").getKeyvalue();
        }else if(code == 2){
            nodeurl = parametersMapper.selectByKey("nodeurl1").getKeyvalue();
            pwd = parametersMapper.selectByKey("nodepwd2").getKeyvalue();
        }
        String str = "{\n" +
                "\"jsonrpc\": \"2.0\", \n" +
                "\"method\": \"call\", \n" +
                "\"params\": [0, \"unlock\", [\""+pwd+"\"]], \"id\": 1\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        try {
            back = HttpUtil.sendPost(nodeurl,jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> json = JSONObject.parseObject(back,Map.class);
        List<Object> map = (List<Object>) json.get("error");
        System.out.println(map);
        if (map != null){
            return null;
        }
        return "success";
    }


    public String Transfer(String address , String userkeys, double num){
        unlock(1);
        String addre = parametersMapper.selectByKey("address").getKeyvalue();
        String back = null;
        int num1 = (int) num;
        try {
            String str = "{\"jsonrpc\": \"2.0\",\"method\": \"call\",\"params\": [0, \"transfer2\", [\""+addre+"\",\""+address+"\","+num1+",\"TITAN\",\""+userkeys+"\",true]], \"id\": 1}";
            JSONObject jsonObject = JSONObject.parseObject(str);
            back = HttpUtil.sendPost(parametersMapper.selectByKey("nodeurl").getKeyvalue(),jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> json = JSONObject.parseObject(back,Map.class);
        List<Object> map = (List<Object>) json.get("result");
        System.out.println(map);
        if (map.size()>0){
            System.out.println(map.get(0));
            return map.get(0)+"";
        }
        return null;
    }

    public String Transfer1( double num){
        unlock(1);
        String addre = parametersMapper.selectByKey("address1").getKeyvalue();
        String address = parametersMapper.selectByKey("address").getKeyvalue();
        String back = null;
        int num1 = (int) num;
        try {
            String str = "{\"jsonrpc\": \"2.0\",\"method\": \"call\",\"params\": [0, \"transfer2\", [\""+addre+"\",\""+address+"\","+num1+",\"TITAN\",\"\",true]], \"id\": 1}";
            JSONObject jsonObject = JSONObject.parseObject(str);
            back = HttpUtil.sendPost(parametersMapper.selectByKey("nodeurl1").getKeyvalue(),jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> json = JSONObject.parseObject(back,Map.class);
        List<Object> map = (List<Object>) json.get("result");
        System.out.println(map);
        if (map.size()>0){
            System.out.println(map.get(0));
            return map.get(0)+"";
        }
        return null;
    }


    public String getwelletId(){
        String address = parametersMapper.selectByKey("address").getKeyvalue();
        String back = null;
        try {
            back = HttpUtil.sendPost(parametersMapper.selectByKey("nodeurl").getKeyvalue(),"{\n" +
                    "\"jsonrpc\": \"2.0\", \n" +
                    "\"method\": \"call\", \n" +
                    "\"params\": [0, \"get_account\", [\""+address+"\"]], \"id\": 1\n" +
                    "}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.parseObject(back);
        Map result = (Map) json.get("result");
        return result.get("id")+"";
    }


}
