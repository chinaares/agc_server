package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.service.AddressService;
import com.lanjing.wallet.service.UsersService;
import com.lanjing.wallet.model.addree_user;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class AddressController extends BaseContrller {

    @Resource(name = "AddressService")
    private AddressService addressService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Autowired
    private RedisDao redisDao;


    @RequestMapping("/app/sethelp")
    public String setHelps(@RequestBody Map<String,String> map){
        String userkey = getUserKey();
        String help = map.get("help");
        JSONObject json = new JSONObject();
        try{
            addree_user address = addressService.updateByUserIdSelective(userkey,help);
            if(address != null){
                json.put("msg","ok");
                json.put("help",help);
                json.put("keys",address.getKeyes());
                json.put("code",200);
            }else{
                json.put("msg","no");
                json.put("code",202);
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/gethelpword")
    public String gethelpword(){
        try {
            String helps1 = redisDao.getValue("helps"+getUserKey());
            if(helps1 != null){
                JSONArray set1 = JSON.parseArray(helps1);
                StringBuilder strstringList = new StringBuilder();
                for (Object str1 : set1){
                    strstringList.append(str1+" ");
                }
                Map map1 = new HashMap();
                map1.put("list",set1);
                map1.put("strlist",strstringList.toString());
                return JSONObject.toJSONString(new ResultDTO(200,"success",map1));
            }
            List<String> helps = usersService.selectHelpword();
            Set<String> set = new HashSet<>();
            Random random = new Random();
            boolean flag = true;
            while (flag){
                set.add(helps.get(random.nextInt(helps.size())));
                if(set.size() == 12){
                    flag = false;
                }
            }
            StringBuilder strstringList = new StringBuilder();
            for (String str : set){
                strstringList.append(str+" ");
            }
            Map map1 = new HashMap();
            map1.put("list",set);
            map1.put("strlist",strstringList.toString());
            redisDao.setKey1("helps"+getUserKey(),JSONObject.toJSONString(set));
            return JSONObject.toJSONString(new ResultDTO(200,"success",map1));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new ResultDTO(201,"error"));
    }

    @RequestMapping("/app/checkhelps")
    public String checkhelps(@RequestBody Map<String,String> map){
        String helps = map.get("helps");
        addree_user address = addressService.createWellet(getUserKey(),helps);
        if(address != null){
            Map map1 = new HashMap();
            map1.put("Keys",address.getKeyes());
            return JSONObject.toJSONString(new ResultDTO(200,"success",map1));
        }else{
            return JSONObject.toJSONString(new ResultDTO(201,"error"));
        }
    }

    @RequestMapping("/app/importwellet")
    public String ImportWellet(@RequestBody Map<String,String> map){
        String help = map.get("helps");
        addree_user addree = addressService.importwellet(getUserKey(),help);
        if(addree!=null){
            Map map1 = new HashMap();
            map1.put("Keys",addree.getAdress());
            return JSONObject.toJSONString(new ResultDTO(200,"success",map1));
        }
        return JSONObject.toJSONString(new ResultDTO(201,"error"));
    }

    @RequestMapping("/app/getWelletAdress")
    public String getWelletAdress(){
        String userkey = getUserKey();
        addree_user addree = addressService.selectByUserKey(getUserKey());
        Map map1 = new HashMap();
        if(addree!=null){
            map1.put("Adress",addree.getAdress());
            return JSONObject.toJSONString(new ResultDTO(200,"success",map1));
        }
        map1.put("Adress","");
        return JSONObject.toJSONString(new ResultDTO(200,"error",map1));
    }

    @RequestMapping("/app/gethelps")
    public String gethelps(@RequestBody Map<String,String> map){
        String tradepwd = map.get("password");
        addree_user addree = addressService.getaddress(getUserKey(),tradepwd);
        if(addree != null){
            Map map1 = new HashMap();
            map1.put("helps",addree.getHelp().replace(","," "));
            return JSONObject.toJSONString(new ResultDTO(200,"success",map1));
        }
        return JSONObject.toJSONString(new ResultDTO(201,"交易密码错误"));
    }

    @RequestMapping("/app/getKeys")
    public String getKeys(@RequestBody Map<String,String> map){
        String tradepwd = map.get("password");
        addree_user addree = addressService.getaddress(getUserKey(),tradepwd);
        if(addree != null){
            Map map1 = new HashMap();
            map1.put("Keys",addree.getKeyes());
            return JSONObject.toJSONString(new ResultDTO(200,"success",map1));
        }
        return JSONObject.toJSONString(new ResultDTO(201,"交易密码错误"));
    }

    @RequestMapping("/app/checkhelp")
    public String checkhelp(@RequestBody Map<String,String> map){
        String help = map.get("helps");
        if(help.endsWith(" ")){
            help = help.substring(0,help.length()-1);
        }
        addree_user address = addressService.selectByUserKey(getUserKey());
        if(address != null){
            if(address.getHelp().equals(help)){
                return JSONObject.toJSONString(new ResultDTO(200,"success"));
            }
        }
        return JSONObject.toJSONString(new ResultDTO(201,"error"));
    }

}
