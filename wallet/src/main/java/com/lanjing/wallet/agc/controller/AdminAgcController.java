package com.lanjing.wallet.agc.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jester.util.utils.Result;
import com.lanjing.wallet.agc.domain.AgcActivityConfig;
import com.lanjing.wallet.agc.domain.AgcConfig;
import com.lanjing.wallet.agc.domain.AgcConsumeProfit;
import com.lanjing.wallet.agc.domain.AgcConsumeRecord;
import com.lanjing.wallet.agc.domain.AgcCpProfit;
import com.lanjing.wallet.agc.domain.AgcMiningTotal;
import com.lanjing.wallet.agc.domain.AgcMiningUser;
import com.lanjing.wallet.agc.service.IAgcActivityConfigService;
import com.lanjing.wallet.agc.service.IAgcConfigService;
import com.lanjing.wallet.agc.service.IAgcConsumeProfitService;
import com.lanjing.wallet.agc.service.IAgcConsumeRecordService;
import com.lanjing.wallet.agc.service.IAgcCpProfitService;
import com.lanjing.wallet.agc.service.IAgcMiningTotalService;
import com.lanjing.wallet.agc.service.IAgcMiningUserService;
import com.lanjing.wallet.agc.service.IAgcQuotaratioService;
import com.lanjing.wallet.agc.vo.TableDataInfo;
import com.lanjing.wallet.agc.vo.UserMiningVO;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.model.Banner;

/**
 * AGC Controller
 * 
 * @author live
 * @date 2020-10-26
 */
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/admin/agc")
public class AdminAgcController extends BaseContrller {
	@Autowired
	private IAgcConfigService agcConfigService;

	@Autowired
	private IAgcActivityConfigService agcActivityConfigService;

	@Autowired
	private IAgcQuotaratioService agcQuotaratioService;

	@Autowired
	private IAgcConsumeProfitService agcConsumeProfitService;

	@Autowired
	private IAgcCpProfitService agcCpProfitService;

	@Autowired
	private IAgcConsumeRecordService agcConsumeRecordService;

	@Autowired
	private IAgcMiningUserService agcMininguserService;

	@Autowired
	private IAgcMiningTotalService agcMiningTotalService;

	@Autowired
	private IAgcMiningUserService agcMiningUserService;

	/**
	 * 总配置查询
	 */
	@RequestMapping("/getAgcConfig")
	public Result getAgcConfig() {
		AgcConfig config = agcConfigService.selectAgcConfigById(1L);
		return Result.success(config);
	}

	/**
	 * 修改挖矿配置
	 *
	 */
	@RequestMapping("updateAgcConfig")
	public Result updateAgcConfig(@RequestBody AgcConfig agcConfig) {
		AgcConfig config = new AgcConfig();
		config.setId(1L);
		config.setFictitiousCp(agcConfig.getFictitiousCp());
		config.setAgcPrice(agcConfig.getAgcPrice());
		config.setAgcChg(agcConfig.getAgcChg());
		config.setAgcCnyCp(agcConfig.getAgcCnyCp());
		config.setWithdrawRatio(agcConfig.getWithdrawRatio());
		config.setYield(agcConfig.getYield());

		int result = agcConfigService.updateAgcConfig(config);
		return Result.rows(result);
	}

	/**
	 * 平台币总挖矿记录-分页
	 */
	@RequestMapping("/getMiningTotalList")
	public TableDataInfo getMiningTotalList(@RequestBody Map<String, String> map) {
		Integer page = Integer.parseInt(map.get("pageNum"));
		Integer size = Integer.parseInt(map.get("pageSize"));
		startPage(page, size);
		List<AgcMiningTotal> list = agcMiningTotalService.selectAgcMiningTotalList(null);
		return getDataTable(list);
	}

	/**
	 * 用户挖矿记录-分页
	 */
	@RequestMapping("/getMiningUserList")
	public TableDataInfo getMiningUserList(@RequestBody Map<String, String> map) {
		Integer page = Integer.parseInt(map.get("pageNum"));
		Integer size = Integer.parseInt(map.get("pageSize"));
		String userId = map.get("userId");
		startPage(page, size);
		AgcMiningUser user = new AgcMiningUser();
		user.setUserId(userId);
		List<AgcMiningUser> list = agcMiningUserService.selectAgcMiningUserList(user);
		return getDataTable(list);
	}

