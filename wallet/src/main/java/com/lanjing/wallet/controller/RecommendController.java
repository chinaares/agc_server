package com.lanjing.wallet.controller;


import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.ChangelogMapper;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.model.Spread;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecommendController extends BaseContrller {

    @Resource(name = "RecommendService")
    private RecommendService recommendService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource(name = "IncomeService")
    private IncomeService incomeService;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @Resource(name = "SpreadService")
    private SpreadService spreadService;

    @Autowired
    private ChangelogMapper changelogMapper;

    @RequestMapping("/app/getRecommend")
    public String getRecommend(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String key = getUserKey();
        Map map1 = new HashMap<>();
        int begin = 0;
        int end = 5;
        String page = map.get("page");
        String size = map.get("size");
        if ((!"".equals(page) && page != null) || (!"".equals(size) && size != null)) {
            end = Integer.parseInt(size);
            begin = Integer.parseInt(size) * (Integer.parseInt(page) - 1);
        }
        map1.put("userkey2", key);
        map1.put("end", end);
        map1.put("begin", begin);
        List<Recommend> list = recommendService.selectByUserKey2(map1);
        List<String> fids = new ArrayList<>();
        for (Recommend recommend : list) {
            fids.add(recommend.getUserkey1());
        }

        List<Users> users = usersService.selectByPrimaryKeys(fids);
        List<Map<String, String>> userlist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Users u : users) {
            Map<String, String> map2 = new HashMap<>();
            map2.put("fId", u.getFid() == null ? "" : u.getFid() + "");
            map2.put("phonenum", u.getPhonenum() == null ? "" : u.getPhonenum());
            map2.put("nickName", u.getNickname() == null ? "" : u.getNickname());
            if (u.getCreatetime() == null) {
                map2.put("time", "");
            } else {
                map2.put("time", format.format(u.getCreatetime()));
            }
            userlist.add(map2);
        }
        json.put("data", userlist);
        json.put("msg", "ok");
        json.put("code", 200);
        return json.toJSONString();
    }

    @RequestMapping("/app/setrecommend")
    public String setRecommend(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String Invitacode = map.get("Invitacode");
        String key = getUserKey();
        Recommend recommend = recommendService.selectByUserKey1(key);
        recommend.setUserkey2(Invitacode);
        try {
            if (recommendService.updateByPrimaryKeySelective(recommend) > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "邀请人不存在");
                json.put("code", 202);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }
}
