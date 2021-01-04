package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.model.Banner;
import com.lanjing.wallet.service.BannerService;
import com.lanjing.wallet.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * banner图管理
 *
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-26 10:18
 */
@RestController
public class AdminBannerController {

    @Resource(name = "BannerService")
    BannerService bannerService;

    /**
     * 添加banner
     *
     * @return
     */
    @RequestMapping("/admin/banner/addBanner")
    public Object addBanner(@RequestBody Banner banner) {
        if (StringUtils.isEmpty(banner.getUrl())) {
            return Result.error("参数缺失");
        }
        if (banner.getLanguage() != null) {
            if (!banner.getLanguage().equals(1) && !banner.getLanguage().equals(2) && !banner.getLanguage().equals(3)) {
                return Result.error("语言类型错误");
            }
        }
        if (banner.getType() != null) {
            if (!banner.getType().equals(1) && !banner.getType().equals(2) && !banner.getType().equals(3)) {
                return Result.error("轮播图类型错误错误");
            }
        }
        banner.setLanguage(banner.getLanguage() == null ? 1 : banner.getLanguage());
        banner.setType(banner.getType() == null ? 2 : banner.getType());
        banner.setCreateTime(new Date());
        banner.setModifyTime(new Date());
        banner.setStatus(1);
        //http://agc/goods?agc=商品编号
        int result = bannerService.insertSelective(banner);
        return Result.rows(result);
    }

    /**
     * 添加banner
     *
     * @return
     */
    @RequestMapping("/admin/banner/deleteBanner")
    public Object deleteBanner(@RequestBody Banner banner) {
        Banner banner1 = bannerService.selectByPrimaryKey(banner.getId());
        if (banner1 == null) {
            return Result.error("参数缺失");
        }
        int result = bannerService.deleteBanner(banner);
        return Result.rows(result);
    }

    /**
     * 更新banner
     *
     * @return
     */
    @RequestMapping("/admin/banner/updateBanner")
    public Object updateBanner(@RequestBody Banner banner) {
        Banner banner1 = bannerService.selectByPrimaryKey(banner.getId());
        if (banner1 == null) {
            return Result.error("参数缺失");
        }
        int result = bannerService.updateByPrimaryKeySelective(banner);
        return Result.rows(result);
    }


    /**
     * 查询banner
     *
     * @return
     */
    @RequestMapping("/admin/banner/queryBanner")
    public Object queryBanner(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Banner> list = bannerService.selectAll();
        PageInfo pageInfo = PageInfo.of(list);
        return Result.success(pageInfo);
    }


    /**
     * 获取banner
     *
     * @return
     */
    @RequestMapping({"/admin/banner/getBanner", "/app/banner/getBanner"})
    public Object getBanner(@RequestBody JSONObject param) {
        Integer type = param.getInteger("type");
        if (type == null) {
            return Result.error("Parameter error");
        }
        List<Banner> banners_zh = bannerService.selectByTypeAndLanguage(type, 1);
        List<Banner> banners_en = bannerService.selectByTypeAndLanguage(type, 2);
        List<Banner> banners_jp = bannerService.selectByTypeAndLanguage(type, 3);

        HashMap map = new HashMap();
        map.put("banners_zh", banners_zh);
        map.put("banners_en", banners_en);
        map.put("banners_jp", banners_jp);

        return Result.success(map);
    }

    /**
     * 操作banner
     *
     * @return
     */
    @RequestMapping("/admin/banner/operatingBanner")
    public Object operatingBanner(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");

        if (id == null || status == null) {
            return Result.error("参数错误");
        }

        int result = bannerService.updateStatusById(status, id);

        return Result.rows(result);
    }
}
