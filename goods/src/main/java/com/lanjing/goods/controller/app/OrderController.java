package com.lanjing.goods.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jester.util.utils.DateUtil;
import com.jester.util.utils.Result;
import com.lanjing.goods.SpringContextUtil;
import com.lanjing.goods.config.ConfigConst;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.dao.*;
import com.lanjing.goods.ex.CheckEx;
import com.lanjing.goods.ex.ExException;
import com.lanjing.goods.handle.HandleIntegral;
import com.lanjing.goods.model.*;
import com.lanjing.goods.service.*;
import com.lanjing.goods.util.GetInformation;
import com.lanjing.goods.util.KdniaoApiUtils;
import com.lanjing.goods.util.RedisDao;
import com.lanjing.goods.util.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jester.util.utils.PushUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@Transactional
public class OrderController extends BaseController {

    @Value("${wallet.url}")
    private String urlStr;

    @Autowired
    private PushUtil pushUtil;

    @Resource
    private ShopcatMapper shopcatMapper;

    @Resource
    private ShopOrderMapper orderMapper;

    @Resource
    private ShopOrderMapper2 orderMapper2;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private GetInformation getInformation;

    @Resource
    private GoodsskuMapper goodsskuMapper;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private MallShopService mallShopService;

    @Autowired
    private MallWalletService mallWalletService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private MallFundListService mallFundListService;

    @Autowired
    private HandleIntegral handleIntegral;

    @Autowired
    private CoinsMapper coinsMapper;

