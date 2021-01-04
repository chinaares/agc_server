package com.lanjing.otc.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PushUtil;
import com.lanjing.otc.config.Config;
import com.lanjing.otc.controller.BaseController;
import com.lanjing.otc.model.OtcAds;
import com.lanjing.otc.model.OtcOrder;
import com.lanjing.otc.model.Wallets;
import com.lanjing.otc.service.OtcAdsService;
import com.lanjing.otc.service.OtcOrderService;
import com.lanjing.otc.service.ParametersService;
import com.lanjing.otc.service.WalletsService;
import com.lanjing.otc.util.BASE64imgUtil;
import com.lanjing.otc.util.GetInformation;
import com.lanjing.otc.util.ResultDTO;
import com.lanjing.otc.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OtcOrderController extends BaseController {

    @Value("${wallet.url}")
    private String urlStr;

    @Autowired
    private PushUtil pushUtil;

    @Autowired
    private OtcOrderService otcOrderService;

    @Autowired
    private ParametersService parametersService;

    @Autowired
    private OtcAdsService otcAdsService;

    @Autowired
    private GetInformation getInformation;

    @Autowired
    private WalletsService walletsService;

    @RequestMapping("otc/order/trade")
    public String otctrade(@RequestBody JSONObject param){
        Integer adId = param.getInteger("adId");
        Double coinnum = param.getDouble("coinnum");
        String remark = param.getString("remark");
        String tradepwd = param.getString("tradepwd");
        if(adId == null || coinnum == null){
            return JSONObject.toJSONString(new ResultDTO(-1,"参数缺失"));
        }
        if(coinnum <= 0){
            return JSONObject.toJSONString(new ResultDTO(-1,"数量必须大于零"));
        }
        try {
            JSONObject jsonObject = otcOrderService.otctrade(adId,getUserId(),coinnum,remark,tradepwd);
            return jsonObject.toJSONString();
        }catch (Exception e){
            return JSONObject.toJSONString(new ResultDTO(-1,e.getMessage()));
        }
    }

    @RequestMapping("otc/order/surePay")
    public String surePay(@RequestBody JSONObject param){
        Integer orderId = param.getInteger("Id");
        String picture = param.getString("paypicture");
        Integer payawayId = param.getInteger("payawaytype");
        String tradepwd = param.getString("tradepwd");
        if (tradepwd == null || orderId == null || picture == null || payawayId == null){
            return JSONObject.toJSONString(new ResultDTO(-1,"参数缺失"));
        }
        if ("".equals(tradepwd)){
            return JSONObject.toJSONString(new ResultDTO(-1,"请输入交易密码"));
        }
        JSONObject user = getInformation.getUser(getUserKey());
        if(!tradepwd.trim().equals(user.getString("transpassword"))){
            return JSONObject.toJSONString(new ResultDTO(-1,"交易密码错误！"));
        }
        OtcOrder otcOrder = otcOrderService.selectByPrimaryKey(orderId);
        otcOrder.setState(1);
        otcOrder.setPayaWay(payawayId);
        otcOrder.setReject(null);
        String courseFile = parametersService.selectByKey("imgbath").getKeyValue();
        String imgurl = BASE64imgUtil.GenerateImage(picture,courseFile);
        otcOrder.setPayPicture(parametersService.selectByKey("imgurl").getKeyValue()+imgurl.substring(imgurl.lastIndexOf("/")));
        return otcOrderService.otcsurePay(otcOrder).toJSONString();
    }

    @RequestMapping("otc/order/otcRelease")
    public String otcRelease(@RequestBody JSONObject param){
        Integer orderId = param.getInteger("Id");
        Integer state = param.getInteger("state");
        String backcontent = param.getString("content");
        if (orderId == null){
            return JSONObject.toJSONString(new ResultDTO(-1,"参数缺失！"));
        }
        JSONObject jsonObject = otcOrderService.otcrelease(orderId,getUserId(),state,backcontent);
        return jsonObject.toJSONString();
    }

    @RequestMapping("otc/order/appeal")
    public String appeal(@RequestBody JSONObject param){
        Integer orderId = param.getInteger("Id");
        String contenttext = param.getString("contenttext");
        String picture = param.getString("picture");
        String courseFile = parametersService.selectByKey("imgbath").getKeyValue();
        String imgurl = BASE64imgUtil.GenerateImage(picture,courseFile);
        OtcOrder otcOrder = otcOrderService.selectByPrimaryKey(orderId);
        if(otcOrder.getDirection() == 1){
            if(otcOrder.getTradeUser().intValue() != getUserId().intValue()){
                return JSONObject.toJSONString(new ResultDTO(-1,"您没有申诉权限"));
            }
        }else {
            if(otcOrder.getTradeUser().intValue() == getUserId().intValue()){
                return JSONObject.toJSONString(new ResultDTO(-1,"您没有申诉权限"));
            }
        }
        otcOrder.setState(3);
        otcOrder.setAppealStatus(1);
        otcOrder.setAppealText(contenttext);
        otcOrder.setAppealImg(parametersService.selectByKey("imgurl").getKeyValue()+imgurl.substring(imgurl.lastIndexOf("/")));
        otcOrder.setAppealTime(new Date());
        int i = otcOrderService.updateByPrimaryKeySelective(otcOrder);
        if(i > 0){
            if (otcOrder.getDirection() == 1){
                try {
                    pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","申诉成功",null);
                    pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"买家已发起申述，工作人员会及时处理，请保持联系。",null);
                }catch (Exception e){}
                SMSUtil.orderRepresentation(otcOrder.getTradePhone(),otcOrder.getOrderId(),1);
                SMSUtil.orderRepresentation(otcOrder.getAdphone(),otcOrder.getOrderId(),2);
            }else {
                try {
                    pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","申诉成功",null);
                    pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"买家已发起申述，工作人员会及时处理，请保持联系。",null);
                }catch (Exception e){}
                SMSUtil.orderRepresentation(otcOrder.getTradePhone(),otcOrder.getOrderId(),2);
                SMSUtil.orderRepresentation(otcOrder.getAdphone(),otcOrder.getOrderId(),1);
            }
            return JSONObject.toJSONString(new ResultDTO(200,"ok"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1,"failure"));
    }

    @RequestMapping("otc/order/orderlist")
    public String otcOrderlist(@RequestBody JSONObject param){
        Integer state = param.getInteger("state");
        Integer page = param.getInteger("page");
        Integer size = param.getInteger("size");
        if (state == null || page == null || size == null){
            return JSONObject.toJSONString(new ResultDTO(-1,"参数缺失"));
        }
        List<OtcOrder> otcOrderlist = otcOrderService.selectByuser(getUserId(),state,(page-1)*size,size);
        return JSONObject.toJSONString(new ResultDTO(200,"ok",otcOrderlist));
    }

    @RequestMapping("otc/order/otcOrderdetail")
    public String otcOrderdetail(@RequestBody JSONObject param){
        Integer id = param.getInteger("Id");
        OtcOrder otcOrder = otcOrderService.selectByPrimaryKey(id);
        if(otcOrder == null){
            return JSONObject.toJSONString(new ResultDTO(-1,"订单不存在"));
        }
        if(otcOrder.getState() ==0){
            if((otcOrder.getCreateTime().getTime()+1000*60*30) <= (new Date().getTime())){
                if(otcOrder.getDirection().intValue() ==1){
                    otcOrder.setState(-1);
                    otcOrderService.updateByPrimaryKeySelective(otcOrder);
                    OtcAds otcAds = otcAdsService.selectByPrimaryKey(otcOrder.getAdsId());
                    otcAds.setNumBalance(BigDecimal.valueOf(otcAds.getNumBalance().doubleValue()+otcOrder.getCoinNum().doubleValue()));
                    otcAdsService.updateByPrimaryKeySelective(otcAds);
                }else {
                    otcOrder.setState(-1);
                    otcOrderService.updateByPrimaryKeySelective(otcOrder);
                    Wallets wallet = walletsService.selectByUserandcoin(String.valueOf(otcOrder.getTradeUser()),otcOrder.getCoin());
                    wallet.setCoinnum(wallet.getCoinnum()+otcOrder.getCoinNum().doubleValue());
                    wallet.setFrozennum(wallet.getFrozennum()-otcOrder.getCoinNum().doubleValue());

                    if(wallet.getFrozennum()<0){
                        return JSONObject.toJSONString(new ResultDTO(-1,"冻结不应该出现负数"));
                    }


                    walletsService.updateByPrimaryKeySelective(wallet);
                    getInformation.insertwellethistory(wallet.getUserkey(),wallet.getCoinid(),otcOrder.getCoinNum().doubleValue(),9);
                    OtcAds otcAds = otcAdsService.selectByPrimaryKey(otcOrder.getAdsId());
                    otcAds.setNumBalance(BigDecimal.valueOf(otcAds.getNumBalance().doubleValue()+otcOrder.getCoinNum().doubleValue()));
                    otcAdsService.updateByPrimaryKeySelective(otcAds);
                }
                SMSUtil.cancelOrder(otcOrder.getTradePhone(),otcOrder.getOrderId());
                SMSUtil.cancelOrder(otcOrder.getAdphone(),otcOrder.getOrderId());
            }
        }else if(otcOrder.getState() == 1) {
            if((otcOrder.getUpdateTime().getTime()+1000*60*60*24) <= (new Date().getTime())){
                JSONObject jsonObject = otcOrderService.otcrelease(id,getUserId(),1,null);
            }
        }
        Map map = new HashMap();
        if (otcOrder.getDirection() == 1){
            map.put("userkey",otcOrder.getAdsUser());
        }else {
            map.put("userkey",otcOrder.getTradeUser());
        }
        JSONObject jsonObject = getInformation.postObject(urlStr+"zuul/getpayaway",map);
        JSONObject order = JSON.parseObject(JSONObject.toJSONString(otcOrder));
        order.put("pay",jsonObject);
        return JSONObject.toJSONString(new ResultDTO(200,"ok",order));
    }

    @RequestMapping("otc/order/cancel")
    public String cancel(@RequestBody JSONObject param){
        Integer id = param.getInteger("Id");
        OtcOrder otcOrder = otcOrderService.selectByPrimaryKey(id);
        if(otcOrder == null){
            return JSONObject.toJSONString(new ResultDTO(-1,"订单不存在"));
        }
        OtcAds otcAds = otcAdsService.selectByPrimaryKey(otcOrder.getAdsId());
        otcAds.setNumBalance(BigDecimal.valueOf(otcAds.getNumBalance().doubleValue()+otcOrder.getCoinNum().doubleValue()));
        otcAdsService.updateByPrimaryKeySelective(otcAds);
        if(otcOrder.getDirection() == 1){
            if(otcOrder.getTradeUser().intValue() != getUserId().intValue()){
                return JSONObject.toJSONString(new ResultDTO(-1,"您无法取消该订单"));
            }
        }else {
            if(otcOrder.getTradeUser().intValue() == getUserId().intValue()){
                return JSONObject.toJSONString(new ResultDTO(-1,"您无法取消该订单"));
            }
            Wallets wallet = walletsService.selectByUserandcoin(String.valueOf(otcOrder.getTradeUser()),otcOrder.getCoin());
            wallet.setCoinnum(wallet.getCoinnum()+otcOrder.getCoinNum().doubleValue());
            wallet.setFrozennum(wallet.getFrozennum()-otcOrder.getCoinNum().doubleValue());
            if(wallet.getFrozennum()<0){
                return JSONObject.toJSONString(new ResultDTO(-1,"冻结不应该出现负数"));
            }

            walletsService.updateByPrimaryKeySelective(wallet);
            getInformation.insertwellethistory(wallet.getUserkey(),wallet.getCoinid(),otcOrder.getCoinNum().doubleValue(),9);
        }
        if(otcOrder.getState() != 0 ){
            return JSONObject.toJSONString(new ResultDTO(-1,"无法取消该订单"));
        }
        otcOrder.setState(-1);
        int i = otcOrderService.updateByPrimaryKeySelective(otcOrder);
        if(i > 0){
            SMSUtil.cancelOrder(otcOrder.getTradePhone(),otcOrder.getOrderId());
            SMSUtil.cancelOrder(otcOrder.getAdphone(),otcOrder.getOrderId());
            return JSONObject.toJSONString(new ResultDTO(200,"ok"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1,"failure"));
    }

}
