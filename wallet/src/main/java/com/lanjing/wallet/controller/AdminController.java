package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.ex.CheckEx;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.UsersService;
import com.lanjing.wallet.service.WelletService;
import com.lanjing.wallet.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.lanjing.wallet.config.ConfigConst.PLATFORM_ADDRESS;
import static com.lanjing.wallet.config.ConfigConst.Turn_ADDRESS;
import static com.lanjing.wallet.config.ConfigConst.Turn_PWD;

@RestController
@CrossOrigin(value = "*")
public class AdminController {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private ChangelogMapper changelogMapper;

    @Autowired
    private MatchlistMapper matchlistMapper;

    @Autowired
    private AutolistMapper autolistMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private MappingTradeMapper mappingTradeMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private RegistersMapper registersMapper;

    @Autowired
    private addree_userMapper addree_userMapper;

    @Autowired
    private AddUserMapper addUserMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private TrasferlistMapper trasferlistMapper;

    @Autowired
    private WelletsuptLogMapper welletsuptLogMapper;

    @Autowired
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private MallShopMapper mallShopMapper;

    @Autowired
    private WelletService welletService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    //获取用户列表
    @RequestMapping("/admin/selectByUser1")
    @CrossOrigin(value = "*")
    public String selectByUser(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String userName = map.get("userName");
        if (userName == null) {
            userName = "";
        }
        int begin = (page - 1) * pagesize;
        List<Map> list = usersMapper.selectByNameorId1(userName, begin, pagesize);
        int count = usersMapper.selectCount(userName);
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

    @RequestMapping("/sys/adduserwellet")
    public void addwellet(Integer coinId) {
        Coins coin = coinsMapper.selectByPrimaryKey(7);
        if (coin != null) {
            List<Users> usersList = usersMapper.selectAll();
            for (Users user : usersList) {
                addree_user address = addree_userMapper.selectByUserKey(user.getKeyes());
                Wellets wellet = welletsMapper.selectByUserandcoin(user.getKeyes(), coinId);
                if (wellet == null) {
                    Wellets wellet1 = new Wellets();
                    wellet1.setAddress(address.getAdress());
                    wellet1.setUserkey(user.getKeyes());
                    wellet1.setCoinid(coin.getFid());
                    wellet1.setCointype(coin.getCoinshort());
                    welletsMapper.insertSelective(wellet1);
                }
            }
        }
    }

    //设置用户等级
    @RequestMapping("/admin/setusergrede")
    @CrossOrigin(value = "*")
    public String setusergrede(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int grede = Integer.parseInt(map.get("grede"));
        int fid = Integer.parseInt(map.get("Id"));
        Recommend recommend = recommendMapper.selectByUserKey1(usersMapper.selectByPrimaryKey(fid).getKeyes());
        recommend.setUsergrede(grede);
        int i = recommendMapper.updateByPrimaryKeySelective(recommend);
        if (i > 0) {
            json.put("code", 200);
            json.put("msg", "success");
        } else {
            json.put("code", 300);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }

    //修改用户状态
    @RequestMapping("/admin/uptUserState")
    @CrossOrigin(value = "*")
    public String uptuserstate(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String fId = map.get("Id");
        List<String> list = new ArrayList<>();
        if (!"".equals(fId)) {
            String[] str = fId.split(",");
            for (String s : str) {
                list.add(s);
            }
        }
        int state = Integer.parseInt(map.get("state"));
        int i = usersMapper.updateByfids(list, state);
        if (i > 0) {
            json.put("code", 200);
            json.put("msg", "success");
        } else {
            json.put("code", 300);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }

    //重置用户密码
    @RequestMapping("/admin/Resetpassword")
    public String Resetpassword(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String fId = map.get("Id");
        List<String> list = new ArrayList<>();
        if (!"".equals(fId)) {
            String[] str = fId.split(",");
            for (String s : str) {
                list.add(s);
            }
        }
        int state = Integer.parseInt(map.get("type"));
        int i = 0;
        if (state == 1) {
            i = usersMapper.Resetloginpassword(list);
        } else if (state == 2) {
            i = usersMapper.Resettradepassword(list);
        }
        if (i > 0) {
            json.put("code", 200);
            json.put("msg", "success");
        } else {
            json.put("code", 300);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }

    //重置用户手机号
    @RequestMapping("/admin/Resetphone")
    public String Resetphone(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String fId = map.get("Id");
        List<String> list = new ArrayList<>();
        if (!"".equals(fId)) {
            String[] str = fId.split(",");
            for (String s : str) {
                list.add(s);
            }
        }
        String phone = map.get("phone");
        int i = usersMapper.uptphone(list, phone);
        if (i > 0) {
            json.put("code", 200);
            json.put("msg", "success");
        } else {
            json.put("code", 300);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }


    public int selectBycode(String userkey, int num) {
        List<Recommend> list = recommendMapper.selectByUserKey21(userkey);
        num += list.size();
        for (Recommend recommend : list) {
            num = selectBycode(recommend.getUserkey1(), num);
        }
        return num;
    }

    //获取团队一级列表
    @RequestMapping("/admin/getrecommends")
    @CrossOrigin(value = "*")
    public String getrecommends() {
        JSONObject json = new JSONObject();
        List<Map> list = recommendMapper.selectByLev1(1);
        List<Map> list1 = new ArrayList<>();
        for (Map map : list) {
            String userkey = map.get("userkey_1") + "";
            int count = selectBycode(userkey, 0);
            map.put("count", count);
            list1.add(map);
        }
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", list1);
        return json.toJSONString();
    }

    //根据上级找下级成员
    @RequestMapping("/admin/recommendlist")
    @CrossOrigin(value = "*")
    public String recommendlist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String userkey = map.get("upuser");
        List<Map> list = recommendMapper.selectByUserKey2list1(userkey);
        List<Map> list1 = new ArrayList<>();
        for (Map map1 : list) {
            String userkey1 = map1.get("userkey_1") + "";
            int count1 = selectBycode(userkey1, 0);
            map1.put("count", count1 + "");
            list1.add(map1);
        }
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", list1);
        return json.toJSONString();
    }

    //Usd理财
    @RequestMapping("/admin/getAirdropHistory")
    @CrossOrigin(value = "*")
    public String getAirdropHistory(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String beginTime = map.get("beginTime");
        String endTime = map.get("endTime");
        if ("".equals(beginTime)) {
            beginTime = null;
        }
        if ("".equals(endTime)) {
            endTime = null;
        }
        String userName = map.get("userName");
        if (userName == null) {
            userName = "";
        }
        List<Map> list = changelogMapper.selectAirdropByUserOrId1(userName, (page - 1) * pagesize, pagesize, beginTime, endTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> mapList = new ArrayList<>();
        for (Map map1 : list) {
            map1.put("craetetime", format.format(map1.get("craetetime")));
            mapList.add(map1);
        }
        int count = changelogMapper.selectAirdropCount1(userName, beginTime, endTime);

        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", mapList);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", totalpage);
        json.put("coinnumagosum", 0);
        json.put("coinnum1sum", 0);
        json.put("coinnum2sum", 0);
        return json.toJSONString();
    }

    //理财加权
    @RequestMapping("/admin/getWeightAirdropHistory")
    @CrossOrigin(value = "*")
    public String getWeightAirdropHistory(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String beginTime = map.get("beginTime");
        String endTime = map.get("endTime");
        if ("".equals(beginTime)) {
            beginTime = null;
        }
        if ("".equals(endTime)) {
            endTime = null;
        }
        int type = 0;
        if (map.get("type") != null && !"".equals(map.get("type"))) {
            type = Integer.parseInt(map.get("type"));
        }
        String userName = map.get("userName");
        if (userName == null) {
            userName = "";
        }
        List<Map> list = changelogMapper.selectAirdropByUserOrId2(userName, (page - 1) * pagesize, pagesize, type, beginTime, endTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> mapList = new ArrayList<>();
        for (Map map1 : list) {
            map1.put("craetetime", format.format(map1.get("craetetime")));
            mapList.add(map1);
        }
        int count = changelogMapper.selectAirdropCount2(userName, type, beginTime, endTime);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", mapList);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", count / pagesize);
        json.put("type", type);
        json.put("coinnum2sum1", 0);
        json.put("coinnum2sum2", 0);
        return json.toJSONString();
    }

    //交易释放
    @RequestMapping("/admin/getTradeHistory")
    @CrossOrigin(value = "*")
    public String getTradeHistory(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String beginTime = map.get("beginTime");
        String endTime = map.get("endTime");
        String userName = map.get("userName");
        if (userName == null) {
            userName = "";
        }
        if ("".equals(beginTime)) {
            beginTime = null;
        }
        if ("".equals(endTime)) {
            endTime = null;
        }
        List<Map> list = changelogMapper.selectTradeByUserOrId1(userName, (page - 1) * pagesize, pagesize, beginTime, endTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> mapList = new ArrayList<>();
        for (Map map1 : list) {
            map1.put("craetetime", format.format(map1.get("craetetime")));
            mapList.add(map1);
        }
        int count = changelogMapper.selectTradeCount1(userName, beginTime, endTime);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", mapList);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", count / pagesize);
        json.put("sum1", 0);
        json.put("sum2", 0);
        json.put("sum3", 0);
        return json.toJSONString();
    }

    //交易加权
    @RequestMapping("/admin/getWeightTradeHistory")
    @CrossOrigin(value = "*")
    public String getWeightTradeHistory(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String beginTime = map.get("beginTime");
        String endTime = map.get("endTime");
        if ("".equals(beginTime)) {
            beginTime = null;
        }
        if ("".equals(endTime)) {
            endTime = null;
        }
        int type = 0;
        if (map.get("type") != null && !"".equals(map.get("type"))) {
            type = Integer.parseInt(map.get("type"));
        }
        String userName = map.get("userName");
        if (userName == null) {
            userName = "";
        }
        List<Map> list = changelogMapper.selectTradeByUserOrId2(userName, (page - 1) * pagesize, pagesize, type, beginTime, endTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> mapList = new ArrayList<>();
        for (Map map1 : list) {
            map1.put("craetetime", format.format(map1.get("craetetime")));
            mapList.add(map1);
        }
        int count = changelogMapper.selectTradeCount2(userName, type, beginTime, endTime);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", mapList);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", count / pagesize);
        json.put("type", type);
        json.put("sum", 0);
        return json.toJSONString();
    }

    //匹配列表
    @RequestMapping("/admin/Matchlists")
    @CrossOrigin(value = "*")
    public String Matchlists(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String fId = map.get("Id");
        String createtime = map.get("createtime");
        String beginTime = map.get("beginTime");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        List<Matchlist> list = null;
        int count = 0;
        try {
            if (!"".equals(fId) && fId != null) {
                list = matchlistMapper.selectByTimeOrId(Integer.parseInt(fId), null, null, (page - 1) * pagesize, pagesize);
                count = matchlistMapper.selectByTimeOrIdCount(Integer.parseInt(fId), null, null);
            } else if (!"".equals(beginTime) && beginTime != null) {
                list = matchlistMapper.selectByTimeOrId(0, null, format1.parse(beginTime), (page - 1) * pagesize, pagesize);
                count = matchlistMapper.selectByTimeOrIdCount(0, null, format1.parse(beginTime));
            } else if (!"".equals(createtime) && createtime != null) {
                list = matchlistMapper.selectByTimeOrId(0, format1.parse(createtime), null, (page - 1) * pagesize, pagesize);
                count = matchlistMapper.selectByTimeOrIdCount(0, format1.parse(createtime), null);
            } else {
                list = matchlistMapper.selectByTimeOrId(0, null, null, (page - 1) * pagesize, pagesize);
                count = matchlistMapper.selectByTimeOrIdCount(0, null, null);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<JSONObject> jsonlist = new ArrayList<>();
        for (Matchlist matchlist : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(matchlist));
            jsonObject.put("beginTime", format.format(matchlist.getBeginTime()));
            jsonObject.put("createtime", format.format(matchlist.getCreatetime()));
            jsonlist.add(jsonObject);
        }
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", jsonlist);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", count / pagesize);
        json.put("createtime", createtime);
        json.put("beginTime", beginTime);
        return json.toJSONString();
    }

    //添加匹配列表
    @RequestMapping("/admin/addMatchlist")
    @CrossOrigin(value = "*")
    public String addMatchlist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        double sum = Double.parseDouble(map.get("sum"));
        String begintime = map.get("begintime");
        String type = map.get("type");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Matchlist matchlist = new Matchlist();
        try {
            matchlist.setSum(sum);
            matchlist.setBeginTime(format.parse(begintime));
            matchlist.setCreatetime(new Date());
            matchlist.setType(type);
            matchlist.setRestNum(sum);
            int i = matchlistMapper.insertSelective(matchlist);
            if (i > 0) {
                json.put("code", 200);
                json.put("msg", "success");
            } else {
                json.put("code", 300);
                json.put("msg", "error");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            json.put("code", 400);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }

    //生成数据列表
    @RequestMapping("/admin/getAutolist")
    @CrossOrigin(value = "*")
    public String getAutolist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        int count = autolistMapper.selectAllCount();
        List<Autolist> list = autolistMapper.selectAll((page - 1) * pagesize, pagesize);
        List<JSONObject> jsonlist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Autolist autolist : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(autolist));
            jsonObject.put("beginTime", format1.format(autolist.getBeginTime()));
            jsonObject.put("createtime", format1.format(autolist.getCreatetime()));
            jsonlist.add(jsonObject);
        }

        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", jsonlist);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", totalpage);
        return json.toJSONString();
    }

    @RequestMapping("/admin/addAutolist")
    @CrossOrigin(value = "*")
    public String addAutolist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String takt_time = map.get("takttime");
        String begintime = map.get("begintime");
        String sum = map.get("sum");
        String coin_min = map.get("coinmin");
        String coin_max = map.get("coinmax");
        String type = map.get("type");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Autolist autolist = new Autolist();
        try {
            autolist.setTaktTime(Integer.parseInt(takt_time));
            autolist.setBeginTime(format.parse(begintime));
            autolist.setSum(Double.valueOf(Integer.parseInt(sum)));
            autolist.setCoinMin(Double.parseDouble(coin_min));
            autolist.setCoinMax(Double.parseDouble(coin_max));
            autolist.setType(Integer.parseInt(type));
            autolist.setAdminName("Titan");
            autolist.setCreateuser("2012221");
            autolist.setCreatetime(new Date());
            autolistMapper.insertSelective(autolist);
            json.put("code", 200);
            json.put("msg", "success");
        } catch (ParseException e) {
            e.printStackTrace();
            json.put("code", 400);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }

    //管理员登录
    @RequestMapping("/admin/login")
    @CrossOrigin(value = "*")
    public String login(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String loginName = map.get("loginName");
        String password = map.get("password");

        MD5Util.getMD5String("");

        Admin admin = adminMapper.selectByLogin(loginName, password);
        if (admin != null) {
            if (admin.getArole() != 1) {
                json.put("code", 300);
                json.put("msg", "shop account");
            } else {
                Map<String, String> usermap = new HashMap<>();
                usermap.put("fId", admin.getFid() + "");
                usermap.put("role", admin.getArole() + "");
                String token = JwtUtils.encode(usermap, 1000 * 60 * 60);
                json.put("data", admin);
                json.put("token", token);
                json.put("code", 200);
                json.put("msg", "success");
            }
        } else {
            json.put("code", 300);
            json.put("msg", "not find");
        }
        return json.toJSONString();
    }

    //店铺账户登录
    @RequestMapping("/admin/shop/login")
    @CrossOrigin(value = "*")
    public String shopLogin(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String loginName = map.get("loginName");
        String password = map.get("password");

        Admin admin = adminMapper.selectByLogin(loginName, password);
        if (admin != null) {
            MallShop mallShop = mallShopMapper.findByCode(admin.getCode());
            if (admin.getArole() != 0) {
                json.put("code", 300);
                json.put("msg", "admin account");
            } else {
                Map<String, String> usermap = new HashMap<>();
                usermap.put("fId", admin.getFid() + "");
                usermap.put("role", admin.getArole() + "");
                usermap.put("phone", mallShop.getContactNumber());
                String token = JwtUtils.encode(usermap, 1000 * 60 * 60);
                json.put("data", admin);
                json.put("phone", mallShop.getContactNumber());
                json.put("token", token);
                json.put("code", 200);
                json.put("msg", "success");
            }
        } else {
            json.put("code", 300);
            json.put("msg", "not find");
        }
        return json.toJSONString();
    }


    public String test(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();

        return json.toJSONString();
    }

    //节点列表
    @RequestMapping("/admin/nodelist")
    @CrossOrigin(value = "*")
    public String nodelist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String likes = map.get("likes");
        String state = map.get("state");
        Integer state1 = null;
        if (!"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        List<Map> list = usersMapper.selectByNode((page - 1) * pagesize, pagesize, likes, state1);
        int count = usersMapper.selectByNodeCount(likes, state1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonlist = new ArrayList<>();
        for (Map user : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(user));
            if (user.get("IsNodetime") != null && !"".equals(user.get("IsNodetime") + "")) {
                jsonObject.put("IsNodetime", user.get("IsNodetime") + "");
            } else {
                jsonObject.put("IsNodetime", "");
            }
            jsonlist.add(jsonObject);
        }

        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        int opencount = usersMapper.selectByNodeOpen();
        int closecount = usersMapper.selectByNodeClose();

        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", jsonlist);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", totalpage);
        json.put("opencount", opencount);
        json.put("closecount", closecount);
        json.put("count", count);
        return json.toJSONString();
    }


