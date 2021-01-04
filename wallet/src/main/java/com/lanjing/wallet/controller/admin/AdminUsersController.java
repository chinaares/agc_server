package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.Result;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.PayawayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(value = "*")
public class AdminUsersController extends BaseContrller {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private addree_userMapper addreeMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Resource(name = "PayawayService")
    private PayawayService payawayService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ParametersMapper parametersMapper;

    //获取用户列表
    @RequestMapping("/admin/selectByUser")
    @CrossOrigin(value = "*")
    public String selectByUser(@RequestBody JSONObject map) {
        JSONObject json = new JSONObject();
        int page = map.getIntValue("page");
        int pagesize = map.getIntValue("pagesize");
        String userName = map.getString("userName");

        //时间的区间
        String start = map.getString("start");
        if (start == null || start.equals("")) {
            start = "1970-01-01 00:00:00";
        }
        String end = map.getString("end");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (end == null || end.equals("")) {
            end = sdf.format(new Date());
        }

        System.out.println("得到的数据是：" + map.toJSONString());

        if (userName == null) {
            userName = "";
        }
        int begin = (page - 1) * pagesize;
        List<Map> list = usersMapper.selectByNameorId2(userName, begin, pagesize, start, end);

        int count = usersMapper.selectCount2(userName, start, end);


        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        int normal = usersMapper.selectBystateCount();
        int allcount = usersMapper.selectALLCount();
        int todaynum = usersMapper.selectBytodayCount();

        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", list);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", totalpage);
        json.put("allcount", allcount);
        json.put("normal", normal);
        json.put("todaynum", todaynum);

