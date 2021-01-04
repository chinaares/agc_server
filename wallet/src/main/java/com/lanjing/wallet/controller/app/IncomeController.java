package com.lanjing.wallet.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.dao.IncomeMapper;
import com.lanjing.wallet.model.Income;
import com.lanjing.wallet.model.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class IncomeController extends BaseContrller {

    @Autowired
    private IncomeMapper incomeMapper;

    @RequestMapping("/app/getreward")
    public String getreward(@RequestBody Map<String, String> map) {
        String userkey = getUserKey();
        int page = Integer.parseInt(map.get("page"));
        int pagezise = Integer.parseInt(map.get("size"));
        int type = Integer.parseInt(map.get("type"));
        List<Income> list = incomeMapper.queryByUser(Integer.parseInt(userkey), type, (page - 1) * pagezise, pagezise);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Income income : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(income));
            jsonObject.put("createTime", format.format(income.getCreateTime()));
            jsonObjectList.add(jsonObject);
        }
        return JSONObject.toJSONString(new ResultDTO(200, "ok", jsonObjectList));
    }

    @RequestMapping("/app/income/list")
    public Object incomeList(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer type = param.getInteger("type");

        if (pageNum == null || pageSize == null || type == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Income> incomes = incomeMapper.selectByUserId(getUserKey(), type);
        PageInfo<Income> pageInfo = new PageInfo<>(incomes);
        return Result.success(pageInfo);
    }

}
