package com.lanjing.goods.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.Result;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.dao.ShopOrderMapper;
import com.lanjing.goods.model.Evaluation;
import com.lanjing.goods.model.Goods;
import com.lanjing.goods.model.MallShop;
import com.lanjing.goods.model.ShopOrder;
import com.lanjing.goods.service.EvaluationService;
import com.lanjing.goods.service.GoodsService;
import com.lanjing.goods.service.MallShopService;
import com.lanjing.goods.util.GetInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class EvaluationController extends BaseController {

    @Autowired
    private EvaluationService evaluationService;

    @Resource
    private ShopOrderMapper orderMapper;

    @Autowired
    private GetInformation getInformation;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MallShopService mallShopService;



    @RequestMapping("/app/orders/queryevaluation")
    public Object queryevaluation(@RequestBody JSONObject param){
        int goodsId = param.getIntValue("goodsId");
        int pageNum = param.getIntValue("pageNum");
        int pageSize = param.getIntValue("pageSize");
        List<Evaluation> evaluations = evaluationService.queryByGoodsidPage(goodsId,pageSize*(pageNum-1),pageSize);
        List<Map<String,Object>> jsonList = new ArrayList<>();
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setMaximumFractionDigits(1);
        for (Evaluation evaluation : evaluations){

            double svg = (evaluation.getGoodsevaluation()+evaluation.getShopevaluation()+evaluation.getLogisticsevaluation())/3;
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(evaluation));
            jsonObject.put("svg",nf.format(svg));
            jsonObject.put("imgs",JSONArray.parseArray(evaluation.getImgs()));
            jsonList.add(jsonObject);
        }
        int count = evaluationService.selectByGoodsidcount(goodsId);
        Map map = new HashMap();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("evaluations",jsonList);
        map.put("pages",count%pageSize == 0?count/pageSize:count/pageSize + 1);
        map.put("count",count);
        return Result.success(map);
    }
}
