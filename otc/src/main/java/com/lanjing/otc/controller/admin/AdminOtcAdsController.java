package com.lanjing.otc.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.otc.model.OtcAds;
import com.lanjing.otc.model.po.AdminAds;
import com.lanjing.otc.service.OtcAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * otc广告管理
 *
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 14:35
 */
@RestController
public class AdminOtcAdsController {
    @Autowired
    OtcAdsService otcAdsService;

    /**
     * 获取广告列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/otc/ads/adsList")
    public Object adsList(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String key = param.getString("key");
        Integer status = param.getInteger("status");
        Integer reviewStatus = param.getInteger("reviewStatus");
        Integer type = param.getInteger("type");
        Integer coinId = param.getInteger("coinId");

        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<AdminAds> otcAds = otcAdsService.findAll(key, status, reviewStatus, type, coinId);
        PageInfo<AdminAds> pageInfo = new PageInfo<>(otcAds);
        return Result.success(pageInfo);
    }

    /**
     * 通过id获取广告
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/otc/ads/getById")
    public Object getById(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        if (id == null) {
            return Result.error("参数错误");
        }
        OtcAds otcAds = otcAdsService.selectByPrimaryKey(id);
        return Result.success(otcAds);
    }

    /**
     * 广告审核
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/otc/ads/adsReview")
    public Object adsReview(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");
        if (id == null || status == null) {
            return Result.error("参数错误");
        }
        OtcAds ads = new OtcAds();
        ads.setId(id);
        ads.setReviewStatus(status);
        try {
            otcAdsService.adsReview(ads);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     * 冻结解冻
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/otc/ads/freezeThaw")
    public Object freezeThaw(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");
        if (id == null || status == null) {
            return Result.error("参数错误");
        }

        if (status != 1 && status != 2) {
            return Result.error("参数错误");
        }

        OtcAds otcAds = otcAdsService.selectByPrimaryKey(id);
        if (otcAds == null) {
            return Result.error("id不存在");
        }

        OtcAds ads = new OtcAds();
        ads.setId(id);
        ads.setReviewStatus(status);

        int result = otcAdsService.updateByPrimaryKeySelective(ads);

        if (result < 1) {
            return Result.error("修改失败");
        }
        return Result.success();
    }
}
