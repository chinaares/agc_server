package com.lanjing.wallet.agc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lanjing.wallet.util.DateUtils;
import com.lanjing.wallet.util.SqlUtil;
import com.lanjing.wallet.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lanjing.wallet.agc.mapper.AgcConsumeRecordMapper;
import com.github.pagehelper.PageHelper;
import com.lanjing.wallet.agc.domain.AgcActivityConfig;
import com.lanjing.wallet.agc.domain.AgcConfig;
import com.lanjing.wallet.agc.domain.AgcConsumeProfit;
import com.lanjing.wallet.agc.domain.AgcConsumeRecord;
import com.lanjing.wallet.agc.domain.AgcCpProfit;
import com.lanjing.wallet.agc.domain.AgcQuotaratio;
import com.lanjing.wallet.agc.service.IAgcActivityConfigService;
import com.lanjing.wallet.agc.service.IAgcConfigService;
import com.lanjing.wallet.agc.service.IAgcConsumeProfitService;
import com.lanjing.wallet.agc.service.IAgcConsumeRecordService;
import com.lanjing.wallet.agc.service.IAgcCpProfitService;
import com.lanjing.wallet.agc.service.IAgcMiningUserService;
import com.lanjing.wallet.agc.service.IAgcQuotaratioService;
import com.lanjing.wallet.agc.vo.InviteUserVO;
import com.lanjing.wallet.agc.vo.MiningCpVO;
import com.lanjing.wallet.agc.vo.PageDomain;
import com.lanjing.wallet.agc.vo.TableSupport;
import com.lanjing.wallet.agc.vo.UserMiningVO;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.model.WellethistoryState;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.RecommendService;
import com.lanjing.wallet.service.WelletService;
import com.lanjing.wallet.service.WellethistoryStateService;
import com.lanjing.wallet.timer.ScheduledTasks;

