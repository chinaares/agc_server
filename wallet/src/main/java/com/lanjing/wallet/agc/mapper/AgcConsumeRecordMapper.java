package com.lanjing.wallet.agc.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanjing.wallet.agc.domain.AgcConsumeRecord;
import com.lanjing.wallet.agc.vo.MiningCpVO;
import com.lanjing.wallet.agc.vo.UserMiningVO;

/**
 * 用户消费记录Mapper接口
 * 
 * @author live
 * @date 2020-10-26
 */
/**
 * @author Administrator
 *
 */
public interface AgcConsumeRecordMapper 
{
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
     * 删除用户消费记录
     * 
     * @param id 用户消费记录ID
     * @return 结果
     */
    public int deleteAgcConsumeRecordById(Long id);

    /**
     * 批量删除用户消费记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgcConsumeRecordByIds(Long[] ids);

    
	/**
	 * 查询用户累计消费额度CNY
	 * @param userId
	 * @return
	 */
	public BigDecimal selectAgcConsumeRecordTotalByUserId(String userId);
	
	/**
	 * 查询用户累计消费次数
	 * @param userId
	 * @return
	 */
	public Integer selectAgcConsumeRecordCountByUserId(String userId);

	/**
     * 查询用户消费+活动算力
     * @return 结果
     */
	public List<MiningCpVO> selectMiningCpVOs();

	/**
	 * 查询总算力
	 */
	public BigDecimal selectAllCp();

	/**我的消费算力*/
	public BigDecimal selectConsumeCpByUserId(String userId);

	/**
	 * 我的消费算力明细-分页{}
	 */
	public List<AgcConsumeRecord> selectConsumeRecordListByUserId(String userId);

	/**
	 * 我和我的团体所有的消费业绩
	 * @param userIdList
	 */
	public BigDecimal getTotalAchievementAll(@Param("userIdList") List<String> userIdList);

}
