package com.lanjing.wallet.agc.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.lanjing.wallet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.lanjing.wallet.agc.mapper.AgcCpProfitMapper;
import com.lanjing.wallet.agc.domain.AgcCpProfit;
import com.lanjing.wallet.agc.service.IAgcCpProfitService;
import com.lanjing.wallet.agc.vo.MiningCpProfitVO;

/**
 * 算力分佣记录Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcCpProfitServiceImpl implements IAgcCpProfitService 
{
    @Autowired
    private AgcCpProfitMapper agcCpProfitMapper;

    /**
     * 查询算力分佣记录
     * 
     * @param id 算力分佣记录ID
     * @return 算力分佣记录
     */
    @Override
    public AgcCpProfit selectAgcCpProfitById(Long id)
    {
        return agcCpProfitMapper.selectAgcCpProfitById(id);
    }

    /**
     * 查询算力分佣记录列表
     * 
     * @param agcCpProfit 算力分佣记录
     * @return 算力分佣记录
     */
    @Override
    public List<AgcCpProfit> selectAgcCpProfitList(AgcCpProfit agcCpProfit)
    {
        return agcCpProfitMapper.selectAgcCpProfitList(agcCpProfit);
    }

    /**
     * 新增算力分佣记录
     * 
     * @param agcCpProfit 算力分佣记录
     * @return 结果
     */
    @Override
    public int insertAgcCpProfit(AgcCpProfit agcCpProfit)
    {
        agcCpProfit.setCreateTime(DateUtils.getNowDate());
        return agcCpProfitMapper.insertAgcCpProfit(agcCpProfit);
    }

    /**
     * 修改算力分佣记录
     * 
     * @param agcCpProfit 算力分佣记录
     * @return 结果
     */
    @Override
    public int updateAgcCpProfit(AgcCpProfit agcCpProfit)
    {
        agcCpProfit.setUpdateTime(DateUtils.getNowDate());
        return agcCpProfitMapper.updateAgcCpProfit(agcCpProfit);
    }

    /**
     * 批量删除算力分佣记录
     * 
     * @param ids 需要删除的算力分佣记录ID
     * @return 结果
     */
    @Override
    public int deleteAgcCpProfitByIds(Long[] ids)
    {
        return agcCpProfitMapper.deleteAgcCpProfitByIds(ids);
    }

    /**
     * 删除算力分佣记录信息
     * 
     * @param id 算力分佣记录ID
     * @return 结果
     */
    @Override
    public int deleteAgcCpProfitById(Long id)
    {
        return agcCpProfitMapper.deleteAgcCpProfitById(id);
    }

    /**
     * 激活下级5代未激活的算力
     * 
     * @param userId 用户ID
     */
	public void activationEra5AgcCpProfit(String userId) {
		agcCpProfitMapper.activationEra5AgcCpProfit(userId);
	}

	/**
     * 查询用户邀请算力
     * 
     * @return 结果
     */
	@Override
	public List<MiningCpProfitVO> selectMiningCpProfitVOs() {
		return agcCpProfitMapper.selectMiningCpProfitVOs();
	}

	/**
	 * 总的邀请算力
	 */
	@Override
	public BigDecimal selectAllProfitCp() {
		return agcCpProfitMapper.selectAllProfitCp();
	}

	/**我的邀请算力*/
	@Override
	public BigDecimal selectProfitCpByUserId(String userId) {
		return agcCpProfitMapper.selectProfitCpByUserId(userId);
	}

	/**获得激活的直推算力*/
	@Override
	public BigDecimal selectInviteCpByUserIdAndTime1(String userId, String startTime, String endTime) {
		return agcCpProfitMapper.selectInviteCpByUserIdAndTime1(userId,startTime,endTime);
	}

	/**获得激活的间推算力*/
	@Override
	public BigDecimal selectInviteCpByUserIdAndTime2(String userId, String startTime, String endTime) {
		return agcCpProfitMapper.selectInviteCpByUserIdAndTime2(userId,startTime,endTime);
	}

	// 直推算力分佣记录
	@Override
	public List<AgcCpProfit> getUserInviteCpProfitDirectList(String userId, String startTime, String endTime) {
		return agcCpProfitMapper.getUserInviteCpProfitDirectList(userId,startTime,endTime);
	}

	// 间推分佣算力记录
	@Override
	public List<AgcCpProfit> getUserInviteCpProfitIndirectList(String userId, String startTime, String endTime) {
		return agcCpProfitMapper.getUserInviteCpProfitIndirectList(userId,startTime,endTime);
	}
}
