package com.lanjing.goods.service;

import com.lanjing.goods.model.Admin;
import com.lanjing.goods.model.MallShop;
import com.lanjing.goods.model.MallShopExample;
import com.lanjing.goods.model.po.MallShops;
import com.lanjing.goods.model.po.Merchant;
import com.lanjing.goods.model.po.Shop;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
public interface MallShopService {


    long countByExample(MallShopExample example);

    int deleteByExample(MallShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallShop record);

    int insertSelective(MallShop record);

    List<MallShop> selectByExample(MallShopExample example);

    MallShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(MallShop record, MallShopExample example);

    int updateByExample(MallShop record, MallShopExample example);

    int updateByPrimaryKeySelective(MallShop record);

    int updateByPrimaryKey(MallShop record);

    int addMallShop(MallShop mallShop);

    /**
     * 获取店铺信息
     *
     * @param key
     * @return
     */
    List<Merchant> getMerchant(String key);

    MallShop findByCode(Long code);

    /**
     * 0 冻结，1正常，-1注销
     *
     * @param updateAdmin
     * @param updateMallShop
     * @return
     */
    int operateMerchant(Admin updateAdmin, MallShop updateMallShop);

    MallShops findByCodes(Long code);

    /**
     * 获取店铺简略信息
     *
     * @param key
     * @return
     */
    List<Shop> getShop(String key);

    /**
     * 获取精品、推荐店铺
     *
     * @return
     */
    List<Shop> findBySort();

    /**
     * 通过code获取店铺简略信息
     *
     * @param code
     * @return
     */
    Shop selectByCode(Long code);

    /**
     * 通过phone获取店铺简略信息
     *
     * @param phone
     * @return
     */
    MallShop findByPhone(String phone);
}



