package com.lanjing.otc.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.otc.controller.BaseController;
import com.lanjing.otc.model.Underwriter;
import com.lanjing.otc.service.UnderwriterService;
import com.lanjing.otc.service.UsersService;
import com.lanjing.otc.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 16:41
 */
@RestController
public class AdminUnderwriterController extends BaseController {

    @Autowired
    UnderwriterService underwriterService;

    @Autowired
    UsersService usersService;

    /**
     * 承兑商申请列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/otc/underwriter/list")
    public Object list(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer status = param.getInteger("status");
        String key = param.getString("key");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Underwriter> underwriterList = underwriterService.findAllByStatusAndPhone(status, key);
        PageInfo<Underwriter> pageInfo = new PageInfo<>(underwriterList);

        return Result.success(pageInfo);
    }

    @RequestMapping("/admin/otc/underwriter/review")
    public Object review(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");

        if (id == null || status == null) {
            return Result.error("参数错误");
        }

        Underwriter underwriter = underwriterService.selectByPrimaryKey(id);
        if (underwriter == null) {
            return Result.error("id不存在");
        }

        underwriter.setStatus(status);

        int result = underwriterService.updateByPrimaryKeySelective(underwriter);

        if (result < 1) {
            return Result.error("审核失败");
        }

        //发送审核通知
        SMSUtil.sendUnderwriterMsg(underwriter.getPhone(),status);

        return Result.success();
    }
}
