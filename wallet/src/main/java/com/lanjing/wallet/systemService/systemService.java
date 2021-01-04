package com.lanjing.wallet.systemService;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.util.HttpUtil;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service("systemService")
@RestController
public class systemService {

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
    private OrdersMapper ordersMapper;

    @Autowired
    private RedisDao redisDao;

    @RequestMapping("/sys/dayAirdrop")
    //@Scheduled(cron = "0 0 0 * * ?")
    public void dayAirdrop(){
        Coins Titan = coinsMapper.selectByPrimaryKey(1);
        double Tprice = Titan.getPrice();
        List<Wellets> list = welletsMapper.selectByCointype(4);
        for (Wellets wellet : list){
            double release = wellet.getCoinnum().doubleValue()*0.006/Tprice;
            systemutil.uptWellet(wellet.getUserkey(),4,wellet.getCoinnum().doubleValue()*0.006,0,1);
            systemutil.uptWellet(wellet.getUserkey(),2,release,1,1);
            systemutil.insertAirdropLog(wellet.getUserkey(),0,4,wellet.getCoinnum().doubleValue()*0.006,wellet.getCoinnum().doubleValue(),wellet.getCoinnum().doubleValue()-release,2,release,1);
        }
    }

    /***
     * 每日衰减用户节点USD待理财
     * */
    @RequestMapping("/sys/updateNodenum")
    //@Scheduled(cron = "0 0 0 * * ?")
    public void updateNodenum(){
        System.out.println("-----------1");
        usersMapper.updateNodenum();
    }


