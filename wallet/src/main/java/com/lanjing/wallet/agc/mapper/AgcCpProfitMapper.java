package com.lanjing.wallet.agc.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.lanjing.wallet.agc.domain.AgcCpProfit;
import com.lanjing.wallet.agc.vo.MiningCpProfitVO;

/**
 * 算力分佣记录Mapper接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface AgcCpProfitMapper 
{
    /**
     * 查询算力分佣记录
     * 
     * @param id 算力分佣记录ID
     * @return 算力分佣记录
     */
    public AgcCpProfit selectAgcCpProfitById(Long id);

    /**
     * 查询算力分佣记录列表
     * 
     * @param agcCpProfit 算力分佣记录
     * @return 算力分佣记录集合
     */
    public List<AgcCpProfit> selectAgcCpProfitList(AgcCpProfit agcCpProfit);

    /**
     * 新增算力分佣记录
     * 
     * @param agcCpProfit 算力分佣记录
     * @return 结果
     */
    public int insertAgcCpProfit(AgcCpProfit agcCpProfit);

    /**
     * 修改算力分佣记录
     * 
     * @param agcCpProfit 算力分佣记录
     * @return 结果
     */
    public int updateAgcCpProfit(AgcCpProfit agcCpProfit);

    /**
     * 删除算力分佣记录
     * 
     * @param id 算力分佣记录ID
     * @return 结果
     */
    public int deleteAgcCpProfitById(Long id);

    /**
     * 批量删除算力分佣记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgcCpProfitByIds(Long[] ids);

    /**
     * 激活下级5代未激活的算力
     * 
     * @param userId 用户ID
     */
	public void activationEra5AgcCpProfit(String userId);

	/**
     * 查询用户邀请算力
     * @return 结果
     */
	public List<MiningCpProfitVO> selectMiningCpProfitVOs();

	/**
	 * 总的邀请算力
	 */
	public BigDecimal selectAllProfitCp();

	/**我的邀请算力*/
	public BigDecimal selectProfitCpByUserId(String userId);

	/**获得激活的直推算力*/
	public BigDecimal selectInviteCpByUserIdAndTime1(String userId, String startTime, String endTime);

	/**获得激活的间推算力*/
	public BigDecimal selectInviteCpByUserIdAndTime2(String userId, String startTime, String endTime);

	// 直推算力分佣记录
	public List<AgcCpProfit> getUserInviteCpProfitDirectList(String userId, String startTime, String endTime);

	// 间推分佣算力记录
	public List<AgcCpProfit> getUserInviteCpProfitIndirectList(String userId, String startTime, String endTime);
	
}
