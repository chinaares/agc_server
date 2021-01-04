package com.lanjing.wallet.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.Result;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.model.Integral;
import com.lanjing.wallet.service.IntegralListService;
import com.lanjing.wallet.service.IntegralService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-19 10:21
 */
@RestController
@SuppressWarnings("all")
public class IntegralController extends BaseContrller {
    @Resource(name = "IntegralService")
    private IntegralService integralService;

    @Resource(name = "IntegralListService")
    private IntegralListService integralListService;

    /**
     * 添加活动积分信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/earnPoints")
    public Object earnPoints(@RequestBody JSONObject param) {
        Long code = param.getLong("code");
        Integer userId = getUserId();
        if (code == null) {
            return Result.error("Parameter error");
        }

        if (userId == null) {
            return Result.error("please login again");
        }

        Integral integral = integralService.findByCode(code);

        if (integral == null) {
            return Result.error("Code does not exist");
        }

        Long startTime = integral.getStartTime().getTime();
        Long endTime = integral.getEndTime().getTime();
        Long now = System.currentTimeMillis();

        if (now < startTime) {
            return Result.error(204, "Activity has not started", integral);
        }

        if (now > endTime) {
            return Result.error(205, "The event is over", integral);
        }

        int result;
        //0 未开始，1 进行中，2已结束
        Integer status = integral.getStatus();
        if (status == 1) {
            //是否参加过
            long count = integralListService.countByUserIdAndIId(integral.getId(), userId);

            if (count > 0) {
                return Result.error(203, "Already involved", integral);
            }

            try {
                result = integralService.earnPoints(integral, userId);
            } catch (Exception e) {
                return Result.error(202, e.getMessage(), integral);
            }
        } else if (status == 0) {
            return Result.error(204, "Activity has not started", integral);
        } else {
            return Result.error(205, "The event is over", integral);
        }

        if (result < 1) {
            return Result.error(202, "Please try again later", integral);
        }

        return Result.success(integral);
    }
}
