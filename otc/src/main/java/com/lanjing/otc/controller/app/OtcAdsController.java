package com.lanjing.otc.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.DateUtil;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.otc.config.Config;
import com.lanjing.otc.controller.BaseController;
import com.lanjing.otc.model.*;
import com.lanjing.otc.model.po.Ads;
import com.lanjing.otc.model.po.Wallet;
import com.lanjing.otc.service.*;
import com.lanjing.otc.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广告模块
 *
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 14:35
 */
@RestController
@SuppressWarnings("all")
public class OtcAdsController extends BaseController {

    @Autowired
    OtcAdsService otcAdsService;

    @Autowired
    UsersService usersService;

    @Autowired
    WalletsService walletsService;

    @Autowired
    PayWayService payWayService;

    @Autowired
    UnderwriterService underwriterService;

    @Autowired
    RedisDao redisDao;

    /**
     * 发布广告
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/otc/ads/addAds")
    public Object addAds(@RequestBody JSONObject param) {
        OtcAds otcAds = JSONObject.toJavaObject(param, OtcAds.class);
        String password = param.getString("password");
        Integer userId = getUserId();
        if (userId == null || StringUtils.isEmpty(password)) {
            return Result.error("参数错误");
        }

        Users user = usersService.selectByPrimaryKey(userId);

        Integer isReal = user.getIsreal();

        //实名认证
        if (isReal == null || isReal != 2) {
            return Result.error(202, "请待实名通过");
        }

        //收款方式设置
        int count = payWayService.countByUserId(getUserId());
        if (count < 1) {
            return Result.error(203, "请设置收款方式");
        }

        //承兑商
        Underwriter underwriter = underwriterService.findByUserId(getUserId());
        if (underwriter == null) {
            return Result.error(204, "请申请承兑商");
        }

        //0 待审核，1审核通过，2审核拒绝
        if (underwriter.getStatus() == 0) {
            return Result.error("请等待承兑商审核");
        }
        if (underwriter.getStatus() == 2) {
            return Result.error("承兑商审核未通过");
        }

        String key=String.format("%s%s", Config.KEY_PASS,userId);

        Boolean hasKey = redisDao.hasKey(key);

        if (hasKey) {
            Integer num = Integer.valueOf(redisDao.getValue(key));
            //每日限制交易密码只能错误三次
            if (num > 3) {
                return Result.error("交易密码错误超过3次,请明日再试");
            }
        }

        if (!password.equals(user.getTranspassword())) {
            redisDao.increment(key);
            if (!hasKey){
                //初始化过期时间
                redisDao.setTime(key, DateUtil.nextDay()/1000);
            }
            return Result.error("交易密码错误");
        }

        //密码正确，清除错误密码累计
        if (hasKey){
            redisDao.remove(key);
        }

        try {


            otcAdsService.addAds(otcAds, userId);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     * 获取承兑商状态
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/otc/ads/getUnderwriter")
    public Object getUnderwriter() {
        //承兑商
        Underwriter underwriter = underwriterService.findByUserId(getUserId());
        if (underwriter == null) {
            return Result.error(203);
        }
        return Result.success(underwriter);
    }

    /**
     * 取消广告
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/otc/ads/cancelAds")
    public Object cancelAds(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer userId = getUserId();
        if (userId == null) {
            return Result.error("获取登陆信息错误");
        }

        if (id == null) {
            return Result.error("参数错误");
        }

        try {
            otcAdsService.cancelAds(id, userId);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

    /**
     * 用户广告
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/otc/ads/adsList")
    public Object adsList(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer id = param.getInteger("id");

        Integer userId = getUserId();

        if (pageSize == null || pageNum == null || id==null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<OtcAds> otcAds = otcAdsService.selectAll(userId,id);
        PageInfo<OtcAds> pageInfo = new PageInfo<>(otcAds);
        return Result.success(pageInfo);
    }



    /**
     * 用户广告
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/otc/ads/adsLists")
    public Object adsLists(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer id = param.getInteger("id");

        Integer userId = getUserId();

        if (pageSize == null || pageNum == null || id==null) {
            return Result.error("参数错误");
        }

        Wallets wallet = walletsService.selectByUserandcoin(getUserKey(), id);

        PageHelper.startPage(pageNum, pageSize);
        List<OtcAds> otcAds = otcAdsService.selectAll(userId,id);
        PageInfo<OtcAds> pageInfo = new PageInfo<>(otcAds);
        return Result.success(wallet,pageInfo);
    }

    /**
     * 买入卖出列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/otc/ads/adsBuyOrSell")
    public Object adsBuyOrSell(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer type = param.getInteger("type");
        Integer coinId = param.getInteger("coinId");

        String userKey = getUserKey();

        if (StringUtils.isEmpty(userKey) || pageSize == null || pageNum == null || type == null) {
            return Result.error("参数错误");
        }

        WalletsExample walletsExample = new WalletsExample();
        WalletsExample.Criteria criteria = walletsExample.createCriteria();
        criteria.andUserkeyEqualTo(userKey);
        criteria.andCoinidEqualTo(coinId);

        List<Wallets> wallets = walletsService.selectByExample(walletsExample);

        double balance = 0;
        double freeze = 0;
        if (wallets.size() > 0) {
            Wallets wallet = wallets.get(0);
            //可用余额
            balance = wallet.getCoinnum();
            //冻结
            freeze = wallet.getFrozennum();
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Ads> otcAds = otcAdsService.selectAdsBuyOrSell(coinId, type);
        PageInfo<Ads> pageInfo = new PageInfo<>(otcAds);

        JSONObject result = new JSONObject();
        result.put("freeze", freeze);
        result.put("balance", balance);
        result.put("pageInfo", pageInfo);
        return Result.success(result);
    }

    /**
     * 获取钱包信息
     */

    @RequestMapping("/app/otc/walletInformation")
    public Object walletInformation() {
        String userKey = getUserKey();

        if (StringUtils.isEmpty(userKey)) {
            return Result.error("获取登陆信息失败");
        }
        List<Wallet> wallets = walletsService.selectByUserId(userKey);
        return Result.success(wallets);
    }
}
