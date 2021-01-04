package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.ParametersMapper;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.*;
import com.lanjing.wallet.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UserContrller extends BaseContrller {

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource(name = "RecommendService")
    private RecommendService recommendService;

    @Resource(name = "PhoneCodeService")
    private PhoneCodeService phoneCodeService;

    @Resource(name = "AddressService")
    private AddressService addressService;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @Resource(name = "WelletService")
    private WelletService welletService;

    @Resource(name = "CoinsService")
    private CoinsService coinsService;

    @Autowired
    private RedisDao redisDao;

    @Resource(name = "RegistersService")
    private RegistersService registersService;

    @Resource(name = "FeedbackService")
    private FeedbackService feedbackService;

    @Resource
    private ParametersMapper parametersMapper;

    @RequestMapping("/app/login")
    public String Login(@RequestBody JSONObject map) {
        String userName = map.getString("userName");
        String loginPassword = map.getString("loginPassword");
        String device = map.getString("device");
        JSONObject json = new JSONObject();
        Users user = new Users();
        user.setUsername(userName);
        user.setLoginpassword(loginPassword);
        Users loginuser = usersService.selectByLogin(user);
        if (loginuser == null) {
            json.put("msg", "密码错误，请注意检查");
            json.put("code", 201);
            return json.toJSONString();
        }
        if (loginuser.getState() == 0) {
            json.put("msg", "账户已冻结");
            json.put("code", 201);
            return json.toJSONString();
        }
        if (loginuser.getState() == -1) {
            json.put("msg", "账户不存在");
            json.put("code", 201);
            return json.toJSONString();
        }
        JSONObject userjson = new JSONObject();
        userjson.put("keyes", loginuser.getKeyes());
        userjson.put("device", device);
        String token = JwtUtils.encode(userjson, 60 * 1000 * 60 * 24);
        loginuser.setToken(token);
        loginuser.setDevice(device);
        loginuser.setUpdatetime(new Date());
        usersService.updateByPrimaryKeySelective(loginuser);
        json.put("msg", "ok");
        json.put("code", 200);
        json.put("token", token);
        String registerWebUrl = "";
        ParametersWithBLOBs parametersWithBLOBs = parametersMapper.selectByKey("registerWebUrl");
        if (parametersWithBLOBs != null) {
            registerWebUrl = parametersWithBLOBs.getKeyvalue();
        }
        json.put("registerWebUrl", registerWebUrl);
        return json.toJSONString();
    }

    @RequestMapping("/app/register")
    @Transactional
    public String register(@RequestBody JSONObject map) {
        String username = map.getString("phone");
        String msgcode = map.getString("msgcode");
        String loginpassword = map.getString("loginpassword");
        String transpassword = map.getString("transpassword");
        String Invitacode = map.getString("Invitacode");
        JSONObject json = new JSONObject();
        try {
            if ("".equals(username) || username == null) {
                json.put("msg", "手机号不能为空");
                json.put("code", 300);
                return json.toJSONString();
            }
            if (!AccountValidatorUtil.isMobile(username)) {
                json.put("code", 300);
                json.put("msg", "手机号无效");
                return json.toJSONString();
            }
            if ("".equals(Invitacode) || Invitacode == null) {
                json.put("msg", "请填写邀请码");
                json.put("code", 300);
                return json.toJSONString();
            }

            if (usersService.selectByUsername(username) > 0) {
                json.put("msg", "该手机号已被注册");
                json.put("code", 300);
                return json.toJSONString();
            }
            if (phoneCodeService.checkByCode(username, msgcode) <= 0) {
                json.put("msg", "验证码错误");
                json.put("code", 300);
                return json.toJSONString();
            }

            String key = UUID.randomUUID().toString().replace("-", "");
            Users user = new Users();
            user.setKeyes(key);
            user.setUsername(username);
            user.setNickname(username);
            user.setPhonenum(username);
            user.setLoginpassword(loginpassword);
            user.setTranspassword(transpassword);
            user.setCreatetime(new Date());
            //判断上级是否激活
            Recommend uprecommend = recommendService.selectByUserKey1(Invitacode);
            if (uprecommend == null || "".equals(uprecommend.getUserkey2())) {
                json.put("msg", "邀请人不存在");
                json.put("code", 202);
                return json.toJSONString();
            }
            Users user1 = usersService.register(user, "");
            int i = user1 == null ? 0 : 1;
            key = user1.getKeyes();
            Recommend recommend = new Recommend();
            recommend.setUserkey1(key);
            recommend.setUserkey2(Invitacode);
            recommend.setCreateTime(new Date());
            if (recommendService.insertSelective(recommend) < 1) {
                json.put("msg", "邀请人不存在");
                json.put("code", 202);
                return json.toJSONString();
            }
            if (i > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "no");
                json.put("code", 202);
            }

            if (phoneCodeService.expireByCode(username, msgcode) <= 0) {
                json.put("msg", "修改验证码过期错误");
                json.put("code", 300);
                return json.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 201);
            throw new RuntimeException("error");
        }
        return json.toJSONString();
    }


    @RequestMapping("/app/register1")
    @CrossOrigin(value = "*")
    @Transactional
    public String register1(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String nickname = map.get("nickname");
        String loginpassword = map.get("loginpassword");
        String transpassword = map.get("transpassword");
        String Invitacode = map.get("Invitacode");
        JSONObject json = new JSONObject();
        try {

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String timestr = format.format(new Date());

            int hours = new Date().getHours();
            /*** 定制每日9:00执行方法 ***/
            if (hours < Integer.parseInt(parametersService.selectByKey("registerbegintime").getKeyvalue())) {
                json.put("msg", "今日注册人数已上限");
                json.put("code", 300);
                return json.toJSONString();
            }

            if ("1".equals(parametersService.selectByKey("Isregister").getKeyvalue())) {
                json.put("msg", "今日注册人数已上限");
                json.put("code", 300);
                return json.toJSONString();
            }

            int todaynum = usersService.selectByTodayCount();
            Registers register = registersService.selectByTime(timestr);
            if (register != null) {
                if (register.getUsernum() <= todaynum) {
                    json.put("msg", "今日注册人数已上限");
                    json.put("code", 300);
                    return json.toJSONString();
                }
            }

            String key = UUID.randomUUID().toString().replace("-", "");
            Users user = new Users();
            user.setKeyes(key);
            if ("".equals(username) || username == null) {
                json.put("msg", "用户名不能为空");
                json.put("code", 300);
                return json.toJSONString();
            }
            if (usersService.selectByUsername(username) > 0) {
                json.put("msg", "该用户名已存在");
                json.put("code", 300);
                return json.toJSONString();
            }
            user.setUsername(username);
            if ("".equals(nickname) || nickname == null) {
                user.setNickname(username);
            } else {
                user.setNickname(nickname);
            }
            /*if(loginpassword.trim().length()<6){
                json.put("msg",loginpassword+"登录密码长度少于6");
                json.put("code",300);
                return json.toJSONString();
            }*/
            /*if(transpassword.trim().length()<6){
                json.put("msg","交易密码长度少于6");
                json.put("code",300);
                return json.toJSONString();
            }*/
            user.setLoginpassword(MD5Util.getMD5String(loginpassword));
            user.setTranspassword(MD5Util.getMD5String(transpassword));
            user.setCreatetime(new Date());
            if (!"".equals(Invitacode) && Invitacode != null) {
                //判断上级是否激活
                Recommend uprecommend = recommendService.selectByUserKey1(Invitacode);
                if (uprecommend == null || "".equals(uprecommend.getUserkey2())) {
                    json.put("msg", "邀请人不存在");
                    json.put("code", 202);
                    return json.toJSONString();
                }
            }
            Users user1 = usersService.insertSelective(user);
            int i = user1 == null ? 0 : 1;
            if (i > 0) {
                List<String> helps = usersService.selectHelpword();
                String key1 = user1.getKeyes();
                Recommend recommend = new Recommend();
                recommend.setUserkey1(key1);
                recommend.setCreateTime(new Date());
                recommendService.insertSelective(recommend);
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
                addree_user address = addressService.selectByUserKey(user1.getKeyes());
                address.setHelp(stringList.substring(0, stringList.length() - 1));
                int j = addressService.updateByPrimaryKeySelective(address);
            }
            if (i > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "no");
                json.put("code", 202);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 201);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/isfindpassword")
    public String Isfindpassword(@RequestBody Map<String, String> map) {
        String help = map.get("help");
        String keys = map.get("keys");
        JSONObject json = new JSONObject();
        try {
            String userId = usersService.selectByhelpKey(help, keys);
            if (!"".equals(userId) && userId != null) {
                json.put("msg", "ok");
                json.put("code", 200);
                json.put("userId", userId.trim());
                json.put("userName", usersService.selectByPrimaryKey(Integer.parseInt(userId)).getUsername());
                redisDao.setKey(userId.trim(), "yes");
                return json.toJSONString();
            } else {
                json.put("msg", "no");
                json.put("code", 204);
                return json.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 201);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/findpassword")
    public String findpassword(@RequestBody Map<String, String> map) {
        String msgcode = map.get("msgcode");
        String phone = map.get("phone");
        String loginpassword = map.get("loginpassword");
        JSONObject json = new JSONObject();
        if (!AccountValidatorUtil.isMobile(phone)) {
            json.put("code", 300);
            json.put("msg", "手机号无效");
            return json.toJSONString();
        }
        if ("".equals(loginpassword.trim()) || loginpassword.trim().length() < 6) {
            json.put("code", 300);
            json.put("msg", "登录密码长度必须大于等于6");
            return json.toJSONString();
        }
        if (phoneCodeService.selectByCode(phone, msgcode) <= 0) {
            json.put("msg", "验证码错误");
            json.put("code", 300);
            return json.toJSONString();
        }
        try {
            Users u = new Users();
            u.setUsername(phone);
            List<Users> users = usersService.selectBy(u, 1, 1);
            if (users.size() < 1) {
                json.put("msg", "该用户不存在");
                json.put("code", 300);
                return json.toJSONString();
            }
            Users user = users.get(0);
            user.setLoginpassword(loginpassword);
            int i = usersService.updateByPrimaryKeySelective(user);
            if (i > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "no");
                json.put("code", 204);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 201);
        }
        return json.toJSONString();
    }

    /*@RequestMapping("/app/findpassword")
    public String findpassword(@RequestBody Map<String,String> map){
        String userId= map.get("userId");
        String loginpassword =map.get("loginpassword");
        String tradepassword =map.get("tradepassword");
        JSONObject json = new JSONObject();
        if("".equals(userId.trim())){
            json.put("code",300);
            json.put("msg","用户无效");
            return json.toJSONString();
        }
        if("".equals(loginpassword.trim()) || loginpassword.trim().length() < 6){
            json.put("code",300);
            json.put("msg","登录密码长度必须大于等于6");
            return json.toJSONString();
        }
        if("".equals(tradepassword.trim()) || tradepassword.trim().length() < 6){
            json.put("code",300);
            json.put("msg","交易密码长度必须大于等于6");
            return json.toJSONString();
        }
        try {
            if(redisDao.getValue(userId.trim()) == null || "no".equals(redisDao.getValue(userId.trim()))){
                json.put("code",400);
                json.put("msg","修改时间过期或该请求已处理");
                return json.toJSONString();
            }
            redisDao.setKey(userId+"1","no");
            Users user = new Users();
            user.setFid(Integer.parseInt(userId));
            user.setLoginpassword(loginpassword);
            user.setTranspassword(tradepassword);
            int i = usersService.updateByPrimaryKeySelective(user);
            if (i>0){
                json.put("msg","ok");
                json.put("code",200);
            }else{
                json.put("msg","no");
                json.put("code",204);
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",201);
        }
        return json.toJSONString();
    }*/

    @RequestMapping("/app/uptpassword")
    public String updatePassWord(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String type = map.get("type");
        String newpassword = map.get("newpassword");
        if (newpassword.trim().length() < 6) {
            json.put("msg", "密码长度小于6");
            json.put("code", 300);
            return json.toJSONString();
        }
        Users user = usersService.selectByUserKey(getUserKey());
        if ("1".equals(type)) {
            String oldpassword = map.get("oldpassword");
            if (oldpassword == null || "".equals(oldpassword.trim())) {
                json.put("msg", "旧密码为空");
                json.put("code", 204);
                return json.toJSONString();
            }
            if (oldpassword.equals(user.getLoginpassword())) {
                user.setLoginpassword(newpassword);
            } else {
                json.put("msg", "旧密码错误");
                json.put("code", 204);
                return json.toJSONString();
            }
        } else if ("2".equals(type)) {
            String msgcode = map.get("code");
            if (msgcode == null || "".equals(msgcode.trim())) {
                json.put("msg", "验证码为空");
                json.put("code", 204);
                return json.toJSONString();
            }
            if (phoneCodeService.selectByCode(user.getPhonenum(), msgcode) > 0) {
                user.setTranspassword(newpassword);
            } else {
                json.put("msg", "验证码错误");
                json.put("code", 204);
                return json.toJSONString();
            }
        } else {
            json.put("msg", "无效参数");
            json.put("code", 300);
            return json.toJSONString();
        }
        try {
            int i = usersService.updateByPrimaryKeySelective(user);
            if (i > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "error");
                json.put("code", 201);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 201);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/uptphone")
    public String setPhone(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String phone = map.get("phonenum");
        String msgcode = map.get("code");
        String loginpwd = map.get("loginpwd");
        if ("".equals(msgcode.trim()) || msgcode == null) {
            json.put("msg", "请输入登录密码");
            json.put("code", 300);
            return json.toJSONString();
        }
        if ("".equals(msgcode.trim()) || msgcode == null) {
            json.put("msg", "请输入验证码");
            json.put("code", 300);
            return json.toJSONString();
        }
        if (phoneCodeService.selectByCode(phone, msgcode) > 0) {
            int num = phoneCodeService.selectByPhone(phone);
            if (num >= 1) {
                json.put("msg", "该手机号已被绑定！");
                json.put("code", 400);
                return json.toJSONString();
            }
            Users user = usersService.selectByPrimaryKey(Integer.parseInt(getUserKey()));
            if (!loginpwd.trim().equals(user.getLoginpassword())) {
                json.put("msg", "登录密码错误！");
                json.put("code", 400);
                return json.toJSONString();
            }
            user.setPhonenum(phone);
            user.setUsername(phone);
            try {
                if (usersService.updateByPrimaryKeySelective(user) > 0) {
                    json.put("msg", "ok");
                    json.put("code", 200);
                } else {
                    json.put("msg", "no");
                    json.put("code", 201);
                }
            } catch (Exception e) {
                e.printStackTrace();
                json.put("msg", "error");
                json.put("code", 300);
            }
        } else {
            json.put("msg", "验证码有误，请重新输入");
            json.put("code", 204);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/getperson")
    public String getperson() {
        JSONObject json = new JSONObject();
        try {
            Users user = usersService.selectByUserKey(getUserKey());
            user.setLoginpassword(null);
            user.setTranspassword(null);
            Coins coin = coinsService.selectByPrimaryKey(1);
            Wellets wellet = welletService.selectByUserKey(user.getKeyes(), 1);
            /*JSONObject userjson = JSONObject.parseObject(user.toString(),JSONObject.class);
            userjson.put("registerTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreatetime()));*/
            if (user != null) {
                json.put("msg", "ok");
                json.put("code", 200);
                json.put("data", user);
                /*json.put("grede",recommendService.selectByUserKey1(user.getKeyes()).getUsergrede());
                json.put("Titan",wellet.getCoinnum().doubleValue());
                json.put("Usd",(wellet.getCoinnum().doubleValue())*coin.getPrice());*/
            } else {
                json.put("msg", "no");
                json.put("code", 201);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }

    /*@RequestMapping("/app/uploaduserpicture")
    public String uploadFile(HttpServletRequest request,HttpServletResponse response) {
        JSONObject json = new JSONObject();
        List<MultipartFile> multipartFiles = UploadUtil.getFileSet(request, 1024 * 20, null);
        String path = "D:" + File.separator;
        if (multipartFiles.size() == 0) {
            // TODO 给出提示,不允许没选择文件点击上传
            json.put("msg","请选择图片");
            json.put("code",201);
            return json.toJSONString();
        }
        try {
            String filePath = UploadUtil.uploadFile(multipartFiles.get(0), path);
            System.out.println(filePath);
            json.put("msg","ok");
            json.put("picture",filePath);
            json.put("code",200);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg","error");
            json.put("code",300);
        }
        return json.toJSONString();
    }*/


    @RequestMapping("/app/uploaduserpicture")
    public String uploadFile(@RequestBody Map<String, String> map) throws IOException {
        JSONObject json = new JSONObject();
        String img = map.get("picture");
        File directory = new File("");// 参数为空
        String courseFile = parametersService.selectByKey("imgbath").getKeyvalue();
        String imgurl = BASE64imgUtil.GenerateImage(img, courseFile);
        Users user = usersService.selectByUserKey(getUserKey());
        user.setPicture(parametersService.selectByKey("imgurl").getKeyvalue() + imgurl.substring(imgurl.lastIndexOf("/")));
        try {
            if (usersService.updateByPrimaryKeySelective(user) > 0) {
                json.put("msg", "ok");
                json.put("imgurl", user.getPicture());
                json.put("code", 200);
            } else {
                json.put("msg", "no");
                json.put("code", 204);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/uptnickname")
    public String uptNickName(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String NickName = map.get("nickname");
        if (NickName == null || "".equals(NickName.trim())) {
            json.put("msg", "昵称不能为空");
            json.put("code", 201);
            return json.toJSONString();
        }
        Users user = usersService.selectByUserKey(getUserKey());
        if (user != null) {
            user.setNickname(NickName);
            try {
                if (usersService.updateByPrimaryKeySelective(user) > 0) {
                    json.put("msg", "ok");
                    json.put("code", 200);
                } else {
                    json.put("msg", "no");
                    json.put("code", 204);
                }
            } catch (Exception e) {
                e.printStackTrace();
                json.put("msg", "error");
                json.put("code", 300);
            }
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/sendcode")
    public String sendcode(@RequestBody JSONObject map) {
        //map.decrypt();
        JSONObject json = new JSONObject();
        String phonenum = map.getString("phonenum");
        try {
            if (phoneCodeService.sendCode(phonenum) > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "no");
                json.put("code", 204);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/VerificationTradepassword")
    public String VerificationTradepassword(@RequestBody Map<String, String> map) {
        return VerificationTradepassword1(map, getUserKey()).toJSONString();
    }


    public JSONObject VerificationTradepassword1(Map<String, String> map, String userkey) {
        JSONObject json = new JSONObject();
        String phonenum = map.get("password");
        System.out.println("-----------" + phonenum);
        try {
            Users user = usersService.selectByPrimaryKey(Integer.parseInt(userkey));
            if (user != null) {
                if (user.getTranspassword().equals(phonenum.trim())) {
                    json.put("msg", "ok");
                    json.put("code", 200);
                } else {
                    json.put("msg", "密码错误！");
                    json.put("code", 201);
                }
            } else {
                json.put("msg", "no");
                json.put("code", 201);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json;
    }


    @RequestMapping("/app/gethelp")
    public String gethelp(@RequestBody Map<String, String> map) {
        String userkey = getUserKey();
        JSONObject json = VerificationTradepassword1(map, userkey);
        if (json.getIntValue("code") == 200) {
            addree_user addree = addressService.selectByUserKey(userkey);
            if (addree != null) {
                json.put("help", addree.getHelp());
            } else {
                json.put("help", "");
            }
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/getwelletkeys")
    public String getwelletkeys(@RequestBody Map<String, String> map) {
        String userkey = getUserKey();
        JSONObject json = VerificationTradepassword1(map, userkey);
        if (json.getIntValue("code") == 200) {
            addree_user addree = addressService.selectByUserKey(userkey);
            if (addree != null) {
                json.put("welletkey", addree.getKeyes());
            } else {
                json.put("welletkey", "");
            }
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/savawelletname")
    public String savawelletName(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String wlletName = map.get("welletname");
        String userkey = getUserKey();
        try {
            Users user = usersService.selectByUserKey(userkey);
            user.setWelletname(wlletName);
            int i = usersService.updateByPrimaryKeySelective(user);
            if (i > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "no");
                json.put("code", 204);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/getwelletlist")
    public String getwelletlist() {
        JSONObject json = new JSONObject();
        Users user = new Users();
        user.setDevice(getDevice());
        try {
            List<Users> list = usersService.selectBy(user, null, null);
            List<Map<String, String>> data = new ArrayList<>();
            for (Users user1 : list) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("username", user1.getUsername());
                map1.put("wId", addressService.selectByUserKey(user1.getKeyes()).getAdress());
                map1.put("address", parametersService.selectByKey("address").getKeyvalue());
                map1.put("welletName", user1.getWelletname());
                map1.put("token", user1.getToken());
                data.add(map1);
            }
            json.put("data", data);
            json.put("msg", "ok");
            json.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }

    @RequestMapping("/app/delwellet")
    public String delwellet(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        Users user = new Users();
        user.setToken(map.get("tokens"));
        try {
            List<Users> list = usersService.selectBy(user, null, null);
            if (list.size() < 1) {
                json.put("msg", "not find");
                json.put("code", 204);
                return json.toJSONString();
            }
            Users uptuser = list.get(0);
            uptuser.setDevice("");
            int i = usersService.updateByPrimaryKeySelective(uptuser);
            if (i > 0) {
                json.put("msg", "ok");
                json.put("code", 200);
            } else {
                json.put("msg", "删除失败！");
                json.put("code", 204);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }


    @RequestMapping("/app/getExemption")
    public String getExemption() {
        JSONObject json = new JSONObject();
        json.put("ExemptionCH", parametersService.selectByKey("ExemptionCH").getKeyvalue());
        json.put("ExemptionEN", parametersService.selectByKey("ExemptionEN").getKeyvalue());
        json.put("msg", "ok");
        json.put("code", 200);
        return json.toJSONString();
    }

    @RequestMapping("/app/getAgreement")
    public String getAgreement() {
        JSONObject json = new JSONObject();
        json.put("AgreementEN", parametersService.selectByKey("ServiceAgreementEN").getKeyvalue());
        json.put("AgreementCH", parametersService.selectByKey("ServiceAgreementCH").getKeyvalue());
        json.put("msg", "ok");
        json.put("code", 200);
        return json.toJSONString();
    }

    //意见反馈
    @RequestMapping("/app/feedback")
    public String feedback(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        Feedback feedback = new Feedback();
        Users user = usersService.selectByUserKey(getUserKey());
        feedback.setUserkey(user.getKeyes());
        feedback.setUsername(user.getUsername());
        feedback.setTitle(map.get("title"));
        feedback.setContent(map.get("content"));
        feedback.setCreatetime(new Date());
        try {
            feedbackService.insertSelective(feedback);
            json.put("msg", "ok");
            json.put("code", 200);
        } catch (Exception e) {
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }

    //用户反馈列表
    @RequestMapping("/app/feedbacklist")
    public String feedbacklist(@RequestBody JSONObject map) {
        JSONObject json = new JSONObject();
        int page = map.getIntValue("page");
        int pagesize = map.getIntValue("pagesize");
        List<Feedback> list = feedbackService.selectByUserKey(getUserKey(), page, pagesize);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonlist = new ArrayList<>();
        for (Feedback feedback : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(feedback));
            jsonObject.put("createtime", format.format(feedback.getCreatetime()));
            jsonlist.add(jsonObject);
        }
        json.put("data", jsonlist);
        json.put("msg", "ok");
        json.put("code", 200);
        return json.toJSONString();
    }

    @RequestMapping("/app/getReal")
    public String getRealName() {
        Map identitys = usersService.selectIdentityByPrimaryKey(Integer.valueOf(getUserKey()));
        Map result = new HashMap();
        result.put("IsReal", identitys.get("IsReal"));
        result.put("Realname", identitys.get("Realname"));
        result.put("IdentityId", identitys.get("IdentityId"));
        result.put("IdentityImg1", identitys.get("IdentityImg1"));
        result.put("IdentityImg2", identitys.get("IdentityImg2"));
        return JSONObject.toJSONString(new ResultDTO(200, "ok", result));
    }

    @RequestMapping("/app/Real")
    public String setRealName(@RequestBody Map<String, String> map) {
        String realname = map.get("realname");
        String IdentityId = map.get("identityId");
        String IdentityImg1 = map.get("IdentityImg1");
        String IdentityImg2 = map.get("IdentityImg2");
        String courseFile = parametersService.selectByKey("imgbath").getKeyvalue();
        String imgurl1 = BASE64imgUtil.GenerateImage(IdentityImg1, courseFile);
        String imgurl2 = BASE64imgUtil.GenerateImage(IdentityImg2, courseFile);

        //实名认证
        int i = usersService.userreal(Integer.valueOf(getUserKey()), realname, IdentityId, parametersService.selectByKey("imgurl").getKeyvalue() + imgurl1.substring(imgurl1.lastIndexOf("/")), parametersService.selectByKey("imgurl").getKeyvalue() + imgurl2.substring(imgurl2.lastIndexOf("/")));
        if (i > 0) {
            Users user = usersService.selectByPrimaryKey(Integer.valueOf(getUserKey()));
            redisDao.setKey(user.getKeyes(), JSON.toJSONString(user), 600);
            return JSONObject.toJSONString(new ResultDTO(200, "ok"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "failure"));
    }
}
