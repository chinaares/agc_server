package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.jester.util.utils.UUIDUtil;
import com.lanjing.wallet.model.Integral;
import com.lanjing.wallet.service.IntegralService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-19 10:21
 */
@RestController
@SuppressWarnings("all")
public class AdminIntegralController {
    @Resource(name = "IntegralService")
    private IntegralService integralService;

    /**
     * 添加活动积分信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/addIntegral")
    public Object addIntegral(@RequestBody JSONObject param) {
        Integral integral = param.toJavaObject(Integral.class);
        integral.setId(null);
        integral.setCode(UUIDUtil.nextId());
        integral.setCreateTime(new Date());

        int result = integralService.insertSelective(integral);

        if (result < 1) {
            return Result.error("添加失败");
        }

        return Result.success();
    }

    /**
     * 修改活动积分信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/updateIntegral")
    public Object updateIntegral(@RequestBody JSONObject param) {
        Integral integral = param.toJavaObject(Integral.class);

        if (integral == null) {
            return Result.error("参数错误");
        }

        Integer id = integral.getId();
        if (id == null) {
            return Result.error("参数错误");
        }

        Integral oldInfo = integralService.selectByPrimaryKey(id);
        if (oldInfo == null) {
            return Result.error("ID不存在");
        }

        int result = integralService.updateByPrimaryKeySelective(integral);

        if (result < 1) {
            return Result.error("修改失败");
        }

        return Result.success();
    }

    /**
     * 删除活动积分信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/delIntegral")
    public Object delIntegral(@RequestBody JSONObject param) {

        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");
        if (id == null || status == null) {
            return Result.error("参数错误");
        }

        int result = integralService.updateStatusById(status, id);

        if (result < 1) {
            return Result.error("操作失败");
        }

        return Result.success();
    }

    /**
     * 获取活动积分信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/getIntegral")
    public Object getIntegral(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Integral> list = integralService.findAllByCode(null);
        PageInfo<Integral> pageInfo = PageInfo.of(list);

        return Result.success(pageInfo);
    }
}