    @RequestMapping("app/goods/toorder")
    public String toorder(@RequestBody JSONObject param) {
        String goodsskustr = param.getString("goodsskuIds");
        if (goodsskustr == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        String[] goodsskuIds = goodsskustr.split(",");
        Map<String, String> orderIds = new HashMap<>();
        List<ShopOrder> orderList = new ArrayList<>();
        List<JSONObject> orderjson = new ArrayList<>();
        Map map = new HashMap();
        BigDecimal sumprice = new BigDecimal("0");
        BigDecimal sumintegralnum = new BigDecimal("0");
        BigDecimal sumintegralprice = new BigDecimal("0");
        for (String skuid : goodsskuIds) {
            String[] sku = skuid.split(":");
            int goodsskuId = Integer.valueOf(sku[0]);
            Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(goodsskuId);
            if (goodssku != null) {
                if (goodssku.getSum() < Integer.valueOf(sku[1])) {
                    map.put("orders", orderjson);
                    map.put("sumprice", sumprice);
                    map.put("sumintegralnum", sumintegralnum);
                    map.put("sumintegralprice", sumintegralprice);
                    return JSONObject.toJSONString(new ResultDTO(-2, goodssku.getSkuname() + goodssku.getSpecs() + "库存不足", map));
                }
                Goods goods = goodsService.selectByCode(Long.valueOf(goodssku.getGoodscode()));
                String orderId = orderIds.get(goods.getShopCode() + "");
                if (orderId == null || "".equals(orderId)) {
                    orderId = (new Date()).getTime() + "";
                    orderIds.put(goods.getShopCode() + "", orderId);
                }
                if (goods != null && goods.getStatus() != 0 && goods.getStatus() != 2) {
                    ShopOrder order = new ShopOrder();
                    order.setOrderid(orderId);
                    order.setGoodsskuId(goodsskuId);
                    order.setGoodsid(goods.getId());
                    order.setShop_code(goods.getShopCode());
                    order.setNum(Integer.valueOf(sku[1]));
                    order.setStandard(JSONObject.toJSONString(goodssku));
                    sumprice = sumprice.add(goodssku.getPrice().multiply(new BigDecimal(sku[1])));
                    sumintegralnum = sumintegralnum.add(goodssku.getIntegralnum().multiply(new BigDecimal(sku[1])));
                    sumintegralprice = sumintegralprice.add(goodssku.getIntegralprice().multiply(new BigDecimal(sku[1])));
                    orderList.add(order);
                }
            }
        }
        for (String s : orderIds.keySet()) {
            JSONObject json = new JSONObject();
            MallShop mallShop = mallShopService.findByCode(Long.valueOf(s));
            json.put("mallshopname", mallShop.getName());
            json.put("mallshopcode", mallShop.getCode());
            List<JSONObject> map1 = new ArrayList<>();
            for (ShopOrder order : orderList) {
                if (order.getShop_code().longValue() == mallShop.getCode()) {
                    Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(order.getGoodsskuId());
                    JSONObject goodsskujson = JSON.parseObject(JSONObject.toJSONString(goodssku), JSONObject.class);
                    goodsskujson.put("num", order.getNum());
                    goodsskujson.put("goodsId", order.getGoodsid());
                    map1.add(goodsskujson);
                }
            }
            json.put("goodsskus", map1);
            orderjson.add(json);
        }
        map.put("orders", orderjson);
        map.put("sumprice", sumprice);
        map.put("sumintegralnum", sumintegralnum);
        map.put("sumintegralprice", sumintegralprice);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }


    /**
     * 生成订单
     */
    @RequestMapping("app/goods/tobuy")
    public String tobuy(@RequestBody JSONObject param) {
        Integer addressId = param.getInteger("addressId");
        String goodsskustr = param.getString("goodsskuIds");
        if (addressId == null || goodsskustr == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        String remark = param.getString("remark");

        String[] goodsskuIds = goodsskustr.split(",");
        JSONObject user = getInformation.getUser(getUserKey());
        Map<String, String> orderIds = new HashMap<>();
        List<ShopOrder> orderList = new ArrayList<>();
        BigDecimal sumintegralnum = new BigDecimal("0");
        BigDecimal sumintegralprice = new BigDecimal("0");
        Address address = addressService.selectByPrimaryKey(addressId);
        if (address == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该地址信息不存在！"));
        }
        if (!address.getUserkey().equals(getUserKey())) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该地址信息不存在！"));
        }
        for (String skuid : goodsskuIds) {
            String[] sku = skuid.split(":");
            int goodsskuId = Integer.parseInt(sku[0]);
            Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(goodsskuId);
            if (goodssku != null) {
                if (goodssku.getSum() < Integer.parseInt(sku[1])) {
                    return JSONObject.toJSONString(new ResultDTO(-2, goodssku.getSkuname() + goodssku.getSpecs() + "库存不足"));
                }
                Goods goods = goodsService.selectByCode(Long.valueOf(goodssku.getGoodscode()));
                String orderId = orderIds.get(goods.getShopCode() + "");
                if (orderId == null || "".equals(orderId)) {
                    orderId = (new Date()).getTime() + "";
                    orderIds.put(goods.getShopCode() + "", orderId);
                }
                if (goods.getStatus() != 0 && goods.getStatus() != 2) {
                    ShopOrder order = new ShopOrder();
                    order.setOrderid(orderId);
                    order.setGoodsskuId(goodsskuId);
                    order.setGoodsid(goods.getId());
                    order.setShop_code(goods.getShopCode());
                    JSONObject json = new JSONObject();
                    json.put("company", "");
                    json.put("waybillnumber", "");
                    json.put("uadmin", "");
                    order.setLogistics(json.toJSONString());
                    order.setUserkey(user.getInteger("fid"));
                    order.setUserphone(user.getString("username"));
                    order.setCoinprive(BigDecimal.valueOf(goodssku.getPrice().multiply(new BigDecimal(sku[1])).doubleValue()));//当前花费的人民币
                    order.setNum(Integer.valueOf(sku[1]));
                    order.setStandard(JSONObject.toJSONString(goodssku));
                    order.setPhone(address.getPhone());
                    order.setUsername(address.getUsername());
                    order.setAddress(address.getAddress());
                    order.setState(-1);
                    order.setCreatetime(new Date());
                    order.setRemark(remark);
                    sumintegralprice = sumintegralprice.add(order.getCoinprive());
                    orderMapper.insertSelective(order);
                    goodssku.setSum(goodssku.getSum() - order.getNum());
                    goodsskuMapper.updateByPrimaryKeySelective(goodssku);
                    uptshopcat(Integer.valueOf(getUserKey()), goods.getId(), goodssku.getId(), -Integer.parseInt(sku[1]));
                    orderList.add(order);
                }
            }
        }
        List<JSONObject> orderjson = new ArrayList<>();
        for (String s : orderIds.keySet()) {
            JSONObject json = new JSONObject();
            json.put("orderId", orderIds.get(s));
            MallShop mallShop = mallShopService.findByCode(Long.valueOf(s));
            json.put("mallshopname", mallShop.getName());
            json.put("mallshopcode", mallShop.getCode());
            List<JSONObject> map = new ArrayList<>();
            for (ShopOrder order : orderList) {
                if (order.getShop_code().longValue() == mallShop.getCode()) {
                    Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(order.getGoodsskuId());
                    JSONObject goodsskujson = JSON.parseObject(JSONObject.toJSONString(goodssku), JSONObject.class);
                    goodsskujson.put("num", order.getNum());
                    goodsskujson.put("goodsId", order.getGoodsid());
                    map.add(goodsskujson);
                }
            }
            json.put("goodsskus", map);
            orderjson.add(json);
        }
        Map map = new HashMap();
        if (orderList.size() > 0) {
            map.put("name", orderList.get(0).getUsername());
            map.put("phone", orderList.get(0).getPhone());
            map.put("address", orderList.get(0).getAddress());
        } else {
            map.put("name", "");
            map.put("phone", "");
            map.put("address", "");
        }
        map.put("orders", orderjson);
        map.put("sumintegralnum", sumintegralnum);
        map.put("sumintegralprice", sumintegralprice.stripTrailingZeros().toPlainString());
        try {
            pushUtil.aliasPush(getUserKey(), "商城订单通知", "您生成了一笔订单，请及时支付", null);
        } catch (Exception e) {
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }

    public void uptshopcat(Integer userId, Integer goodsId, Integer goodsskuId, Integer num) {
        Shopcat shopcat = shopcatMapper.selectByGoodsidAndGoodsskuid(userId, goodsId, goodsskuId);
        if (shopcat != null) {
            shopcat.setNum(shopcat.getNum() + num);
            shopcatMapper.updateByPrimaryKeySelective(shopcat);
            if (shopcat.getNum() <= 0) {
                shopcatMapper.deleteByPrimaryKey(shopcat.getId());
            }
        }
    }

    @RequestMapping("app/goods/buy")
    @Transactional
    public String buy(@RequestBody JSONObject param) {
        String orderIdstr = param.getString("orderIds");
        String tradepwd = param.getString("tradepwd");
        if (orderIdstr == null || tradepwd == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        JSONObject user = getInformation.getUser(getUserKey());
        String key = String.format("%s%s", "user:check:pass:", getUserKey());

        Boolean hasKey = redisDao.hasKey(key);

        if (hasKey) {
            Integer errornum = Integer.valueOf(redisDao.getValue(key));
            //每日限制交易密码只能错误三次
            if (errornum > 3) {
                return JSONObject.toJSONString(new ResultDTO(-1, "交易密码错误超过3次,请明日再试"));
            }
        }

        if (!tradepwd.trim().equals(user.getString("transpassword"))) {
            redisDao.increment(key);
            if (!hasKey) {
                //初始化过期时间
                redisDao.setTime(key, DateUtil.nextDay() / 1000);
            }
            return JSONObject.toJSONString(new ResultDTO(-1, "交易密码错误"));
        }

        //密码正确，清除错误密码累计
        if (hasKey) {
            redisDao.remove(key);
        }
        String[] orderIds = orderIdstr.split(",");
        BigDecimal sumprice = new BigDecimal("0");
        BigDecimal sumintegralnum = new BigDecimal("0");
        for (String orderId : orderIds) {
            List<ShopOrder> orderList = orderMapper.queryByOrderid(orderId);
            for (ShopOrder order : orderList) {
                sumprice = sumprice.add(order.getCoinprive());
                sumintegralnum = sumintegralnum.add(order.getIntegralnum());
            }
        }
        Map pay = new HashMap();
        pay.put("userkey", getUserKey());
        pay.put("integralnum", handleIntegral.getIntegralNumber());
        pay.put("coinnum", sumprice);
        pay.put("remark", "购物消费");
        JSONObject payjson = getInformation.postObject(urlStr + "zuul/Pay1", pay);
        CheckEx.check(payjson.getInteger("code") < 0, payjson.toJSONString());

        for (String orderId1 : orderIds) {
            orderMapper.updateByOrderid(orderId1, 0);
            BigDecimal sumprice1 = new BigDecimal("0");
            BigDecimal sumintegralnum1 = new BigDecimal("0");
            List<ShopOrder> orderList = orderMapper.queryByOrderid(orderId1);
            //添加商铺余额
            //1获取的店铺钱包
            Long code = orderList.get(0).getShop_code();//商家code
            Long orderId = Long.valueOf(orderId1);//订单id
            for (ShopOrder order : orderList) {
                Goods goods = goodsService.selectByPrimaryKey(order.getGoodsid());
                goods.setSalesVolume(goods.getSalesVolume() + order.getNum());
                goodsService.updateByPrimaryKeySelective(goods);
                sumprice1 = sumprice1.add(order.getCoinprive());
                sumintegralnum1 = sumintegralnum1.add(order.getIntegralnum());
            }
            MallWallet byCodeAndCoinId = mallWalletService.findByCodeAndCoinId(code, ConfigConst.YYB_ID);


            int result = mallWalletService.updateByCodeAndCoinIdAndVersion(sumprice1, code, ConfigConst.YYB_ID, byCodeAndCoinId.getVersion());
            CheckEx.db(result, "Payment Fail");

            //添加商铺YYB资金记录
            MallFundList mallFundList = new MallFundList();
            mallFundList.setCode(code);
            mallFundList.setOrderId(orderId);
            mallFundList.setCoinId(ConfigConst.YYB_ID);
            mallFundList.setType(1);
            mallFundList.setAmount(sumprice1);
            mallFundList.setCreateTime(new Date());

            result = mallFundListService.insertSelective(mallFundList);
            CheckEx.db(result, "购买商品添加商家钱包日志失败");

            MallWallet byCodeAndCoinId1 = mallWalletService.findByCodeAndCoinId(code, 4);

            result = mallWalletService.updateByCodeAndCoinIdAndVersion(sumintegralnum1, code, 4, byCodeAndCoinId1.getVersion());
            CheckEx.db(result, "Payment Fail");

            //添加商铺资金积分记录
            MallFundList mallFundList1 = new MallFundList();
            mallFundList1.setCode(code);
            mallFundList1.setOrderId(orderId);
            mallFundList1.setCoinId(4);
            mallFundList1.setType(1);
            mallFundList1.setAmount(sumintegralnum1);
            mallFundList1.setCreateTime(new Date());

            result = mallFundListService.insertSelective(mallFundList1);
            CheckEx.db(result, "购买商品添加商家钱包日志失败");
        }

        try {
            pushUtil.aliasPush(getUserKey(), "商城订单通知", "商品订单支付成功", null);
        } catch (Exception e) {

        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
    }


    @RequestMapping("app/goods/orders")
    public String orderlist(@RequestBody JSONObject param) {
        Integer state = param.getInteger("state");
        Integer userkey = Integer.parseInt(getUserKey());
        Integer page = param.getInteger("page");
        Integer size = param.getInteger("size");
        if (page == null || size == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        List<Map> list = orderMapper.selectOrderBypage(userkey, state, (page - 1) * size, size);
        List<JSONObject> list1 = new ArrayList<>();
        for (Map map : list) {
            JSONObject jsonObject = queryByOrderid(map.get("orderId") + "");
            list1.add(jsonObject);
        }
        int count = orderMapper.selectOrderBypageCount(userkey, state);
        Map map = new HashMap();
        map.put("page", page);
        map.put("size", size);
        map.put("data", list1);
        map.put("state", state);
        map.put("count", count);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }

    @RequestMapping("/app/orders/evaluation")
    private Object addEvaluation(@RequestBody JSONObject param) {

        if (orderMapper2 == null) {
            // return Result.error("orderMapper't find order");
            orderMapper2 = SpringContextUtil.getBean(ShopOrderMapper2.class);
        }

        String orderId = param.getString("orderId");
        int goodsskuId = param.getIntValue("goodsskuId");
        double goodsevaluation = param.getDoubleValue("goodsevaluation");
        double shopevaluation = param.getDoubleValue("shopevaluation");
        double logisticsevaluation = param.getDoubleValue("logisticsevaluation");
        String content = param.getString("content");
        String imgs = param.getString("imgs");


        if (goodsService == null) {
            goodsService = SpringContextUtil.getBean(GoodsService.class);
        }
        if (mallShopService == null) {
            mallShopService = SpringContextUtil.getBean(MallShopService.class);
        }
        if (getInformation == null) {
            getInformation = SpringContextUtil.getBean(GetInformation.class);
        }
        if (evaluationService == null) {
            evaluationService = SpringContextUtil.getBean(EvaluationService.class);
        }
        ShopOrder order = orderMapper2.queryByOrderidAndGoodsskuId(orderId, goodsskuId);
        if (order == null) {
            return Result.error("Can't find order");
        }
        if (order.getUserkey() != Integer.parseInt(getUserKey())) {
            return Result.error("Can't find order");
        }
        Goods goods = goodsService.selectByPrimaryKey(order.getGoodsid());
        MallShop mallShop = mallShopService.findByCode(goods.getShopCode());
        JSONObject user = getInformation.getUser(getUserKey());
        Evaluation evaluation = new Evaluation();
        evaluation.setUserid(order.getUserkey());
        evaluation.setUsername(user.getString("nickname"));
        evaluation.setUserpicture(user.getString("picture"));
        evaluation.setOrderid(orderId);
        evaluation.setGoodsid(order.getGoodsid());
        evaluation.setSkuid(order.getGoodsskuId());
        evaluation.setGoodsevaluation(goodsevaluation);
        evaluation.setShopevaluation(shopevaluation);
        evaluation.setLogisticsevaluation(logisticsevaluation);
        evaluation.setContent(content);
        evaluation.setShopcode(mallShop.getCode());
        String[] strings = imgs.split(",");
        JSONArray jsonArray = new JSONArray();
        for (String str : strings) {
            jsonArray.add(str);
        }
        evaluation.setImgs(jsonArray.toJSONString());
        evaluationService.insertSelective(evaluation);
        List<Evaluation> evaluationList = evaluationService.selectByGoodsid(goods.getId());
        double goodssevaluationnum = 0;
        double shopsevaluationnum = 0;
        for (Evaluation evaluation1 : evaluationList) {
            goodssevaluationnum += evaluation1.getGoodsevaluation();
            shopsevaluationnum += evaluation1.getShopevaluation();
        }
        goods.setPraise((int) (goodssevaluationnum / evaluationList.size()) / 5 * 100);
        mallShop.setPraise(Double.valueOf((int) (shopsevaluationnum / evaluationList.size()) / 5 * 100));
        order.setState(3);
        goodsService.updateByPrimaryKeySelective(goods);
        mallShopService.updateByPrimaryKeySelective(mallShop);
        if (orderMapper == null) {
            orderMapper = SpringContextUtil.getBean(ShopOrderMapper.class);
        }
        orderMapper.updateByPrimaryKeySelective(order);
        return Result.success();

    }

    public JSONObject queryByOrderid(String orderId) {
        List<ShopOrder> orderList = orderMapper.queryByOrderid(orderId);
        JSONObject json = new JSONObject();
        ShopOrder order = orderList.get(0);
        json.put("orderId", order.getOrderid());
        json.put("name", order.getUsername());
        json.put("phone", order.getPhone());
        json.put("address", order.getAddress());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("得到的时间是：" + JSONObject.toJSONString(order));
        if (order.getCreatetime() == null) {
            json.put("time", format.format(new Date()));
        } else {
            json.put("time", format.format(order.getCreatetime()));
        }

        if (order.getPaytime() == null) {
            json.put("endtime", "");
        } else {
            json.put("endtime", format.format(order.getPaytime()));
        }
        json.put("Logistics", JSONObject.parseObject(order.getLogistics()));
        json.put("state", order.getState());
        MallShop mallShop = mallShopService.findByCode(Long.valueOf(order.getShop_code()));
        if (mallShop != null) {
            json.put("mallshopname", mallShop.getName());
            json.put("mallshopcode", mallShop.getCode());
        } else {
            json.put("mallshopname", "");
            json.put("mallshopcode", "0");
        }
        List<JSONObject> map = new ArrayList<>();
        BigDecimal sumprice = new BigDecimal("0");
        BigDecimal sumintegralnum = new BigDecimal("0");
        for (ShopOrder order1 : orderList) {
            if (order1.getShop_code().longValue() == mallShop.getCode()) {
                Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(order1.getGoodsskuId());
                JSONObject goodsskujson = JSON.parseObject(JSONObject.toJSONString(goodssku), JSONObject.class);
                goodsskujson.put("num", order1.getNum());
                goodsskujson.put("goodsId", order1.getGoodsid());
                sumprice = sumprice.add(order1.getCoinprive());
                sumintegralnum = sumintegralnum.add(order1.getIntegralnum());
                map.add(goodsskujson);
            }
        }
        //计算AGC的数量
        Coins coins = coinsMapper.selectByPrimaryKey(ConfigConst.AGC_ID);
        BigDecimal agcNumber = sumprice.divide(new BigDecimal(coins.getPrice() + ""), RoundingMode.DOWN).setScale(6, RoundingMode.DOWN);
        json.put("goodsskus", map);
        json.put("goodsnum", map.size());
        json.put("sumprice", sumprice.stripTrailingZeros().toPlainString());
        json.put("sumintegralnum", sumintegralnum.stripTrailingZeros().toPlainString());
        json.put("agcNumber", agcNumber.stripTrailingZeros().toPlainString());
        return json;
    }

    @RequestMapping("app/goods/orderdetail")
    public String orderDetails(@RequestBody JSONObject param) {
        String orderId = param.getString("orderId");
        JSONObject json = queryByOrderid(orderId);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", json));
    }

    @RequestMapping("app/goods/ordersure")
    public String ordersure(@RequestBody JSONObject param) {
        String orderId = param.getString("orderId");
        String tradepwd = param.getString("tradepwd");
        if (orderId == null || tradepwd == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        List<ShopOrder> orderList = orderMapper.queryByOrderid(orderId);
        if (orderList == null || orderList.size() <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该订单不存在"));
        }
        if (orderList.get(0).getState() == 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该订单还未发货！"));
        }
        if (!orderList.get(0).getUserkey().toString().equals(getUserKey())) {
            return JSONObject.toJSONString(new ResultDTO(-1, "订单错误！"));
        }
        JSONObject user = getInformation.getUser(getUserKey());
        String key = String.format("%s%s", "user:check:pass:", getUserKey());

        Boolean hasKey = redisDao.hasKey(key);

        if (hasKey) {
            Integer errornum = Integer.valueOf(redisDao.getValue(key));
            //每日限制交易密码只能错误三次
            if (errornum > 3) {
                return JSONObject.toJSONString(new ResultDTO(-1, "交易密码错误超过3次,请明日再试"));
            }
        }

        if (!tradepwd.trim().equals(user.getString("transpassword"))) {
            redisDao.increment(key);
            if (!hasKey) {
                //初始化过期时间
                redisDao.setTime(key, DateUtil.nextDay() / 1000);
            }
            return JSONObject.toJSONString(new ResultDTO(-1, "交易密码错误"));
        }

        //密码正确，清除错误密码累计
        if (hasKey) {
            redisDao.remove(key);
        }
        if (orderList.get(0).getState() == 1) {
            orderMapper.updateByOrderid(orderId, 2);
        }
        try {
            pushUtil.aliasPush(getUserKey(), "商城订单通知", "您有一个订单已确认收货，请注意查收", null);
        } catch (Exception e) {
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
    }

    @RequestMapping("app/goods/cancelorder")
    public String cancelorder(@RequestBody JSONObject param) {
        String orderId = param.getString("orderId");
        if (orderId == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        List<ShopOrder> orderList = orderMapper.queryByOrderid(orderId);
        if (orderList == null || orderList.size() <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该订单不存在"));
        }
        if (orderList.get(0).getState() != -1) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该订单不能取消！"));
        }
        if (!orderList.get(0).getUserkey().toString().equals(getUserKey())) {
            return JSONObject.toJSONString(new ResultDTO(-1, "订单错误！"));
        }
        if (orderList.get(0).getState() == -1) {
            orderMapper.updateByOrderid(orderId, -2);
            for (ShopOrder order : orderList) {
                Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(order.getGoodsskuId());
                goodssku.setSum(goodssku.getSum() + order.getNum());
                goodsskuMapper.updateByPrimaryKeySelective(goodssku);
            }
        }

        try {
            pushUtil.aliasPush(getUserKey(), "商城订单通知", "订单取消成功", null);
        } catch (Exception e) {
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
    }

    //快递鸟
    @RequestMapping("/app/orderLogisticsDetail")
    public String orderLogisticsDetail(@RequestBody JSONObject param) {
        String orderId = param.getString("orderId");
        ShopOrder shopOrder = orderMapper.selectByPrimaryKey(orderId);
        JSONObject jsonObject = JSONObject.parseObject(shopOrder.getLogistics());
        if(jsonObject.containsKey("waybillnumber")){
            String waybillnumber = jsonObject.getString("waybillnumber");
            KuaidiniaoEntity kuaidiniaoEntity = KdniaoApiUtils.queryOrderTracesByJson(waybillnumber);
            return JSONObject.toJSONString(new ResultDTO(200, "ok", kuaidiniaoEntity));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "物流异常！"));
    }
}
