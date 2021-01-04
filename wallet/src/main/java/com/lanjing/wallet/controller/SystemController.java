package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.ParametersMapper;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SystemController {

    @Autowired
    private ParametersMapper parametersMapper;

    @Autowired
    private RedisDao redisDao;

    @RequestMapping("/sys/Islicai")
    public String lcopen(){
        Map map = new HashMap();
        Integer Isopen = null;
        try {
            String s = redisDao.getValue("lcopen");
            if(s != null ){
                Isopen = Integer.valueOf(s);
            }else {
                Isopen = Integer.parseInt(parametersMapper.selectByKey("Islc").getKeyvalue());
                redisDao.setKey("lcopen",Isopen.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("Isopen",Isopen);
        return JSONObject.toJSONString(new ResultDTO(200,"ok",map));
    }

}
