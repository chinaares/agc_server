package com.lanjing.goods.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.Result;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.model.Admin;
import com.lanjing.goods.model.Evaluation;
import com.lanjing.goods.service.AdminService;
import com.lanjing.goods.service.EvaluationService;
import com.lanjing.goods.util.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AdminEvaluationController extends BaseController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/orders/queryevaluation")
    public Object queryevaluation(@RequestBody JSONObject param){
        int pageNum = param.getIntValue("pageNum");
        int pageSize = param.getIntValue("pageSize");
        Integer state = param.getInteger("state");
        Admin admin = adminService.selectByPrimaryKey(getId());
        if(admin == null){
            JSONObject.toJSONString(new ResultDTO(600,"请重新登录"));
        }
        List<Evaluation> evaluations = evaluationService.queryAll(state,admin.getCode(),null,pageSize*(pageNum-1),pageSize);
        List<JSONObject> jsonList = new ArrayList<>();
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setMaximumFractionDigits(1);
        for (Evaluation evaluation : evaluations){
            double svg = (evaluation.getGoodsevaluation()+evaluation.getShopevaluation()+evaluation.getLogisticsevaluation())/3;
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(evaluation));
            jsonObject.put("svg",nf.format(svg));
            jsonObject.put("imgs", JSONArray.parseArray(evaluation.getImgs()));
            jsonList.add(jsonObject);
        }
        int count = evaluationService.queryAllcount(state,admin.getCode(),null);
        Map map = new HashMap();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("state",state);
        map.put("evaluations",jsonList);
        map.put("pages",count%pageSize == 0?count/pageSize:count/pageSize + 1);
        return Result.success(map);
    }

    @RequestMapping("/admin/orders/backevaluation")
    public Object backevaluation (@RequestBody JSONObject param){
        int Id = param.getIntValue("Id");
        String backcontent = param.getString("backcontent");
        String imgs = param.getString("imgs");
        Evaluation evaluation = evaluationService.selectByPrimaryKey(Id);
        if (evaluation == null){
            return Result.error("Not find");
        }
        evaluation.setBackcontent(backcontent);
        evaluation.setUpttime(new Date());
        String[] strings = imgs.split(",");
        JSONArray jsonArray = new JSONArray();
        for (String str : strings){
            jsonArray.add(str);
        }
        evaluation.setBackimgs(jsonArray.toJSONString());
        evaluation.setState(1);
        int result = evaluationService.updateByPrimaryKeySelective(evaluation);
        if (result > 0){
            return Result.success();
        }
        return Result.error("error");
    }

    

}