    //序列池
    @RequestMapping("/admin/mappingtradeAll")
    public String mappingtradeAll(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String likes = map.get("likes");
        String type = map.get("type");
        String state = map.get("state");
        String Isreal = map.get("Isreal");
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String createtime = map.get("createtime");
        String updatetime = map.get("updatetime");
        Integer type1 = null;
        Integer state1 = null;
        Integer Isreal1 = null;
        if (type != null && !"".equals(type)) {
            type1 = Integer.parseInt(type);
        }
        if (state != null && !"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        if (Isreal != null && !"".equals(Isreal)) {
            Isreal1 = Integer.parseInt(Isreal);
        }
        if (createtime == null || "".equals(createtime)) {
            createtime = null;
        }
        if (updatetime == null || "".equals(updatetime)) {
            updatetime = null;
        }
        int count = mappingTradeMapper.selectAllCount(likes, type1, state1, Isreal1, createtime, updatetime);
        List<Map> list = mappingTradeMapper.selectAll(likes, type1, state1, Isreal1, createtime, updatetime, (page - 1) * pagesize, pagesize);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonlist = new ArrayList<>();
        for (Map user : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(user));
            jsonObject.put("createtime", format.format(user.get("createtime")));
            if ("2".equals(user.get("state") + "")) {
                if (user.get("updatetime") != null && !"".equals(user.get("updatetime"))) {
                    jsonObject.put("updatetime", format.format(user.get("updatetime")));
                }
            } else {
                jsonObject.put("updatetime", "");
            }
            jsonlist.add(jsonObject);
        }

        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", jsonlist);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", totalpage);
        return json.toJSONString();
    }

    //卖出列表
    @RequestMapping("/admin/selllist")
    public String selllist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String likes = map.get("likes");
        String state = map.get("state");
        String Isreal = map.get("Isreal");
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String createtime = map.get("createtime");
        String updatetime = map.get("updatetime");
        Integer state1 = 2;
        Integer Isreal1 = null;
        if (state != null && !"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        if (Isreal != null && !"".equals(Isreal)) {
            Isreal1 = Integer.parseInt(Isreal);
        }
        if (createtime == null || "".equals(createtime)) {
            createtime = null;
        }
        if (updatetime == null || "".equals(updatetime)) {
            updatetime = null;
        }
        int count = mappingTradeMapper.selectAllCount(likes, 2, state1, Isreal1, createtime, updatetime);
        List<Map> list = mappingTradeMapper.selectAll(likes, 2, state1, Isreal1, createtime, updatetime, (page - 1) * pagesize, pagesize);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonlist = new ArrayList<>();
        for (Map user : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(user));
            jsonObject.put("createtime", format.format(user.get("createtime")));
            if ("2".equals(user.get("state") + "")) {
                if (user.get("updatetime") != null && !"".equals(user.get("updatetime"))) {
                    jsonObject.put("updatetime", format.format(user.get("updatetime")));
                }
            } else {
                jsonObject.put("updatetime", "");
            }
            jsonlist.add(jsonObject);
        }

        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", jsonlist);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", totalpage);
        return json.toJSONString();
    }

    //买入列表
    @RequestMapping("/admin/buylist")
    public String buylist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String likes = map.get("likes");
        String state = map.get("state");
        String Isreal = map.get("Isreal");
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String createtime = map.get("createtime");
        String updatetime = map.get("updatetime");
        Integer state1 = 2;
        Integer Isreal1 = null;
        if (state != null && !"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        if (Isreal != null && !"".equals(Isreal)) {
            Isreal1 = Integer.parseInt(Isreal);
        }
        if (createtime == null || "".equals(createtime)) {
            createtime = null;
        }
        if (updatetime == null || "".equals(updatetime)) {
            updatetime = null;
        }
        int count = mappingTradeMapper.selectAllCount(likes, 1, state1, Isreal1, createtime, updatetime);
        List<Map> list = mappingTradeMapper.selectAll(likes, 1, state1, Isreal1, createtime, updatetime, (page - 1) * pagesize, pagesize);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonlist = new ArrayList<>();
        for (Map user : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(user));
            jsonObject.put("createtime", format.format(user.get("createtime")));
            if ("2".equals(user.get("state") + "")) {
                if (user.get("updatetime") != null && !"".equals(user.get("updatetime"))) {
                    jsonObject.put("updatetime", format.format(user.get("updatetime")));
                }
            } else {
                jsonObject.put("updatetime", "");
            }
            jsonlist.add(jsonObject);
        }

        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("data", jsonlist);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("totalPage", totalpage);
        return json.toJSONString();
    }


    //提币列表
    @RequestMapping("/admin/withdrawlist")
    public String withdrawlist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String adress = map.get("adress");
        String likes = map.get("likes");
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String start = map.get("start"); //开始时间
        String end = map.get("end");//结束时间

        if (start == null || start.equals("")) {
            start = "1970-01-01 00:00:00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (end == null || end.equals("")) {
            end = sdf.format(new Date());
        }
        String coinType = map.get("coinType");
        if (coinType == null) {
            coinType = "";
        }

        List<Map> list = ordersMapper.withdrawlist(adress, likes, (page - 1) * pagesize, pagesize, start, end, coinType);
        int count = ordersMapper.withdrawlistCount(adress, likes, start, end, coinType);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> mapList = new ArrayList<>();
        for (Map map1 : list) {
            map1.put("createtime", format.format(map1.get("createtime")));
            map1.put("confirmtime", format.format(map1.get("confirmtime")));
            mapList.add(map1);
        }
        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("adress", adress);
        json.put("likes", likes);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("data", mapList);
        json.put("totalPage", totalpage);
        json.put("code", 200);
        json.put("coinType", coinType);
        json.put("msg", "success");
        return json.toJSONString();
    }

    //提币审核接口
    @RequestMapping("/admin/withdrawexamine")
    @Transactional
    public String withdrawexamine(@RequestBody JSONObject param) {
        Integer fId = param.getInteger("fId");
        Integer state = param.getInteger("state");
//        String str = redisDao.getValue("withdrawexamine" + fId);
//        if (str != null) {
//            return JSONObject.toJSONString(new ResultDTO(-1, "提币审核失败，10分钟后再试！"));
//        }
        Orders order = ordersMapper.selectByPrimaryKey(fId);
        if (order.getState() != -1) {
            return JSONObject.toJSONString(new ResultDTO(-1, "提币审核失败"));
        }
        if (state == 0) {
            int i1 = wellethistoryStateMapper.uptByKeyes(order.getOrderid(), -1);
            CheckEx.error(i1 < 1);
            order.setState(2);
            Wellets welled = welletsMapper.selectByUserandcoin(order.getUsersell(), order.getTradecoinid());
            welled.setCoinnum(BigDecimal.valueOf(welled.getCoinnum().doubleValue() + order.getTradenum().doubleValue() + order.getFee().doubleValue()));
            int i2 = welletsMapper.updateByPrimaryKeySelective(welled);
            CheckEx.error(i2 < 1);
            int i = ordersMapper.updateByPrimaryKeySelective(order);
            CheckEx.error(i < 1);
            return JSONObject.toJSONString(new ResultDTO(200, "提币拒绝成功"));
        } else if (state == 1) {
            Date time = new Date();
            List<WellethistoryState> wallensteinStates = wellethistoryStateMapper.selectByKey2(order.getOrderid());
            if (wallensteinStates.size() > 1) {
                WellethistoryState wellethistoryState = wallensteinStates.get(0);
                WellethistoryState wealthiestState1 = wallensteinStates.get(1);
                wellethistoryState.setUpdatetime(time);
                wealthiestState1.setUpdatetime(time);
                wellethistoryState.setState(1);
                wealthiestState1.setState(1);
                int i1 = wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState);
                CheckEx.error(i1 < 1);
                int i2 = wellethistoryStateMapper.updateByPrimaryKeySelective(wealthiestState1);
                CheckEx.error(i2 < 1);

                //发起真实的提币
                String orderid = "";
                if (order.getTradecoinid() == 2) {// AGC
                    String password = MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + "2012457")));
                    try {
                        orderid = EthUtilHuang.tokenTransfer(ConfigConst.PLATFORM_ADDRESS, order.getSelladress(), order.getBuyadress(), password,
                                order.getTradenum().stripTrailingZeros().toPlainString(), null);
                        if (StringUtils.isEmpty(orderid)) {
                            CheckEx.ex();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CheckEx.ex();
                    }
                    order.setOrderid(orderid);
                    order.setState(1);
                    order.setProcessstate(-1);
                    order.setConfirmtime(time);
                    int i = ordersMapper.updateByPrimaryKeySelective(order);
                    CheckEx.error(i < 1);
                    return JSONObject.toJSONString(new ResultDTO(200, "提币审核成功"));
                }

                if (order.getTradecoinid() == 6) {// USDT
                    String password = MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + "2012457")));
                    try {
                        orderid = EthUtilHuang.tokenTransfer(ConfigConst.PLATFORM_ADDRESS1, order.getSelladress(), order.getBuyadress(), password,
                                order.getTradenum().stripTrailingZeros().toPlainString(), null);
                        if (StringUtils.isEmpty(orderid)) {
                            CheckEx.ex();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CheckEx.ex();
                    }
                    order.setOrderid(orderid);
                    order.setState(1);
                    order.setProcessstate(-1);
                    order.setConfirmtime(time);
                    int i = ordersMapper.updateByPrimaryKeySelective(order);
                    CheckEx.error(i < 1);
                    return JSONObject.toJSONString(new ResultDTO(200, "提币审核成功"));
                }
            }
            return JSONObject.toJSONString(new ResultDTO(-1, "审核异常"));
        } else {
            return JSONObject.toJSONString(new ResultDTO(-1, "参数错误"));
        }
    }


    //充币列表
    @RequestMapping("/admin/Rechargelist")
    public String Rechargelist(@RequestBody JSONObject map) {
        System.out.println("查询得到数据：" + map);
        JSONObject json = new JSONObject();
        String adress = map.getString("adress");
        if (adress == null) {
            adress = "";
        }
        String likes = map.getString("likes");
        int page = map.getIntValue("page");
        int pagesize = map.getIntValue("pagesize");
        String start = map.getString("start"); //开始时间
        String end = map.getString("end");//结束时间

        if (start == null || start.equals("")) {
            start = "1970-01-01 00:00:00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (end == null || end.equals("")) {
            end = sdf.format(new Date());
        }
        String coinType = map.getString("coinType");
        if (coinType == null) {
            coinType = "";
        }

        List<Map> list = ordersMapper.rechargelist(adress, likes, (page - 1) * pagesize, pagesize, start, end, coinType);

        int count = ordersMapper.rechargelistCount(adress, likes, start, end, coinType);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> mapList = new ArrayList<>();
        for (Map map1 : list) {
            map1.put("createtime", format.format(map1.get("createtime")));
            map1.put("confirmtime", format.format(map1.get("confirmtime")));
            mapList.add(map1);
        }
        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("adress", adress);
        json.put("likes", likes);
        json.put("page", page);
        json.put("pagesize", pagesize);
        json.put("data", mapList);
        json.put("totalPage", totalpage);
        json.put("coinType", coinType);
        json.put("code", 200);
        json.put("msg", "success");
        return json.toJSONString();
    }


    //添加注册人数限制
    @RequestMapping("/admin/addRegister")
    public String addRegister(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String time = map.get("registerTime");
        Integer num = Integer.parseInt(map.get("num"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Registers register1 = registersMapper.selectByTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            int i = 0;
            Date register = format.parse(time);
            Registers registers = new Registers();
            registers.setTodaytime(register);
            registers.setCreatetime(new Date());
            registers.setUsernum(num);
            if (register1 != null) {
                register1.setUsernum(num);
                i = registersMapper.updateByPrimaryKeySelective(register1);
            } else {
                i = registersMapper.insertSelective(registers);
            }
            if (i > 0) {
                json.put("code", 200);
                json.put("msg", "success");
            } else {
                json.put("code", 201);
                json.put("msg", "fail");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            json.put("code", 201);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }


    //修改注册人数限制
    @RequestMapping("/admin/uptRegister")
    public String uptRegister(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int fId = Integer.parseInt(map.get("fId"));
        String time = map.get("registerTime");
        Integer num = 0;
        if (!"".equals(map.get("num"))) {
            num = Integer.parseInt(map.get("num"));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Registers registers = new Registers();
            registers.setFid(fId);
            if (!"".equals(time)) {
                Date register = format.parse(time);
                registers.setTodaytime(register);
            } else {
                registers.setCreatetime(new Date());
            }
            if (num >= 0) {
                registers.setUsernum(num);
            }
            int i = registersMapper.updateByPrimaryKeySelective(registers);
            if (i > 0) {
                json.put("code", 200);
                json.put("msg", "success");
            } else {
                json.put("code", 201);
                json.put("msg", "fail");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            json.put("code", 201);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }

    //批量注册用户
    @RequestMapping("/admin/registers")
    public String registers(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String loginpassword = map.get("loginpassword");
        String transpassword = map.get("transpassword");
        String num = map.get("num");
        JSONObject json = new JSONObject();
        int usernum = 0;
        try {
            /*int todaynum = usersMapper.selectByTodayCount();
            Registers register = registersMapper.selectByTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            if (register != null) {
                if (register.getUsernum() <= todaynum) {
                    json.put("msg", "今日注册人数已上限");
                    json.put("code", 300);
                    return json.toJSONString();
                } else if (register.getUsernum() - todaynum < Integer.parseInt(num)) {
                    json.put("msg", "注册人数超过限制");
                    json.put("code", 300);
                    return json.toJSONString();
                }
            }*/
            if ("".equals(username) || username == null) {
                json.put("msg", "用户名不能为空");
                json.put("code", 300);
                return json.toJSONString();
            }

            String key = UUID.randomUUID().toString().replace("-", "");
            int count = Integer.parseInt(num);
            List<String> helps = usersService.selectHelpword();
            MessageDigest md5 = MessageDigest.getInstance("md5");
            for (int a = 0; a < count; ) {
                Users user = new Users();
                user.setWays(2);
                user.setKeyes(key);
                String username1 = username + (a + 1);
                if (usersMapper.selectByUsername(username1) > 0) {
                    a++;
                    count++;
                    continue;
                }
                user.setUsername(username1);
                user.setNickname(username1);
                user.setLoginpassword(MD5Util.getMD5String(loginpassword));
                user.setTranspassword(MD5Util.getMD5String(transpassword));
                user.setCreatetime(new Date());
                Users user1 = usersService.insertSelective(user);
                int i = user1 == null ? 0 : 1;
                if (i > 0) {
                    String key1 = user1.getKeyes();
                    Recommend recommend = new Recommend();
                    recommend.setUserkey1(key1);
                    recommendMapper.insertSelective(recommend);
                    Set<String> set = new HashSet<>();
                    Random random = new Random();
                    boolean flag = true;
                    while (flag) {
                        set.add(helps.get(random.nextInt(helps.size())));
                        if (set.size() == 3) {
                            flag = false;
                        }
                    }
                    String stringList = "";
                    for (String str : set) {
                        stringList = stringList + str + ",";
                    }
                    addree_user address = addree_userMapper.selectByUserKey(user1.getKeyes());
                    address.setHelp(stringList.substring(0, stringList.length() - 1));
                    int j = addree_userMapper.updateByPrimaryKeySelective(address);
                    if (j > 0) {
                        AddUser addUser = new AddUser();
                        addUser.setUserkey(user1.getKeyes());
                        addUser.setLoginpassword(loginpassword);
                        addUser.setTradepassword(transpassword);
                        addUser.setUsername(user1.getUsername());
                        addUser.setCreatetime(user1.getCreatetime());
                        addUserMapper.insertSelective(addUser);
                        usernum++;
                        a++;
                    }
                }
            }
            json.put("msg", "success");
            json.put("count", usernum);
            json.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("count", usernum);
            json.put("code", 201);
        }
        return json.toJSONString();
    }

    //添加用户列表
    @RequestMapping("/admin/AddUserlist")
    public String AddUserlist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String likes = map.get("likes");
        String state = map.get("state");
        Integer state1 = null;
        if (state != null && !"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        List<AddUser> list = addUserMapper.selectAll(likes, state1, (page - 1) * pagesize, pagesize);
        int count = addUserMapper.selectAllCount(likes, state1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonList = new ArrayList<>();
        for (AddUser addUser : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(addUser));
            jsonObject.put("createtime", format.format(addUser.getCreatetime()));
            jsonList.add(jsonObject);
        }
        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        Registers register1 = registersMapper.selectByTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        json.put("data", jsonList);
        json.put("page", page);
        json.put("totalPage", totalpage);
        json.put("likes", likes);
        json.put("state", state);
        json.put("code", 200);
        if (register1 != null) {
            json.put("registernum", register1.getUsernum());
        } else {
            json.put("registernum", "----");
        }
        json.put("todayRegister", usersMapper.selectByTodayCount());
        json.put("msg", "success");
        return json.toJSONString();
    }

    //启用添加用户
    @RequestMapping("/admin/uptAddUser")
    public String uptAddUser(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String fId = map.get("Id");
        List<String> list = new ArrayList<>();
        if (!"".equals(fId)) {
            String[] str = fId.split(",");
            for (String s : str) {
                list.add(s);
            }
        }
        int state = 1;
        int i = addUserMapper.updateByfids(list, state);
        if (i > 0) {
            json.put("code", 200);
            json.put("msg", "success");
        } else {
            json.put("code", 300);
            json.put("msg", "error");
        }
        return json.toJSONString();
    }


    //反馈列表
    @RequestMapping("/admin/feedbacklist")
    public String feedbacklist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        String likes = map.get("likes");
        String state = map.get("state");
        Integer state1 = null;
        if (state != null && !"".equals(state)) {
            state1 = Integer.parseInt(state);
        }
        List<Feedback> list = feedbackMapper.selectAll(likes, state1, (page - 1) * pagesize, pagesize);
        int count = feedbackMapper.selectAllCount(likes, state1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonList = new ArrayList<>();
        for (Feedback feedback : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(feedback));
            jsonObject.put("createtime", format.format(feedback.getCreatetime()));
            jsonList.add(jsonObject);
        }
        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("data", jsonList);
        json.put("page", page);
        json.put("totalPage", totalpage);
        json.put("likes", likes);
        json.put("state", state);
        json.put("code", 200);
        json.put("msg", "success");
        return json.toJSONString();
    }

    //反馈详情
    @RequestMapping("/admin/feedbackdetail")
    public String feedbackdetail(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        int fId = Integer.parseInt(map.get("Id"));
        Feedback feedback = feedbackMapper.selectByPrimaryKey(fId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(feedback));
        jsonObject.put("createtime", format.format(feedback.getCreatetime()));
        feedback.setState(1);
        feedbackMapper.updateByPrimaryKeySelective(feedback);
        json.put("data", jsonObject);
        json.put("code", 200);
        json.put("msg", "success");
        return json.toJSONString();
    }

    //查询员工并且分页
    @RequestMapping("/admin/findAdminByPage")
    public String findAdminByPage(@RequestBody Map<String, String> param) {
        try {
            int pageNo = Integer.parseInt(param.get("page"));
            int pagesize = Integer.parseInt(param.get("pagesize"));
            String adminName = param.get("adminName");
            pageNo = pageNo <= 0 ? 0 : (pageNo - 1) * pagesize;
            List<Admin> adminList = adminMapper.findAdminByPage(pageNo, pagesize, adminName);
            int count = adminMapper.findAdminByPagCount(adminName);
            Map<String, Object> map = new HashMap<>();
            map.put("adminList", adminList);
            map.put("count", count);
            return JSONObject.toJSONString(new ResultDTO(200, "success", map));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }


    //批量启用或者禁用员工 0禁用 1启用
    @RequestMapping("/admin/changeAdminStatus")
    public String changeAdminStatus(@RequestBody Map<String, Object> param) {
        try {
            String[] adminId = param.get("adminId").toString().split(",");
            int status = (Integer) param.get("status");
            return JSONObject.toJSONString(new ResultDTO(200, "success", adminMapper.updateAdminStatus(status, adminId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }

    //添加员工
    @RequestMapping("/admin/addAdmin")
    public String addAdmin(@RequestBody Admin param) {
        try {
            int i = adminMapper.insert(param);
            if (i > 0) {
                return JSONObject.toJSONString(new ResultDTO(200, "success"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }

    @RequestMapping("/admin/deleteAdmin")
    public ResultDTO deleteAdmin(@RequestBody Map<String, Integer> param) {
        try {
            int adminId = param.get("adminId");
            int num = adminMapper.deleteByPrimaryKey(adminId);
            if (num > 0) {
                return new ResultDTO(200, "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultDTO(201, "error");
    }

    //用户资产
    @RequestMapping("/admin/welletlist")
    @CrossOrigin(value = "*")
    public String welletlist(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String page = map.get("page");
        String pagesize = map.get("pagesize");
        String likes = map.get("likes");
        if (likes == null) {
            likes = "";
        }
        try {
            List<Map> list = welletsMapper.Adminwelletlist(likes, (Integer.parseInt(page) - 1) * Integer.parseInt(pagesize), Integer.parseInt(pagesize));
            int count = welletsMapper.AdminwelletlistCount(likes) / 4;
            int totalpage = (count % Integer.parseInt(pagesize) == 0 ? count / Integer.parseInt(pagesize) : count / Integer.parseInt(pagesize) + 1);
            json.put("code", 200);
            json.put("msg", "success");
            json.put("page", page);
            json.put("totalPage", totalpage);
            json.put("likes", likes);
            json.put("data", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 400);
        }
        return json.toJSONString();
    }

    //用户资产详情
    @RequestMapping("/admin/welletByuser")
    public String welletByuser(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String userkey = map.get("userkey");
        if (userkey == null) {
            userkey = "";
        }
        try {
            Map wellet = welletsMapper.AdminwelletByuser(userkey);
            json.put("code", 200);
            json.put("msg", "success");
            json.put("data", wellet);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 400);
        }
        return json.toJSONString();
    }

    //修改用户资产
    @RequestMapping("/admin/uptwelletByuser")
    public String uptwelletByuser(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        try {
            String Id = map.get("Id");
            String auId = map.get("auId");
            double titannum = Double.parseDouble(map.get("titannum"));
            double titanShift = 0;
            if (map.get("titanshift") != null) {
                titanShift = Double.parseDouble(map.get("titanshift"));
            }
            double titancnum = Double.parseDouble(map.get("titancnum"));
            double usd1num = Double.parseDouble(map.get("usd1num"));
            double usd2num = Double.parseDouble(map.get("usd2num"));
            String titan = map.get("titan");
            String titanc = map.get("titanc");
            String usd1 = map.get("usd1");
            String usd2 = map.get("usd2");
            Wellets Titan = welletsMapper.selectByUserandcoin(Id, 1);
            Wellets Titanc = welletsMapper.selectByUserandcoin(Id, 2);
            Wellets USD1 = welletsMapper.selectByUserandcoin(Id, 3);
            Wellets USD2 = welletsMapper.selectByUserandcoin(Id, 4);
            if (titannum >= 0) {
                welletsMapper.uptWelletByuserandcoin(titannum, 1, Id);
            }
            if (map.get("titanshift") != null && titanShift >= 0) {
                Wellets wellet = new Wellets();
                wellet.setFid(Titan.getFid());
                wellet.setShiftnum(BigDecimal.valueOf(titanShift));
                welletsMapper.updateByPrimaryKeySelective(wellet);
            }
            if (titancnum >= 0) {
                welletsMapper.uptWelletByuserandcoin(titancnum, 2, Id);
            }
            if (usd1num >= 0) {
                welletsMapper.uptWelletByuserandcoin(usd1num, 3, Id);
            }
            if (usd2num >= 0) {
                welletsMapper.uptWelletByuserandcoin(usd2num, 4, Id);
            }
            Users user = usersMapper.selectByUserKey(Id);
            WelletsuptLog wlog = new WelletsuptLog();
            wlog.setUserkey(user.getKeyes());
            wlog.setUsername(user.getUsername());
            wlog.setCreatetime((int) (System.currentTimeMillis() / 1000));
            wlog.setTitanago(Titan.getCoinnum().doubleValue());
            wlog.setTitanafter(titannum);
            wlog.setTitancago(Titanc.getCoinnum().doubleValue());
            wlog.setTitancafter(titancnum);
            wlog.setTitanshiftago(Titan.getShiftnum().doubleValue());
            wlog.setTitanshiftafter(titanShift);
            wlog.setUsd1ago(USD1.getCoinnum().doubleValue());
            wlog.setUsd1after(usd1num);
            wlog.setUsd2ago(USD2.getCoinnum().doubleValue());
            wlog.setUsd2after(usd2num);
            wlog.setAuname(auId);
            welletsuptLogMapper.insertSelective(wlog);
            json.put("code", 200);
            json.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 400);
        }
        return json.toJSONString();
    }

    //资产修改记录
    @RequestMapping("/admin/welletuptlog")
    private String welletuptlog(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String likes = map.get("likes");
        String times = map.get("times");
        int page = Integer.parseInt(map.get("page"));
        int pagesize = Integer.parseInt(map.get("pagesize"));
        List<WelletsuptLog> list = welletsuptLogMapper.selectAllBypage(likes, times, (page - 1) * pagesize, pagesize);
        List<JSONObject> jsonlist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (WelletsuptLog welletsuptLog : list) {
            JSONObject json1 = JSONObject.parseObject(JSON.toJSONString(welletsuptLog));
            json1.put("createtime", format.format(new Date(welletsuptLog.getCreatetime())));
            jsonlist.add(json1);
        }

        int count = welletsuptLogMapper.selectAllCount(likes, times);
        int totalpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
        json.put("data", jsonlist);
        json.put("code", 200);
        json.put("msg", "success");
        json.put("page", page);
        json.put("totalPage", totalpage);
        json.put("likes", likes);
        json.put("times", times);
        return json.toJSONString();
    }


    /**
     * 平台统计
     */
    @RequestMapping("/admin/statistics")
    public String statistics() {
        JSONObject json = new JSONObject();
        json.put("code", 201);
        json.put("msg", "Server exception");
        try {
           /* ETH总充币数量、BTC总充币数量、YYB总充币数量、ETH总提币数量、BTC总提币数量
            YYB总提币数量、ETH今日充币数量、BTC今日充币数量、YYB今日充币数量、ETH今日提币数量
            BTC今日提币数量、 YYB今日提币数量、平台总人数、今日注册总人数、总积分赠送、总积分消费*/
            double allETHIn = wellethistoryStateMapper.selectAllETHIn();
            double allBTCIn = wellethistoryStateMapper.selectAllBTCIn();
            double allYYBIn = wellethistoryStateMapper.selectAllYYBIn();
            double todayETHIn = wellethistoryStateMapper.selectTodayETHIn();
            double todayBTCIn = wellethistoryStateMapper.selectTodayBTCIn();
            double todayYYBIn = wellethistoryStateMapper.selectTodayYYBIn();
            double allETHOut = wellethistoryStateMapper.selectAllETHOut();
            double allBTCOut = wellethistoryStateMapper.selectAllBTCOut();
            double allYYBOut = wellethistoryStateMapper.selectAllYYBOut();
            double todayETHOut = wellethistoryStateMapper.selectTodayETHOut();
            double todayBTCOut = wellethistoryStateMapper.selectTodayBTCOut();
            double todayYYBOut = wellethistoryStateMapper.selectTodayYYBOut();
            int allUserCount = usersMapper.selectUserCount();
            int todayUserCount = usersMapper.selectTodayUserCount();
            double allIntegralOut = wellethistoryStateMapper.selectAllIntegralOut();
            double allIntegralIn = wellethistoryStateMapper.selectAllIntegralIn();
            Map<java.lang.String, Object> data = new HashMap<>();
            //总充值
            data.put("allETHIn", allETHIn);
            data.put("allBTCIn", allBTCIn);
            data.put("allYYBIn", allYYBIn);
            //今日充值
            data.put("todayETHIn", todayETHIn);
            data.put("todayBTCIn", todayBTCIn);
            data.put("todayYYBIn", todayYYBIn);
            //总提币
            data.put("allETHOut", allETHOut);
            data.put("allBTCOut", allBTCOut);
            data.put("allYYBOut", allYYBOut);
            //今日提币
            data.put("todayETHOut", todayETHOut);
            data.put("todayBTCOut", todayBTCOut);
            data.put("todayYYBOut", todayYYBOut);
            //总注册人数
            data.put("allRegisterUser", allUserCount);
            //今日注册人数
            data.put("todayRegisterUser", todayUserCount);
            //总赠送积分
            data.put("allGiveIntegral", allIntegralIn);
            //总消费积分
            data.put("allConsumptionIntegral", allIntegralOut);


            json.put("code", 200);
            json.put("msg", "success");
            json.put("data", data);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toJSONString();
    }


}
