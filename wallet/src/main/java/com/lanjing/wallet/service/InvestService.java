package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Invest;
import com.lanjing.wallet.model.po.IncomeInterest;
import com.lanjing.wallet.model.po.InvestCheck;
import com.lanjing.wallet.model.po.InvestReward;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface InvestService {

    /**
     * 通过id或者状态查询理财记录
     * @param id
     * @param status
     * @return
     */
    List<Invest> selectAllOrByPrimaryKeyOrStatusOrUserId(Integer id, Integer status, Integer userId);


    List<Invest> selectAllOrByPrimaryKeyOrStatusesOrUserIds(Integer id, List<Integer> status,List<Integer> userId);

    /**
     * 查询理财释放信息
     * @return
     */
    List<IncomeInterest> selectIncomeInterest();

    /**
     * 获取提前出仓信息
     * @return
     * @param phone
     * @param status
     */
    List<InvestCheck> getInvestCheck(String phone, Integer status);

    /**
     * 提前出仓审核
     * @param id
     * @param status true 通过,false 不通过
     * @return
     */
    Object investCheckReview(Integer id, Boolean status);

    /**
     * 查询理财记录
     * @param phone
     * @return
     */
    List<InvestCheck> getInvest(String phone);

    /**
     * /参与理财数量
     * @return
     */
    BigDecimal sumPrincipal();

    /**
     * 待收益总量
     * @return
     */
    BigDecimal sumRevenue();

    /**
     * 累计释放总量
     * @return
     */
    BigDecimal sumTotalRelease();

    /**
     * 今日新增理财
     *
     * @param today
     * @param date
     * @return
     */
    BigDecimal sumTodayPrincipal(Date today, Date date);

    /**
     * 查询理财奖励
     * @param userId
     * @return
     */
    List<InvestReward> selectByUserId(Integer userId);
}
