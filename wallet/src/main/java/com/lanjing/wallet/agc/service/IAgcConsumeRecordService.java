package com.lanjing.wallet.agc.service;

import java.math.BigDecimal;
import java.util.List;
import com.lanjing.wallet.agc.domain.AgcConsumeRecord;
import com.lanjing.wallet.agc.vo.MiningCpVO;
import com.lanjing.wallet.agc.vo.UserMiningVO;

/**
 * 用户消费记录Service接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface IAgcConsumeRecordService {
	/**
	 * 查询用户消费记录
	 * 
	 * @param id 用户消费记录ID
	 * @return 用户消费记录
	 */
	public AgcConsumeRecord selectAgcConsumeRecordById(Long id);

	/**
	 * 查询用户消费记录列表
	 * 
	 * @param agcConsumeRecord 用户消费记录
	 * @return 用户消费记录集合
	 */
	public List<AgcConsumeRecord> selectAgcConsumeRecordList(AgcConsumeRecord agcConsumeRecord);

	/**
	 * 新增用户消费记录
	 * 
	 * @param agcConsumeRecord 用户消费记录
	 * @return 结果
	 */
	public int insertAgcConsumeRecord(AgcConsumeRecord agcConsumeRecord);

	/**
	 * 修改用户消费记录
	 * 
	 * @param agcConsumeRecord 用户消费记录
	 * @return 结果
	 */
	public int updateAgcConsumeRecord(AgcConsumeRecord agcConsumeRecord);

	/**
	 * 批量删除用户消费记录
	 * 
	 * @param ids 需要删除的用户消费记录ID
	 * @return 结果
	 */
	public int deleteAgcConsumeRecordByIds(Long[] ids);

	/**
	 * 删除用户消费记录信息
	 * 
	 * @param id 用户消费记录ID
	 * @return 结果
	 */
	public int deleteAgcConsumeRecordById(Long id);

	/**
	 * 记录用户消费记录
	 * 
	 * @param userId 用户ID
	 * @param amount 消费金额CNY
	 * @param number 消费币的数量
	 * @return 结果
	 */
	public void recordAgcConsumeRecord(String userId, BigDecimal amount, BigDecimal number);

	/**
	 * 查询用户消费+活动算力
	 * 
	 * @return 结果
	 */
	public List<MiningCpVO> selectMiningCpVOs();

	/**
	 * 查询总算力
	 */
	public BigDecimal selectAllCp();

	/**
	 * 我的数据接口{今日收益，累计收益，我的消费算力，我的邀请算力}
	 */
	public UserMiningVO myInfo(String userKey);

	/**
	 * 我的消费算力明细-分页{}
	 */
	public List<AgcConsumeRecord> selectConsumeRecordListByUserId(String userId);

	public BigDecimal getTotalAchievement(String userId);
}