	/**
	 * 用户消费获得算力记录-分页
	 */
	@RequestMapping("/getAgcConsumeRecordList")
	public TableDataInfo getAgcConsumeRecordList(@RequestBody Map<String, String> map) {
		Integer page = Integer.parseInt(map.get("pageNum"));
		Integer size = Integer.parseInt(map.get("pageSize"));
		String userId = map.get("userId");
		startPage(page, size);
		AgcConsumeRecord user = new AgcConsumeRecord();
		user.setUserId(userId);
		List<AgcConsumeRecord> list = agcConsumeRecordService.selectAgcConsumeRecordList(user);
		return getDataTable(list);
	}
	
	/**
	 * 修改-用户消费获得算力记录
	 * 记录ID
	 * 消费算力
	 */
	@RequestMapping("updateAgcConsumeRecord")
	public Result updateAgcConsumeRecord(@RequestBody AgcConsumeRecord agcConsumeRecord) {
		AgcConsumeRecord record = new AgcConsumeRecord();
		record.setId(agcConsumeRecord.getId());
		record.setConsumeCp(agcConsumeRecord.getConsumeCp());
		
		int result = agcConsumeRecordService.updateAgcConsumeRecord(record);
		return Result.rows(result);
	}
	
	/**
	 * 新增-用户消费获得算力记录
	 * 用户ID
	 * 消费算力
	 */
	@RequestMapping("addAgcConsumeRecord")
	public Result addAgcConsumeRecord(@RequestBody AgcConsumeRecord agcConsumeRecord) {
		AgcConsumeRecord record = new AgcConsumeRecord();
		record.setConsumeCp(agcConsumeRecord.getConsumeCp());
		record.setUserId(agcConsumeRecord.getUserId());
		
		int result = agcConsumeRecordService.insertAgcConsumeRecord(record);
		return Result.rows(result);
	}

	/**
	 * 用户算力分佣记录-分页
	 */
	@RequestMapping("/getAgcCpProfitList")
	public TableDataInfo getAgcCpProfitList(@RequestBody Map<String, String> map) {
		Integer page = Integer.parseInt(map.get("pageNum"));
		Integer size = Integer.parseInt(map.get("pageSize"));
		String userId = map.get("userId");
		startPage(page, size);
		AgcCpProfit user = new AgcCpProfit();
		user.setUserId(userId);
		List<AgcCpProfit> list = agcCpProfitService.selectAgcCpProfitList(user);
		return getDataTable(list);
	}

	/**
	 * AGC消费分佣记录-分页
	 */
	@RequestMapping("/getAgcConsumeProfitList")
	public TableDataInfo getAgcConsumeProfitList(@RequestBody Map<String, String> map) {
		Integer page = Integer.parseInt(map.get("pageNum"));
		Integer size = Integer.parseInt(map.get("pageSize"));
		String userId = map.get("userId");
		startPage(page, size);
		AgcConsumeProfit user = new AgcConsumeProfit();
		user.setUserId(userId);
		List<AgcConsumeProfit> list = agcConsumeProfitService.selectAgcConsumeProfitList(user);
		return getDataTable(list);
	}

	/**
	 * 活动配置列表-分页
	 */
	@RequestMapping("/getAgcActivityConfigList")
	public TableDataInfo getAgcActivityConfigList(@RequestBody Map<String, String> map) {
		Integer page = Integer.parseInt(map.get("pageNum"));
		Integer size = Integer.parseInt(map.get("pageSize"));
		startPage(page, size);
		List<AgcActivityConfig> list = agcActivityConfigService.selectAgcActivityConfigList(null);
		return getDataTable(list);
	}

	/**
	 * 修改活动配置
	 */
	@RequestMapping("updateAgcActivityConfig")
	public Result updateAgcActivityConfig(@RequestBody AgcActivityConfig agcConfig) {
		int result = agcActivityConfigService.updateAgcActivityConfig(agcConfig);
		return Result.rows(result);
	}

	/**
	 * 新增活动配置
	 */
	@RequestMapping("addAgcActivityConfig")
	public Result addAgcActivityConfig(@RequestBody AgcActivityConfig agcConfig) {
		int result = agcActivityConfigService.insertAgcActivityConfig(agcConfig);
		return Result.rows(result);
	}

	/**
	 * 删除活动配置
	 */
	@RequestMapping("deleteAgcActivityConfig")
	public Result deleteAgcActivityConfig(@RequestBody Map<String, String> map) {
		Long id = Long.parseLong(map.get("id"));
		if (id == null || id == 0) {
			return Result.error("参数id不能为空");
		}
		int result = agcActivityConfigService.deleteAgcActivityConfigById(id);
		return Result.rows(result);
	}

}
