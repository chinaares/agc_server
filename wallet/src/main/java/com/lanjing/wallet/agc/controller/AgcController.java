package com.lanjing.wallet.agc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jester.util.utils.Result;
import com.lanjing.wallet.agc.domain.AgcConfig;
import com.lanjing.wallet.agc.domain.AgcConsumeProfit;
import com.lanjing.wallet.agc.domain.AgcConsumeRecord;
import com.lanjing.wallet.agc.domain.AgcCpProfit;
import com.lanjing.wallet.agc.domain.AgcMiningUser;
import com.lanjing.wallet.agc.service.IAgcActivityConfigService;
import com.lanjing.wallet.agc.service.IAgcConfigService;
import com.lanjing.wallet.agc.service.IAgcConsumeProfitService;
import com.lanjing.wallet.agc.service.IAgcConsumeRecordService;
import com.lanjing.wallet.agc.service.IAgcCpProfitService;
import com.lanjing.wallet.agc.service.IAgcMiningTotalService;
import com.lanjing.wallet.agc.service.IAgcMiningUserService;
import com.lanjing.wallet.agc.service.IAgcQuotaratioService;
import com.lanjing.wallet.agc.vo.InviteUserVO;
import com.lanjing.wallet.agc.vo.TableDataInfo;
import com.lanjing.wallet.agc.vo.UserMiningVO;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.service.RecommendService;
import com.lanjing.wallet.service.UsersService;

/**
 * AGC Controller
 * 
 * @author live
 * @date 2020-10-26
 */
@RestController
@RequestMapping("/agc")
public class AgcController extends BaseContrller {
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

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource(name = "RecommendService")
    private RecommendService recommendService;
    
