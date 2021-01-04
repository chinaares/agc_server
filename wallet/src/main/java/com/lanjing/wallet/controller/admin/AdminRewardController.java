package com.lanjing.wallet.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.enums.IncomeEnum;
import com.lanjing.wallet.model.po.AdminReward;
import com.lanjing.wallet.service.AirdropRewardService;
import com.lanjing.wallet.service.IncomeService;
import com.lanjing.wallet.service.InformationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 奖励列表
 */
@RestController
@CrossOrigin(value = "*")
public class AdminRewardController {

    @Resource(name = "IncomeService")
    IncomeService incomeService;

    @Resource(name = "InformationService")
    InformationService informationService;

    @Resource(name = "AirdropRewardService")
    AirdropRewardService airdropRewardService;

    /**
     * 获取EXC奖励
     * @param param
     * @return
     */
    @RequestMapping("/admin/getRewardList")
    public Object getRewardList(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String phone = param.getString("phone");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<AdminReward> adminRewards =informationService.getRewardList(phone);
        PageInfo<AdminReward> pageInfo = new PageInfo<>(adminRewards);

        return Result.success(pageInfo);
    }

    /**
     * 奖励统计
     * @return
     */
    @RequestMapping("/admin/rewardStatistics")
    public Object rewardStatistics() {
        /**EXC奖励总数*/
        Double exc=airdropRewardService.sumTotalRewards(ConfigConst.TOTAL);
        //分享奖励
        Double share=incomeService.reward(IncomeEnum.SHARE.getCode());
        //社区奖励
        Double community=incomeService.reward(IncomeEnum.COMMUNITY.getCode());
        //超级社区奖励
        Double superCommunity=incomeService.reward(IncomeEnum.SUPER.getCode());

        HashMap hashMap=new HashMap();
        hashMap.put("exc",exc);
        hashMap.put("share",share);
        hashMap.put("community",community);
        hashMap.put("superCommunity",superCommunity);
        return Result.success(hashMap);
    }
}
