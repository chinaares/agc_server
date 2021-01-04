package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.service.InformationService;
import com.lanjing.wallet.service.ParametersService;
import com.lanjing.wallet.model.Information;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class InformationController {

    @Resource(name = "InformationService")
    private InformationService informationService;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @RequestMapping("/app/getinformation")
    public String getInformation(@RequestBody Map<String,String> map){
        JSONObject json = new JSONObject();
        String type = map.get("type");
        String page = map.get("page");
        String size = map.get("size");
        if(type == null || "".equals(type)){
            json.put("msg","参数缺失！");
            json.put("code",300);
            return json.toJSONString();
        }
        Integer page1 = null;
        Integer size1 = null;
        try {
            if(page != null || !"".equals(page)){
                page1 = Integer.parseInt(page);
            }
            if(size != null || !"".equals(size)){
                size1 = Integer.parseInt(size);
            }
            Information information = new Information();
            information.setType(Integer.parseInt(type));
            List<Information> list = informationService.selectBy(information,page1,size1);
            List<JSONObject> jsonlist = new ArrayList<>();
            List<JSONObject> jsonlist1 = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Information information1 : list){
                if("0".equals(information1.getEdition())){
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(information1));
                    jsonObject.put("createtime",format.format(information1.getCreatetime()));
                    jsonObject.put("updatetime",format.format(information1.getUpdatetime()));
                    jsonlist.add(jsonObject);
                }else{
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(information1));
                    jsonObject.put("createtime",format.format(information1.getCreatetime()));
                    jsonObject.put("updatetime",format.format(information1.getUpdatetime()));
                    jsonlist1.add(jsonObject);
                }
            }
            List<String> bannerlist = new ArrayList<>();
            List<String> bannerlist1 = new ArrayList<>();
            bannerlist.add(parametersService.selectByKey("banner1").getKeyvalue());
            bannerlist.add(parametersService.selectByKey("banner2").getKeyvalue());
            bannerlist.add(parametersService.selectByKey("banner3").getKeyvalue());
            bannerlist1.add(parametersService.selectByKey("banner1EN").getKeyvalue());
            bannerlist1.add(parametersService.selectByKey("banner2EN").getKeyvalue());
            bannerlist1.add(parametersService.selectByKey("banner3EN").getKeyvalue());
            json.put("bannerCH",bannerlist);
            json.put("bannerEH",bannerlist1);
            json.put("dataEH",jsonlist);
            json.put("dataCH",jsonlist1);
            json.put("msg","ok");
            json.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/getinformationdetail")
    public String getInformationDetail(@RequestBody Map<String,String> map){
        JSONObject json = new JSONObject();
        String Id = map.get("Id");
        if(Id == null || "".equals(Id)){
            json.put("msg","参数缺失！");
            json.put("code",300);
            return json.toJSONString();
        }
        try {
            int fid = Integer.parseInt(Id);
            Information information = informationService.selectByPrimaryKey(fid);
            if(information == null){
                json.put("msg","not find!");
                json.put("code",204);
            }else{
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(information));
                jsonObject.put("createtime",format.format(information.getCreatetime()));
                jsonObject.put("updatetime",format.format(information.getUpdatetime()));
                json.put("data",jsonObject);
                json.put("msg","ok");
                json.put("code",200);
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }

}
