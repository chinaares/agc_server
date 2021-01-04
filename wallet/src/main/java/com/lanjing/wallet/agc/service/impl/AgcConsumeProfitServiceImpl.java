package com.lanjing.wallet.agc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lanjing.wallet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.lanjing.wallet.agc.mapper.AgcConsumeProfitMapper;
import com.lanjing.wallet.agc.domain.AgcConsumeProfit;
import com.lanjing.wallet.agc.domain.AgcCpProfit;
import com.lanjing.wallet.agc.service.IAgcConsumeProfitService;
import com.lanjing.wallet.agc.service.IAgcCpProfitService;
import com.lanjing.wallet.agc.vo.InviteVO;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.service.RecommendService;

/**
 * 用户消费分佣记录Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcConsumeProfitServiceImpl implements IAgcConsumeProfitService 
{
    @Autowired
    private AgcConsumeProfitMapper agcConsumeProfitMapper;
    
	@Resource(name = "RecommendService")
	private RecommendService recommendService;
	
	@Autowired
    private IAgcCpProfitService agcCpProfitService;

    /**
     * 查询用户消费分佣记录
     * 
     * @param id 用户消费分佣记录ID
     * @return 用户消费分佣记录
     */
    @Override
    public AgcConsumeProfit selectAgcConsumeProfitById(Long id)
    {
        return agcConsumeProfitMapper.selectAgcConsumeProfitById(id);
    }

    /**
     * 查询用户消费分佣记录列表
     * 
     * @param agcConsumeProfit 用户消费分佣记录
     * @return 用户消费分佣记录
     */
    @Override
    public List<AgcConsumeProfit> selectAgcConsumeProfitList(AgcConsumeProfit agcConsumeProfit)
    {
        return agcConsumeProfitMapper.selectAgcConsumeProfitList(agcConsumeProfit);
    }

    /**
     * 新增用户消费分佣记录
     * 
     * @param agcConsumeProfit 用户消费分佣记录
     * @return 结果
     */
    @Override
    public int insertAgcConsumeProfit(AgcConsumeProfit agcConsumeProfit)
    {
        agcConsumeProfit.setCreateTime(DateUtils.getNowDate());
        return agcConsumeProfitMapper.insertAgcConsumeProfit(agcConsumeProfit);
    }

    /**
     * 修改用户消费分佣记录
     * 
     * @param agcConsumeProfit 用户消费分佣记录
     * @return 结果
     */
    @Override
    public int updateAgcConsumeProfit(AgcConsumeProfit agcConsumeProfit)
    {
        agcConsumeProfit.setUpdateTime(DateUtils.getNowDate());
        return agcConsumeProfitMapper.updateAgcConsumeProfit(agcConsumeProfit);
    }

    /**
     * 批量删除用户消费分佣记录
     * 
     * @param ids 需要删除的用户消费分佣记录ID
     * @return 结果
     */
    @Override
    public int deleteAgcConsumeProfitByIds(Long[] ids)
    {
        return agcConsumeProfitMapper.deleteAgcConsumeProfitByIds(ids);
    }

    /**
     * 删除用户消费分佣记录信息
     * 
     * @param id 用户消费分佣记录ID
     * @return 结果
     */
    @Override
    public int deleteAgcConsumeProfitById(Long id)
    {
        return agcConsumeProfitMapper.deleteAgcConsumeProfitById(id);
    }

    /**
     * 今日收益
     * now 时间
     * userId 用户ID
     * */
	@Override
	public BigDecimal selectDayIncomeByNowAndUserId(Date now, String userId) {
		return agcConsumeProfitMapper.selectDayIncomeByNowAndUserId(now,userId);
	}
	
	/**
     * 累计收益
     * userId 用户ID
     * */
	@Override
	public BigDecimal selectDayIncomeByUserId(String userId) {
		return agcConsumeProfitMapper.selectDayIncomeByUserId(userId);
	}

	/**
	 * 我的推广
	 * startTime 查询范围开始时间 
	 * endTime 查询范围结束时间
	 */
	@Override
	public Map getUserInvite(String userId, String startTime, String endTime) {
		Map map = new HashMap();
		InviteVO vo1 = new InviteVO();// 直推数据
		int userTotal1 = 0;// 直推人数
		userTotal1 = recommendService.selectByUserKey2AndTimeCount(userId,startTime,endTime);
		
		BigDecimal inviteCp1 = BigDecimal.ZERO;// 推广获得算力
		inviteCp1 = agcCpProfitService.selectInviteCpByUserIdAndTime1(userId,startTime,endTime);
		
		BigDecimal inviteAgc1 = BigDecimal.ZERO;// 推广获得Agc
		inviteAgc1 = agcConsumeProfitMapper.selectInviteAgcByUserIdAndTime1(userId,startTime,endTime);
		
		vo1.setUserTotal(userTotal1);
		vo1.setInviteCp(inviteCp1);
		vo1.setInviteAgc(inviteAgc1);
		
		InviteVO vo2 = new InviteVO();// 简推数据
		int userTotal2 = 0;// 简推人数
		List<String> userIdList2 = new ArrayList<String>();
		List<Recommend> reList = recommendService.selectAll();
		List<String> userIdList = new ArrayList<String>();
		userIdList = recommendService.selectUserSubordinateList(reList, userIdList, userId ,startTime, endTime,userIdList2);
		userTotal2 = userIdList2.size() - userTotal1;
		
		BigDecimal inviteCp2 = BigDecimal.ZERO;// 简推获得算力
		inviteCp2 = agcCpProfitService.selectInviteCpByUserIdAndTime2(userId,startTime,endTime);
		
		BigDecimal inviteAgc2 = BigDecimal.ZERO;// 简推获得Agc
		inviteAgc2 = agcConsumeProfitMapper.selectInviteAgcByUserIdAndTime2(userId,startTime,endTime);
		
		vo2.setUserTotal(userTotal2);
		vo2.setInviteCp(inviteCp2);
		vo2.setInviteAgc(inviteAgc2);
		
		map.put("invite1", vo1);
		map.put("invite2", vo2);
		return map;
	}

	@Override
	public List<AgcConsumeProfit> getUserInviteAgcDirectList(String userId, String startTime, String endTime) {
		return agcConsumeProfitMapper.getUserInviteAgcDirectList(userId,startTime,endTime);
	}

	@Override
	public List<AgcConsumeProfit> getUserInviteAgcIndirectList(String userId, String startTime, String endTime) {
		return agcConsumeProfitMapper.getUserInviteAgcIndirectList(userId,startTime,endTime);
	}
	
}
