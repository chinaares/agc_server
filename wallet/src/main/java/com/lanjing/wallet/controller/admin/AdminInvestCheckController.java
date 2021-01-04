package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jester.util.utils.DateUtil;
import com.jester.util.utils.Result;
import com.lanjing.wallet.enums.InvestEnum;
import com.lanjing.wallet.model.Invest;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.po.InvestCheck;
import com.lanjing.wallet.service.AirdropService;
import com.lanjing.wallet.service.InvestService;
import com.lanjing.wallet.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class AdminInvestCheckController {

    @Resource(name = "InvestService")
    private InvestService investService;

    @Resource(name = "AirdropService")
    private AirdropService airdropService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    /**
     * 查询提前出仓信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/getInvestCheck")
    public Object getInvestCheck(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        String phone = param.getString("phone");
        Integer status = param.getInteger("status");

        PageHelper.startPage(pageNum, pageSize);
        List<InvestCheck> list = investService.getInvestCheck(phone, status);
        PageInfo<InvestCheck> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询理财记录
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/getInvest")
    public Object getInvest(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        String phone = param.getString("phone");

        PageHelper.startPage(pageNum, pageSize);
        List<InvestCheck> list = investService.getInvest(phone);
        PageInfo<InvestCheck> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    /**
     * 提前出仓审核
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/investCheckReview")
    public Object investCheckReview(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer id = param.getInteger("id");
        Boolean status = param.getBoolean("status");

        if (id == null || status == null) {
            return Result.error("参数错误");
        }

        List<Invest> invests = investService.selectAllOrByPrimaryKeyOrStatusOrUserId(id, null, null);
        if (invests.size() < 1) {
            return Result.error("未找到该记录");
        }

        Invest invest = invests.get(0);

        if (invest == null) {
            return Result.error("未找到该记录");
        }

        if (InvestEnum.ABORT.getVal() != invest.getStatus().intValue()) {
            return Result.error("该记录不可以审核");
        }

        try {
            return investService.investCheckReview(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 添加理财
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/inAirdrops")
    public Object inAirdrops(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer id = param.getInteger("id");//理财信息id
        String phone = param.getString("phone");//手机号码
        String password = param.getString("password");//手机号码
        Double principal = param.getDouble("principal");//理财本金

        if (id == null || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || principal == null) {
            return Result.error("参数错误");
        }

        Users user = usersService.selectByPhone(phone);

        if (user == null) {
            return Result.error("未找到用户信息");
        }

        if (password.equals(user.getTranspassword())) {
            return Result.error("密码错误");
        }

        try {
            //return airdropService.invest(user, Integer.valueOf(id), Double.valueOf(principal));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 理财统计
     * @return
     */
    @RequestMapping("/admin/airdropStatistics")
    public Object airdropStatistics() {
        Date today = DateUtil.today();
        Date tomorrow = DateUtil.tomorrow();

        //参与理财数量
        BigDecimal totalPrincipal= investService.sumPrincipal();
        //待收益总量
        BigDecimal revenue= investService.sumRevenue();
        //累计释放总量
        BigDecimal totalRelease= investService.sumTotalRelease();
        //今日新增理财
        BigDecimal todayPrincipal= investService.sumTodayPrincipal(today,tomorrow);

        //注册用户数
        BigDecimal totalUser= usersService.sumTotalUser();
        //有效用户数
        BigDecimal effectiveUser= usersService.sumEffectiveUser();
        //今日新增用户
        BigDecimal todayUser= usersService.sumTodayUser(today,tomorrow);


        JSONObject result=new JSONObject();
        result.put("revenue",revenue);
        result.put("totalRelease",totalRelease);
        result.put("todayPrincipal",todayPrincipal);
        result.put("totalPrincipal",totalPrincipal);
        result.put("totalUser",totalUser);
        result.put("effectiveUser",effectiveUser);
        result.put("todayUser",todayUser);

        return Result.success(result);
    }
}
