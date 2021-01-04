package com.lanjing.wallet.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jester.util.utils.DoubleTwoUtil;
import com.jester.util.utils.Result;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.model.Income;
import com.lanjing.wallet.model.TradeAirdrop;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.IncomeService;
import com.lanjing.wallet.service.TradeAirdropService;
import com.lanjing.wallet.service.UsersService;
import com.lanjing.wallet.service.WelletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
public class TradeAirdropController extends BaseContrller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "TradeAirdropService")
    private TradeAirdropService tradeAirdropService;

    @Resource(name = "IncomeService")
    private IncomeService incomeService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource(name = "WelletService")
    private WelletService welletService;

    /**
     * 获取购物空投信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/tradeAirdrops/list")
    public Object tradeAirdropsList(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String userKey = getUserKey();


        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        //获取DZCC余额
        Wellets wellets = welletService.selectByUserandcoin(userKey, ConfigConst.DZCC_COIN);
        if (wellets == null) {
            return Result.error("钱包异常");
        }

        //累计消费
        double consume = tradeAirdropService.sumConsume(userKey);
        //我的配额 quotaNum
        BigDecimal quota = wellets.getQuotanum();
        //累计激活
        double activation = tradeAirdropService.sumActivation(userKey);
        //累计释放
        double freed = tradeAirdropService.sumFreed(userKey);

        PageHelper.startPage(pageNum, pageSize);
        List<TradeAirdrop> tradeAirdropList = tradeAirdropService.selectByUserKey(userKey);
        PageInfo<TradeAirdrop> pageInfo = new PageInfo<>(tradeAirdropList);

        HashMap hashMap = new HashMap();
        hashMap.put("quota", DoubleTwoUtil.toShortString(quota));
        hashMap.put("freed", DoubleTwoUtil.toShortString(freed));
        hashMap.put("consume", DoubleTwoUtil.toShortString(consume));
        hashMap.put("activation", DoubleTwoUtil.toShortString(activation));
        hashMap.put("pageInfo", pageInfo);

        return Result.success(hashMap);
    }

    /**
     * 获取空投详情
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/tradeAirdrops/details")
    public Object details(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        TradeAirdrop tradeAirdrop = tradeAirdropService.selectByPrimaryKey(id);

        PageHelper.startPage(pageNum, pageSize);
        List<Income> incomes = incomeService.selectByInvestId(id, null);
        PageInfo<Income> pageInfo = new PageInfo<>(incomes);
        HashMap map = new HashMap();
        map.put("tradeAirdrop", tradeAirdrop);
        map.put("pageInfo", pageInfo);

        return Result.success(map);
    }


}