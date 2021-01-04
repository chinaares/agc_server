package com.lanjing.wallet.timer;

import com.lanjing.wallet.enums.EnableEnum;
import com.lanjing.wallet.model.AirdropList;
import com.lanjing.wallet.service.AirdropListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 理财释放
 *
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-17 10:11
 */
@Slf4j
@Component
public class FinancialRelease {

    @Resource(name = "AirdropListService")
    private AirdropListService airdropListService;

    //@Scheduled(cron = "0 0 1 * * ? ")
    public void calculate() {
        //获取所有理财记录
        List<AirdropList> list = airdropListService.findByStatus(EnableEnum.ON.getVal());

        int size = list.size();
        if (size > 0) {
            //log.info("理财释放,时间：{},数量：{}条", new Date(), size);
            list.forEach(airdropList -> {
                Integer id = airdropList.getId();
                //已释放天数
                Integer days = airdropList.getDays() + 1;
                //总释放天数
                Integer cycle = airdropList.getCycle();
                try {
                    if (days < cycle) {
                        //继续释放
                        airdropListService.financialRelease(airdropList, false);
                        //log.info("理财释放,理财id：{},msg：释放成功", id);
                    } else if (days == cycle) {
                        //本次释放完成
                        airdropListService.financialRelease(airdropList, true);
                       // log.info("理财释放,理财id：{},msg：已释放完成", id);
                    } else {
                       // log.error("理财释放异常,理财id：{},已释放天数days：{},总释放天数cycle：{}", id, days, cycle);
                    }
                } catch (Exception e) {
                   // log.info("理财释放失败,理财id：{},msg：{}", id, e.getMessage());
                }
            });
        }
    }
}
