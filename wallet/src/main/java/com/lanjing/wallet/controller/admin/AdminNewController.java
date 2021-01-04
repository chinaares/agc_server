package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.gson.JsonObject;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.dao.CoinsMapper;
import com.lanjing.wallet.dao.ParametersMapper;
import com.lanjing.wallet.dao.ShopOrderMapper;
import com.lanjing.wallet.dao.UsersMapper;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.AirdropService;
import com.lanjing.wallet.service.WellethistoryStateService;
import com.lanjing.wallet.util.BigDecimalUtils;
import com.lanjing.wallet.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 商城改进需求
 */
@RestController
@CrossOrigin(value = "*")
@SuppressWarnings("all")
public class AdminNewController extends BaseContrller {

    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Resource
    ParametersMapper parametersMapper;

    /**
     * 昨日 1.需要有销售额、用户访问量、订单量、新增用户等页面显示
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/yesterdayStatistics")
    public Object yesterdayStatistics() {
        BigDecimal salesVolume = BigDecimal.ZERO;
        BigDecimal visitsVolume = BigDecimal.ZERO;
        BigDecimal orderVolume = BigDecimal.ZERO;
        BigDecimal newsUserVolume = BigDecimal.ZERO;
        //查询昨日销售额
        List<ShopOrder> shopOrders = shopOrderMapper.queryYesterdaySalesList();
        for (ShopOrder shopOrder : shopOrders) {
            salesVolume = salesVolume.add(shopOrder.getCoinprive());
        }
        //用户访问量
        long numner = usersMapper.queryYesterdayLoginList();
        visitsVolume = BigDecimal.valueOf(numner);
        //订单量
        orderVolume = BigDecimal.valueOf(shopOrders.size());
        //新增用户
        long number = usersMapper.queryYesterdayNewUserList();
        newsUserVolume = BigDecimal.valueOf(number);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("salesVolume", BigDecimalUtils.getBigDecimalStr(salesVolume));//销售额
        jsonObject.put("visitsVolume", BigDecimalUtils.getBigDecimalStr(visitsVolume));//用户访问量
        jsonObject.put("orderVolume", BigDecimalUtils.getBigDecimalStr(orderVolume));//订单量
        jsonObject.put("newsUserVolume", BigDecimalUtils.getBigDecimalStr(newsUserVolume));//新增用户
        return Result.success(jsonObject);
    }

    /**
     * 订单管理页面需要有总销量统计
     * 统计订单总量  总共支付多少人民币 总共支付多少AGC
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/allOrderStatistics")
    public Object allOrderStatistics() {
        BigDecimal agcVolume = BigDecimal.ZERO;//总共支付多少AGC
        BigDecimal orderVolume = BigDecimal.ZERO;//订单总量
        BigDecimal cnyVolume = BigDecimal.ZERO;//总共支付多少人民币
        //订单总量
        List<ShopOrder> shopOrders = shopOrderMapper.selectAll();
        orderVolume = BigDecimal.valueOf(shopOrders.size());
        //总共支付多少人民币
        for (ShopOrder shopOrder : shopOrders) {
            cnyVolume = cnyVolume.add(shopOrder.getCoinprive());
        }
        //总共支付多少AGC
        Coins coins = coinsMapper.selectByPrimaryKey(2);
        //花费的人民币/该币的人民币价格
        agcVolume = cnyVolume.divide(new BigDecimal(coins.getPrice() + ""), RoundingMode.DOWN).setScale(6, RoundingMode.DOWN);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agcVolume", BigDecimalUtils.getBigDecimalStr(agcVolume));//总共支付多少AGC
        jsonObject.put("orderVolume", BigDecimalUtils.getBigDecimalStr(orderVolume));//订单总量
        jsonObject.put("cnyVolume", BigDecimalUtils.getBigDecimalStr(cnyVolume));//总共支付多少人民币
        return Result.success(jsonObject);
    }


    /**
     * 更新提币限制配置信息 withdrawalRestrictions提币百分比  wrMsg提币内容
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/updateWithdrawalParams")
    public Object allOrderStatistics(@RequestBody JSONObject param) {
        int type = param.getIntValue("type");//代表要修改的内容 1 修改提币限额百分比 比如0.1就代表10% 2 修改提币限额显示内容
        if (type == 1) {//修改提币限额百分比
            String withdrawalRestrictions = param.getString("withdrawalRestrictions");
            if (StringUtils.isEmpty(withdrawalRestrictions)) {
                return Result.error("百分比不能为空");
            }
            ParametersWithBLOBs parametersWithBLOBs = parametersMapper.selectByKey("withdrawalRestrictions");
            parametersWithBLOBs.setKeyvalue(withdrawalRestrictions);
            parametersMapper.updateByPrimaryKeySelective(parametersWithBLOBs);
        } else if (type == 2) {//修改提币限额显示内容
            String wrMsg = param.getString("wrMsg");
            if (StringUtils.isEmpty(wrMsg)) {
                return Result.error("提示内容不能为空");
            }
            ParametersWithBLOBs parametersWithBLOBs = parametersMapper.selectByKey("wrMsg");
            parametersWithBLOBs.setKeyvalue(wrMsg);
            parametersMapper.updateByPrimaryKeySelective(parametersWithBLOBs);
        }
        return Result.success();
    }

    /**
     * 查询提币限制配置信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/queryWithdrawalParams")
    public Object queryWithdrawalParams() {
        JSONObject jsonObject = new JSONObject();
        ParametersWithBLOBs withdrawalRestrictions = parametersMapper.selectByKey("withdrawalRestrictions");
        ParametersWithBLOBs wrMsg = parametersMapper.selectByKey("wrMsg");
        jsonObject.put("withdrawalRestrictions", withdrawalRestrictions.getKeyvalue());
        jsonObject.put("wrMsg", wrMsg.getKeyvalue());
        return Result.success(jsonObject);
    }
}
