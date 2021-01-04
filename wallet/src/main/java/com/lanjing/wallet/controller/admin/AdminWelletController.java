package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.ParametersService;
import com.lanjing.wallet.util.EthUtilHuang;
import com.lanjing.wallet.util.MD5Util;
import com.lanjing.wallet.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class AdminWelletController {

    @Autowired
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @RequestMapping("/admin/transferlist")
    public String transferlist(@RequestBody Map<String, String> map) {
        String state = map.get("state");
        int page = Integer.parseInt(map.get("page"));
        int size = Integer.parseInt(map.get("size"));
        Integer state1 = null;
        if (state != null && !"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        List<WellethistoryState> list = wellethistoryStateMapper.queryAlltransfer(state1, (page - 1) * size, size);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", list));
    }

    @RequestMapping("/admin/examine")
    public String examine(@RequestBody Map<String, String> map) {
        int fId = Integer.parseInt(map.get("Id"));
        int state = Integer.parseInt(map.get("Isadopt"));
        if (state == 1 || state == -2) {// 审核通过/拒绝
            WellethistoryState wellethistoryState = wellethistoryStateMapper.selectByPrimaryKey(fId);
            if (wellethistoryState.getState() == -1) {// 如果订单是 待审核
                wellethistoryState.setState(state);
                Wellets wellet = welletsMapper.selectByPrimaryKey(wellethistoryState.getWelletid());
                double num = wellethistoryState.getChangenum().doubleValue();
                if (num < 0) {
                    num = -num;
                }
                if (state == 1) {// 审核通过
                    //待接入节点
                    //wellethistoryState.setState(0);
                    String orderid = null;
                    /*if(wellet.getCoinid() == 1){
                        orderid = EthUtil.ethsend(ConfigConst.Turn_ADDRESS,wellet.getAddress(),num, ConfigConst.Turn_PWD, (long) (gas_price/21000*1000000000000000000l));
                    }else if(wellet.getCoinid() == 2){
                        orderid = EthUtil.tokensend(ConfigConst.Turn_ADDRESS,wellet.getAddress(),num,ConfigConst.Turn_PWD,(long) (gas_price/21000*1000000000000000000l));
                    }*/

                    orderid = UUID.randomUUID().toString().replace("-", "");
                    wellethistoryState.setKeyes(orderid);
                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + num));
                } else {// 拒绝
                    wellet.setReleasenum(BigDecimal.valueOf(wellet.getReleasenum().doubleValue() + num));
                    int i = welletsMapper.updateByPrimaryKeySelective(wellet);
                    if (i > 0) {
                        return JSONObject.toJSONString(new ResultDTO(-1, "审核失败"));
                    }
                }
                int i = welletsMapper.updateByPrimaryKeySelective(wellet);
                if (i > 0) {
                    int j = wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState);
                    if (j > 0) {
                        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
                    }
                }
                return JSONObject.toJSONString(new ResultDTO(-1, "审核失败"));
            }
            return JSONObject.toJSONString(new ResultDTO(-1, "该记录已被审核"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "审核失败"));
    }

    @RequestMapping("/admin/coinsList")
    public String coinsList() {
        List<Coins> coinsList = coinsMapper.selectAll();
        return JSONObject.toJSONString(new ResultDTO(200, "ok", coinsList));
    }

    @RequestMapping("/admin/updateCoins")
    public String updateCoins(@RequestBody JSONObject param) {
        Integer fId = param.getInteger("fId");
        BigDecimal price = param.getBigDecimal("price");
        Coins coins = coinsMapper.selectByPrimaryKey(fId);
        if (coins == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "币不存在"));
        }

        if (price.compareTo(BigDecimal.ZERO) < 1) {
            return JSONObject.toJSONString(new ResultDTO(-1, "价格要大于0"));
        }

        coins.setPrice(price.doubleValue());
        int i = coinsMapper.updateByPrimaryKeySelective(coins);
        if (i > 0) {
            return JSONObject.toJSONString(new ResultDTO(200, "ok"));
        } else {
            return JSONObject.toJSONString(new ResultDTO(-1, "更新失败"));
        }
    }

    //修改提币限额
    @RequestMapping("/admin/updateCoinsWithdrawLimit")
    public String updateCoinsWithdrawLimit(@RequestBody JSONObject param) {
        int coin = param.getIntValue("coinId");
        //统计今天最大的提币额度
        ParametersWithBLOBs parametersWithBLOBs = null;
        if (coin == 1) {//ETH
            parametersWithBLOBs = parametersService.findByKey("ethWithdrawLimit");
        } else if (coin == 2) {//AGC
            parametersWithBLOBs = parametersService.findByKey("agcWithdrawLimit");
        } else if (coin == 4) {//integral
            parametersWithBLOBs = parametersService.findByKey("integralWithdrawLimit");
        } else if (coin == 6) {//USDT
            parametersWithBLOBs = parametersService.findByKey("usdtWithdrawLimit");
        }

        if (parametersWithBLOBs != null) {
            String limitNumber = param.getString("limitNumber");
            parametersWithBLOBs.setKeyvalue(StringUtils.isEmpty(limitNumber) ? "0" : limitNumber);
            parametersService.updateByPrimaryKeySelective(parametersWithBLOBs);
        } else {
            return JSONObject.toJSONString(new ResultDTO(-1, "币不存在"));
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
    }

    //查询提币限额
    @RequestMapping("/admin/selectCoinsWithdrawLimit")
    public String selectCoinsWithdrawLimit() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        ParametersWithBLOBs parametersWithBLOBs1 = parametersService.findByKey("ethWithdrawLimit");
        jsonObject.put("limitNumber", parametersWithBLOBs1.getKeyvalue());
        jsonObject.put("coinId", 1);
        jsonObject.put("coin", "ETH");
        jsonArray.add(jsonObject);
        parametersWithBLOBs1 = parametersService.findByKey("agcWithdrawLimit");
        jsonObject = new JSONObject();
        jsonObject.put("limitNumber", parametersWithBLOBs1.getKeyvalue());
        jsonObject.put("coinId", 2);
        jsonObject.put("coin", "AGC");
        jsonArray.add(jsonObject);
        parametersWithBLOBs1 = parametersService.findByKey("integralWithdrawLimit");
        jsonObject = new JSONObject();
        jsonObject.put("limitNumber", parametersWithBLOBs1.getKeyvalue());
        jsonObject.put("coinId", 4);
        jsonObject.put("coin", "integral");
        jsonArray.add(jsonObject);
        parametersWithBLOBs1 = parametersService.findByKey("usdtWithdrawLimit");
        jsonObject = new JSONObject();
        jsonObject.put("limitNumber", parametersWithBLOBs1.getKeyvalue());
        jsonObject.put("coinId", 6);
        jsonObject.put("coin", "USDT");
        jsonArray.add(jsonObject);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", jsonArray));
    }

    //修改提币手续费比例
    @RequestMapping("/admin/updateServiceCharge")
    public String updateServiceCharge(@RequestBody JSONObject param) {
        ParametersWithBLOBs parametersWithBLOBs = parametersService.findByKey("serviceCharge");
        if (parametersWithBLOBs != null) {
            String serviceCharge = param.getString("serviceCharge");
            parametersWithBLOBs.setKeyvalue(StringUtils.isEmpty(serviceCharge) ? "0.005" : serviceCharge);
            parametersService.updateByPrimaryKeySelective(parametersWithBLOBs);
        } else {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数不存在"));
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
    }

    //查询提币手续费比例
    @RequestMapping("/admin/selectServiceCharge")
    public String selectServiceCharge() {
        JSONObject jsonObject = new JSONObject();
        ParametersWithBLOBs parametersWithBLOBs1 = parametersService.findByKey("serviceCharge");
        jsonObject.put("serviceCharge", parametersWithBLOBs1.getKeyvalue());
        return JSONObject.toJSONString(new ResultDTO(200, "ok", jsonObject));
    }
}
