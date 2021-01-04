package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.model.Payaway;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.service.ParametersService;
import com.lanjing.wallet.service.PayawayService;
import com.lanjing.wallet.util.BASE64imgUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PayawayController extends BaseContrller {

    @Resource(name = "PayawayService")
    private PayawayService payawayService;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @RequestMapping("/app/paylist")
    public String getPayaway(){
        Map map = new HashMap();
        List<Payaway> list1 = payawayService.selectByUser(getUserKey(),1);
        if(list1 != null){
            if(list1.size() >0){
                Payaway ZFB = list1.get(0);
                map.put("ZFBfId",ZFB.getFid());
                map.put("ZFBaccounts",ZFB.getAccounts());
                map.put("ZFBusername",ZFB.getUsername());
                map.put("ZFBqrcode",ZFB.getQrCode());
            }else {
                map.put("ZFBfId","0");
                map.put("ZFBaccounts","");
                map.put("ZFBusername","");
                map.put("ZFBqrcode","");
            }
        }
        List<Payaway> list2 = payawayService.selectByUser(getUserKey(),2);
        if(list2 != null){
            if(list2.size() >0){
                Payaway WX = list2.get(0);
                map.put("WXfId",WX.getFid());
                map.put("WXaccounts",WX.getAccounts());
                map.put("WXusername",WX.getUsername());
                map.put("WXqrcode",WX.getQrCode());
            }else {
                map.put("WXfId","0");
                map.put("WXaccounts","");
                map.put("WXusername","");
                map.put("WXqrcode","");
            }
        }
        List<Payaway> list3 = payawayService.selectByUser(getUserKey(),3);
        if(list3 != null){
            if(list3.size() >0){
                Payaway bank = list3.get(0);
                map.put("bankfId",bank.getFid());
                map.put("bankaccounts",bank.getAccounts());
                map.put("bankusername",bank.getUsername());
                map.put("Bankaccount",bank.getBankaccount());
                map.put("AccountOpeningBranch",bank.getAccountOpeningBranch());
            }else {
                map.put("bankfId","0");
                map.put("bankaccounts","");
                map.put("bankusername","");
                map.put("Bankaccount","");
                map.put("AccountOpeningBranch","");
            }
        }
        return JSONObject.toJSONString(new ResultDTO(200,"ok",map));
    }

    @RequestMapping("/zuul/getpayaway")
    public JSONObject getPayaway1(@RequestBody JSONObject jsonObject){
        Map map = new HashMap();
        List<Payaway> list1 = payawayService.selectByUser(jsonObject.getString("userkey"),1);
        if(list1 != null){
            if(list1.size() >0){
                Payaway ZFB = list1.get(0);
                map.put("ZFBfId",ZFB.getFid());
                map.put("ZFBaccounts",ZFB.getAccounts());
                map.put("ZFBusername",ZFB.getUsername());
                map.put("ZFBqrcode",ZFB.getQrCode());
            }else {
                map.put("ZFBfId","0");
                map.put("ZFBaccounts","");
                map.put("ZFBusername","");
                map.put("ZFBqrcode","");
            }
        }
        List<Payaway> list2 = payawayService.selectByUser(jsonObject.getString("userkey"),2);
        if(list2 != null){
            if(list2.size() >0){
                Payaway WX = list2.get(0);
                map.put("WXfId",WX.getFid());
                map.put("WXaccounts",WX.getAccounts());
                map.put("WXusername",WX.getUsername());
                map.put("WXqrcode",WX.getQrCode());
            }else {
                map.put("WXfId","0");
                map.put("WXaccounts","");
                map.put("WXusername","");
                map.put("WXqrcode","");
            }
        }
        List<Payaway> list3 = payawayService.selectByUser(jsonObject.getString("userkey"),3);
        if(list3 != null){
            if(list3.size() >0){
                Payaway bank = list3.get(0);
                map.put("bankfId",bank.getFid());
                map.put("bankaccounts",bank.getAccounts());
                map.put("bankusername",bank.getUsername());
                map.put("Bankaccount",bank.getBankaccount());
                map.put("AccountOpeningBranch",bank.getAccountOpeningBranch());
            }else {
                map.put("bankfId","0");
                map.put("bankaccounts","");
                map.put("bankusername","");
                map.put("Bankaccount","");
                map.put("AccountOpeningBranch","");
            }
        }
        return JSON.parseObject(JSONObject.toJSONString(map));
    }

    @RequestMapping("/app/editpay")
    public String addPay(@RequestBody Map<String,String> map){
        String id = map.get("fId");
        if(id == null || "".equals(id)){
            id = "0";
        }
        Integer fId = Integer.valueOf(id);
        Integer type = Integer.valueOf(map.get("type"));
        String accounts = map.get("accounts");
        String userName = map.get("userName");
        String QR_code = map.get("qrcode");
        String bankaccount = map.get("bankaccount");
        String Account_opening_branch = map.get("AccountOpeningBranch");
        if(accounts == null || "".equals(accounts) || userName == null || "".equals(userName)){
            return JSONObject.toJSONString(new ResultDTO(-1,"请检查信息是否完善"));
        }
        if(type == 1 || type == 2){
            if(QR_code == null || "".equals(QR_code)){
                return JSONObject.toJSONString(new ResultDTO(-1,"请检查信息是否完善"));
            }
        }else if(type == 3){
            if(bankaccount == null || "".equals(bankaccount) || Account_opening_branch == null || "".equals(Account_opening_branch)){
                return JSONObject.toJSONString(new ResultDTO(-1,"请检查信息是否完善"));
            }
        }else {
            return JSONObject.toJSONString(new ResultDTO(-1,"类型错误！"));
        }
        List<Payaway> list = payawayService.selectByUser(getUserKey(),type);
        if(fId > 0){
            if (list !=null){
                if(list.size()>0){
                    Payaway payaway = list.get(0);
                    payaway.setUsername(userName);
                    payaway.setAccounts(accounts);
                    if(type == 1 || type == 2){
                        String courseFile = parametersService.selectByKey("imgbath").getKeyvalue();
                        String imgurl = BASE64imgUtil.GenerateImage(QR_code,courseFile);
                        payaway.setQrCode(parametersService.selectByKey("imgurl").getKeyvalue()+imgurl.substring(imgurl.lastIndexOf("/")));
                    }else if (type == 3){
                        payaway.setBankaccount(bankaccount);
                        payaway.setAccountOpeningBranch(Account_opening_branch);
                    }
                    int i = payawayService.updateByPrimaryKeySelective(payaway);
                    if(i>0){
                        return JSONObject.toJSONString(new ResultDTO(200,"ok"));
                    }
                    return JSONObject.toJSONString(new ResultDTO(-1,"failure"));
                }
            }
            return JSONObject.toJSONString(new ResultDTO(-1,"请添加支付方式"));
        }else {
            if (list !=null){
                if(list.size()>0){
                    return JSONObject.toJSONString(new ResultDTO(-1,"请勿重复添加收款方式"));
                }
            }
            Payaway payaway = new Payaway();
            payaway.setType(type);
            payaway.setUserid(Integer.valueOf(getUserKey()));
            payaway.setUsername(userName);
            payaway.setAccounts(accounts);
            if(type == 1 || type == 2){
                String courseFile = parametersService.selectByKey("imgbath").getKeyvalue();
                String imgurl = BASE64imgUtil.GenerateImage(QR_code,courseFile);
                payaway.setQrCode(parametersService.selectByKey("imgurl").getKeyvalue()+imgurl.substring(imgurl.lastIndexOf("/")));
            }else if (type == 3){
                payaway.setBankaccount(bankaccount);
                payaway.setAccountOpeningBranch(Account_opening_branch);
            }
            int i = payawayService.insertSelective(payaway);
            if(i>0){
                return JSONObject.toJSONString(new ResultDTO(200,"ok"));
            }
            return JSONObject.toJSONString(new ResultDTO(-1,"failure"));
        }
    }


    @RequestMapping("/app/uploadpicture")
    public String uploadFile(@RequestBody Map<String,String> map) throws IOException {
        JSONObject json = new JSONObject();
        String img = map.get("picture");
        File directory = new File("");// 参数为空
        String courseFile = parametersService.selectByKey("imgbath").getKeyvalue();
        String imgurl = BASE64imgUtil.GenerateImage(img,courseFile);
        json.put("msg","ok");
        json.put("imgurl",parametersService.selectByKey("imgurl").getKeyvalue()+imgurl.substring(imgurl.lastIndexOf("/")));
        json.put("code",200);
        return json.toJSONString();
    }

}
