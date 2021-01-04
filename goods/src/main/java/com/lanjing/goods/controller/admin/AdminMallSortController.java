package com.lanjing.goods.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.jester.util.utils.UUIDUtil;
import com.lanjing.goods.model.MallSort;
import com.lanjing.goods.service.MallSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 分类管理
 *
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 10:14
 */
@RestController
@SuppressWarnings("all")
public class AdminMallSortController {
    @Autowired
    private MallSortService mallSortService;

    /**
     * 获取分类信息
     * @param param
     * @return
     */
    @RequestMapping({"/admin/shop/getMallSort","/app/shop/getMallSort"})
    public Object getMallSort(@RequestBody JSONObject param){

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum,pageSize);
        List list =mallSortService.selectAll();
        PageInfo pageInfo=PageInfo.of(list);

        return Result.success(pageInfo);
    }

    /**
     * 添加分类信息
     * @param mallSort
     * @return
     */
    @RequestMapping("/admin/shop/addMallSort")
    public Object addMallSort(@RequestBody MallSort mallSort){
        mallSort.setId(null);
        mallSort.setCode(UUIDUtil.nextId());
        mallSort.setCreateTime(new Date());
        int result =mallSortService.insertSelective(mallSort);

        return Result.rows(result);
    }

    /**
     * 更新分类信息
     * @param mallSort
     * @return
     */
    @RequestMapping("/admin/shop/updateMallSort")
    public Object updateMallSort(@RequestBody MallSort mallSort){
        if (mallSort.getId()==null) {
            return Result.error("ID is empty");
        }
        int result =mallSortService.updateByPrimaryKeySelective(mallSort);

        return Result.rows(result);
    }

    /**
     * 禁用、启用、删除
     * @param mallSort
     * @return
     */
    @RequestMapping("/admin/shop/operateMallSort")
    public Object operateMallSort(@RequestBody JSONObject param){

        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");

        if (id==null||status==null) {
            return Result.error("参数错误");
        }

        MallSort mallSort=new MallSort();
        mallSort.setId(id);
        mallSort.setStatus(status);

        int result =mallSortService.updateByPrimaryKeySelective(mallSort);

        return Result.rows(result);
    }
}
