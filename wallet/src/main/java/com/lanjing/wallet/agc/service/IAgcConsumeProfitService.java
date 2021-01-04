package com.lanjing.wallet.agc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanjing.wallet.agc.domain.AgcConsumeProfit;
import com.lanjing.wallet.agc.domain.AgcCpProfit;

/**
 * 用户消费分佣记录Service接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface IAgcConsumeProfitService 
{
    /**
     * 查询用户消费分佣记录
     * 
     * @param id 用户消费分佣记录ID
     * @return 用户消费分佣记录
     */
    public AgcConsumeProfit selectAgcConsumeProfitById(Long id);

    /**
     * 查询用户消费分佣记录列表
     * 
     * @param agcConsumeProfit 用户消费分佣记录
     * @return 用户消费分佣记录集合
     */
    public List<AgcConsumeProfit> selectAgcConsumeProfitList(AgcConsumeProfit agcConsumeProfit);

    /**
     * 新增用户消费分佣记录
     * 
     * @param agcConsumeProfit 用户消费分佣记录
     * @return 结果
     */
    public int insertAgcConsumeProfit(AgcConsumeProfit agcConsumeProfit);

    /**
     * 修改用户消费分佣记录
     * 
     * @param agcConsumeProfit 用户消费分佣记录
     * @return 结果
     */
    public int updateAgcConsumeProfit(AgcConsumeProfit agcConsumeProfit);

    /**
     * 批量删除用户消费分佣记录
     * 
     * @param ids 需要删除的用户消费分佣记录ID
     * @return 结果
     */
    public int deleteAgcConsumeProfitByIds(Long[] ids);

    /**
     * 删除用户消费分佣记录信息
     * 
     * @param id 用户消费分佣记录ID
     * @return 结果
     */
    public int deleteAgcConsumeProfitById(Long id);

    /**今日收益
     * now 时间
     * userId 用户ID
     * */
	public BigDecimal selectDayIncomeByNowAndUserId(Date now, String userId);
	
	/**
     * 累计收益
     * userId 用户ID
     * */
	public BigDecimal selectDayIncomeByUserId(String userId);

	/**
	 * 我的推广
	 * startTime 查询范围开始时间 
	 * endTime 查询范围结束时间
	 */
	public Map getUserInvite(String string, String startTime, String endTime);

	//推广直推下级分佣获得AGC列表-分页{时间范围}
	public List<AgcConsumeProfit> getUserInviteAgcDirectList(String userKey, String startTime, String endTime);
	
	//推广间推下级分佣获得AGC列表-分页{时间范围}
	public List<AgcConsumeProfit> getUserInviteAgcIndirectList(String userKey, String startTime, String endTime);
}
