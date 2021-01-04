package com.lanjing.otc.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PushUtil;
import com.lanjing.otc.controller.BaseController;
import com.lanjing.otc.dao.OtcAdsMapper;
import com.lanjing.otc.dao.OtcOrderMapper;
import com.lanjing.otc.dao.WalletsMapper;
import com.lanjing.otc.model.OtcAds;
import com.lanjing.otc.model.OtcOrder;
import com.lanjing.otc.model.Wallets;
import com.lanjing.otc.util.GetInformation;
import com.lanjing.otc.util.ResultDTO;
import com.lanjing.otc.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminOtcOrderController extends BaseController {

    @Autowired
    private PushUtil pushUtil;

    @Autowired
    private OtcOrderMapper otcOrderMapper;

    @Autowired
    private WalletsMapper walletsMapper;

    @Autowired
    private OtcAdsMapper otcAdsMapper;

    @Autowired
    private GetInformation getInformation;

    @RequestMapping("/admin/otcOrder/list")
    public String OtcOrderlist(@RequestBody JSONObject param){
        Integer state = param.getInteger("state");
        Integer page = param.getInteger("page");
        Integer size = param.getInteger("size");
        if (page == null || size == null){
            return JSONObject.toJSONString(new ResultDTO(-1,"参数缺失"));
        }
        List<OtcOrder> otcOrderlist = otcOrderMapper.selectByuser1(null,state,(page-1)*size,size);
        int count = otcOrderMapper.selectByuserCount(null,state);
        int totalpage = (count % size == 0 ? count / size : count / size + 1);
        ResultDTO resultDTO = new ResultDTO(200,"ok",otcOrderlist);
        JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(resultDTO));
        jsonObject.put("page",page);
        jsonObject.put("size",size);
        jsonObject.put("totalpage",totalpage);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/admin/otcOrder/examine")
    public String Otcappealexamine(@RequestBody JSONObject param){
        Integer Id = param.getInteger("id");
        Integer state = param.getInteger("state");
        OtcOrder otcOrder = otcOrderMapper.selectByPrimaryKey(Id);
        if(state == 1){
            otcOrder.setAppealStatus(2);
            otcOrder.setState(2);
            Wallets wallet1 = null;
            Wallets wallet2 = null;
            OtcAds otcAds = otcAdsMapper.selectByPrimaryKey(otcOrder.getAdsId());
            if(otcOrder.getDirection() == 1){
                wallet1 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getAdsUser()),otcAds.getCoinId());
                wallet2 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getTradeUser()),otcAds.getCoinId());
            }else {
                wallet1 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getTradeUser()),otcAds.getCoinId());
                wallet2 = walletsMapper.selectByUserandcoin(String.valueOf(otcOrder.getAdsUser()),otcAds.getCoinId());
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
                    try {
                        pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"已完成",null);
                        pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"已完成",null);
                    }catch (Exception e){}
                    return JSONObject.toJSONString(new ResultDTO(200,"ok"));
                }else {
                    throw new RuntimeException("状态修改失败");
                }
            }else {
                throw new RuntimeException("钱包操作失败");
            }
        }else if (state == 0){
            otcOrder.setAppealStatus(-1);
            otcOrder.setState(-3);
            int i = otcOrderMapper.updateByPrimaryKeySelective(otcOrder);
            if(i>0){
                try {
                    pushUtil.aliasPush(String.valueOf(otcOrder.getAdsUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"申诉完成，请注意查看结果",null);
                    pushUtil.aliasPush(String.valueOf(otcOrder.getTradeUser()),"otc订单通知","订单"+otcOrder.getOrderId()+"申诉完成，请注意查看结果",null);
                }catch (Exception e){}
                return JSONObject.toJSONString(new ResultDTO(200,"ok"));
            }else {
                throw new RuntimeException("状态修改失败");
            }
        }else {
            return JSONObject.toJSONString(new ResultDTO(-1,"参数无效"));
        }
    }

}
