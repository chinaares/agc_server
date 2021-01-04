package com.lanjing.wallet.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.model.Airdrop;
import com.lanjing.wallet.service.AirdropService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@SuppressWarnings("all")
public class AdminAirdropController {

    @Resource(name = "AirdropService")
    private AirdropService airdropService;

    /**
     * 查询理财信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/getAirdrop")
    public Object getAirdrop(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        Integer enable = param.getInteger("enable");
        String name = param.getString("name");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        Airdrop airdrop = param.toJavaObject(Airdrop.class);

        PageHelper.startPage(pageNum, pageSize);
        List<Airdrop> list = airdropService.findByNameLikeAndEnable(name,enable);
        PageInfo<Airdrop> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    /**
     * 添加理财信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/addAirdrop")
    public Object addAirdrop(@RequestBody JSONObject param) {
        Airdrop airdrop = param.toJavaObject(Airdrop.class);

        if (airdrop == null) {
            return Result.error("参数错误");
        }

        airdrop.setId(null);
        Date date = new Date();
        airdrop.setCreateTime(date);
        airdrop.setUpdateTime(date);
       if(
        airdrop.getFreeBtc().doubleValue()<0||
        airdrop.getFreedBtc().doubleValue()<0||
        airdrop.getFreedYyb().doubleValue()<0||
        airdrop.getFreeYyb().doubleValue()<0||
        airdrop.getFreezeBtc().doubleValue()<0||
        airdrop.getFreezeYyb().doubleValue()<0){
           return Result.error("参数错误");
        }
        if(airdrop.getStartTime()==null){
            airdrop.setStartTime(new Date());
        }
        int num = airdropService.insertSelective(airdrop);

        if (num > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 修改理财信息
     *
     * @param airdrop
     * @return
     */
    @RequestMapping("/admin/updateAirdrop")
    public Object updateAirdrop(@RequestBody Airdrop airdrop) {
        if (airdrop == null || airdrop.getId() == null) {
            return Result.error("参数错误");
        }


        Airdrop old = airdropService.selectByPrimaryKey(airdrop.getId());

        if (old==null){
            return Result.error("改记录不存在");
        }

        if(
                airdrop.getFreeBtc().doubleValue()<0||
                        airdrop.getFreedBtc().doubleValue()<0||
                        airdrop.getFreedYyb().doubleValue()<0||
                        airdrop.getFreeYyb().doubleValue()<0||
                        airdrop.getFreezeBtc().doubleValue()<0||
                        airdrop.getFreezeYyb().doubleValue()<0){
            return Result.error("参数错误");
        }
        if(old.getStartTime()==null){
            old.setStartTime(new Date());
        }
        int num = airdropService.updateByPrimaryKeySelective(airdrop);

        if (num > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 上架、下架、删除
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/operateAirdrop")
    public Object operateAirdrop(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer id = param.getInteger("id");
        Integer enable = param.getInteger("enable");
        if (id==null) {
            return Result.error("参数错误");
        }

        int num = airdropService.operateAirdrop(id,enable);

        if (num > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }
}
