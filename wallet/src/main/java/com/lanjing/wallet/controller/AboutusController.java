package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.model.ParametersWithBLOBs;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.service.ParametersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AboutusController {

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    //关于我们
    @RequestMapping("/app/aboutUs")
    public String aboutUs() {
        Map map = new HashMap();
        map.put("qq", parametersService.selectByKey("qq").getKeyvalue());
        map.put("phone", parametersService.selectByKey("phone").getKeyvalue());
        map.put("email", parametersService.selectByKey("email").getKeyvalue());
        ParametersWithBLOBs parametersWithBLOBs = parametersService.selectByKey("customer_service");
        if (parametersWithBLOBs == null) {
            map.put("customer", "");
        } else {
            map.put("customer", parametersWithBLOBs.getKeyvalue());
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }
}
