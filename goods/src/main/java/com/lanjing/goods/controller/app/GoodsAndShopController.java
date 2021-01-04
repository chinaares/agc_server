package com.lanjing.goods.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.goods.model.po.Cargo;
import com.lanjing.goods.model.po.SalesVolume;
import com.lanjing.goods.model.po.Shop;
import com.lanjing.goods.service.GoodsService;
import com.lanjing.goods.service.MallShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 商品或店铺搜索
 */
@RestController
@SuppressWarnings("all")
@Transactional
public class GoodsAndShopController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MallShopService mallShopService;

    /**
     * 商品或店铺搜索
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/shop/search")
    public Object search(@RequestBody JSONObject param) {
        //商品、店铺分页
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        String key = param.getString("key");
        //搜索店铺时店铺商品分页
        Integer num = param.getInteger("num");
        Integer size = param.getInteger("size");

        //1商品、2商城
        Integer type = param.getInteger("type");

        if (pageSize == null || pageNum == null || type == null || StringUtils.isEmpty(key)) {
            return Result.error("Parameter error");
        }

        if (!IntStream.of(1, 2).anyMatch(x -> x == type)) {
            return Result.error("Parameter error");
        }

        if (type == 1) {
            //查询商品
            PageHelper.startPage(pageNum, pageSize);
            List<Cargo> goods = goodsService.getGoods(key);
            return Result.success(PageInfo.of(goods));
        } else {
            //查询店铺和店铺商品
            PageHelper.startPage(pageNum, pageSize);
            List<Shop> shop = mallShopService.getShop(key);
            PageInfo<Shop> shopPageInfo = PageInfo.of(shop);

            List list = new ArrayList();

            shopPageInfo.getList().forEach(s -> {
                PageHelper.startPage(num, size);
                List<Cargo> goods = goodsService.getGoodsByCode(s.getCode(), type);
                PageInfo<Cargo> info = PageInfo.of(goods);

                HashMap map = new HashMap();
                map.put("shop", s);
                map.put("goods", info);
                list.add(map);
            });
            shopPageInfo.setList(list);

            return Result.success(shopPageInfo);
        }
    }

    /**
     * 分类商品
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/shop/sortGoods")
    public Object sortGoods(@RequestBody JSONObject param) {
        //商品、店铺分页
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        //分类code
        Long code = param.getLong("code");

        if (pageSize == null || pageNum == null || code == null) {
            return Result.error("Parameter error");
        }
        //查询商品
        PageHelper.startPage(pageNum, pageSize);
        List<Cargo> goods = goodsService.sortGoods(code);
        return Result.success(PageInfo.of(goods));
    }

    /**
     * 精品、推荐店铺
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/shop/boutiqueShop")
    public Object boutiqueShop(@RequestBody JSONObject param) {
        //商品、店铺分页
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        //搜索店铺时店铺商品分页
        Integer num = param.getInteger("num");
        Integer size = param.getInteger("size");

        if (pageSize == null || pageNum == null || num == null || size==null) {
            return Result.error("Parameter error");
        }

        //查询店铺和店铺商品
        PageHelper.startPage(pageNum, pageSize);
        List<Shop> shop = mallShopService.findBySort();
        PageInfo<Shop> shopPageInfo = PageInfo.of(shop);

        List list = new ArrayList();

        shopPageInfo.getList().forEach(s -> {
            PageHelper.startPage(num, size);
            List<Cargo> goods = goodsService.getGoodsByCode(s.getCode(), null);
            PageInfo<Cargo> info = PageInfo.of(goods);

            HashMap map = new HashMap();
            map.put("shop", s);
            map.put("goods", info);
            list.add(map);
        });
        shopPageInfo.setList(list);

        return Result.success(shopPageInfo);
    }

    /**
     * 进店、店铺
     * @param param
     * @return
     */
    @RequestMapping("/app/shop/intoTheShop")
    public Object intoTheShop(@RequestBody JSONObject param) {
        Long code = param.getLong("code");
        //商品、店铺分页
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
         //1销量、2价格降序
        Integer type = param.getInteger("type");
        if(type==null){
            type = 3;
        }
        if (pageSize == null || pageNum == null) {
            return Result.error("Parameter error");
        }



        Shop shop = mallShopService.selectByCode(code);

        if (shop==null){
            return Result.error("Shop not found");
        }


        //获取店铺中商品
        PageHelper.startPage(pageNum,pageSize);
        List<Cargo> goods = goodsService.getGoodsByCode(code,type);
       // PageInfo<Cargo> pageInfo = PageInfo.of(goods);

        //获取店铺销售量
        Integer salesVolume = goodsService.sumBySalesVolume(code);

        JSONObject result=new JSONObject();
        result.put("shop",shop);
        result.put("goods",goods);
        result.put("salesVolume",salesVolume);

        return Result.success(result);
    }

    /**
     * 商城首页商品
     * @param param
     * @return
     */
    @RequestMapping("/app/shop/homeGoods")
    public Object homeGoods(@RequestBody JSONObject param) {
        //商品
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
         //1热门排序、2最新上架、3价格降序、4价格升序
        Integer type = param.getInteger("type");
        //商品名称
        String key = param.getString("key");

        //分类code
        Long code = param.getLong("code");

        //最高、最低价
        Double max = param.getDouble("max");
        Double min = param.getDouble("min");

        if (pageSize == null || pageNum == null) {
            return Result.error("Parameter error");
        }

        //获取商品
        PageHelper.startPage(pageNum,pageSize);
        List<Cargo> goods = goodsService.brushSelection(code,max,min,key,type);
        PageInfo<Cargo> pageInfo = PageInfo.of(goods);

        return Result.success(pageInfo);
    }

    /**
     * 销量榜
     * @param param
     * @return
     */
    @RequestMapping("/app/shop/salesList")
    public Object salesList(@RequestBody JSONObject param) {
        //商品
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        //分类code
        Long code = param.getLong("code");

        if (pageSize == null || pageNum == null) {
            return Result.error("Parameter error");
        }

        //获取商品
        PageHelper.startPage(pageNum,pageSize);
        List<SalesVolume> goods = goodsService.salesList(code);
        PageInfo<SalesVolume> pageInfo = PageInfo.of(goods);

        return Result.success(pageInfo);
    }


}