    /***
     * 匹配币币交易
     *
     * matchsum   匹配数量
     * type       1,买    2,卖
     * */
    @RequestMapping("/sys/match")
    public void match(Integer fId,@RequestParam(name = "matchsum") double matchsum,@RequestParam(name = "type") Integer type){
        Coins Titan = coinsMapper.selectByPrimaryKey(1);
        Coins Usd = coinsMapper.selectByPrimaryKey(3);
        boolean flag = true;
        while (flag){
            List<MappingTrade> list = mappingTradeMapper.selectByType1(type);
            for (MappingTrade mappingTrade : list){
                if(mappingTrade.getIsreal() == 0){
                    if(matchsum>=mappingTrade.getRest().doubleValue()){
                        mappingTrade.setState(2);
                        mappingTrade.setUpdatetime(new Date());
                        //matchsum = matchsum - mappingTrade.getRest().doubleValue();
                        mappingTradeMapper.updateByPrimaryKeySelective(mappingTrade);
                        //matchlistMapper.updatenum(fId,matchsum);
                    }else{
                        flag = false;
                        break;
                    }
                }else{
                    if(matchsum>=mappingTrade.getRest().doubleValue()){
                        systemutil.uptMappingTradeLog(mappingTrade.getFid(),mappingTrade.getRest().doubleValue());
                        matchsum = matchsum - mappingTrade.getRest().doubleValue();
                        matchlistMapper.updatenum(fId,matchsum);
                    }else{
                        flag = false;
                        break;
                    }
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public void autoTrade(Users user,Integer type,double price){
        MappingTrade mappingTrade = new MappingTrade();
        mappingTrade.setFuser(user.getKeyes());
        mappingTrade.setType(type);
        mappingTrade.setState(1);
        List<MappingTrade> list = mappingTradeMapper.selectBy(mappingTrade,0,10);
        mappingTrade.setType(2);
        int buyNum = wellethistoryStateMapper.gettodaybuyNum(welletsMapper.selectByUserandcoin(user.getKeyes(),1).getFid());
        List<MappingTrade> list1 = mappingTradeMapper.selectBy(mappingTrade,0,10);
        double releaseNum = changelogMapper.userTitanKT1(mappingTrade.getFuser());
        if(list1.size() < 1 && list.size()<1 && releaseNum <= 0){
            if(type == 1){
                if(buyNum<1){
                    Wellets Titan = welletsMapper.selectByUserandcoin(user.getKeyes(),1);
                    Wellets Titanc = welletsMapper.selectByUserandcoin(user.getKeyes(),2);
                    if (Titan.getCoinnum().doubleValue()>=1){
                        if(Titan.getCoinnum().doubleValue()>0 && Titan.getShiftnum().doubleValue()!=0 && Titanc.getCoinnum().doubleValue()*20 !=0){
                            MappingTrade mappingTrade1 = new MappingTrade();
                            mappingTrade1.setFuser(user.getKeyes());
                            mappingTrade1.setType(type);
                            mappingTrade1.setCoinid(1);
                            mappingTrade1.setCointype("titanusd");
                            if(Titan.getShiftnum().doubleValue() > Titanc.getCoinnum().doubleValue()*20){
                                if(Titan.getCoinnum().doubleValue()>Titanc.getCoinnum().doubleValue()*20){
                                    systemutil.addWellethistoryState(35,"卖出",((int)-Titanc.getCoinnum().doubleValue()*20)*0.998,Titan.getFid());
                                    systemutil.addWellethistoryState(30,"手续费",((int)-Titanc.getCoinnum().doubleValue()*20)*0.002,Titan.getFid());
                                    mappingTrade1.setCoinnum(BigDecimal.valueOf(((int)Titanc.getCoinnum().doubleValue()*20)*price*0.998));
                                    mappingTrade1.setFee(BigDecimal.valueOf(((int)Titanc.getCoinnum().doubleValue()*20)*0.002));
                                    mappingTrade1.setRest(BigDecimal.valueOf(((int)Titanc.getCoinnum().doubleValue()*20)*price*0.998));
                                    Titan.setFrozennum(BigDecimal.valueOf(Titan.getFrozennum().doubleValue() + ((int)Titanc.getCoinnum().doubleValue()*20)*0.998));
                                    Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue() - ((int)Titanc.getCoinnum().doubleValue()*20)));
                                }else{
                                    systemutil.addWellethistoryState(35,"卖出",((int)-Titan.getCoinnum().doubleValue())*0.998,Titan.getFid());
                                    systemutil.addWellethistoryState(30,"手续费",((int)-Titan.getCoinnum().doubleValue())*0.002,Titan.getFid());
                                    mappingTrade1.setCoinnum(BigDecimal.valueOf(((int)Titan.getCoinnum().doubleValue())*price*0.998));
                                    mappingTrade1.setFee(BigDecimal.valueOf(((int)Titan.getCoinnum().doubleValue())*0.002));
                                    mappingTrade1.setRest(BigDecimal.valueOf(((int)Titan.getCoinnum().doubleValue())*price*0.998));
                                    Titan.setFrozennum(BigDecimal.valueOf(Titan.getFrozennum().doubleValue() + ((int)Titan.getCoinnum().doubleValue())*0.998));
                                    Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue() - ((int)Titan.getCoinnum().doubleValue())));
                                }
                            }else{
                                if(Titan.getCoinnum().doubleValue()> Titan.getShiftnum().doubleValue()){
                                    systemutil.addWellethistoryState(35,"卖出",((int)-Titan.getShiftnum().doubleValue())*0.998,Titan.getFid());
                                    systemutil.addWellethistoryState(30,"手续费",((int)-Titan.getShiftnum().doubleValue())*0.002,Titan.getFid());
                                    mappingTrade1.setCoinnum(BigDecimal.valueOf(((int)Titan.getShiftnum().doubleValue())*price*0.998));
                                    mappingTrade1.setFee(BigDecimal.valueOf(((int)Titan.getShiftnum().doubleValue())*0.002));
                                    mappingTrade1.setRest(BigDecimal.valueOf(((int)Titan.getShiftnum().doubleValue())*price*0.998));
                                    Titan.setFrozennum(BigDecimal.valueOf(Titan.getFrozennum().doubleValue()+((int)Titan.getShiftnum().doubleValue())*0.998));
                                    Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()-((int)Titan.getShiftnum().doubleValue())));
                                }else{
                                    systemutil.addWellethistoryState(35,"卖出",((int)-Titan.getCoinnum().doubleValue())*0.998,Titan.getFid());
                                    systemutil.addWellethistoryState(30,"手续费",((int)-Titan.getCoinnum().doubleValue())*0.002,Titan.getFid());
                                    mappingTrade1.setCoinnum(BigDecimal.valueOf(((int)Titan.getCoinnum().doubleValue())*price*0.998));
                                    mappingTrade1.setFee(BigDecimal.valueOf(((int)Titan.getCoinnum().doubleValue())*0.002));
                                    mappingTrade1.setRest(BigDecimal.valueOf(((int)Titan.getCoinnum().doubleValue())*price*0.998));
                                    Titan.setFrozennum(BigDecimal.valueOf(Titan.getFrozennum().doubleValue() + ((int)Titan.getCoinnum().doubleValue())*0.998));
                                    Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue() - ((int)Titan.getCoinnum().doubleValue())));
                                }
                            }
                            mappingTrade1.setTradenum(BigDecimal.valueOf(0));
                            mappingTrade1.setPrice(BigDecimal.valueOf(price));
                            mappingTrade1.setState(1);
                            Date time = new Date();
                            mappingTrade1.setCreatetime(time);
                            mappingTrade1.setUpdatetime(time);
                            welletsMapper.updateByPrimaryKeySelective(Titan);
                            int i = mappingTradeMapper.insertSelective(mappingTrade1);
                        }
                    }
                }
            }else if(type == 2){
                Wellets USD = welletsMapper.selectByUserandcoin(user.getKeyes(),3);
                if(USD.getCoinnum().doubleValue() != 0 && USD.getCoinnum().doubleValue()>=1){
                    Wellets Titan = welletsMapper.selectByUserandcoin(user.getKeyes(),1);
                    Wellets Titanc = welletsMapper.selectByUserandcoin(user.getKeyes(),2);
                    MappingTrade mappingTrade1 = new MappingTrade();
                    mappingTrade1.setFuser(user.getKeyes());
                    mappingTrade1.setType(type);
                    mappingTrade1.setCoinid(1);
                    mappingTrade1.setCointype("titanusd");
                /*if(Titan.getShiftnum().doubleValue()<Titanc.getCoinnum().doubleValue()*20){
                    mappingTrade1.setCoinnum(Titan.getShiftnum());
                    mappingTrade1.setRest(Titan.getShiftnum());
                    Titan.setShiftnum(BigDecimal.valueOf(0));
                    Titan.setFrozennum(Titan.getShiftnum());
                }else {
                    mappingTrade1.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()*20));
                    mappingTrade1.setRest(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()*20));
                    Titan.setShiftnum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue() - Titanc.getCoinnum().doubleValue()*20));
                    Titan.setFrozennum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue() - Titanc.getCoinnum().doubleValue()*20));
                }*/
                    mappingTrade1.setCoinnum(BigDecimal.valueOf(((int)USD.getCoinnum().doubleValue())*0.998));
                    mappingTrade1.setFee(BigDecimal.valueOf(((int)USD.getCoinnum().doubleValue())*0.002));
                    mappingTrade1.setRest(BigDecimal.valueOf(((int)USD.getCoinnum().doubleValue())*0.998));
                    mappingTrade1.setTradenum(BigDecimal.valueOf(0));
                    mappingTrade1.setPrice(BigDecimal.valueOf(price));
                    systemutil.addWellethistoryState(35,"卖出",((int)-USD.getCoinnum().doubleValue())*0.998,USD.getFid());
                    systemutil.addWellethistoryState(30,"手续费",((int)-USD.getCoinnum().doubleValue())*0.002,USD.getFid());
                    USD.setFrozennum(BigDecimal.valueOf(USD.getFrozennum().doubleValue()+((int)USD.getCoinnum().doubleValue())*0.998));
                    USD.setCoinnum(BigDecimal.valueOf(USD.getCoinnum().doubleValue()-((int)USD.getCoinnum().doubleValue())));

                    mappingTrade1.setState(1);
                    Date time = new Date();
                    mappingTrade1.setCreatetime(time);
                    mappingTrade1.setUpdatetime(time);

                    //挂单释放
                    /*if(Titan.getShiftnum().doubleValue()<USD.getCoinnum().doubleValue()*0.998/price){
                        if(Titan.getShiftnum().doubleValue()>Titanc.getCoinnum().doubleValue()*20){
                            double ago = Titanc.getCoinnum().doubleValue();
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+Titanc.getCoinnum().doubleValue()));
                            mappingTrade1.setReleasenum(Titanc.getCoinnum());
                            Titanc.setCoinnum(BigDecimal.valueOf(0));
                            systemutil.insertReleaseLog(user.getKeyes(),recommendMapper.selectByUserKey1(user.getKeyes()).getUsergrede(),USD.getCoinnum().doubleValue()*0.998/price,2,ago,ago,0,1,ago,2);
                        }else{
                            double ago = Titanc.getCoinnum().doubleValue();
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+Titan.getShiftnum().doubleValue()*0.05));
                            Titanc.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()-Titan.getShiftnum().doubleValue()*0.05));
                            mappingTrade1.setReleasenum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue()*0.05));
                            systemutil.insertReleaseLog(user.getKeyes(),recommendMapper.selectByUserKey1(user.getKeyes()).getUsergrede(),USD.getCoinnum().doubleValue()*0.998/price,2,Titan.getShiftnum().doubleValue()*0.05,ago,Titanc.getCoinnum().doubleValue(),1,Titan.getShiftnum().doubleValue()*0.05,2);
                        }
                        Titan.setShiftnum(BigDecimal.valueOf(0));
                        welletsMapper.updateByPrimaryKeySelective(Titanc);
                        welletsMapper.updateByPrimaryKeySelective(Titan);
                    }else {
                        if(USD.getCoinnum().doubleValue()*0.998/price>Titanc.getCoinnum().doubleValue()*20){
                            double ago = Titanc.getCoinnum().doubleValue();
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+Titanc.getCoinnum().doubleValue()));
                            mappingTrade1.setReleasenum(Titanc.getCoinnum());
                            Titanc.setCoinnum(BigDecimal.valueOf(0));
                            systemutil.insertReleaseLog(user.getKeyes(),recommendMapper.selectByUserKey1(user.getKeyes()).getUsergrede(),USD.getCoinnum().doubleValue()*0.998/price,2,ago,ago,0,1,ago,2);
                        }else{
                            double ago = Titanc.getCoinnum().doubleValue();
                            Titan.setCoinnum(BigDecimal.valueOf(Titan.getCoinnum().doubleValue()+USD.getCoinnum().doubleValue()*0.998/price*0.05));
                            Titanc.setCoinnum(BigDecimal.valueOf(Titanc.getCoinnum().doubleValue()-USD.getCoinnum().doubleValue()*0.998/price*0.05));
                            mappingTrade1.setReleasenum(BigDecimal.valueOf(USD.getCoinnum().doubleValue()*0.998/price*0.05));
                            Recommend recommend = recommendMapper.selectByUserKey1(user.getKeyes());
                            if(recommend != null){
                                systemutil.insertReleaseLog(user.getKeyes(),recommend.getUsergrede(),USD.getCoinnum().doubleValue()*0.998/price,2,USD.getCoinnum().doubleValue()*0.998/price*0.05,ago,Titanc.getCoinnum().doubleValue(),1,USD.getCoinnum().doubleValue()*0.998/price*0.05,2);
                            }else{
                                systemutil.insertReleaseLog(user.getKeyes(),0,USD.getCoinnum().doubleValue()*0.998/price,2,USD.getCoinnum().doubleValue()*0.998/price*0.05,ago,Titanc.getCoinnum().doubleValue(),1,USD.getCoinnum().doubleValue()*0.998/price*0.05,2);
                            }
                        }
                        Titan.setShiftnum(BigDecimal.valueOf(Titan.getShiftnum().doubleValue()-USD.getCoinnum().doubleValue()/price));
                        welletsMapper.updateByPrimaryKeySelective(Titanc);
                        welletsMapper.updateByPrimaryKeySelective(Titan);
                    }*/

                    welletsMapper.updateByPrimaryKeySelective(USD);
                    int i = mappingTradeMapper.insertSelective(mappingTrade1);
                }
            }
        }
    }


    @Scheduled(cron = "0 0 0/2 * * ? ")
    public double getcny1(){
        double price =0;
        try {
            Map map = JSONObject.parseObject(HttpUtil.sendGet("http://api.k780.com/?app=finance.rate&scur=USD&tcur=CNY&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4"),Map.class);
            Map list = (Map) map.get("result");
            price = Double.parseDouble(list.get("rate")+"");
            Coins USD = coinsMapper.findByCoinname("USDT");
            Coins YYB = coinsMapper.findByCoinname("YYB");
            USD.setPrice(price);
            YYB.setPrice(1/price);
            coinsMapper.updateByPrimaryKeySelective(USD);
            coinsMapper.updateByPrimaryKeySelective(YYB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return price;
    }

    public double getcny(){
        Coins USD = coinsMapper.findByCoinname("USDT");
        return USD.getPrice();
    }


    //@Scheduled(cron = "0 0/1 * * * ? ")
    public void gettickers(){
        try {
            double cnyprice = getcny();
            Map map = JSONObject.parseObject(HttpUtil.sendGet("https://api.huobipro.com/market/tickers"),Map.class);
            List<Map> list = (List<Map>) map.get("data");
            System.out.println("-------------"+list.size());
            for (Map map1 : list){

                Market market = new Market();
                if((map1.get("symbol")+"").endsWith("usdt") || "btcusdt".equals((map1.get("symbol")+""))){
                    String symbol = map1.get("symbol")+"";
                    market.setSymbol(symbol.substring(0,symbol.length()-4).toUpperCase()+"/"+symbol.substring(symbol.length()-4,symbol.length()).toUpperCase());
                    List<Market> list1 = marketMapper.selectBy(market);
                    if(list1.size()>0){
                        Market market1 = list1.get(0);
                        try {
                            market1.setAmount(BigDecimal.valueOf(Double.parseDouble(map1.get("amount")+"")));
                            market1.setClose(BigDecimal.valueOf(Double.parseDouble(map1.get("close")+"")*cnyprice));
                            market1.setCnyprice(BigDecimal.valueOf(Double.parseDouble(map1.get("close")+"")));
                            market1.setOpen(BigDecimal.valueOf(Double.parseDouble(map1.get("open")+"")*cnyprice));
                            market1.setPrice(BigDecimal.valueOf(Double.parseDouble(map1.get("close")+"")*cnyprice));
                            if ("ETH/USDT".equals(market1.getSymbol())){
                                Coins eth = coinsMapper.findByCoinname("ETH");
                                eth.setPrice(market1.getCnyprice().doubleValue());
                                coinsMapper.updateByPrimaryKeySelective(eth);
                            }else if ("BTC/USDT".equals(market1.getSymbol())){
                                Coins BTC = coinsMapper.findByCoinname("BTC");
                                BTC.setPrice(market1.getCnyprice().doubleValue());
                                coinsMapper.updateByPrimaryKeySelective(BTC);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        marketMapper.updateByPrimaryKeySelective(market1);
                    }else {
                        try {
                            market.setAmount(BigDecimal.valueOf(Double.parseDouble(map1.get("amount")+"")));
                            market.setClose(BigDecimal.valueOf(Double.parseDouble(map1.get("close")+"")*cnyprice));
                            market.setCnyprice(BigDecimal.valueOf(Double.parseDouble(map1.get("close")+"")));
                            market.setOpen(BigDecimal.valueOf(Double.parseDouble(map1.get("open")+"")*cnyprice));
                            market.setPrice(BigDecimal.valueOf(Double.parseDouble(map1.get("close")+"")*cnyprice));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        marketMapper.insertSelective(market);
                    }
                }
            }
            redisDao.remove("newmarket");
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    //@Scheduled(cron = "0 0/1 0/1 1/1 * ? ")
    public void getTraferhistory(){
        systemutil.unlock(1);
        String addre = parametersMapper.selectByKey("address").getKeyvalue();
        String addreId = systemutil.getwelletId();
        int maxId = trasferlistMapper.selectBymaxId();
        System.out.println("-------"+maxId);
        if(maxId <= 0){
            maxId = 1;
        }

        String back = null;
        try {
            back = HttpUtil.sendPost(parametersMapper.selectByKey("nodeurl").getKeyvalue(),"{\n" +
                    "\"jsonrpc\": \"2.0\", \n" +
                    "\"method\": \"call\", \n" +
                    "\"params\": [0, \"get_account_history_by_operations\", [\""+addre+"\",[0],"+maxId+",100]], \"id\": 1\n" +
                    "}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------返回数据："+back);
        Map<String,Object> json = JSONObject.parseObject(back,Map.class);
        Map<String,Object> map1 = (Map<String, Object>) json.get("result");
        List list = (List) map1.get("details");
        System.out.println("-------"+list.size());
        for (Object obj : list){
            Map map = (Map) obj;
            System.out.println(map.get("memo")+"*********"+map.get("transaction_id"));
            Map op = (Map) map.get("op");
            List oplist = (List) op.get("op");
            Map text = (Map) oplist.get(1);
            String fromid = (String) text.get("from");
            String toid = (String) text.get("to");
            Map fee = (Map) text.get("fee");
            Map amount = (Map) text.get("amount");
            if(fromid !=null && toid != null){
                if(fromid.equals(addreId)){
                    System.out.println("----------提币");
                    Trasferlist trasferlist = new Trasferlist();
                    trasferlist.setType(1);
                    trasferlist.setDescription(map.get("description")+"");
                    trasferlist.setTxid(map.get("transaction_id")+"");
                    trasferlist.setMemo(map.get("memo")+"");
                    trasferlist.setFeenum(Double.parseDouble(fee.get("amount")+"")/100000);
                    trasferlist.setTradenum(Double.parseDouble(amount.get("amount")+"")/100000);
                    trasferlistMapper.insertSelective(trasferlist);
                }
                if(toid.equals(addreId)){
                    System.out.println("----------充币");
                    Trasferlist trasferlist = new Trasferlist();
                    trasferlist.setType(2);
                    trasferlist.setDescription(map.get("description")+"");
                    trasferlist.setTxid(map.get("transaction_id")+"");
                    trasferlist.setMemo(map.get("memo")+"");
                    trasferlist.setFeenum(Double.parseDouble(fee.get("amount")+"")/100000);
                    trasferlist.setTradenum(Double.parseDouble(amount.get("amount")+"")/100000);
                    trasferlistMapper.insertSelective(trasferlist);
                }
            }
        }
    }

    //@Scheduled(cron = "0/30 0/1 0/1 1/1 * ? ")
    public void Recharge(){
        List<Trasferlist> list = trasferlistMapper.selectBystate(0);
        for (Trasferlist trasferlist : list){
            Wellets wellet2 = welletsMapper.selectByUserandcoin(trasferlist.getMemo(),1);
            if(wellet2 != null){
                wellet2.setShiftnum(BigDecimal.valueOf(wellet2.getShiftnum().doubleValue()+trasferlist.getTradenum()));
                wellet2.setCoinnum(BigDecimal.valueOf(wellet2.getCoinnum().doubleValue()+trasferlist.getTradenum()));
                welletsMapper.updateByPrimaryKeySelective(wellet2);
                WellethistoryState wellethistoryState2 = new WellethistoryState();
                wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
                wellethistoryState2.setBalance(wellet2.getCoinnum());
                wellethistoryState2.setType(32);
                wellethistoryState2.setRemark("充币");
                Date time = new Date();
                wellethistoryState2.setCreatetime(time);
                wellethistoryState2.setUpdatetime(time);
                wellethistoryState2.setWelletid(wellet2.getFid());
                wellethistoryState2.setState(1);
                wellethistoryState2.setKeyes(trasferlist.getTxid());
                Orders order = new Orders();
                order.setOrderid(trasferlist.getTxid());
                order.setTradecoin("Titan");
                order.setTradecoinid(1);
                order.setCreatetime(new Date());
                order.setConfirmtime(new Date());
                order.setBuyadress(parametersMapper.selectByKey("address").getKeyvalue());
                order.setUserbuy(trasferlist.getMemo());
                order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
                order.setUsersell("");
                order.setState(1);
                String desctiption = trasferlist.getDescription().substring(trasferlist.getDescription().indexOf("from")+5);
                order.setSelladress(desctiption.substring(0,desctiption.indexOf(" ")));
                ordersMapper.insertSelective(order);
                int i =wellethistoryStateMapper.insertSelective(wellethistoryState2);
                if(i > 0){
                    trasferlistMapper.updateByfId(trasferlist.getFid());
                }
            }
        }
    }


    public void autohistory(Integer type ,double num){
        double price = coinsMapper.selectByPrimaryKey(1).getPrice();
        MappingTrade mappingTrade = new MappingTrade();
        mappingTrade.setFuser("99999999");
        mappingTrade.setType(type);
        mappingTrade.setCoinid(1);
        mappingTrade.setCointype("titanusd");
        mappingTrade.setState(1);
        mappingTrade.setPrice(BigDecimal.valueOf(price));
        mappingTrade.setCoinnum(BigDecimal.valueOf(num));
        mappingTrade.setIsreal(0);
        mappingTrade.setRest(BigDecimal.valueOf(num));
        mappingTrade.setTradenum(BigDecimal.valueOf(0));
        Date time = new Date();
        mappingTrade.setCreatetime(time);
        mappingTrade.setUpdatetime(time);
        mappingTradeMapper.insertSelective(mappingTrade);
    }

    //假数据
    //@Scheduled(cron = "0 0/1 * * * ? ")
    public void auto(){
        double price = coinsMapper.selectByPrimaryKey(1).getPrice();
        int min = 1;
        int max = 10;
        int num = min + ((int) (new Random().nextFloat() * (max - min)));
        autohistory(1,num);
        float num1 = min + ((int) (new Random().nextFloat() * (max - min)));
        autohistory(2,num1);
    }


    public void auto1(Integer type ,Integer max ,Integer min){
        int num = min + ((int) (new Random().nextFloat() * (max - min)));
        autohistory(type,num);
    }


    //@Scheduled(cron = "0 0/1 0/1 1/1 * ? ")
    public void automatch(){
        List<Map> list = matchlistMapper.selectByNostart();
        for (Map map : list){
            System.out.println("------准备执行匹配任务"+map.get("fId")+"");
            matchlistMapper.updatestate(Integer.parseInt(map.get("fId")+""));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("------开始执行匹配任务"+map.get("fId")+"");
                    match(Integer.parseInt(map.get("fId")+""),Double.parseDouble(map.get("sum")+""),Integer.parseInt(map.get("type")+""));
                    Matchlist matchlist = new Matchlist();
                    matchlist.setFid(Integer.parseInt(map.get("fId")+""));
                    matchlist.setStates(1);
                    matchlistMapper.updateByPrimaryKeySelective(matchlist);
                }
            }).start();
        }
    }

    //@Scheduled(cron = "0 0/1 0/1 1/1 * ? ")
    public void autohistory1(){

        List<Map> list = autolistMapper.selectByNostart();
        for (Map map : list){
            System.out.println("------准备执行生成任务"+map.get("fId")+"");
            autolistMapper.updatestate(Integer.parseInt(map.get("fId")+""));
            double sum = Double.parseDouble(map.get("sum")+"");
            redisDao.setKey(map.get("fId")+"",sum+"");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //sum = sum - 1;
                    String sum1 = redisDao.getValue(map.get("fId")+"");
                    int sum2 = (int) Double.parseDouble(sum1);
                    System.out.println("-------------------"+Integer.parseInt(map.get("type")+""));
                    int num = (int)Double.parseDouble(map.get("coin_min")+"") + ((int) (new Random().nextFloat() * ((int)Double.parseDouble(map.get("coin_max")+"") - (int)Double.parseDouble(map.get("coin_min")+""))));
                    if(num >= sum2){
                        autohistory(Integer.parseInt(map.get("type")+""),sum2);
                        sum2 = 0;
                        redisDao.setKey(map.get("fId")+"",sum2+"");
                        autolistMapper.updatestate2(Integer.parseInt(map.get("fId")+""));
                        timer.cancel();
                    }else{
                        autohistory(Integer.parseInt(map.get("type")+""),num);
                        sum2 = sum2 - num;
                        redisDao.setKey(map.get("fId")+"",sum2+"");
                    }
                }
            },new Date(),Integer.parseInt(map.get("takt_time")+"")*60*1000);
        }
    }


    @RequestMapping("/sys/start")
    public void start(){
        //dayAirdrop();
        /*dayAirdrop();
        match(100000000,1);*/
        //KTjiaquan();
        match(1,100000000,1);
    }


}
