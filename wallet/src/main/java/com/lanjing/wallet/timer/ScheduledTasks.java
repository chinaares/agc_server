package com.lanjing.wallet.timer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lanjing.wallet.agc.service.IAgcMiningTotalService;
import com.lanjing.wallet.util.DateUtils;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
    @Autowired
    private IAgcMiningTotalService agcMiningTotalService;

	
//	/** test定时任务 1s一次*/
//	@Scheduled(cron = "* * * * * ?")
//	public void ieoInfoTask(){
//		logger.info("test");
//	}
	

	/**  每日凌晨 AGC开始算力挖矿  每天的0点 执行一次*/
	@Scheduled(cron = "0 0 0 * * ?")
//	@Scheduled(cron = "0 22 15 * * ?")// 测试
	public void updateStartUnlockTotalStarTask(){
		Date now = DateUtils.getNowDate();
		System.err.println("AGC开始算力挖矿--开始--{}"+now);
		long startTime = System.currentTimeMillis();
		
		try {
			agcMiningTotalService.miningAgc();
		}catch (Exception e) {
			System.err.println("每日凌晨 AGC开始算力挖矿---失败！！！"+e);
			e.printStackTrace();
		}
		
		System.err.println("AGC开始算力挖矿--结束--用时 [{} ms]"+(System.currentTimeMillis() - startTime));
	}
	
}
