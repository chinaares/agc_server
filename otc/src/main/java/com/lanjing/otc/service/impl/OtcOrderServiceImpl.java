package com.lanjing.otc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PushUtil;
import com.jester.util.utils.Result;
import com.lanjing.otc.config.Config;
import com.lanjing.otc.dao.OtcAdsMapper;
import com.lanjing.otc.dao.OtcOrderMapper;
import com.lanjing.otc.dao.UsersMapper;
import com.lanjing.otc.dao.WalletsMapper;
import com.lanjing.otc.model.*;
import com.lanjing.otc.service.OtcOrderService;
import com.lanjing.otc.util.GetInformation;
import com.lanjing.otc.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 14:38
 */
@Service
public class OtcOrderServiceImpl implements OtcOrderService {

    @Value("${wallet.url}")
    private String urlStr;

    @Autowired
    private PushUtil pushUtil;

    @Resource
    private OtcOrderMapper otcOrderMapper;

    @Resource
    private OtcAdsMapper otcAdsMapper;

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private WalletsMapper walletsMapper;

    @Autowired
    private GetInformation getInformation;

    @Override
    public long countByExample(OtcOrderExample example) {
        return otcOrderMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(OtcOrderExample example) {
        return otcOrderMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return otcOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OtcOrder record) {
        return otcOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(OtcOrder record) {
        return otcOrderMapper.insertSelective(record);
    }

    @Override
    public List<OtcOrder> selectByExample(OtcOrderExample example) {
        return otcOrderMapper.selectByExample(example);
    }

    @Override
    public OtcOrder selectByPrimaryKey(Integer id) {
        return otcOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(OtcOrder record, OtcOrderExample example) {
        return otcOrderMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(OtcOrder record, OtcOrderExample example) {
        return otcOrderMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(OtcOrder record) {
        return otcOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public JSONObject otcsurePay(OtcOrder record) {
        JSONObject jsonObject = new JSONObject();
        int j = otcOrderMapper.updateByPrimaryKeySelective(record);
        if (j > 0){
            SMSUtil.orderPaid(record.getTradePhone(),record.getOrderId(),1);
            SMSUtil.orderPaid(record.getAdphone(),record.getOrderId(),2);
            if (record.getDirection() == 1){
                try {
                    pushUtil.aliasPush(String.valueOf(record.getTradeUser()),"otc订单通知","确认支付成功，请等待对方确认",null);
                    pushUtil.aliasPush(String.valueOf(record.getAdsUser()),"otc订单通知","您有一笔交易对方已确认收款，请注意查看并放行交易",null);
                }catch (Exception e){}
                SMSUtil.orderPaid(record.getTradePhone(),record.getOrderId(),1);
                SMSUtil.orderPaid(record.getAdphone(),record.getOrderId(),2);
            }else {
                try {
                    pushUtil.aliasPush(String.valueOf(record.getAdsUser()),"otc订单通知","确认支付成功，请等待对方确认",null);
                    pushUtil.aliasPush(String.valueOf(record.getTradeUser()),"otc订单通知","您有一笔交易对方已确认收款，请注意查看并放行交易",null);
                }catch (Exception e){}
                SMSUtil.orderPaid(record.getTradePhone(),record.getOrderId(),2);
                SMSUtil.orderPaid(record.getAdphone(),record.getOrderId(),1);
            }
            jsonObject.put("code",200);
            jsonObject.put("msg","ok");
            return jsonObject;
        }else {
            jsonObject.put("code",-1);
            jsonObject.put("msg","failure");
            return jsonObject;
        }
    }

    @Override
    public int updateByPrimaryKey(OtcOrder record) {
        return otcOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public JSONObject otctrade(Integer adId, Integer userkey, Double coinnum, String remark, String tradepwd) {
        JSONObject json = new JSONObject();
        Users tradeuser = usersMapper.selectByPrimaryKey(userkey);
        OtcAds otcAds = otcAdsMapper.selectByPrimaryKey(adId);
        int unFinishedCount  =  otcOrderMapper.selectUnfinished(userkey);
        if(unFinishedCount>0){
            json.put("code",-1);
            json.put("msg","还有："+unFinishedCount+" 笔订单未完成");
            return json;
        }
        if(otcAds != null && otcAds.getStatus() == 1 && otcAds.getReviewStatus() == 1){
            if(tradeuser.getFid().intValue() == otcAds.getUserKey().intValue()){
                json.put("code",-1);
                json.put("msg","不能跟本账号交易！");
                return json;
            }
            if (otcAds.getNumBalance().doubleValue() <coinnum){
                json.put("code",-1);
                json.put("msg","该广告剩余量不足，请减少交易数量！");
                return json;
            }
            Users aduser = usersMapper.selectByPrimaryKey(otcAds.getUserKey());
            if (aduser==null){
                json.put("code",-1);
                json.put("msg","该订单不可交易！");
                return json;
            }
            OtcOrder otcOrder = new OtcOrder();
            otcOrder.setAdsId(otcAds.getId());
            otcOrder.setCoin(otcAds.getCoinId());
            otcOrder.setAdphone(aduser.getPhonenum());
            otcOrder.setAdsUser(aduser.getFid());
            otcOrder.setAdsUserName(otcAds.getUserName());
            otcOrder.setCoinNum(BigDecimal.valueOf(coinnum));
            otcOrder.setTradeUser(tradeuser.getFid());
            otcOrder.setTradePhone(tradeuser.getPhonenum());
            otcOrder.setRemark(remark);
            otcOrder.setPrice(otcAds.getPrice());
            otcOrder.setCnyNum(BigDecimal.valueOf(coinnum*otcAds.getPrice().doubleValue()));
            Date time = new Date();
            otcOrder.setOrderId(String.valueOf(time.getTime()));
            otcOrder.setCreateTime(time);

            //改变广告剩余量
            if(otcAds.getNumBalance().doubleValue() <coinnum){
                json.put("code",-1);
                json.put("msg","该广告得剩余不足");
                return json;
            }
            //方向
            if(otcAds.getType() == 1){
                if(tradepwd == null){
                    json.put("code",-1);
                    json.put("msg","交易密码错误");
                    return json;
                }
                if(!tradeuser.getTranspassword().equals(tradepwd.trim())){
                    json.put("code",-1);
                    json.put("msg","交易密码错误");
                    return json;
                }
                otcOrder.setDirection(2);
                Wallets wallet = walletsMapper.selectByUserKeyAndCoinId(String.valueOf(otcOrder.getTradeUser()),otcOrder.getCoin());
                if(wallet.getCoinnum()<otcOrder.getCoinNum().doubleValue()){
                    json.put("code",-1);
                    json.put("msg","余额不足");
                    return json;
                }
                wallet.setCoinnum(wallet.getCoinnum()-otcOrder.getCoinNum().doubleValue());
                wallet.setFrozennum(wallet.getFrozennum() + otcOrder.getCoinNum().doubleValue());
                int i = walletsMapper.updateByPrimaryKeySelective(wallet);
                if(i < 1){
                    json.put("code",-1);
                    json.put("msg","钱包支付失败");
                    return json;
                }
                getInformation.insertwellethistory(String.valueOf(otcOrder.getTradeUser()),otcOrder.getCoin(),otcOrder.getCoinNum().doubleValue(),8);
            }else if(otcAds.getType() == 2){
                otcOrder.setDirection(1);
            }else {
                json.put("code",-1);
                json.put("msg","参数有误！");
                return json;
            }
            otcAds.setNumBalance(BigDecimal.valueOf(otcAds.getNumBalance().doubleValue()-coinnum));
            if(otcAds.getNumBalance().doubleValue() == 0){
                otcAds.setStatus(0);
            }
            int i = otcAdsMapper.updateByPrimaryKeySelective(otcAds);
            if(i>0){
                int j = otcOrderMapper.insertSelective(otcOrder);
                if(j > 0){
                    Map map = new HashMap();
                    if (otcOrder.getDirection() == 1){
                        map.put("userkey",otcOrder.getAdsUser());
                    }else {
                        map.put("userkey",otcOrder.getTradeUser());
                    }
                    JSONObject jsonObject = getInformation.postObject(urlStr+"zuul/getpayaway",map);
                    json.put("code",200);
                    json.put("msg","ok");
                    json.put("data",otcOrder);
                    json.put("payaway",jsonObject);
                    if (otcOrder.getDirection() == 1){
                        SMSUtil.orderLocked(otcOrder.getTradePhone(),otcOrder.getOrderId(),1);
                        SMSUtil.orderLocked(otcOrder.getAdphone(),otcOrder.getOrderId(),2);
                    }else {
                        SMSUtil.orderLocked(otcOrder.getTradePhone(),otcOrder.getOrderId(),2);
                        SMSUtil.orderLocked(otcOrder.getAdphone(),otcOrder.getOrderId(),1);
                    }
                    try {
                        pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","您生成了一笔otc订单"+otcOrder.getOrderId()+"，请及时处理",null);
                        pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","您生成了一笔otc订单"+otcOrder.getOrderId()+"，请及时处理",null);
                    }catch (Exception e){}
                    return json;
                }else{
                    throw new RuntimeException("下单失败");
                }
            }else{
                throw new RuntimeException("下单失败");
            }
        }else {
            json.put("code",-1);
            json.put("msg","该广告不存在");
            return json;
        }
    }

    @Override
    @Transactional
    public JSONObject otcrelease(Integer id,Integer userkey,Integer state, String backContent){
        JSONObject json = new JSONObject();
        OtcOrder otcOrder = otcOrderMapper.selectByPrimaryKey(id);
        if(otcOrder == null){
            json.put("code",-1);
            json.put("msg","该订单不存在！");
            return json;
        }
        if(otcOrder.getState() != 1){
            json.put("code",-1);
            json.put("msg","该订单还不能操作！");
            return json;
        }

        if(otcOrder.getDirection() == 1){
            if (otcOrder.getAdsUser().intValue() != userkey.intValue()){
                json.put("code",-1);
                json.put("msg","您没有权限操作该订单！");
                return json;
            }
        }else {
            if (otcOrder.getTradeUser().intValue() != userkey.intValue()){
                json.put("code",-1);
                json.put("msg","您没有权限操作该订单！");
                return json;
            }
        }
        Wallets wallet1 = null;
        Wallets wallet2 = null;
        OtcAds otcAds = otcAdsMapper.selectByPrimaryKey(otcOrder.getAdsId());
        if(otcOrder.getTradeUser().intValue() != userkey.intValue()){
            if(otcOrder.getDirection() == 1){
                wallet1 = walletsMapper.selectByUserandcoin(String.valueOf(userkey),otcAds.getCoinId());
                wallet2 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getTradeUser()),otcAds.getCoinId());
            }else {
                wallet1 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getTradeUser()),otcAds.getCoinId());
                wallet2 = walletsMapper.selectByUserandcoin(String.valueOf(userkey),otcAds.getCoinId());
            }
        }else {
            if(otcOrder.getDirection() == 1){
                wallet1 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getAdsUser()),otcAds.getCoinId());
                wallet2 = walletsMapper.selectByUserandcoin(String.valueOf(userkey),otcAds.getCoinId());
            }else {
                wallet1 = walletsMapper.selectByUserandcoin(String.valueOf(userkey),otcAds.getCoinId());
                wallet2 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getAdsUser()),otcAds.getCoinId());
            }
        }
        if(state > 0){
            otcOrder.setState(2);
        }else {
            otcOrder.setState(-2);
            otcOrder.setReject(backContent);
            int i = otcOrderMapper.updateByPrimaryKeySelective(otcOrder);
            if(i>0){
                if (otcOrder.getDirection() == 1){
                    try {
                        pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","你有一笔交易"+otcOrder.getOrderId()+"被对方驳回，请注意查看",null);
                        pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","驳回成功",null);
                    }catch (Exception e){}
                    SMSUtil.orderOverrule(otcOrder.getTradePhone(),otcOrder.getOrderId(),1);
                    SMSUtil.orderOverrule(otcOrder.getAdphone(),otcOrder.getOrderId(),2);
                }else {
                    try {
                        pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","你有一笔交易"+otcOrder.getOrderId()+"被对方驳回，请注意查看",null);
                        pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","驳回成功",null);
                    }catch (Exception e){}
                    SMSUtil.orderOverrule(otcOrder.getTradePhone(),otcOrder.getOrderId(),2);
                    SMSUtil.orderOverrule(otcOrder.getAdphone(),otcOrder.getOrderId(),1);
                }
                json.put("code",200);
                json.put("msg","ok");
                return json;
            }else {
                throw new RuntimeException("驳回失败");
            }
        }
        wallet1.setFrozennum(wallet1.getFrozennum().doubleValue()-otcOrder.getCoinNum().doubleValue());
        wallet2.setCoinnum(wallet2.getCoinnum().doubleValue()+otcOrder.getCoinNum().doubleValue());
        int w1 = walletsMapper.updateByPrimaryKeySelective(wallet1);
        int w2 = 0;
        if(w1 > 0){
            w2 = walletsMapper.updateByPrimaryKeySelective(wallet2);
        }else {
            throw new RuntimeException("钱包操作失败");
        }
        if (w2 > 0){
            getInformation.insertwellethistory(wallet2.getUserkey(),wallet2.getCoinid(),otcOrder.getCoinNum().doubleValue(),7);
            int i = otcOrderMapper.updateByPrimaryKeySelective(otcOrder);
            if(i>0){
                SMSUtil.orderComplete(otcOrder.getTradePhone(),otcOrder.getOrderId());
                SMSUtil.orderComplete(otcOrder.getAdphone(),otcOrder.getOrderId());
                json.put("code",200);
                json.put("msg","ok");
                try {
                    pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"已完成",null);
                    pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"已完成",null);
                }catch (Exception e){}
                return json;
            }else {
                throw new RuntimeException("下单失败");
            }
        }else {
            throw new RuntimeException("下单失败");
        }
    }

    @Override
    public List<OtcOrder> selectByuser(Integer trade_user, Integer state, Integer begin, Integer pagesize) {
        return otcOrderMapper.selectByuser(trade_user,state,begin,pagesize);
    }

}

