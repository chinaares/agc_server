package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.dao.IntegralListMapper;
import com.lanjing.wallet.model.po.Integrals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 积分模块
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-29 17:20
 */
@RestController
public class AdminIntegralsController {
    @Autowired
    IntegralListMapper integralListMapper;

    /**
     * 获取积分记录
     * @param param
     * @return
     */
    @RequestMapping("/admin/integral/getIntegrals")
    public Object getIntegrals(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String key = param.getString("key");


        String start = param.getString("start");
        if(start==null||start.equals("")){
            start= "1970-01-01 00:00:00";
        }

        String end = param.getString("end");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(end==null||end.equals("")){
            end = sdf.format(new Date());
        }



        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum,pageSize);
        List<Integrals> integrals = integralListMapper.findByUserId(key,start,end);

        return Result.success(PageInfo.of(integrals));
    }
}
