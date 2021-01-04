package com.lanjing.goods.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.Result;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.dao.GoodsskuMapper;
import com.lanjing.goods.dao.ShopcatMapper;
import com.lanjing.goods.model.Goodssku;
import com.lanjing.goods.model.MallShop;
import com.lanjing.goods.model.Shopcat;
import com.lanjing.goods.service.MallShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Transactional
public class ShopcatController extends BaseController {

    @Autowired
    private ShopcatMapper shopcatMapper;

    @Autowired
    private MallShopService mallShopService;

    @Autowired
    private GoodsskuMapper goodsskuMapper;

    /**
     * 查询购物车
     *
     * */
    @RequestMapping("/app/goods/getShopcat")
    public Object queryShopcat(){
        List<Shopcat> shopcats = shopcatMapper.selectByUserid(Integer.valueOf(getUserKey()));
        Set<String> shopstr = new HashSet<>();
        List<JSONObject> shopcatsjson = new ArrayList<>();
        for (Shopcat shopcat : shopcats){
            shopstr.add(String.valueOf(shopcat.getShopCode()));
        }
        for (String s : shopstr){
            JSONObject json = new JSONObject();
            MallShop mallShop = mallShopService.findByCode(Long.valueOf(s));
            json.put("mallshopname",mallShop.getName());
            json.put("mallshopcode",mallShop.getCode());
            List<JSONObject> map = new ArrayList<>();
            for (Shopcat shopcat : shopcats){
                if (shopcat.getShopCode().longValue() == mallShop.getCode() ){
                    Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(shopcat.getGoodsskuid());
                    JSONObject goodsskujson = JSON.parseObject(JSONObject.toJSONString(goodssku),JSONObject.class);
                    goodsskujson.put("shopcatid",shopcat.getId());
                    goodsskujson.put("num",shopcat.getNum());
                    goodsskujson.put("goodsId",shopcat.getGoodsid());
                    map.add(goodsskujson);
                }
            }
            json.put("goodsskus",map);
            shopcatsjson.add(json);
        }
        return Result.success(shopcatsjson);
    }

    /***
     * 修改购物车
     *
     */
    @RequestMapping("/app/goods/uptShopcat")
    public Object uptShopcat(@RequestBody JSONObject param){
        int shopcatid = param.getIntValue("shopcatid");
        int num = param.getIntValue("num");
        Shopcat shopcat = shopcatMapper.selectByPrimaryKey(shopcatid);
        if (shopcat != null){
            shopcat.setNum(num);
            shopcatMapper.updateByPrimaryKeySelective(shopcat);
            if (num <= 0){
                shopcatMapper.deleteByPrimaryKey(shopcatid);
            }
        }
        return Result.success();
    }


    /***
     * 购物车删除商品
     *
     */
    @RequestMapping("/app/goods/delShopcat")
    public Object delShopcat(@RequestBody JSONObject param){
        String shopcatids = param.getString("shopcatids");
        if (shopcatids != null){
            String[] shopcatidlist = shopcatids.split(",");
            for (String shopcatid : shopcatidlist){
                shopcatMapper.deleteByPrimaryKey(Integer.valueOf(shopcatid));
            }
        }
        return Result.success();
    }

    /***
     * 添加购物车
     *
     */
    @RequestMapping("/app/goods/addShopcat")
    public Object addShopcat(@RequestBody JSONObject param){
        Shopcat shopcat = shopcatMapper.selectByGoodsidAndGoodsskuid(Integer.valueOf(getUserKey()),param.getInteger("goodsId"),param.getInteger("goodssukId"));
        int result = 0;
        if(shopcat == null){
            shopcat = new Shopcat();
            shopcat.setUserid(Integer.valueOf(getUserKey()));
            shopcat.setGoodsid(param.getInteger("goodsId"));
            shopcat.setShopCode(param.getLongValue("shopcode"));
            shopcat.setGoodsskuid(param.getInteger("goodssukId"));
            shopcat.setNum(param.getIntValue("num"));
            result = shopcatMapper.insertSelective(shopcat);
        }else {
            shopcat.setNum(shopcat.getNum() + param.getIntValue("num"));
            result = shopcatMapper.updateByPrimaryKeySelective(shopcat);
        }
        if (result > 0){
            return Result.success();
        }
        return Result.error("error");
    }

}