	/**
	 * 查询AGC基础配置列表
	 */
	@RequestMapping("/list")
	public Result list(AgcConfig agcConfig) {
//    	agcConsumeRecordService.recordAgcConsumeRecord("2012446", new BigDecimal(100000), new BigDecimal(1000));
		try {
//			agcMiningTotalService.miningAgc();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error();
		}
		return Result.success();
	}

//    1.全网数据接口{全网算力，消费算力，邀请算力，已挖出，待挖出，今日产量}
	/**
	 * 全网数据接口{全网算力，消费算力，邀请算力，已挖出，待挖出，今日产量}
	 */
	@RequestMapping("/info")
	public Result info() {
		try {
			Map map = agcMiningTotalService.selectInfo();
			return Result.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error();
		}
	}

//    2.我的数据接口{今日收益，累计收益，我的消费算力，我的邀请算力}
	/**
	 * 我的数据接口{今日收益，累计收益，我的消费算力，我的邀请算力}
	 */
	@RequestMapping("/myInfo")
	public Result myInfo() {
		try {
			UserMiningVO vo = agcConsumeRecordService.myInfo(getUserKey());
			return Result.success(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error();
		}
	}

//    3.我的收益明细-分页{每天挖矿的数量}
	/**
	 * 我的挖矿收益明细-分页{每天挖矿的数量}
	 */
	@RequestMapping("/getMiningList")
	public TableDataInfo getMiningList(@RequestBody Map<String, String> map) {
        Integer page = Integer.parseInt(map.get("pageNum"));
        Integer size = Integer.parseInt(map.get("pageSize"));
		startPage(page,size);
		List<AgcMiningUser> list = agcMininguserService.selectMiningUserListByUserId(getUserKey());
		return getDataTable(list);
	}

//    4.我的消费算力明细-分页{}
	/**
	 * 我的消费算力明细-分页{}
	 */
	@RequestMapping("/getConsumeRecordList")
	public TableDataInfo getConsumeRecordList(@RequestBody Map<String, String> map) {
        Integer page = Integer.parseInt(map.get("pageNum"));
        Integer size = Integer.parseInt(map.get("pageSize"));
		startPage(page,size);
		List<AgcConsumeRecord> list = agcConsumeRecordService.selectConsumeRecordListByUserId(getUserKey());
		return getDataTable(list);
	}

//    5.我的推广{直推与间推的 用户数/算力G/AGC}时间范围查询
	/**
	 * 我的推广
	 * startTime 查询范围开始时间 
	 * endTime 查询范围结束时间
	 */
	@RequestMapping("/getUserInvite")
	public Result getUserInvite(@RequestBody Map<String, String> maps) {
		String startTime = maps.get("startTime");
		String endTime = maps.get("endTime");
		Map map = agcConsumeProfitService.getUserInvite(getUserKey(),startTime,endTime);
		return Result.success(map);
	}
	
//    6.推广下级用户列表-分页{时间范围}
	/**
	 * 推广直推+间推下级用户列表-分页{时间范围}
	 * startTime 查询范围开始时间 
	 * endTime 查询范围结束时间
	 */
	@RequestMapping("/getUserInviteList")
	public TableDataInfo getUserInviteList(@RequestBody Map<String, String> maps) {
        Integer page = Integer.parseInt(maps.get("pageNum"));
        Integer size = Integer.parseInt(maps.get("pageSize"));
		String startTime = maps.get("startTime");
		String endTime = maps.get("endTime");
		
		String type = maps.get("type");// direct=直推   indirect=间推
		List<InviteUserVO> list = new ArrayList<InviteUserVO>();
	    if ("direct".equals(type)) {// direct=直推
			list = usersService.getUserInviteDirectList(getUserKey(),startTime,endTime,page,size);
		}else if ("indirect".equals(type)) {// indirect=间推
			list = usersService.getUserInviteIndirectList(getUserKey(),startTime,endTime,page,size);
		}
		
		return getDataTable(list);
	}
	
	
//    7.推广分佣算力列表-分页{时间范围}
	/**
	 * 推广直推+间推下级分佣算力列表-分页{时间范围}
	 * startTime 查询范围开始时间 
	 * endTime 查询范围结束时间
	 */
	@RequestMapping("/getUserInviteCpProfitList")
	public TableDataInfo getUserInviteCpProfitList(@RequestBody Map<String, String> maps) {
        Integer page = Integer.parseInt(maps.get("pageNum"));
        Integer size = Integer.parseInt(maps.get("pageSize"));
		String startTime = maps.get("startTime");
		String endTime = maps.get("endTime");
		String type = maps.get("type");// direct=直推   indirect=间推
		startPage(page, size);
		List<AgcCpProfit> list = new ArrayList<AgcCpProfit>();
	    if ("direct".equals(type)) {// direct=直推
			list = agcCpProfitService.getUserInviteCpProfitDirectList(getUserKey(),startTime,endTime);
		}else if ("indirect".equals(type)) {// indirect=间推
			list = agcCpProfitService.getUserInviteCpProfitIndirectList(getUserKey(),startTime,endTime);
		}
		
		return getDataTable(list);
	}
	
	
//    8.推广获得AGC列表-分页{时间范围}
	/**
	 * 推广直推+间推下级分佣获得AGC列表-分页{时间范围}
	 * startTime 查询范围开始时间 
	 * endTime 查询范围结束时间
	 */
	@RequestMapping("/getUserInviteAgcList")
	public TableDataInfo getUserInviteAgcList(@RequestBody Map<String, String> maps) {
        Integer page = Integer.parseInt(maps.get("pageNum"));
        Integer size = Integer.parseInt(maps.get("pageSize"));
		String startTime = maps.get("startTime");
		String endTime = maps.get("endTime");
		String type = maps.get("type");// direct=直推   indirect=间推
		startPage(page, size);
		List<AgcConsumeProfit> list = new ArrayList<AgcConsumeProfit>();
	    if ("direct".equals(type)) {// direct=直推
			list = agcConsumeProfitService.getUserInviteAgcDirectList(getUserKey(),startTime,endTime);
		}else if ("indirect".equals(type)) {// indirect=间推
			list = agcConsumeProfitService.getUserInviteAgcIndirectList(getUserKey(),startTime,endTime);
		}
		
		return getDataTable(list);
	}
	
	/**
	 * 我和我的团队总消费业绩
	 */
	@RequestMapping("/getTotalAchievement")
	public Result getTotalAchievement() {
		BigDecimal data = new BigDecimal(0);
		data = agcConsumeRecordService.getTotalAchievement(getUserKey());
		return Result.success(data);
	}

}
