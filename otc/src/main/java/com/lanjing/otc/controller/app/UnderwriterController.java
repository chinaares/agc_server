package com.lanjing.otc.controller.app;

import com.jester.util.utils.Result;
import com.lanjing.otc.controller.BaseController;
import com.lanjing.otc.model.Underwriter;
import com.lanjing.otc.model.Users;
import com.lanjing.otc.service.UnderwriterService;
import com.lanjing.otc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 16:41
 */
@RestController
public class UnderwriterController extends BaseController {

    @Autowired
    UnderwriterService underwriterService;

    @Autowired
    UsersService usersService;

    @RequestMapping("/app/otc/underwriter/apply")
    public Object apply() {
        Integer userId = getUserId();
        if (userId == null) {
            return Result.error("获取登陆信息失败");
        }

        Users user = usersService.selectByPrimaryKey(userId);
        if (user == null) {
            return Result.error("获取登陆信息失败");
        }

        if (user.getIsreal() != 2) {
            return Result.error("请待实名通过");
        }

        Underwriter oldUnderwriter = underwriterService.findByUserId(userId);
        if (oldUnderwriter != null) {
            return Result.error("请勿重复申请");
        }

        Date date = new Date();

        Underwriter underwriter = new Underwriter();
        underwriter.setUserId(userId);
        underwriter.setPhone(user.getPhonenum());
        underwriter.setName(user.getRealname());
        underwriter.setStatus(0);
        underwriter.setCreateTime(date);
        underwriter.setUpdateTime(date);

        int result = underwriterService.insertSelective(underwriter);
        if (result < 1) {
            return Result.error("承兑商申请失败");
        }

        return Result.success();
    }
}
