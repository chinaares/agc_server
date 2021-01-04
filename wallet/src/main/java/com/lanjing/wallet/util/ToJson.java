package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.UUIDUtil;
import com.lanjing.wallet.model.Integral;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 12:24
 */
public class ToJson {
    public static void main(String[] args) {
        Date date=new Date();
        Integral integral=new Integral();
        integral.setId(1);
        integral.setCode(UUIDUtil.nextId());
        integral.setName("测试活动积分");
        integral.setAmount( 15600);
        integral.setStartTime(date);
        integral.setEndTime(date);
        integral.setCreateTime(date);

        Object o = JSONObject.toJSON(integral);
    }
}
