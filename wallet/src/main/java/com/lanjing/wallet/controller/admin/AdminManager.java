package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.DateUtil;
import com.jester.util.utils.DoubleUtil;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.model.po.EmptyRecord;
import com.lanjing.wallet.model.po.FinancialRecord;
import com.lanjing.wallet.model.po.RewardBonus;
import com.lanjing.wallet.model.po.Verified;
import com.lanjing.wallet.service.*;
import com.lanjing.wallet.util.RedisDao;
import com.lanjing.wallet.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 后台管理
 */
@RestController
@CrossOrigin(value = "*")
@SuppressWarnings("all")
public class AdminManager {
    private static Logger logger = LoggerFactory.getLogger(AdminManager.class);

    @Resource(name = "TradeAirdropService")
    private TradeAirdropService tradeAirdropService;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @Resource(name = "InformationService")
    private InformationService informationService;

    @Resource(name = "IncomeService")
    private IncomeService incomeService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource(name = "AirdropListService")
    private AirdropListService airdropListService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private InvestMapper investMapper;

    @Autowired
    private AirdropMapper airdropMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private TradeAirdropMapper tradeAirdropMapper;

    @Autowired
    private AirdropListMapper airdropListMapper;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private WelletsMapper walletMapper;

    @Autowired
    private WellethistoryStateMapper walletHistoryMapper;

    /**
     * 查询banner
     *
     * @return
     */
    @RequestMapping("/admin/getBanner")
    public Object getBanner() {
        //查询banner
        List<Parameters> banner_zh = parametersService.selectByType(1);
        List<Parameters> banner_en = parametersService.selectByType(2);
        List<Parameters> banner_jp = parametersService.selectByType(4);
        JSONObject result = new JSONObject();
        result.put("banner_zh", banner_zh);
        result.put("banner_en", banner_en);
        result.put("banner_jp", banner_jp);
        return Result.success(result);
    }

    /**
     * 删除banner
     *
     * @return
     */
    @RequestMapping("/admin/delBanner")
    public Object delBanner(@RequestBody JSONObject param) {
        String key = param.getString("key");

        if (StringUtils.isEmpty(key)) {
            return Result.error("参数错误");
        }

        ParametersWithBLOBs parametersWithBLOBs = parametersService.findByKey(key);

        if (parametersWithBLOBs == null) {
            return Result.error("key不存在");
        }

        int result = parametersService.deleteByPrimaryKey(parametersWithBLOBs.getFid());
        if (result < 1) {
            return Result.error("删除失败");
        }
        return Result.success(result);
    }

    /**
     * 添加banner
     *
     * @return
     */
    @RequestMapping("/admin/addBanner")
    public Object addBanner(@RequestBody JSONObject param) {
        String key = param.getString("key");
        String value = param.getString("value");
        Integer type = param.getInteger("type");

        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || type == null) {
            return Result.error("参数错误");
        }

        ParametersWithBLOBs parametersWithBLOBs = parametersService.findByKey(key);

        if (parametersWithBLOBs != null) {
            return Result.error("key已存在");
        }

        ParametersWithBLOBs parameters = new ParametersWithBLOBs();
        parameters.setKeyvalue(value);
        parameters.setKeyname(key);
        parameters.setType(type);

        String remark;

        if (type == 1)
            remark = "中文";
        else if (type == 2)
            remark = "英文";
        else if (type == 4)
            remark = "日文";
        else
            return Result.error("type类型不存在");

        parameters.setRemark("banner图"+remark);
        int result = parametersService.insertSelective(parameters);

        if (result < 1) {
            return Result.error("添加失败");
        }