/**
 * 用户消费记录Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcConsumeRecordServiceImpl implements IAgcConsumeRecordService {
	@Autowired
	private AgcConsumeRecordMapper agcConsumeRecordMapper;

	private static final Logger log = LoggerFactory.getLogger(AgcConsumeRecordServiceImpl.class);

	@Autowired
	private IAgcConfigService agcConfigService;

	@Autowired
	private IAgcActivityConfigService agcActivityConfigService;

	@Autowired
	private IAgcQuotaratioService agcQuotaratioService;

	@Autowired
	private IAgcConsumeProfitService agcConsumeProfitService;
	
	@Autowired
	private IAgcMiningUserService agcMiningUserService;

	@Autowired
	private IAgcCpProfitService agcCpProfitService;

	@Resource(name = "RecommendService")
	private RecommendService recommendService;
	
	@Resource(name = "WelletService")
	private WelletService welletService;
	
	@Resource(name = "WellethistoryStateService")
	private WellethistoryStateService wellethistoryStateService;

	/**
	 * 查询用户消费记录
	 * 
	 * @param id 用户消费记录ID
	 * @return 用户消费记录
	 */
	@Override
	public AgcConsumeRecord selectAgcConsumeRecordById(Long id) {
		return agcConsumeRecordMapper.selectAgcConsumeRecordById(id);
	}

	/**
	 * 查询用户消费记录列表
	 * 
	 * @param agcConsumeRecord 用户消费记录
	 * @return 用户消费记录
	 */
	@Override
	public List<AgcConsumeRecord> selectAgcConsumeRecordList(AgcConsumeRecord agcConsumeRecord) {
		return agcConsumeRecordMapper.selectAgcConsumeRecordList(agcConsumeRecord);
	}

	/**
	 * 新增用户消费记录
	 * 
	 * @param agcConsumeRecord 用户消费记录
	 * @return 结果
	 */
	@Override
	public int insertAgcConsumeRecord(AgcConsumeRecord agcConsumeRecord) {
		agcConsumeRecord.setCreateTime(DateUtils.getNowDate());
		return agcConsumeRecordMapper.insertAgcConsumeRecord(agcConsumeRecord);
	}

	/**
	 * 修改用户消费记录
	 * 
	 * @param agcConsumeRecord 用户消费记录
	 * @return 结果
	 */
	@Override
	public int updateAgcConsumeRecord(AgcConsumeRecord agcConsumeRecord) {
		agcConsumeRecord.setUpdateTime(DateUtils.getNowDate());
		return agcConsumeRecordMapper.updateAgcConsumeRecord(agcConsumeRecord);
	}

	/**
	 * 批量删除用户消费记录
	 * 
	 * @param ids 需要删除的用户消费记录ID
	 * @return 结果
	 */
	@Override
	public int deleteAgcConsumeRecordByIds(Long[] ids) {
		return agcConsumeRecordMapper.deleteAgcConsumeRecordByIds(ids);
	}

	/**
	 * 删除用户消费记录信息
	 * 
	 * @param id 用户消费记录ID
	 * @return 结果
	 */
	@Override
	public int deleteAgcConsumeRecordById(Long id) {
		return agcConsumeRecordMapper.deleteAgcConsumeRecordById(id);
	}

	/**
	 * 记录用户消费记录
	 * 
	 * @param userId 用户ID
	 * @param amount 消费金额CNY
	 * @param number 消费币的数量
	 * @return 结果
	 */
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void recordAgcConsumeRecord(String userId, BigDecimal amount, BigDecimal number) {
		AgcConfig agcConfig = agcConfigService.selectAgcConfigById(1L);
		List<AgcQuotaratio> agcQuotaratioList = agcQuotaratioService.selectAgcQuotaratioList(null);
//    	1.用户下单成功》
//    	2.记录用户消费记录》
		Date now = DateUtils.getNowDate();
		AgcConsumeRecord record = new AgcConsumeRecord();
		record.setCreateTime(now);
		record.setUserId(userId);
		record.setAmount(amount);
		record.setNumber(number);

//    	3.查出配置判断用户消费度范围计算算力》
		BigDecimal consumeCnyTotal = agcConsumeRecordMapper.selectAgcConsumeRecordTotalByUserId(userId);// 用户的消费总CNY额度
		int consumeCnyCount = 0;
		consumeCnyCount = agcConsumeRecordMapper.selectAgcConsumeRecordCountByUserId(userId);// 用户消费次数
		BigDecimal consumeCp = new BigDecimal(0);// 消费算力

		log.info("历史金额consumeCnyTotal[{}]", consumeCnyTotal);
		log.info("消费金额amount[{}]", amount);

		if (consumeCnyCount == 0) {// 没有消费记录首次消费
			for (AgcQuotaratio ratio : agcQuotaratioList) {

				if (StringUtils.ifInScope(ratio.getStartQuota(), ratio.getEndQuota(), amount)) {// 如果在这个范围
					log.debug("在这个范围[{}]-[{}]", ratio.getStartQuota(), ratio.getEndQuota());
					BigDecimal a = amount.subtract(ratio.getStartQuota());// 多出该范围的金额
					BigDecimal b = a.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
					BigDecimal c = b.multiply(ratio.getCpRatio()).setScale(8, BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
					consumeCp = consumeCp.add(c);// 算力G累计
					log.debug("最终累计[{}]", c);
					break;
				}

				BigDecimal a = ratio.getEndQuota().subtract(ratio.getStartQuota());
				BigDecimal b = a.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
				BigDecimal c = b.multiply(ratio.getCpRatio()).setScale(8, BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
				consumeCp = consumeCp.add(c);// 算力G累计
				log.debug("累计[{}]", c);
			}
		} else {// 第N次消费
			// 1000 + 1000 = 2000 没有越界【ok】
			// 1000 + 10000 = 11000 越1界【ok】
			// 1000 + 100000 = 101000越2界【ok】
			for (int i = 0; i < agcQuotaratioList.size(); i++) {
				AgcQuotaratio ratio = agcQuotaratioList.get(i);

				// 历史累计 或者 加起来都在这个范围 【没有越界】
				if (StringUtils.ifInScope(ratio.getStartQuota(), ratio.getEndQuota(), consumeCnyTotal) && StringUtils
						.ifInScope(ratio.getStartQuota(), ratio.getEndQuota(), consumeCnyTotal.add(amount))) {
					log.info("在这个范围[{}]-[{}]", ratio.getStartQuota(), ratio.getEndQuota());
					BigDecimal a = amount;// 多出该范围的金额
					BigDecimal b = a.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
					BigDecimal c = b.multiply(ratio.getCpRatio()).setScale(8, BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
					consumeCp = consumeCp.add(c);// 算力G累计
					log.info("最终累计[{}]", c);
					break;
				}

				// 历史累计 或者 加起来都在这个范围 【越1界】
				if (StringUtils.ifInScope(ratio.getStartQuota(), ratio.getEndQuota(), consumeCnyTotal)
						&& StringUtils.ifInScope(agcQuotaratioList.get(i + 1).getStartQuota(),
								agcQuotaratioList.get(i + 1).getEndQuota(), consumeCnyTotal.add(amount))) {
					log.info("在这个范围越1界[{}]-[{}]", ratio.getStartQuota(), ratio.getEndQuota());

					BigDecimal a = ratio.getEndQuota().subtract(consumeCnyTotal);// 多出该范围的金额
					BigDecimal b = a.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
					BigDecimal c = b.multiply(ratio.getCpRatio()).setScale(8, BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
					consumeCp = consumeCp.add(c);// 算力G累计
					log.info("最终累计[{}]", c);

					BigDecimal a2 = consumeCnyTotal.add(amount).subtract(agcQuotaratioList.get(i + 1).getStartQuota());// 多出该范围的金额
					BigDecimal b2 = a2.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
					BigDecimal c2 = b2.multiply(agcQuotaratioList.get(i + 1).getCpRatio()).setScale(8,
							BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
					consumeCp = consumeCp.add(c2);// 算力G累计
					log.info("最终累计[{}]", c2);
					break;
				}

				// 历史累计 或者 加起来都在这个范围 【越2界】
				if (StringUtils.ifInScope(ratio.getStartQuota(), ratio.getEndQuota(), consumeCnyTotal)
						&& StringUtils.ifInScope(agcQuotaratioList.get(i + 2).getStartQuota(),
								agcQuotaratioList.get(i + 2).getEndQuota(), consumeCnyTotal.add(amount))) {
					log.info("在这个范围越2界[{}]-[{}]", ratio.getStartQuota(), ratio.getEndQuota());

					BigDecimal a = ratio.getEndQuota().subtract(consumeCnyTotal);// 多出该范围的金额
					BigDecimal b = a.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
					BigDecimal c = b.multiply(ratio.getCpRatio()).setScale(8, BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
					consumeCp = consumeCp.add(c);// 算力G累计
					log.info("最终累计[{}]", c);

					BigDecimal a2 = amount.subtract(agcQuotaratioList.get(i + 1).getStartQuota());// 多出该范围的金额
					BigDecimal b2 = a2.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
					BigDecimal c2 = b2.multiply(agcQuotaratioList.get(i + 1).getCpRatio()).setScale(8,
							BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
					consumeCp = consumeCp.add(c2);// 算力G累计
					log.info("最终累计[{}]", c2);

					BigDecimal a3 = consumeCnyTotal.add(amount).subtract(agcQuotaratioList.get(i + 2).getStartQuota());// 多出该范围的金额
					BigDecimal b3 = a3.divide(agcConfig.getAgcCnyCp(), 8, BigDecimal.ROUND_DOWN);// 消费换算成算力G
					BigDecimal c3 = b3.multiply(agcQuotaratioList.get(i + 2).getCpRatio()).setScale(8,
							BigDecimal.ROUND_HALF_UP);// 该范围的算力加成
					consumeCp = consumeCp.add(c3);// 算力G累计
					log.info("最终累计[{}]", c3);
					break;
				}

			}
		}

		log.info("算力consumeCp[{}]", consumeCp);
		record.setConsumeCp(consumeCp);
//    	4.判断活动加成算力》
		BigDecimal activityCp = new BigDecimal(0);// 活动算力
		List<AgcActivityConfig> agcActivityConfigList = agcActivityConfigService.selectAgcActivityConfigList(null);
		if (StringUtils.isNotEmpty(agcActivityConfigList)) {// 活动范围判断
			for (AgcActivityConfig agcActivityConfig : agcActivityConfigList) {
				if (DateUtils.isEffectiveDate(now, agcActivityConfig.getStartDate(), agcActivityConfig.getEndDate())) {// 在活动范围
					activityCp = consumeCp.multiply(agcActivityConfig.getCpRatio());// 活动加成
				}
			}
		}

		log.info("活动算力activityCp[{}]", activityCp);
		record.setActivityCp(activityCp);

		insertAgcConsumeRecord(record);// 插入消费记录数据

//    	5.消费AGC返佣2代[判断每代上级是否消费过]》
		Recommend recommend1 = recommendService.selectByUserKey1(userId);
		String parentId1 = recommend1.getUserkey2();// 直推上级 10%
		if (StringUtils.isNotEmpty(parentId1)) {
			AgcConsumeProfit profit = new AgcConsumeProfit();
			profit.setCreateTime(now);
			profit.setFromId(userId);
			profit.setUserId(parentId1);
			profit.setEra(1L);
			profit.setNumber(number.multiply(new BigDecimal("0.1")));
//			BigDecimal parentConsumeCnyTotal = agcConsumeRecordMapper.selectAgcConsumeRecordTotalByUserId(parentId1);// 用户的消费总CNY额度
			int consumeCnyCount2 = 0;
//			consumeCnyCount2 = agcConsumeRecordMapper.selectAgcConsumeRecordCountByUserId(parentId1);// 用户消费次数
			consumeCnyCount2 = 1;
			if (consumeCnyCount2 > 0) {// 消费过
				agcConsumeProfitService.insertAgcConsumeProfit(profit);
				Wellets agc = welletService.selectByUserandcoin(parentId1, 2);
				System.out.println(parentId1+"-"+profit.getNumber());
				// 资产记录
                WellethistoryState wellethistoryState = new WellethistoryState();
                wellethistoryState.setCreatetime(new Date());
                wellethistoryState.setCointype(2);
                wellethistoryState.setRemark("挖矿空投");
                wellethistoryState.setUpdatetime(new Date());
                wellethistoryState.setState(1);
                wellethistoryState.setType(3);
                wellethistoryState.setChangenum(profit.getNumber());
                wellethistoryState.setUserid(Integer.parseInt(parentId1));
                wellethistoryState.setWelletid(agc.getFid());
                wellethistoryStateService.insertSelective(wellethistoryState);
				
                // 增加资产
	            agc.setCoinnum(agc.getCoinnum().add(profit.getNumber()));
	            welletService.updateByPrimaryKey(agc);

				Recommend recommend2 = recommendService.selectByUserKey1(parentId1);// 简推上级 5%
				String parentId2 = recommend2.getUserkey2();
				if (StringUtils.isNotEmpty(parentId2)) {
					AgcConsumeProfit profit2 = new AgcConsumeProfit();
					profit2.setCreateTime(now);
					profit2.setFromId(userId);
					profit2.setUserId(parentId2);
					profit2.setEra(2L);
					profit2.setNumber(number.multiply(new BigDecimal("0.05")));
//					BigDecimal parentConsumeCnyTotal2 = agcConsumeRecordMapper
//							.selectAgcConsumeRecordTotalByUserId(parentId2);// 用户的消费总CNY额度
					int consumeCnyCount3 = 0;
//					consumeCnyCount3 = agcConsumeRecordMapper.selectAgcConsumeRecordCountByUserId(parentId2);// 用户消费次数
					consumeCnyCount3 = 1;
					if (consumeCnyCount3 > 0) {// 消费过
						agcConsumeProfitService.insertAgcConsumeProfit(profit2);
						
						Wellets agc2 = welletService.selectByUserandcoin(parentId2, 2);
						System.out.println(parentId2+"-"+profit2.getNumber());
						// 资产记录
		                WellethistoryState wellethistoryState2 = new WellethistoryState();
		                wellethistoryState2.setCreatetime(new Date());
		                wellethistoryState2.setCointype(2);
		                wellethistoryState2.setRemark("挖矿空投");
		                wellethistoryState2.setUpdatetime(new Date());
		                wellethistoryState2.setState(1);
		                wellethistoryState2.setType(3);
		                wellethistoryState2.setChangenum(profit2.getNumber());
		                wellethistoryState2.setUserid(Integer.parseInt(parentId2));
		                wellethistoryState2.setWelletid(agc2.getFid());
		                wellethistoryStateService.insertSelective(wellethistoryState2);
						
		                // 增加资产
		                agc2.setCoinnum(agc2.getCoinnum().add(profit2.getNumber()));
			            welletService.updateByPrimaryKey(agc2);
					}else {
						log.info("未消费过[{}]无法分佣下级的AGC",parentId2);
					}
				}
			}else {
				log.info("未消费过[{}]无法分佣下级的AGC",parentId1);
			}

		}

//    	6.算力分佣5代[判断每代上级是否消费过][直推10%，2-5代5%]》
		int era = 1;
		String userId2 = userId;
		BigDecimal totalCp  = consumeCp.add(activityCp);
		while (era <= 5) {// 分佣5代
			Recommend recommend = recommendService.selectByUserKey1(userId2);
			String parentId = recommend.getUserkey2();// 直推上级 10%
			if (StringUtils.isNotEmpty(parentId)) {
				AgcCpProfit cpProfit = new AgcCpProfit();
				cpProfit.setCreateTime(now);
				cpProfit.setFromId(userId);
				cpProfit.setUserId(parentId);
				cpProfit.setEra((long) era);// 代数
				if (era == 1) {// 第一代10%
					cpProfit.setCp(totalCp.multiply(new BigDecimal("0.1")));
				} else {// 2-5代5%
					cpProfit.setCp(totalCp.multiply(new BigDecimal("0.05")));
				}
				
				int consumeCnyCount4 = 0;
//				consumeCnyCount4 = agcConsumeRecordMapper.selectAgcConsumeRecordCountByUserId(parentId);// 用户消费次数
				consumeCnyCount4 = 1;// 不管3721全部激活
				if (consumeCnyCount4 <= 0) {// 未消费过
					cpProfit.setStatus("2");// 2未激活
				} else {
					cpProfit.setStatus("1");// 1激活
				}

				agcCpProfitService.insertAgcCpProfit(cpProfit);
			} else {
				break;// 没有上级了循环结束
			}
			userId2 = parentId;
			era = era + 1;
		}

//    	7.如果是首次消费 激活下级5代未激活的算力
		if (consumeCnyTotal.compareTo(BigDecimal.ZERO) == 0) {
			agcCpProfitService.activationEra5AgcCpProfit(userId);
		}
		
	}

    /**
     * 查询用户消费+活动算力
     * 
     * @return 结果
     */
	@Override
	public List<MiningCpVO> selectMiningCpVOs() {
		return agcConsumeRecordMapper.selectMiningCpVOs();
	}

	/**
	 * 查询总算力
	 */
	@Override
	public BigDecimal selectAllCp() {
		return agcConsumeRecordMapper.selectAllCp();
	}

    /**
     * 我的数据接口{今日收益，累计收益，我的消费算力，我的邀请算力}
     */
	@Override
	public UserMiningVO myInfo(String userId) {
		UserMiningVO vo = new UserMiningVO();
		Date now = DateUtils.getNowDate();
		/**今日收益*/
//		BigDecimal dayIncome = agcConsumeProfitService.selectDayIncomeByNowAndUserId(now,userId);
		BigDecimal dayIncome = agcMiningUserService.selectDayIncomeByNowAndUserId(now,userId);
		
		/**累计收益*/
//		BigDecimal totalIncome = agcConsumeProfitService.selectDayIncomeByUserId(userId);
		BigDecimal totalIncome = agcMiningUserService.selectDayIncomeByUserId(userId);
		
		/**我的消费算力*/
		BigDecimal consumeCp = agcConsumeRecordMapper.selectConsumeCpByUserId(userId);
		
		/**我的邀请算力*/
		BigDecimal profitCp = agcCpProfitService.selectProfitCpByUserId(userId);
		
		vo.setConsumeCp(consumeCp);
		vo.setDayIncome(dayIncome);
		vo.setProfitCp(profitCp);
		vo.setTotalIncome(totalIncome);
		return vo;
	}

	/**
	 * 我的消费算力明细-分页{}
	 */
	@Override
	public List<AgcConsumeRecord> selectConsumeRecordListByUserId(String userId) {
		return agcConsumeRecordMapper.selectConsumeRecordListByUserId(userId);
	}

	@Override
	public BigDecimal getTotalAchievement(String userId) {
		List<Recommend> reList = recommendService.selectAll();
		List<String> userIdList = new ArrayList<String>();
		userIdList = recommendService.selectUserSubordinateList(reList, userIdList, userId ,"1970-01-01 00:00:00", "2220-11-09 00:00:00",new ArrayList<String>());
		userIdList.add(userId);// 把自己也算上
		BigDecimal data = agcConsumeRecordMapper.getTotalAchievementAll(userIdList);
		return data;
	}
}
