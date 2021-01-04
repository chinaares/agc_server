package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.agc.service.IAgcConsumeRecordService;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.CoinsMapper;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.model.WellethistoryState;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.WelletService;
import com.lanjing.wallet.service.WellethistoryStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@RestController
public class zuulController {

    @Resource(name = "WelletService")
    private WelletService welletService;

    @Resource(name = "WellethistoryStateService")
    private WellethistoryStateService wellethistoryStateService;
    
    @Autowired
    private IAgcConsumeRecordService agcConsumeRecordService;

    @Resource
    private CoinsMapper coinsMapper;

    @RequestMapping("/zuul/Pay")
    public String Pay(@RequestBody JSONObject param) {
        String userkey = param.getString("userkey");
        Integer coin = param.getInteger("coin");
        Double coinnum = param.getDouble("coinnum");
        String remark = param.getString("remark");
        if (userkey == null || coin == null || coinnum == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        if (coinnum <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "价格异常！"));
        }
        Wellets wellet = welletService.selectByUserandcoin(userkey, coin);
        if (wellet.getCoinnum().doubleValue() <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "余额不足！"));
        }
        if (wellet.getCoinnum().doubleValue() < coinnum) {
            return JSONObject.toJSONString(new ResultDTO(-1, "余额不足！"));
        }
        wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() - coinnum));
        int i = welletService.updateByPrimaryKeySelective(wellet);
        if (i > 0) {
            WellethistoryState wellethistoryState = new WellethistoryState();
            wellethistoryState.setWelletid(wellet.getFid());
            wellethistoryState.setChangenum(BigDecimal.valueOf(-coinnum));
            wellethistoryState.setBalance(wellet.getCoinnum());
            wellethistoryState.setType(6);
            wellethistoryState.setRemark(remark);
            Date time = new Date();
            wellethistoryState.setCreatetime(time);
            wellethistoryState.setUpdatetime(time);
            wellethistoryState.setState(1);
            wellethistoryState.setCointype(coin);
            wellethistoryState.setUserid(Integer.valueOf(userkey));
            wellethistoryStateService.insertSelective(wellethistoryState);
            return JSONObject.toJSONString(new ResultDTO(200, "支付成功！"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "支付失败！"));
    }

    @RequestMapping("/zuul/Pay1")
    public String Pay1(@RequestBody JSONObject param) {
        String userkey = param.getString("userkey");
        Integer coin = ConfigConst.YYB_ID;
        Integer coin2 = ConfigConst.INTEGRAL_ID;
        Double coinnum = param.getDouble("coinnum");
        Double integralnum = param.getDouble("integralnum");
        String remark = param.getString("remark");
        if (userkey == null || coinnum == null || integralnum == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        if (coinnum <= 0 || integralnum < 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "价格异常！"));
        }
        //钱包存的是个数
        Wellets wellet = welletService.selectByUserandcoin(userkey, coin);
        Wellets integral = welletService.selectByUserandcoin(userkey, coin2);
        if (wellet.getCoinnum().doubleValue() <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "AGC余额不足！"));
        }
        //判断
        Coins coins = coinsMapper.selectByPrimaryKey(coin);
        //花费的人民币/该币的人民币价格
        BigDecimal number = new BigDecimal(coinnum).divide(new BigDecimal(coins.getPrice() + ""), RoundingMode.DOWN)
                .setScale(6, RoundingMode.DOWN);

        if (wellet.getCoinnum().compareTo(number) < 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "AGC余额不足！"));
        }
        wellet.setCoinnum(wellet.getCoinnum().subtract(number));
        
        int i = welletService.updateByPrimaryKeySelective(wellet);
        if (i > 0) {
	        //添加购物消费记录
	        WellethistoryState wellethistoryState = new WellethistoryState();
	        wellethistoryState.setWelletid(wellet.getFid());
	        wellethistoryState.setChangenum(BigDecimal.valueOf(-number.doubleValue()));
	        wellethistoryState.setBalance(wellet.getCoinnum());
	        wellethistoryState.setType(6);
	        wellethistoryState.setRemark(remark);//购物消费
	        Date time = new Date();
	        wellethistoryState.setCreatetime(time);
	        wellethistoryState.setUpdatetime(time);
	        wellethistoryState.setState(1);
	        wellethistoryState.setCointype(coin);//AGC币
	        wellethistoryState.setUserid(Integer.valueOf(userkey));
	        wellethistoryStateService.insertSelective(wellethistoryState);
	        
//	        //送人民总值的 20%
//	        integralnum = new BigDecimal(coinnum).multiply(new BigDecimal("0.2")).doubleValue();
//	
//	        //购物送积分
//	        if (integralnum > 0) {
//	            integral.setCoinnum(BigDecimal.valueOf(integral.getCoinnum().doubleValue() + integralnum));
//	            welletService.updateByPrimaryKeySelective(integral);
//	            //添加购物积分记录
//	            wellethistoryState = new WellethistoryState();
//	            wellethistoryState.setType(6);
//	            wellethistoryState.setRemark("积分赠送");//购物消费
//	            wellethistoryState.setCreatetime(time);
//	            wellethistoryState.setUpdatetime(time);
//	            wellethistoryState.setState(1);
//	            wellethistoryState.setUserid(Integer.valueOf(userkey));
//	            wellethistoryState.setWelletid(integral.getFid());
//	            wellethistoryState.setChangenum(BigDecimal.valueOf(integralnum));
//	            wellethistoryState.setBalance(integral.getCoinnum());
//	            wellethistoryState.setCointype(4);//积分币
//	            wellethistoryStateService.insertSelective(wellethistoryState);
//	        }
	        
	        //增加算力
	        agcConsumeRecordService.recordAgcConsumeRecord(userkey, new BigDecimal(coinnum), number);
        }
        return JSONObject.toJSONString(new ResultDTO(200, "支付成功！"));
        
    }


    @RequestMapping("/zuul/PayCancel")
    public String PayCancel(@RequestBody JSONObject param) {
        String userkey = param.getString("userkey");
        Integer coin = param.getInteger("coin");
        Double coinnum = param.getDouble("coinnum");
        if (userkey == null || coin == null || coinnum == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数缺失！"));
        }
        if (coinnum <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "价格异常！"));
        }
        Wellets wellet = welletService.selectByUserandcoin(userkey, coin);
        wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + coinnum));
        int i = welletService.updateByPrimaryKeySelective(wellet);
        if (i > 0) {
            WellethistoryState wellethistoryState = new WellethistoryState();
            wellethistoryState.setWelletid(wellet.getFid());
            wellethistoryState.setChangenum(BigDecimal.valueOf(coinnum));
            wellethistoryState.setBalance(wellet.getCoinnum());
            wellethistoryState.setType(10);
            wellethistoryState.setRemark("购物退回");
            Date time = new Date();
            wellethistoryState.setCreatetime(time);
            wellethistoryState.setUpdatetime(time);
            wellethistoryState.setState(1);
            wellethistoryState.setCointype(coin);
            wellethistoryState.setUserid(Integer.valueOf(userkey));
            wellethistoryStateService.insertSelective(wellethistoryState);
            return JSONObject.toJSONString(new ResultDTO(200, "退款成功！"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "退款失败！"));
    }

}