        return Result.success(result);
    }

    /**
     * 更新banner
     *
     * @return
     */
    @RequestMapping("/admin/updateBannerByKey")
    public Object updateBannerByKey(@RequestBody JSONObject param) {
        String key = param.getString("key");
        String value = param.getString("value");

        ParametersWithBLOBs parametersWithBLOBs = parametersService.findByKey(key);

        if (parametersWithBLOBs == null) {
            return Result.error("key不存在");
        }

        ParametersWithBLOBs parameters=new ParametersWithBLOBs();
        parameters.setFid(parametersWithBLOBs.getFid());
        parameters.setKeyvalue(value);

        int result = parametersService.updateByPrimaryKeySelective(parameters);

        if (result < 1) {
            return Result.error("更新失败");
        }

        return Result.success(result);
    }


    /**
     * 更新banner
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/admin/updateBanner")
    public Object updateBanner(HttpServletRequest request, Integer id) {
        List<MultipartFile> multipartFiles;
        try {
            multipartFiles = UploadUtil.getFileSet(request, 1024 * 1024 * 2, null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        String path = parametersService.selectByKey("imgbath").getKeyvalue();
        if (multipartFiles.size() == 0) {
            return Result.error("请选择图片");
        }

        if (id == null) {
            return Result.error("参数错误");
        }
        try {
            String filePath = UploadUtil.uploadFile(multipartFiles.get(0), path, parametersService.selectByKey("imgurl").getKeyvalue());
            String bannerKey = String.format(ConfigConst.BANNER, id);
            int result = parametersService.updateByKeyName(bannerKey, filePath);
            if (result < 1) {
                return Result.error("更新失败");
            }
            return Result.success(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 获取公告
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/announcement")
    public Object announcement(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer type = param.getInteger("type");
        Integer id = param.getInteger("id");
        String title = param.getString("title");

        if (id != null) {
            List<Information> information = informationService.selectByTypeOrTitle(id, null, null);
            return Result.success(information);
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Information> information = informationService.selectByTypeOrTitle(id, type, title);
        PageInfo<Information> pageInfo = new PageInfo<>(information);

        return Result.success(pageInfo);
    }

    /**
     * 平台设置列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/platform")
    public Object platform(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Parameters> parameters = parametersService.selectByType(null);
        PageInfo<Parameters> pageInfo = new PageInfo<>(parameters);

        return Result.success(pageInfo);
    }

    /**
     * 平台设置列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/platform/selectById")
    public Object selectById(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("参数错误");
        }

        ParametersWithBLOBs parametersWithBLOBs = parametersService.selectByPrimaryKey(id);
        return Result.success(parametersWithBLOBs);
    }

    /**
     * 平台设置列表
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/admin/platform/updateParameters")
    public Object updateParameters(@RequestBody ParametersWithBLOBs parameters) {
        Integer id = parameters.getFid();

        if (id == null) {
            return Result.error("参数错误");
        }

        int i = parametersService.updateByPrimaryKeySelective(parameters);
        if (i < 1) {
            return Result.error("更新失败");
        }
        return Result.success();
    }

    /**
     * 理财奖励
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/financial/reward")
    public Object financialReward(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String key = param.getString("key");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        //2直推、3节点团队分红
        PageHelper.startPage(pageNum, pageSize);
        List<RewardBonus> rewardBonuses = incomeService.selectFinancialReward(key);
        PageInfo<RewardBonus> pageInfo = new PageInfo<>(rewardBonuses);
        return Result.success(pageInfo);
    }

    /**
     * 空投推广奖励
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/promotion/reward")
    public Object promotion(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String key = param.getString("key");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        //5推广奖励
        PageHelper.startPage(pageNum, pageSize);
        List<RewardBonus> rewardBonuses = incomeService.selectPromotionReward(key);
        PageInfo<RewardBonus> pageInfo = new PageInfo<>(rewardBonuses);
        return Result.success(pageInfo);
    }

    /**
     * 空投记录
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/empty/record")
    public Object emptyList(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String key = param.getString("key");
        Integer type = param.getInteger("type");
        Integer count = param.getInteger("count");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        Date start = DateUtil.today();
        Date end = DateUtil.tomorrow();

        //空投记录
        PageHelper.startPage(pageNum, pageSize);
        List<EmptyRecord> rewardBonuses = incomeService.selectEmptyRecord(key, type, count, start, end);
        PageInfo<EmptyRecord> pageInfo = new PageInfo<>(rewardBonuses);
        return Result.success(pageInfo);
    }

    /**
     * 空投记录激活
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/empty/activation")
    public Object activation(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("参数错误");
        }

        Date start = DateUtil.today();
        Date end = DateUtil.tomorrow();

        TradeAirdrop tradeAirdrop = tradeAirdropMapper.selectByPrimaryKey(id);

        if (tradeAirdrop == null) {
            return Result.error("id不存在");
        }

        if (tradeAirdrop.getType() != 2) {
            return Result.error("只能激活配额记录");
        }

        IncomeExample incomeExample = new IncomeExample();
        IncomeExample.Criteria criteria = incomeExample.createCriteria();
        criteria.andInvestIdEqualTo(id);
        criteria.andCreateTimeBetween(start, end);
        List<Income> incomes = incomeMapper.selectByExample(incomeExample);
        if (incomes.size() > 0) {
            return Result.error("已激活");
        }


        Integer airdropId = tradeAirdrop.getId();
        //数量
        Double amount = tradeAirdrop.getAmount();
        //累计释放
        Double total = tradeAirdrop.getTotal();
        //释放天数
        Integer days = tradeAirdrop.getPeriodsNum();
        //锁仓余额
        Double lockBalance = tradeAirdrop.getLockBalance();

        //今日释放数
        double release = DoubleUtil.toShort(amount * (1.0 / days));

        int result;

        try {
            if (amount < total + release) {
                //判断是否超额释放
                result = tradeAirdropService.dailyRelease(amount - lockBalance, tradeAirdrop, true);
                logger.info(String.format("记录%d释放超额,已修正释放完成", airdropId));
            } else if (amount == (total + release)) {
                result = tradeAirdropService.dailyRelease(release, tradeAirdrop, true);
                logger.info(String.format("记录%d释放完成", airdropId));
            } else {
                result = tradeAirdropService.dailyRelease(release, tradeAirdrop, false);
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        if (result < 1) {
            return Result.error("激活失败");
        }
        return Result.success();
    }

    /**
     * 获取管理员用户
     *
     * @return
     */
    @RequestMapping("/admin/getAdmin")
    public Object getAdmin() {
        //List<Administrator> admins = adminMapper.selectAll();
        List<Admin> admins = adminMapper.selectList();
        return Result.success(admins);
    }


    /**
     * 设置管理员权限
     *
     * @return
     */
    @RequestMapping("/admin/updateAdmin")
    public Object updateAdmin(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer purview = param.getInteger("purview");
        if (id == null || purview == null) {
            return Result.error("参数错误");
        }

        Admin admin = adminMapper.selectByPrimaryKey(id);

        if (admin == null) {
            return Result.error("管理员id不存在");
        }

        int result = adminMapper.updatePurviewByPrimaryKey(id, purview);

        if (result < 1) {
            return Result.error("更新失败");
        }

        return Result.success();
    }

    /**
     * 添加管理员
     *
     * @return
     */
    @RequestMapping("/admin/addAdmins")
    public Object getAdmin(@RequestBody Admin admin) {
        admin.setFid(null);
        Date date = new Date();
        admin.setState("1");
        admin.setArole(1);
        admin.setCreatetime(date);
        int insert = adminMapper.insert(admin);
        if (insert < 1) {
            return Result.error("添加失败");
        }
        return Result.success();
    }

    /**
     * 修改管理员
     *
     * @return
     */
    @RequestMapping("/admin/updateAdmins")
    public Object updateAdmin(@RequestBody Admin admin) {
        Integer fid = admin.getFid();
        if (fid == null) {
            return Result.error("管理员id为空");
        }
        Admin selectByPrimaryKey = adminMapper.selectByPrimaryKey(fid);
        if (selectByPrimaryKey == null) {
            return Result.error("管理员id不存在");
        }

        int insert = adminMapper.updateByPrimaryKeySelective(admin);
        if (insert < 1) {
            return Result.error("修改失败");
        }
        return Result.success();
    }

    /**
     * 删除管理员
     *
     * @return
     */
    @RequestMapping("/admin/delAdmin")
    public Object delAdmin(@RequestBody JSONObject param) {
        Integer fid = param.getInteger("id");
        if (fid == null) {
            return Result.error("管理员id为空");
        }
        Admin selectByPrimaryKey = adminMapper.selectByPrimaryKey(fid);
        if (selectByPrimaryKey == null) {
            return Result.error("管理员id不存在");
        }

        int insert = adminMapper.deleteByPrimaryKey(fid);
        if (insert < 1) {
            return Result.error("删除失败");
        }
        return Result.success();
    }

    /**
     * 理财记录
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/financialRecord")
    public Object financialRecord(@RequestBody JSONObject param) {
        String key = param.getString("key");
        Integer status = param.getInteger("status");

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<FinancialRecord> list = airdropListMapper.selectByKeyAndStatus(status, key);
        PageInfo<FinancialRecord> pageInfo = PageInfo.of(list);

        return Result.success(pageInfo);
    }

    /**
     * 本周到期理财记录
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/maturityfFinancialRecord")
    public Object maturityfFinancialRecord(@RequestBody JSONObject param) {
        String key = param.getString("key");
        Integer status = param.getInteger("status");

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        Integer week = 7 - DateUtil.getWeek();

        PageHelper.startPage(pageNum, pageSize);
        List<FinancialRecord> list = airdropListMapper.maturityFinancialRecord(week, status, key);
        PageInfo<FinancialRecord> pageInfo = PageInfo.of(list);

        return Result.success(pageInfo);
    }

    /**
     * 理财记录-删除
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/delFinancialRecord")
    public Object delFinancialRecord(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("参数错误");
        }

        int result = airdropListMapper.deleteByPrimaryKey(id);

        if (result<1){
            return Result.error("删除失败");
        }
        return Result.success();
    }



    /**
     * 后台添加用户参与理财
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/addAirdropList")
    public Object addAirdropList(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        String phone = param.getString("phone");

        if (id == null || StringUtils.isEmpty(phone)) {
            return Result.error("参数不存在");
        }

        Airdrop airdrop = airdropMapper.selectByPrimaryKey(id);

        if (airdrop == null) {
            return Result.error("Id不存在");
        }

        Users user = usersMapper.selectByPhone(phone);

        if (user == null) {
            return Result.error("手机号码不存在");
        }

        Integer userId = user.getFid();



        Date date = new Date();

        AirdropList airdropList = new AirdropList();
        airdropList.setId(airdrop.getId());
        airdropList.setUserId(userId);
        airdropList.setCycle(airdrop.getCycle());
        airdropList.setAmount(airdrop.getAmount());
        airdropList.setFreezeBtc(airdrop.getFreezeBtc());
        airdropList.setFreezeYyb(airdrop.getFreezeYyb());
        if(airdrop.getFreeBtc().doubleValue()<0){
            return Result.error("free btc not enough.");

        }
        airdropList.setFreeBtc(airdrop.getFreeBtc());
        airdropList.setFreeYyb(airdrop.getFreeYyb());
        airdropList.setFreedBtc(airdrop.getFreedBtc());
        airdropList.setFreedYyb(airdrop.getFreedYyb());
        airdropList.setCreateTime(date);
        airdropList.setUpdateTime(date);

        //添加记录
        int result;
        try {
            result = airdropListService.invest(airdropList,user.getKeyes());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        if (result<1){
            return Result.error("添加失败");
        }

        return Result.success();
    }

    /**
     * 理财配置
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/financialAllocation")
    public Object financialAllocation(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        AirdropExample example = new AirdropExample();
        example.setOrderByClause("id desc");
        AirdropExample.Criteria criteria = example.createCriteria();
        criteria.andEnableNotEqualTo(0);

        PageHelper.startPage(pageNum, pageSize);
        List<Airdrop> list = airdropMapper.selectByExample(example);
        PageInfo<Airdrop> pageInfo = PageInfo.of(list);

        return Result.success(pageInfo);
    }

    /**
     * 2禁用、 1 启用、 0 删除
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/updateAirdropStatus")
    public Object updateAirdropStatus(@RequestBody JSONObject param) {
        String ids = param.getString("ids");
        Integer enable = param.getInteger("enable");

        if (StringUtils.isEmpty(ids) || enable == null) {
            return Result.error("参数错误");
        }

        //int result = airdropMapper.updateAirdropStatus(ids, enable);

        //if (result < 1) {
        //    return Result.error("操作失败");
        //}

        return Result.success();
    }

    /**
     * 新增激活
     */
    @RequestMapping("/admin/addActivation")
    public Object addActivation(@RequestBody JSONObject param) {
        String phone = param.getString("phone");
        Double amount = param.getDouble("amount");

        if (StringUtils.isEmpty(phone) || amount == null || amount <= 0) {
            return Result.error("参数错误");
        }

        Users user = usersService.selectByPhone(phone);

        if (user == null) {
            return Result.error("未找到用户账户");
        }

        int result = tradeAirdropService.addActivation(user, amount);

        if (result < 1) {
            return Result.error("添加失败");
        }

        return Result.success();
    }

    /**
     * 删除激活
     */
    @RequestMapping("/admin/delActivation")
    public Object delActivation(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("参数错误");
        }

        TradeAirdrop tradeAirdrop = tradeAirdropService.selectByPrimaryKey(id);

        if (tradeAirdrop == null) {
            return Result.error("该id不存在");
        }

        TradeAirdrop updateTradeAirdrop = new TradeAirdrop();
        updateTradeAirdrop.setId(id);
        updateTradeAirdrop.setStatus(3);

        int result = tradeAirdropMapper.updateByPrimaryKeySelective(updateTradeAirdrop);

        if (result < 1) {
            return Result.error("删除失败");
        }

        return Result.success();
    }

    /**
     * 实名认证列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/user/verified")
    public Object verified(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer status = param.getInteger("status");
        String key = param.getString("key");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Verified> verifiedList = usersService.selectByStatusAndKey(status, key);
        PageInfo<Verified> pageInfo = new PageInfo<>(verifiedList);

        return Result.success(pageInfo);
    }

    /**
     * 实名认证审核
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/user/verifiedReview")
    public Object verifiedReview(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");

        if (id == null || status == null || (status != 2 && status != -1)) {
            return Result.error("参数错误");
        }

        Users users = usersMapper.selectByPrimaryKey(id);
        if (users == null) {
            return Result.error("用户不存在");
        }

        Users user = new Users();
        user.setFid(id);
        user.setIsReal(status);

        int result = usersMapper.updateByPrimaryKeySelective(user);
        if (result < 1) {
            return Result.error("审核失败");
        }
        redisDao.setKey(users.getKeyes(), JSON.toJSONString(user),30);
        return Result.success();
    }

    /**
     * 财务管理
     *
     * @return
     */
    @RequestMapping("/admin/financialManagement")
    public Object financialManagement() {
        //平台币种总量 1	ETH、2	YYB、3	BTC、4	integral、6	USDT
        BigDecimal totalBTC = walletMapper.sumTotalCoinNum(3);
        BigDecimal totalETH = walletMapper.sumTotalCoinNum(1);
        BigDecimal totalYYB = walletMapper.sumTotalCoinNum(2);
        BigDecimal totalIntegral = walletMapper.sumTotalCoinNum(4);

        Date today = DateUtil.today();
        Date tomorrow = DateUtil.tomorrow();

        //今日新增币种数量
        BigDecimal newBTC = walletHistoryMapper.sumTotalCoinNum(today, tomorrow, 3, 2);
        BigDecimal newETH = walletHistoryMapper.sumTotalCoinNum(today, tomorrow, 1, 2);
        BigDecimal newYYB = walletHistoryMapper.sumTotalCoinNum(today, tomorrow, 2, 2);
        BigDecimal newIntegral = walletHistoryMapper.sumTotalCoinNum(today, tomorrow, 4, 4);

        //今日转出币种数量
        BigDecimal transferBTC = walletHistoryMapper.sumTotalCoinNum(today, tomorrow, 3, 3);
        BigDecimal transferETH = walletHistoryMapper.sumTotalCoinNum(today, tomorrow, 1, 3);
        BigDecimal transferYYB = walletHistoryMapper.sumTotalCoinNum(today, tomorrow, 2, 3);

        //理财总额
        BigDecimal totalFinances = airdropListMapper.sumTotalCoinNum(null, null);
        BigDecimal newFinances = airdropListMapper.sumTotalCoinNum(today, tomorrow);
        //到期笔数
        Integer maturity = airdropListMapper.countMaturity(7 - DateUtil.getWeek());

        HashMap map=new HashMap();
        map.put("totalBTC",totalBTC);
        map.put("totalETH",totalETH);
        map.put("totalYYB",totalYYB);
        map.put("totalIntegral",totalIntegral);
        map.put("newBTC",newBTC);
        map.put("newETH",newETH);
        map.put("newYYB",newYYB);
        map.put("newIntegral",newIntegral);
        map.put("transferBTC",transferBTC);
        map.put("transferETH",transferETH);
        map.put("transferYYB",transferYYB);
        map.put("totalFinances",totalFinances);
        map.put("newFinances",newFinances);
        map.put("maturity",maturity);

        return Result.success(map);
    }
}
