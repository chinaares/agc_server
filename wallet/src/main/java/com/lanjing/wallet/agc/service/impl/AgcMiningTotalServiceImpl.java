package com.lanjing.wallet.agc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lanjing.wallet.agc.domain.AgcConfig;
import com.lanjing.wallet.agc.domain.AgcMiningTotal;
import com.lanjing.wallet.agc.domain.AgcMiningUser;
import com.lanjing.wallet.agc.mapper.AgcMiningTotalMapper;
import com.lanjing.wallet.agc.service.IAgcActivityConfigService;
import com.lanjing.wallet.agc.service.IAgcConfigService;
import com.lanjing.wallet.agc.service.IAgcConsumeProfitService;
import com.lanjing.wallet.agc.service.IAgcConsumeRecordService;
import com.lanjing.wallet.agc.service.IAgcCpProfitService;
import com.lanjing.wallet.agc.service.IAgcMiningTotalService;
import com.lanjing.wallet.agc.service.IAgcMiningUserService;
import com.lanjing.wallet.agc.service.IAgcQuotaratioService;
import com.lanjing.wallet.agc.vo.MiningCpProfitVO;
import com.lanjing.wallet.agc.vo.MiningCpVO;
import com.lanjing.wallet.model.WellethistoryState;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.RecommendService;
import com.lanjing.wallet.service.WelletService;
import com.lanjing.wallet.service.WellethistoryStateService;
import com.lanjing.wallet.util.DateUtils;
import com.lanjing.wallet.util.StringUtils;