        return json.toJSONString();
    }

    //用户注销
    @RequestMapping("/admin/delUser")
    @CrossOrigin(value = "*")
    public Object delUser(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("参数错误");
        }

        Users user = usersMapper.selectByPrimaryKey(id);
        if (user == null) {
            return Result.error("用户不存在");
        }

        user.setState(-1);
        user.setUsername(user.getUsername() + "(已注销)");
        user.setNickname(user.getNickname() + "(已注销)");
        user.setPhonenum(user.getPhonenum() + "(已注销)");

        int result = usersMapper.updateByPrimaryKeySelective(user);

        if (result < 1) {
            return Result.error("删除失败");
        }

        return Result.success();
    }

    //修改用户推荐人
    @RequestMapping("/admin/uptrecommend")
    @CrossOrigin(value = "*")
    public String uptrecommend(@RequestBody JSONObject map) {
        int fId = map.getInteger("fId");
        Users user = usersMapper.selectByPhone(map.getString("recommendphone"));
        if (user != null) {
            Recommend recommend = recommendMapper.selectByUserKey1(String.valueOf(fId));
            if (recommend != null) {
                recommend.setUserkey2(user.getKeyes());
                int i = recommendMapper.updateByPrimaryKeySelective(recommend);
                if (i > 0) {
                    return JSONObject.toJSONString(new ResultDTO(200, "success"));
                } else {
                    return JSONObject.toJSONString(new ResultDTO(-1, "修改失败"));
                }
            } else {
                return JSONObject.toJSONString(new ResultDTO(-1, "该手机号没有注册"));
            }
        } else {
            return JSONObject.toJSONString(new ResultDTO(-1, "该手机号没有注册"));
        }
    }

    //获取用户详情
    @RequestMapping("/admin/queryUserById")
    @CrossOrigin(value = "*")
    public String queryUserById(@RequestBody JSONObject map) {
        int fId = map.getIntValue("fId");
        Users user = usersMapper.selectByPrimaryKey(fId);
        addree_user address = addreeMapper.selectByUserKey(user.getKeyes());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("fId", user.getFid());
        map1.put("phone", user.getPhonenum());
        map1.put("Nickname", user.getNickname());
        map1.put("Invitacode", user.getFid());
        map1.put("state", user.getState());
        map1.put("register", user.getCreatetime());
        String recommendphone = "";
        Recommend recommend = recommendMapper.selectByUserKey1(String.valueOf(fId));
        if (recommend != null) {
            Users recommenduser = usersMapper.selectByUserKey(recommend.getUserkey2());
            if (recommenduser != null) {
                recommendphone = recommenduser.getPhonenum();
            }
        }
        map1.put("recommendphone", recommendphone);
        if (address != null) {
            map1.put("ETHaddress", address.getAdress());
            map1.put("AGCaddress", address.getAdress());
        } else {
            map1.put("AGCaddress", "");
            map1.put("ETHaddress", "");
        }
        Wellets USDT = welletsMapper.selectByUserandcoin(String.valueOf(fId), 6);
        if (USDT != null) {
            map1.put("USDTaddress", USDT.getAddress());
        } else {
            map1.put("USDTaddress", USDT.getAddress());
        }
        return JSONObject.toJSONString(new ResultDTO(200, "success", map1));
    }

    //修改用户昵称
    @RequestMapping("/admin/uptUserNick")
    @CrossOrigin(value = "*")
    public String uptUserNick(@RequestBody JSONObject map) {
        int fId = map.getIntValue("fId");
        String nickNeme = map.getString("nickname");
        Users user = usersMapper.selectByPrimaryKey(fId);
        if (user == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该用户不存在"));
        }
        user.setNickname(nickNeme);
        int i = usersMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            return JSONObject.toJSONString(new ResultDTO(200, "success"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "修改失败"));
    }

    //修改用户状态
    @RequestMapping("/admin/uptUserstate")
    @CrossOrigin(value = "*")
    public String uptUserstate(@RequestBody JSONObject map) {
        int fId = map.getIntValue("fId");
        int state = map.getIntValue("state");
        if (state != 0 && state != 1) {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数错误！"));
        }
        Users user = usersMapper.selectByPrimaryKey(fId);
        if (user == null) {
            return JSONObject.toJSONString(new ResultDTO(-1, "该用户不存在"));
        }
        user.setState(state);
        int i = usersMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            return JSONObject.toJSONString(new ResultDTO(200, "success"));
        }
        return JSONObject.toJSONString(new ResultDTO(200, "失败"));
    }

    //获取用户钱包详情
    @RequestMapping("/admin/queryUserWellet")
    @CrossOrigin(value = "*")
    public String queryUserWellet(@RequestBody JSONObject map) {
        List<Wellets> wellets = welletsMapper.selectByUserKey(map.getString("fId"));
        Wellets eth = wellets.get(0);
        Wellets agc = wellets.get(1);
        Wellets integral = wellets.get(2);
        Wellets usdt = wellets.get(3);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ETHcoinNum", eth.getCoinnum());
        map1.put("ETHaddress", eth.getAddress());
        map1.put("AGCcoinNum", agc.getCoinnum());
        map1.put("AGCaddress", agc.getAddress());
        map1.put("USDTcoinNum", usdt.getCoinnum());
        map1.put("USDTaddress", usdt.getAddress());
        map1.put("integralNum", integral.getCoinnum());
        return JSONObject.toJSONString(new ResultDTO(200, "success", map1));
    }

    @RequestMapping("/admin/uptUserWellet")
    @CrossOrigin(value = "*")
    public String uptUserWellet(@RequestBody JSONObject map) {
        String ationpassword = map.getString("ationpassword");
        if (ationpassword == null || "".equals(ationpassword)) {
            return JSONObject.toJSONString(new ResultDTO(-1, "请输入操作密码"));
        }

        Admin admin = adminMapper.selectByPrimaryKey(getId());
        if (admin == null) {
            return JSONObject.toJSONString(new ResultDTO(600, "请重新登录"));
        }
        if (!ationpassword.equals(admin.getAtionpassword())) {
            return JSONObject.toJSONString(new ResultDTO(-1, "操作密码错误"));
        }

        List<Wellets> wellets = welletsMapper.selectByUserKey(map.getString("fId"));
        Double ETHcoinNum = map.getDouble("ETHcoinNum");
        Double AGCaddress = map.getDouble("AGCcoinNum");
        Double USDTcoinNum = map.getDouble("USDTcoinNum");
        Double integralNum = map.getDouble("integralNum");
        Wellets eth = wellets.get(0);
        Wellets agc = wellets.get(1);
        Wellets integral = wellets.get(2);
        Wellets usdt = wellets.get(3);

        if (eth != null && ETHcoinNum >= 0) {
            eth.setCoinnum(BigDecimal.valueOf(ETHcoinNum));
            welletsMapper.updateByPrimaryKeySelective(eth);
        }
        if (agc != null && AGCaddress >= 0) {
        	agc.setCoinnum(BigDecimal.valueOf(AGCaddress));
            welletsMapper.updateByPrimaryKeySelective(agc);
        }

        if (usdt != null && USDTcoinNum >= 0) {
        	usdt.setCoinnum(BigDecimal.valueOf(USDTcoinNum));
            welletsMapper.updateByPrimaryKeySelective(usdt);
        }
        if (integral != null && integralNum >= 0) {
            integral.setCoinnum(BigDecimal.valueOf(integralNum));
            welletsMapper.updateByPrimaryKeySelective(integral);
        }
        return JSONObject.toJSONString(new ResultDTO(200, "success"));
    }

    //获取用户邀请列表
    @RequestMapping("/admin/queryInvitacode")
    @CrossOrigin(value = "*")
    public String queryInvitacode(@RequestBody JSONObject map) {
        Map map1 = new HashMap<>();
        int begin = (map.getIntValue("page") - 1) * map.getIntValue("pagesize");
        int end = map.getIntValue("pagesize");
        map1.put("userkey2", map.get("fId"));
        map1.put("begin", begin);
        map1.put("end", end);
        List<Recommend> list = recommendMapper.selectByUserKey2(map1);
        int count = recommendMapper.selectByUserKey2Count(map.getString("fId"));
        int totalpage = (count % end == 0 ? count / end : count / end + 1);
        List<Map> mapList = new ArrayList<>();
        for (Recommend recommend : list) {
            Users user = usersMapper.selectByPrimaryKey(Integer.valueOf(recommend.getUserkey1()));
            if (user != null) {
                Map map2 = new HashMap();
                map2.put("nickname", user.getNickname());
                map2.put("phone", user.getPhonenum());
                map2.put("registertime", user.getCreatetime());
                mapList.add(map2);
            }
        }
        Map data = new HashMap();
        data.put("data", mapList);
        data.put("totalpage", totalpage);
        data.put("page", map.getIntValue("page"));
        data.put("pagesize", end);
        return JSONObject.toJSONString(new ResultDTO(200, "success", data));
    }


    @RequestMapping("/admin/userpaylist")
    public String Payaways(@RequestBody JSONObject param) {
        Map map = new HashMap();
        List<Payaway> list1 = payawayService.selectByUser(param.getString("fId"), 1);
        if (list1 != null) {
            if (list1.size() > 0) {
                Payaway ZFB = list1.get(0);
                map.put("ZFBfId", ZFB.getFid());
                map.put("ZFBaccounts", ZFB.getAccounts());
                map.put("ZFBusername", ZFB.getUsername());
                map.put("ZFBqrcode", ZFB.getQrCode());
            } else {
                map.put("ZFBfId", "0");
                map.put("ZFBaccounts", "");
                map.put("ZFBusername", "");
                map.put("ZFBqrcode", "");
            }
        }
        List<Payaway> list2 = payawayService.selectByUser(param.getString("fId"), 2);
        if (list2 != null) {
            if (list2.size() > 0) {
                Payaway WX = list2.get(0);
                map.put("WXfId", WX.getFid());
                map.put("WXaccounts", WX.getAccounts());
                map.put("WXusername", WX.getUsername());
                map.put("WXqrcode", WX.getQrCode());
            } else {
                map.put("WXfId", "0");
                map.put("WXaccounts", "");
                map.put("WXusername", "");
                map.put("WXqrcode", "");
            }
        }
        List<Payaway> list3 = payawayService.selectByUser(param.getString("fId"), 3);
        if (list3 != null) {
            if (list3.size() > 0) {
                Payaway bank = list3.get(0);
                map.put("bankfId", bank.getFid());
                map.put("bankaccounts", bank.getAccounts());
                map.put("bankusername", bank.getUsername());
                map.put("Bankaccount", bank.getBankaccount());
                map.put("AccountOpeningBranch", bank.getAccountOpeningBranch());
            } else {
                map.put("bankfId", "0");
                map.put("bankaccounts", "");
                map.put("bankusername", "");
                map.put("Bankaccount", "");
                map.put("AccountOpeningBranch", "");
            }
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }

    //保存和修改客服微信号
    @RequestMapping("/admin/addOrUpdateCustomer")
    @CrossOrigin(value = "*")
    public String addOrUpdateCustomer(@RequestBody JSONObject map) {
        String customer = map.getString("customer");
        ParametersWithBLOBs parametersWithBLOBs = parametersMapper.selectByKey("customer_service");
        if (parametersWithBLOBs == null) {
            parametersWithBLOBs = new ParametersWithBLOBs();
            parametersWithBLOBs.setKeyvalue(customer);
            parametersWithBLOBs.setRemark("");
            parametersWithBLOBs.setType(0);
            parametersWithBLOBs.setKeyname("customer_service");
            parametersMapper.insertSelective(parametersWithBLOBs);
        } else {
            parametersWithBLOBs.setKeyvalue(customer);
            parametersMapper.updateByPrimaryKeySelective(parametersWithBLOBs);
        }
        return JSONObject.toJSONString(new ResultDTO(200, "success"));
    }

    //获取客服微信号
    @RequestMapping("/admin/queryCustomer")
    @CrossOrigin(value = "*")
    public String queryCustomer() {
        ParametersWithBLOBs parametersWithBLOBs = parametersMapper.selectByKey("customer_service");
        return JSONObject.toJSONString(new ResultDTO(200, "success", parametersWithBLOBs.getKeyvalue()));
    }
}
