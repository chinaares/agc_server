package com.lanjing.goods.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.goods.dao.GoodsskuMapper;
import com.lanjing.goods.model.Goods;
import com.lanjing.goods.model.Goodssku;
import com.lanjing.goods.model.MallShop;
import com.lanjing.goods.model.po.Cargo;
import com.lanjing.goods.model.po.Standard;
import com.lanjing.goods.service.GoodsService;
import com.lanjing.goods.service.MallShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * app商城管理
 */
@RestController
@Transactional
public class GoodsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsskuMapper goodsskuMapper;

    @Autowired
    private MallShopService mallShopService;

    /**
     * 通过code获取商品id
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/goods/selectGoodsIdByCode")
    public Object selectGoodsIdByCode(@RequestBody JSONObject param) {
        String code = param.getString("code");
        if (code == null) {
            return Result.error("参数错误");
        }
        Goods goods = goodsService.selectByCode(Long.parseLong(code));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsId", goods.getId());
        return Result.success(jsonObject);
    }


    /**
     * 通过id获取商品
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/goods/selectGoodsById")
    public Object selectGoodsById(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        if (id == null) {
            return Result.error("参数错误");
        }
        Goods goods = goodsService.selectByPrimaryKey(id);
        List<Goodssku> goodsskuList = goodsskuMapper.selectBygoodsCode(String.valueOf(goods.getCode()));
        List<Goodssku> list = new ArrayList<>();
        for (Goodssku goodssku : goodsskuList) {
            if (goodssku.getState() == 1) {
                list.add(goodssku);
            }
        }
        /*JSONArray standard = JSONArray.parseArray(goods.getStandard());
        List<Standard> standards = JSONObject.parseArray(standard.toJSONString(), Standard.class);*/
        JSONObject goodsjson = JSON.parseObject(JSONObject.toJSONString(goods));
        MallShop mallShop = mallShopService.findByCode(goods.getShopCode());
        goodsjson.put("city", mallShop.getCity());
        goodsjson.put("logistics", mallShop.getLogistics());
        goodsjson.put("phone", mallShop.getConsumerHotline());

        List<String> imgs = JSON.parseArray(goods.getImgs(), String.class);
        goodsjson.put("standard", list);
        goodsjson.put("imgs", imgs);
        return Result.success(goodsjson);
    }

    @RequestMapping("/app/goods/getGoodssku")
    public Object getGoodssku(@RequestBody JSONObject param) {
        int id = param.getIntValue("id");
        Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(id);
        if (goodssku == null || goodssku.getState() != 1) {
            return Result.error("该规格不存在");
        }
        return Result.success(goodssku);
    }

    /**
     * 获取商品列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/goods/getGoods")
    public Object getGoods(@RequestBody JSONObject param) {
        {
            Integer pageNum = param.getInteger("pageNum");
            Integer pageSize = param.getInteger("pageSize");

            if (pageSize == null || pageNum == null) {
                return Result.error("参数错误");
            }

            PageHelper.startPage(pageNum, pageSize);
            List<Cargo> goods = goodsService.getGoods();
            PageInfo<Cargo> pageInfo = new PageInfo<>(goods);

            return Result.success(pageInfo);
        }
    }

    /**
     * 获取热卖单品列表 和 首发新品列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/goods/goodsByTypeList")
    public Object goodsByTypeList(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer type = param.getInteger("type");

        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Cargo> goods = goodsService.goodsByTypeList(type);
        PageInfo<Cargo> pageInfo = new PageInfo<>(goods);
        return Result.success(pageInfo);
    }
}