package com.lanjing.goods.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.jester.util.utils.UUIDUtil;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.dao.GoodsskuMapper;
import com.lanjing.goods.model.Address;
import com.lanjing.goods.model.Admin;
import com.lanjing.goods.model.Goods;
import com.lanjing.goods.model.Goodssku;
import com.lanjing.goods.service.AddressService;
import com.lanjing.goods.service.AdminService;
import com.lanjing.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台商品管理
 */
@RestController
public class AdminGoodsController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AddressService addressService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private GoodsskuMapper goodsskuMapper;

    /**
     * unknown
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/unknown")
    public Object unknown(@RequestBody JSONObject param) {
        return Result.success(param);
    }

    /**
     * 1上架、0下架、2删除
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/updateGood")
    public Object updateGood(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer status = param.getInteger("status");
        if (id == null || status == null) {
            return Result.error("参数错误");
        }

        int result = goodsService.delGoods(id, status);
        if (result < 1) {
            return Result.error("操作失败");
        }

        return Result.success();
    }

    /**
     * 添加商品
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/addGoods")
    public Object addGoods(@RequestBody JSONObject param) {
        Admin admin = adminService.selectByPrimaryKey(getId());
        Goods record = new Goods();
        record.setShopCode(admin.getCode());
        record.setSortCode(param.getLongValue("sortcode"));
        record.setTitle(param.getString("title"));
        record.setPrice(param.getDouble("price"));
        record.setThumbnail(param.getString("thumbnail"));
        record.setMore(param.getString("more"));
        record.setSerial(param.getInteger("serial"));
        record.setStatus(param.getInteger("status"));
        record.setImgs(param.getJSONArray("imgs").toJSONString());
        record.setUnit(param.getString("unit"));
        record.setRemark(param.getString("remark"));
        record.setGoodsType(param.getInteger("goodsType"));
        Long goodsCode = UUIDUtil.nextId();
        record.setCode(goodsCode);
        record.setCreateTime(new Date());
        int result = goodsService.insertSelective(record);
        if (result < 1) {
            return Result.error("添加失败");
        }
        Goodssku sku = new Goodssku();
        sku.setGoodscode(String.valueOf(goodsCode));
        Long skuCode = UUIDUtil.nextId();
        sku.setSkucode(String.valueOf(skuCode));
        sku.setIntegralnum(BigDecimal.ZERO);
        sku.setPrice(param.getBigDecimal("price"));
        sku.setSpecs("默认");
        sku.setUnit(param.getString("unit"));
        sku.setState(1);
        sku.setThumbnail(record.getThumbnail());
        goodsskuMapper.insertSelective(sku);
        return Result.success();
    }

    /**
     * 添加商品sku
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/addGoodsSku")
    public Object addGoodsSku(@RequestBody Goodssku param) {
        if (param.getGoodscode() == null) {
            return Result.error("商品不存在！");
        }
        Goods goods = goodsService.selectByCode(Long.valueOf(param.getGoodscode()));
        if (goods == null) {
            return Result.error("商品不存在！");
        }
        param.setUnit(goods.getUnit());
        param.setSkuname(goods.getTitle());
        param.setThumbnail(goods.getThumbnail());
        Long skucode = UUIDUtil.nextId();
        param.setSkucode(String.valueOf(skucode));
        param.setRemark(goods.getRemark());
        int result = goodsskuMapper.insertSelective(param);
        if (result < 1) {
            return Result.error("添加失败");
        }
        return Result.success();
    }

    /**
     * 修改商品sku
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/uptGoodsSku")
    public Object uptGoodsSku(@RequestBody Goodssku param) {
        if (param.getId() == null) {
            return Result.error("该商品规格不存在");
        }
        Goods goods = goodsService.selectByCode(Long.valueOf(param.getGoodscode()));
        if (goods == null) {
            return Result.error("商品不存在！");
        }
        param.setSkuname(goods.getTitle() + "-" + param.getSpecs());
        int result = goodsskuMapper.updateByPrimaryKeySelective(param);
        if (result < 1) {
            return Result.error("修改失败");
        }
        return Result.success();
    }


    /**
     * 更新商品信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/updateGoods")
    public Object updateGoods(@RequestBody JSONObject param) {
        Goods record = new Goods();
        record.setId(param.getInteger("id"));
        record.setSortCode(param.getLongValue("sortcode"));
        record.setTitle(param.getString("title"));
        record.setPrice(param.getDouble("price"));
        record.setThumbnail(param.getString("thumbnail"));
        record.setMore(param.getString("more"));
        record.setSerial(param.getInteger("serial"));
        record.setStatus(param.getInteger("status"));
        record.setGoodsType(param.getInteger("goodsType"));
        record.setImgs(param.getJSONArray("imgs").toJSONString());
        record.setUnit(param.getString("unit"));
        record.setRemark(param.getString("remark"));

        if (record.getId() == null) {
            return Result.error("请传入商品id");
        }
        int result = goodsService.updateByPrimaryKeySelective(record);
        if (result < 1) {
            return Result.error("更新失败");
        }
        return Result.success();
    }

    /**
     * 通过id获取商品
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/selectGoodsById")
    public Object selectGoodsById(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        if (id == null) {
            return Result.error("参数错误");
        }
        Goods goods = goodsService.selectByPrimaryKey(id);
        /*JSONObject goodsjson = JSON.parseObject(JSONObject.toJSONString(goods));
        List<Goodssku> goodsskuList = goodsskuMapper.selectBygoodsCode(String.valueOf(goods.getCode()));
        goodsjson.put("standard",goodsskuList);*/
        return Result.success(goods);
    }

    /**
     * 获取商品列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/getGoods")
    public Object getGoods(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer status = param.getInteger("status");
        String name = param.getString("name");
        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }

        Admin admin = adminService.selectByPrimaryKey(getId());
        if (admin == null) {
            return Result.error(600, "登录过期");
        }
        int count = 0;
        if (admin.getArole() == 0) {
            count = goodsService.getGoodsBycount(admin.getCode(), name, status);
        } else {
            String shop_code = param.getString("shopcode");
            long shopcode = 0;
            if (shop_code == null || shop_code == "") {
                shopcode = 0;
            } else {
                shopcode = Long.parseLong(shop_code);
            }
            count = goodsService.getGoodsBycount(shopcode, name, status);
        }
        PageHelper.startPage(pageNum, pageSize);

        List<Goods> goodslist = null;
        if (admin.getArole() == 0) {
            goodslist = goodsService.getGoods(admin.getCode(), name, status);
        } else {
            String shop_code = param.getString("shopcode");
            long shopcode = 0;
            if (shop_code == null || shop_code == "") {
                shopcode = 0;
            } else {
                shopcode = Long.parseLong(shop_code);
            }
            goodslist = goodsService.getGoods(shopcode, name, status);
        }
        List<JSONObject> goodsjsonlist = new ArrayList<>();
        for (Goods goods : goodslist) {
            JSONObject goodsjson = JSON.parseObject(JSONObject.toJSONString(goods));
            List<Goodssku> goodsskuList = goodsskuMapper.selectBygoodsCode(String.valueOf(goods.getCode()));
            int num = 0;
            List<Goodssku> list = new ArrayList<>();
            for (Goodssku goodssku : goodsskuList) {
                if (goodssku.getState() == 1) {
                    num += goodssku.getSum();
                    list.add(goodssku);
                }
            }
            goodsjson.put("standard", list);
            goodsjson.put("sum", num);
            goodsjsonlist.add(goodsjson);
        }

        PageInfo<JSONObject> pageInfo = new PageInfo<>(goodsjsonlist);
        pageInfo.setPages(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setSize(count);

        return Result.success(pageInfo);
    }


    /**
     * 获取商品规格库存列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/getGoodsskus")
    public Object getGoodsskus(@RequestBody JSONObject param) {
        String goodscode = param.getString("goodscode");
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Goodssku> goodsskuList = goodsskuMapper.selectBygoodsCode(goodscode);
        PageInfo<Goodssku> pageInfo = new PageInfo<>(goodsskuList);
        return Result.success(pageInfo);
    }

    /**
     * 修改商品库存
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/goods/addGoodsskunum")
    public Object uptGoodsskunum(@RequestBody JSONObject param) {
        int num = param.getIntValue("num");
        int Id = param.getIntValue("id");
        Goodssku goodssku = goodsskuMapper.selectByPrimaryKey(Id);
        if (goodssku == null) {
            return Result.error("该规格不存在");
        }
        goodssku.setId(Id);
        goodssku.setSum(goodssku.getSum() + num);
        int result = goodsskuMapper.updateByPrimaryKeySelective(goodssku);
        if (result < 1) {
            return Result.error("添加失败");
        }
        return Result.success();
    }

    @RequestMapping("/admin/address/getAddress")
    public Object getAddress(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Address> addresses = addressService.getAddress(null, param.getString("fId"));
        PageInfo<Address> pageInfo = new PageInfo<>(addresses);

        return Result.success(pageInfo);
    }

}