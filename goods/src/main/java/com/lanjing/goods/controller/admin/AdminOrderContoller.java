package com.lanjing.goods.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PushUtil;
import com.lanjing.goods.SpringContextUtil;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.dao.GoodsskuMapper;
import com.lanjing.goods.dao.ShopOrderMapper;
import com.lanjing.goods.model.Admin;
import com.lanjing.goods.model.Goodssku;
import com.lanjing.goods.model.MallShop;
import com.lanjing.goods.model.ShopOrder;
import com.lanjing.goods.service.AdminService;
import com.lanjing.goods.service.MallShopService;
import com.lanjing.goods.util.GetInformation;
import com.lanjing.goods.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AdminOrderContoller extends BaseController {

    @Value("${wallet.url}")
    private String urlStr;

    @Autowired
    private PushUtil pushUtil;

    @Autowired
    private ShopOrderMapper orderMapper;

    @Autowired
    private GetInformation getInformation;

    @Autowired
    private MallShopService mallShopService;

    @Autowired
    private GoodsskuMapper goodsskuMapper;

    @Autowired
    private AdminService adminService;

    /**
     * 订单管理页面需要有可以搜查订单号，收件人，电话号码等
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/orders")
    public String orderlist(@RequestBody JSONObject param) {
        String state = param.getString("state");
        Integer state1 = null;
        if (state != null && !"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        String orderid = param.getString("orderid");//订单号
        String userphone = param.getString("userphone");//手机号
        String username = param.getString("username");//收件人姓名
        Integer page = param.getInteger("page");
        Integer size = param.getInteger("size");
        String shop_code = param.getString("shopcode");
        if (page == null || size == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        Admin admin = adminService.selectByPrimaryKey(getId());
        if (admin == null) {
            return JSONObject.toJSONString(new ResultDTO(600, "请重新登录"));
        }
        List<Map> list;
        int count = 0;
        if (StringUtils.isEmpty(shop_code)) {
            list = orderMapper.selectOrderBypage1(null, state1, orderid, userphone, username, (page - 1) * size, size);
            count = orderMapper.selectOrderBypageCount1(null, state1, orderid, username, userphone);
        } else {
            long shopcode = Long.parseLong(shop_code);
            list = orderMapper.selectOrderBypage1(shopcode, state1, orderid, userphone, username, (page - 1) * size, size);
            count = orderMapper.selectOrderBypageCount1(shopcode, state1, orderid, username, userphone);
        }
        List<JSONObject> list1 = new ArrayList<>();
        for (Map map : list) {
            JSONObject jsonObject = queryByOrderid(map.get("orderId") + "");
            list1.add(jsonObject);
        }
        int totalpage = (count % size == 0 ? count / size : count / size + 1);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        map.put("data", list1);
        map.put("state", state);
        map.put("totalpage", totalpage);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }

    public JSONObject queryByOrderid(String orderId) {
        List<ShopOrder> orderList = orderMapper.queryByOrderid(orderId);
        JSONObject json = new JSONObject();
        ShopOrder order = orderList.get(0);
        json.put("orderId", order.getOrderid());
        json.put("name", order.getUsername());
        json.put("phone", order.getPhone());
        json.put("address", order.getAddress());
        json.put("remark", order.getRemark());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (order.getPaytime() != null) {
            json.put("paytime", format.format(order.getPaytime()));
        } else {
            json.put("paytime", "");
        }
        json.put("Logistics", JSONObject.parseObject(order.getLogistics()));
        json.put("state", order.getState());
        MallShop mallShop = mallShopService.findByCode(order.getShop_code());
        json.put("mallshopname", mallShop.getName());
        json.put("mallshopcode", mallShop.getCode());
        List<JSONObject> map = new ArrayList<>();
        double sumprice = 0;
        double sumintegralnum = 0;
        for (ShopOrder order1 : orderList) {
            if (order1.getShop_code().longValue() == mallShop.getCode()) {
                Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(order1.getGoodsskuId());
                JSONObject goodsskujson = JSON.parseObject(JSONObject.toJSONString(goodssku), JSONObject.class);
                goodsskujson.put("num", order1.getNum());
                goodsskujson.put("goodsId", order1.getGoodsid());
                sumprice += order1.getCoinprive().doubleValue();
                sumintegralnum += order1.getIntegralnum().doubleValue();
                map.add(goodsskujson);
            }
        }
        json.put("goodsskus", map);
        json.put("sumprice", sumprice);
        json.put("sumintegralnum", sumintegralnum);
        return json;
    }

    @RequestMapping("/admin/goods/orderShip")
    public String orderShip(@RequestBody JSONObject param) {
        String orderId = param.getString("orderId");
        String Logistics = param.getString("logistics");
        String waybillnumber = param.getString("waybillnumber");
        if (orderId == null || Logistics == null || waybillnumber == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        JSONObject json = new JSONObject();
        json.put("company", Logistics);
        json.put("waybillnumber", waybillnumber);
        json.put("uadmin", getId());
        List<ShopOrder> orders = orderMapper.queryByOrderid(orderId);
        if (orders != null && orders.size() > 0) {
            if (orders.get(0).getState() != 0) {
                return JSONObject.toJSONString(new ResultDTO(-1, "该订单已经发货！"));
            }
            int i = orderMapper.updateLogisticsByOrderid(orderId, json.toJSONString());
            if (i > 0) {
                return JSONObject.toJSONString(new ResultDTO(200, "ok"));
            }
        }
        try {
            pushUtil.aliasPush(String.valueOf(orders.get(0).getUserkey()), "商城订单通知", "您有一个订单已经发货", null);
        } catch (Exception e) {

        }
        return JSONObject.toJSONString(new ResultDTO(-1, "failure"));
    }

    /**
     * 查询快递
     *
     * @return
     */
    @RequestMapping("/admin/goods/logisticsList")
    public String logisticsList() {
        List<LogisticsInfo> logisticsInfos = new ArrayList<>();
        logisticsInfos.add(new LogisticsInfo("顺丰速运", "SF"));
        logisticsInfos.add(new LogisticsInfo("百世快递", "HTKY"));
        logisticsInfos.add(new LogisticsInfo("中通快递", "ZTO"));
        logisticsInfos.add(new LogisticsInfo("申通快递", "STO"));
        logisticsInfos.add(new LogisticsInfo("圆通速递", "YTO"));
        logisticsInfos.add(new LogisticsInfo("韵达速递", "YD"));
        logisticsInfos.add(new LogisticsInfo("邮政快递包裹", "YZPY"));
        logisticsInfos.add(new LogisticsInfo("EMS", "EMS"));
        logisticsInfos.add(new LogisticsInfo("天天快递", "HHTT"));
        logisticsInfos.add(new LogisticsInfo("京东快递", "JD"));
        logisticsInfos.add(new LogisticsInfo("优速快递", "UC"));
        logisticsInfos.add(new LogisticsInfo("德邦快递", "DBL"));
        logisticsInfos.add(new LogisticsInfo("宅急送", "ZJS"));
        return JSONObject.toJSONString(new ResultDTO(200, "ok", logisticsInfos));
    }

    public static class LogisticsInfo {
        private String logisticsName;//快递名称
        private String logisticsCode;//快递code

        public LogisticsInfo(String logisticsName, String logisticsCode) {
            this.logisticsName = logisticsName;
            this.logisticsCode = logisticsCode;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
        }

        public String getLogisticsCode() {
            return logisticsCode;
        }

        public void setLogisticsCode(String logisticsCode) {
            this.logisticsCode = logisticsCode;
        }
    }

    @RequestMapping("/admin/goods/orderdetail")
    public String orderdetail(@RequestBody JSONObject param) {
        String orderId = param.getString("orderId");
        List<ShopOrder> orderList = orderMapper.queryByOrderid(orderId);
        JSONObject json = new JSONObject();
        ShopOrder order = orderList.get(0);
        json.put("orderId", order.getOrderid());
        json.put("name", order.getUsername());
        json.put("phone", order.getPhone());
        json.put("address", order.getAddress());
        json.put("time", order.getPaytime());
        json.put("Logistics", JSONObject.parseObject(order.getLogistics()));
        json.put("state", order.getState());
        MallShop mallShop = mallShopService.findByCode(Long.valueOf(order.getShop_code()));
        json.put("mallshopname", mallShop.getName());
        json.put("mallshopcode", mallShop.getCode());
        List<JSONObject> map = new ArrayList<>();
        double sumprice = 0;
        double sumintegralnum = 0;
        for (ShopOrder order1 : orderList) {
            if (order1.getShop_code().longValue() == mallShop.getCode()) {
                Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(order1.getGoodsskuId());
                JSONObject goodsskujson = JSON.parseObject(JSONObject.toJSONString(goodssku), JSONObject.class);
                goodsskujson.put("num", order1.getNum());
                goodsskujson.put("goodsId", order1.getGoodsid());
                sumprice += order1.getCoinprive().doubleValue();
                sumintegralnum += order1.getIntegralnum().doubleValue();
                map.add(goodsskujson);
            }
        }
        json.put("goodsskus", map);
        json.put("sumprice", sumprice);
        json.put("sumintegralnum", sumintegralnum);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", json));
    }

    @RequestMapping("/admin/goods/cancelOrder")
    public String cancelOrder(@RequestBody JSONObject param) {
        String fId = param.getString("fId");
        if (orderMapper == null) {
            orderMapper = SpringContextUtil.getBean(ShopOrderMapper.class);
        }
        ShopOrder order = orderMapper.selectByPrimaryKey(fId);
        if (order == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "订单不存在"));
        }
        if (order.getState() != -1) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该订单不能进行此操作"));
        }
        order.setState(-2);
        Map pay = new HashMap();
        pay.put("userkey", order.getUserkey());
        pay.put("coin", "2");
        pay.put("coinnum", order.getCoinprive());
        JSONObject payjson = getInformation.postObject(urlStr + "zuul/PayCancel", pay);
        pay.put("coin", "4");
        pay.put("coinnum", order.getIntegralnum());
        getInformation.postObject(urlStr + "zuul/PayCancel", pay);
        int code = payjson.getInteger("code").intValue();
        if (code == 200) {
            int i = orderMapper.updateByPrimaryKeySelective(order);
            if (i > 0) {
                return JSONObject.toJSONString(new ResultDTO(200, "ok"));
            } else {
                return JSONObject.toJSONString(new ResultDTO(-1, "状态修改失败"));
            }
        }
        try {
            pushUtil.aliasPush(String.valueOf(order.getUserkey()), "商城订单通知", "您有一个订单被取消", null);
        } catch (Exception e) {

        }
        return JSONObject.toJSONString(new ResultDTO(-1, "退款失败！"));
    }
}