/**
 * 平台总挖矿记录Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcMiningTotalServiceImpl implements IAgcMiningTotalService {
	@Autowired
	private AgcMiningTotalMapper agcMiningTotalMapper;

	private static final Logger log = LoggerFactory.getLogger(AgcMiningTotalServiceImpl.class);

	@Autowired
	private IAgcConfigService agcConfigService;

	@Autowired
	private IAgcActivityConfigService agcActivityConfigService;

	@Autowired
	private IAgcQuotaratioService agcQuotaratioService;

	@Autowired
	private IAgcConsumeRecordService agcConsumeRecordService;

	@Autowired
	private IAgcConsumeProfitService agcConsumeProfitService;

	@Autowired
	private IAgcCpProfitService agcCpProfitService;
	
	@Autowired
	private IAgcMiningUserService agcMininguserService;

	@Resource(name = "RecommendService")
	private RecommendService recommendService;
	
	@Resource(name = "WelletService")
	private WelletService welletService;
	
	@Resource(name = "WellethistoryStateService")
	private WellethistoryStateService wellethistoryStateService;
	

	/**
	 * 查询平台总挖矿记录
	 * 
	 * @param id 平台总挖矿记录ID
	 * @return 平台总挖矿记录
	 */
	@Override
	public AgcMiningTotal selectAgcMiningTotalById(Long id) {
		return agcMiningTotalMapper.selectAgcMiningTotalById(id);
	}

	/**
	 * 查询平台总挖矿记录列表
	 * 
	 * @param agcMiningTotal 平台总挖矿记录
	 * @return 平台总挖矿记录
	 */
	@Override
	public List<AgcMiningTotal> selectAgcMiningTotalList(AgcMiningTotal agcMiningTotal) {
		return agcMiningTotalMapper.selectAgcMiningTotalList(agcMiningTotal);
	}

	/**
	 * 新增平台总挖矿记录
	 * 
	 * @param agcMiningTotal 平台总挖矿记录
	 * @return 结果
	 */
	@Override
	public int insertAgcMiningTotal(AgcMiningTotal agcMiningTotal) {
		agcMiningTotal.setCreateTime(DateUtils.getNowDate());
		return agcMiningTotalMapper.insertAgcMiningTotal(agcMiningTotal);
	}

	/**
	 * 修改平台总挖矿记录
	 * 
	 * @param agcMiningTotal 平台总挖矿记录
	 * @return 结果
	 */
	@Override
	public int updateAgcMiningTotal(AgcMiningTotal agcMiningTotal) {
		agcMiningTotal.setUpdateTime(DateUtils.getNowDate());
		return agcMiningTotalMapper.updateAgcMiningTotal(agcMiningTotal);
	}

	/**
	 * 批量删除平台总挖矿记录
	 * 
	 * @param ids 需要删除的平台总挖矿记录ID
	 * @return 结果
	 */
	@Override
	public int deleteAgcMiningTotalByIds(Long[] ids) {
		return agcMiningTotalMapper.deleteAgcMiningTotalByIds(ids);
	}

	/**
	 * 删除平台总挖矿记录信息
	 * 
	 * @param id 平台总挖矿记录ID
	 * @return 结果
	 */
	@Override
	public int deleteAgcMiningTotalById(Long id) {
		return agcMiningTotalMapper.deleteAgcMiningTotalById(id);
	}

	/**
	 * AGC凌晨进行挖矿
	 */
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void miningAgc() throws Exception {
		AgcConfig agcConfig = agcConfigService.selectAgcConfigById(1L);
		Date now = DateUtils.getNowDate();
		AgcMiningTotal miningTotal = new AgcMiningTotal();
		miningTotal.setCreateTime(now);
		
		// 判断今天是否已经挖矿了
		AgcMiningTotal nowMiningTotal = agcMiningTotalMapper.selectAgcMiningTotalByDate(now);
		if (StringUtils.isNotNull(nowMiningTotal)) {
			log.info("今天已经挖矿过,停止重复挖矿!!!");
			return;
		}

		/** 挖矿用户人数 */
		Long userCount = 0L;
		/** 实际总算力 */
		BigDecimal totalCp = BigDecimal.ZERO;
		/** 虚拟总算力 */
		BigDecimal fictitiousCp = agcConfig.getFictitiousCp();
		/** 消费金额 */
		BigDecimal amount = BigDecimal.ZERO;
		/** 消费数量 */
		BigDecimal number = BigDecimal.ZERO;
		/** 消费算力 */
		BigDecimal consumeCp = BigDecimal.ZERO;
		/** 邀请算力 */
		BigDecimal profitCp = BigDecimal.ZERO;
		/** 活动算力 */
		BigDecimal activityCp = BigDecimal.ZERO;
		/** 产出币 */
		BigDecimal produceAgc = agcConfig.getYield();
		
		if (produceAgc.compareTo(BigDecimal.ZERO)==0) {// 产出为0暂停挖矿
			log.info("每天产出AGC为0,暂停挖矿!!!");
			return;
		}

//		1.查询出所有用户的消费算力+活动加成算力+已激活的邀请算力 根据虚拟算力进行挖矿》
//		Map<String, BigDecimal> map 
		List<MiningCpVO> miningCpVOs = agcConsumeRecordService.selectMiningCpVOs();
		List<MiningCpProfitVO> miningCpProfitVOs = agcCpProfitService.selectMiningCpProfitVOs();
		Map<String, BigDecimal> profitMap = new HashMap<String, BigDecimal>();
		for (MiningCpProfitVO miningCpProfitVO : miningCpProfitVOs) {// 分佣算力转换成map
			profitMap.put(miningCpProfitVO.getUserId(), miningCpProfitVO.getCp());
		}
		
		List<AgcMiningUser> userList = new ArrayList<AgcMiningUser>();
		for (MiningCpVO vo : miningCpVOs) {
			AgcMiningUser user = new AgcMiningUser();
			user.setCreateTime(now);
			user.setUserId(vo.getUserId());
			user.setAmount(vo.getAmount());
			user.setNumber(vo.getNumber());
			user.setConsumeCp(vo.getConsumeCp());
			user.setActivityCp(vo.getActivityCp());
			// 总计算力 = 消费算力+活动算力+分佣算力
			user.setTotalCp(vo.getConsumeCp().add(vo.getActivityCp()));
			if (StringUtils.isNotNull(profitMap.get(vo.getUserId()))) {
				user.setProfitCp(profitMap.get(vo.getUserId()));
				user.setTotalCp(user.getTotalCp().add(profitMap.get(vo.getUserId())));
			}else {
				user.setProfitCp(BigDecimal.ZERO );
			}
			
			userList.add(user);
			
			totalCp = totalCp.add(user.getTotalCp());
			amount = amount.add(user.getAmount());
			number = number.add(user.getNumber());
			consumeCp = consumeCp.add(user.getConsumeCp());
			activityCp = activityCp.add(user.getActivityCp());
			profitCp = profitCp.add(user.getProfitCp());
			userCount = userCount + 1;// 人数+1
		}
		
		miningTotal.setActivityCp(activityCp);
		miningTotal.setAmount(amount);
		miningTotal.setNumber(number);
		miningTotal.setConsumeCp(consumeCp);
		miningTotal.setTotalCp(totalCp);
		miningTotal.setProduceAgc(produceAgc);
		miningTotal.setUserCount(userCount);
		miningTotal.setProfitCp(profitCp);
		miningTotal.setFictitiousCp(fictitiousCp);
		/** 挖矿总算力=实际+虚拟 */
		miningTotal.setMiningCp(totalCp.add(fictitiousCp));
		
		for (AgcMiningUser user : userList) {
			if (miningTotal.getMiningCp().compareTo(BigDecimal.ZERO)==0 || user.getTotalCp().compareTo(BigDecimal.ZERO)==0) {
				user.setGetAgc(BigDecimal.ZERO);
				continue;
			}
			
			// 该用户挖到的币 = 该用户总算力/(全网真实算力+虚拟总算力)* 日产量
			BigDecimal userGetAgc = user.getTotalCp().divide(miningTotal.getMiningCp(), 8, BigDecimal.ROUND_DOWN).multiply(agcConfig.getYield()).setScale(8,
					BigDecimal.ROUND_HALF_UP);
			user.setGetAgc(userGetAgc);
		}
		
//		2.并批量插入记录》记录平台挖矿总记录》
		insertAgcMiningTotal(miningTotal);
		if (StringUtils.isNotEmpty(userList)) {
			agcMininguserService.insertAgcMiningUserList(userList);
		}
		
		
//		3.空投矿产》
		/********财务操作-空投AGC矿产--开始**********/
		if (StringUtils.isNotEmpty(userList)) {
			for (AgcMiningUser miningUser : userList) {
				Wellets agc = welletService.selectByUserandcoin(miningUser.getUserId(), 2);
				System.out.println(miningUser.getUserId()+"-"+miningUser.getGetAgc());
				// 资产记录
                WellethistoryState wellethistoryState = new WellethistoryState();
                wellethistoryState.setCreatetime(new Date());
                wellethistoryState.setCointype(2);
                wellethistoryState.setRemark("挖矿空投");
                wellethistoryState.setUpdatetime(new Date());
                wellethistoryState.setState(1);
                wellethistoryState.setType(3);
                wellethistoryState.setChangenum(miningUser.getGetAgc());
                wellethistoryState.setUserid(Integer.parseInt(miningUser.getUserId()));
                wellethistoryState.setWelletid(agc.getFid());
                wellethistoryStateService.insertSelective(wellethistoryState);
				
                // 增加资产
	            agc.setCoinnum(agc.getCoinnum().add(miningUser.getGetAgc()));
	            welletService.updateByPrimaryKey(agc);
			}
		}
		
		/********财务操作-空投AGC矿产--结束**********/
	}

    /**
	 * 全网数据
	 * {全网算力，消费算力，邀请算力，已挖出，待挖出，今日产量}
	 */
	@Override
	public Map selectInfo() {
		AgcConfig c = agcConfigService.selectAgcConfigById(1L);
		
		Map map = new HashMap<>();
		//虚拟算力的50%
		BigDecimal fictitious50 = c.getFictitiousCp().multiply(new BigDecimal("0.5"));
	    // 消费算力 = 消费算力 +活动算力+ 虚拟算力的50%
		BigDecimal consumeCp = agcConsumeRecordService.selectAllCp().add(fictitious50);
		// 邀请算力 = 邀请算力 + 虚拟算力的50%
		BigDecimal profitCp = agcCpProfitService.selectAllProfitCp().add(fictitious50);
		// 全网算力 = 消费算力 + 邀请算力 + 虚拟算力的100%
		BigDecimal allCp = consumeCp.add(profitCp.add(c.getFictitiousCp()));
		// 已挖出 
		BigDecimal digOut = agcMiningTotalMapper.selectDigOut();
		digOut = digOut.add(new BigDecimal("4560717.94521"));
		// 待挖出 = 总量 - 已挖出
		BigDecimal surplus = new BigDecimal("84000000").subtract(digOut);
		// 今日产量
		BigDecimal yield = c.getYield();
		
		map.put("allCp", allCp);
		map.put("consumeCp", consumeCp);
		map.put("profitCp", profitCp);
		map.put("digOut", digOut);
		map.put("surplus", surplus);
		map.put("yield", yield);
		
		map.put("profitYield", 0);// 邀请矿量
		map.put("consumeyield", yield);// 消费矿量
		
		return map;
	}
}
