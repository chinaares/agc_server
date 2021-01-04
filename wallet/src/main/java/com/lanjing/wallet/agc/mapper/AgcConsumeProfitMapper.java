package com.lanjing.wallet.agc.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lanjing.wallet.agc.domain.AgcConsumeProfit;
import com.lanjing.wallet.agc.domain.AgcCpProfit;

/**
 * 用户消费分佣记录Mapper接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface AgcConsumeProfitMapper 
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
     * 删除用户消费分佣记录
     * 
     * @param id 用户消费分佣记录ID
     * @return 结果
     */
    public int deleteAgcConsumeProfitById(Long id);

    /**
     * 批量删除用户消费分佣记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgcConsumeProfitByIds(Long[] ids);

    /**
     * 今日收益
     * now 时间
     * userId 用户ID
     * */
	public BigDecimal selectDayIncomeByNowAndUserId(Date now, String userId);
	
	/**
     * 累计收益
     * userId 用户ID
     * */
	public BigDecimal selectDayIncomeByUserId(String userId);

	/**推广获得Agc-直推*/
	public BigDecimal selectInviteAgcByUserIdAndTime1(String userId, String startTime, String endTime);
	
	/**推广获得Agc-间推*/
	public BigDecimal selectInviteAgcByUserIdAndTime2(String userId, String startTime, String endTime);
	
	/**推广获得Agc列表-直推*/
	public List<AgcConsumeProfit> getUserInviteAgcDirectList(String userId, String startTime, String endTime);
	/**推广获得Agc列表-间推*/
	public List<AgcConsumeProfit> getUserInviteAgcIndirectList(String userId, String startTime, String endTime);
}
