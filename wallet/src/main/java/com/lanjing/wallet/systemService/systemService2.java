package com.lanjing.wallet.systemService;

import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.model.DaySettlement;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("systemService2")
@RestController
public class systemService2 {

    @Resource(name = "systemUtil")
    private systemUtil systemutil;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private MappingTradeMapper mappingTradeMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private ChangelogMapper changelogMapper;

    @Autowired
    private DaySettlementMapper daySettlementMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private ParametersMapper parametersMapper;

    @Autowired
    private TrasferlistMapper trasferlistMapper;

    @Autowired
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Autowired
    private MarketMapper marketMapper;

    @Autowired
    private MatchlistMapper matchlistMapper;

    @Autowired
    private AutolistMapper autolistMapper;

    @Autowired
    private RedisDao redisDao;

    @RequestMapping("/sys/jiaquan")
    public void KTjiaquan(){
        Coins TitanCoin = coinsMapper.selectByPrimaryKey(1);
        Coins UsdCoin = coinsMapper.selectByPrimaryKey(4);
        double Tprice = TitanCoin.getPrice();
        double Uprice = UsdCoin.getPrice();
        int max = recommendMapper.selectMaxlev();
        for (int i =max;i>0;i--){
            List<Recommend> list = recommendMapper.selectByLev(i);
            for (Recommend recommend : list){
                double dayAirdrop = changelogMapper.userUSDKT(recommend.getUserkey1());
                double daytrade = changelogMapper.userTitanKT(recommend.getUserkey1());
                Map map = null;
                if(recommend.getCoverCode()!= null && !"".equals(recommend.getCoverCode())){
                    map = daySettlementMapper.selectByLevCode(recommend.getLev()+1,recommend.getCoverCode());
                    double dayteamAirdropsum = (double) map.get("dayteamAirdropsum");
                    double WeightAirdropsum = (double) map.get("WeightAirdropsum");
                    double dayteamtradesum = (double) map.get("dayteamtradesum");
                    double WeightTradesum = (double) map.get("WeightTradesum");
                    double dayAirdropsum = (double) map.get("dayAirdropsum");
                    double daytradesum = (double) map.get("daytradesum");
                    DaySettlement daySettlement = new DaySettlement();
                    daySettlement.setCoverCode(recommend.getCoverCode());
                    daySettlement.setLev(recommend.getLev());
                    daySettlement.setUserkey(recommend.getUserkey1());
                    daySettlement.setGrede(recommend.getUsergrede());
                    daySettlement.setDayairdrop(dayAirdrop);
                    daySettlement.setDaytrade(daytrade);
                    daySettlement.setDayteamairdrop(dayteamAirdropsum+dayAirdropsum);
                    daySettlement.setDayteamtrade(dayteamtradesum+daytradesum);
                    daySettlement.setWeightairdrop(Double.valueOf(0));
                    if(recommend.getUsergrede() > 0){
                        daySettlement.setWeightairdrop(daySettlement.getDayteamairdrop()*0.01*recommend.getUsergrede()-WeightAirdropsum);
                    }
                    double weighttrade = 0;
                    daySettlement.setWeighttrade(Double.valueOf(0));
                    if(recommend.getUsergrede()>0 && recommend.getUsergrede() <= 6){
                        double weighttradenum = daySettlement.getDayteamtrade()*0.02*recommend.getUsergrede()-WeightTradesum;
                        if(weighttrade>0){
                            daySettlement.setWeighttrade(weighttradenum);
                        }
                    }else if(recommend.getUsergrede() == 7) {
                        //同级直推
                        if(!"99999999".equals(recommend.getUserkey2())){
                            List<DaySettlement> daySettlementslist = daySettlementMapper.selectByLevCode1(recommend.getLev()+1,recommend.getCoverCode());
                            for (DaySettlement daySettlement1 : daySettlementslist){
                                weighttrade = weighttrade + daySettlement1.getWeighttrade().doubleValue()*0.1;
                            }
                        }
                    }
                    daySettlement.setCreatetime(new Date());
                    //Wellets Titan = welletsMapper.selectByUserandcoin(recommend.getUserkey1(),1);
                    Wellets Titanc = welletsMapper.selectByUserandcoin(recommend.getUserkey1(),2);
                    Wellets Usd = welletsMapper.selectByUserandcoin(recommend.getUserkey1(),4);
                    if (Titanc != null && Usd != null ){
                        if(daySettlement.getWeighttrade()>0){
                            //等级交易加权
                            systemutil.insertAirdropLog(recommend.getUserkey1(),recommend.getUsergrede(),2,daySettlement.getWeighttrade()+weighttrade,Titanc.getCoinnum().doubleValue(),Titanc.getCoinnum().doubleValue()-daySettlement.getWeighttrade()-weighttrade,1,daySettlement.getWeighttrade()+weighttrade,5);
                        }
                        if(daySettlement.getWeightairdrop()>0){
                            //等级理财加权
                            systemutil.insertAirdropLog(recommend.getUserkey1(),recommend.getUsergrede(),2,0,Titanc.getCoinnum().doubleValue(),Titanc.getCoinnum().doubleValue()+daySettlement.getWeightairdrop(),2,daySettlement.getWeightairdrop(),3);
                            //systemutil.insertAirdropLog(recommend.getUserkey1(),recommend.getUsergrede(),4,daySettlement.getWeightairdrop()*Tprice/Uprice,Usd.getCoinnum().doubleValue(),Usd.getCoinnum().doubleValue(),2,daySettlement.getWeightairdrop(),3);
                        }
                        double num = daySettlement.getWeightairdrop()-daySettlement.getWeighttrade();
                        if(num>0){
                            systemutil.uptWellet(recommend.getUserkey1(),2,num,1,1);
                        }else if(num<0){
                            systemutil.uptWellet(recommend.getUserkey1(),2,num,0,1);
                        }
                        if(daytradesum > 0 || daySettlement.getWeighttrade()>0){
                            systemutil.uptWellet(recommend.getUserkey1(),1,daytradesum+daySettlement.getWeighttrade(),1,1);
                        }
                        daySettlementMapper.insertSelective(daySettlement);
                    }
                }
            }
        }

    }

    @RequestMapping("/sys/shareUSDKT")
    public void shareUSDKT(){
        int max = recommendMapper.selectMaxlev();
        for (int lev = 1 ;lev<max;lev++){
            List<Recommend> list = recommendMapper.selectByLev(lev);
            for (Recommend recommend : list){
                double sum = 0;
                int num = recommend.getNum();
                Wellets Titanc = welletsMapper.selectByUserandcoin(recommend.getUserkey1(),2);
                if(num>=1){
                    List<Recommend> downlist1 = recommendMapper.selectByUserKey2list(recommend.getUserkey1());
                    for (Recommend recommend1 : downlist1){
                        sum += changelogMapper.userUSDKT(recommend1.getUserkey1())*0.2;
                        if(num>=2){
                            List<Recommend> downlist2 = recommendMapper.selectByUserKey2list(recommend1.getUserkey1());
                            for (Recommend recommend2 : downlist2){
                                sum += changelogMapper.userUSDKT(recommend2.getUserkey1())*0.15;
                                if (num >= 3){
                                    List<Recommend> downlist3 = recommendMapper.selectByUserKey2list(recommend2.getUserkey1());
                                    for (Recommend recommend3 : downlist3){
                                        sum += changelogMapper.userUSDKT(recommend3.getUserkey1())*0.1;
                                        if(num >= 4){
                                            List<Recommend> downlist4 = recommendMapper.selectByUserKey2list(recommend3.getUserkey1());
                                            for (Recommend recommend4 : downlist4){
                                                sum += changelogMapper.userUSDKT(recommend4.getUserkey1())*0.05;
                                                if (num >= 5){
                                                    List<Recommend> downlist5 = recommendMapper.selectByUserKey2list(recommend4.getUserkey1());
                                                    for (Recommend recommend5 : downlist5){
                                                        sum += changelogMapper.userUSDKT(recommend5.getUserkey1())*0.05;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(sum >0){
                    Titanc.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()+sum));
                    int i =welletsMapper.updateByPrimaryKeySelective(Titanc);
                    if(i>0){
                        systemutil.insertAirdropLog(recommend.getUserkey1(),recommend.getUsergrede(),2, 0 ,Titanc.getCoinnum().doubleValue()-sum,Titanc.getCoinnum().doubleValue(),2,sum,4);
                    }
                }
            }
        }
    }

    @RequestMapping("/sys/shareTitanKT")
    public void shareTitanKT(){
        int max = recommendMapper.selectMaxlev();
        for (int lev = 1 ;lev<max;lev++){
            List<Recommend> list = recommendMapper.selectByLev(lev);
            for (Recommend recommend : list){
                double sum = 0;
                List<Recommend> downlist1 = recommendMapper.selectByUserKey2list(recommend.getUserkey1());
                Wellets Titanc = welletsMapper.selectByUserandcoin(recommend.getUserkey1(),2);
                Wellets Titan = welletsMapper.selectByUserandcoin(recommend.getUserkey1(),1);
                for (Recommend recommend1 : downlist1){
                    sum += changelogMapper.userTitanKT(recommend1.getUserkey1());
                }
                if(sum>0){
                    Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+sum*0.1));
                    Titanc.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()-sum*0.1));
                    welletsMapper.updateByPrimaryKeySelective(Titan);
                    welletsMapper.updateByPrimaryKeySelective(Titanc);
                    systemutil.insertAirdropLog(recommend.getUserkey1(),recommend.getUsergrede(),2,sum*0.1,Titanc.getCoinnum().doubleValue()+sum*0.1,Titanc.getCoinnum().doubleValue(),1,sum*0.1,6);
                }
            }
        }
    }

    public void uptrecommendlist(){
        Recommend recommend = recommendMapper.selectByUserKey1("2012222");
        int lev_ = recommend.getLev();
        List<Recommend> list = recommendMapper.selectByUserKey21(recommend.getUserkey1());
        int num = list.size();
        int num1 = 0;
        for (Recommend map : list){
            //if()
        }

    }

}
