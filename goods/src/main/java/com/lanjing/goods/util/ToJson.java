package com.lanjing.goods.util;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.UUIDUtil;
import com.lanjing.goods.model.MallShop;

import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 12:24
 */
public class ToJson {
    public static void main(String[] args) {
        Date date=new Date();
        MallShop mallSort=new MallShop();
        mallSort.setCode(UUIDUtil.nextId());
        mallSort.setName("店铺名称");
        mallSort.setSynopsis("出售:西瓜、啤酒、饮料、矿泉水");
        mallSort.setThumbnail("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566449983410&di=acf174f73a13aaec39d4493f107115ad&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20130618%2FImg379130986.jpg");
        mallSort.setContact("特朗普");
        mallSort.setContactNumber("2654513135");
        mallSort.setConsumerHotline("141541656");
        mallSort.setCity("加尼福利亚");
        mallSort.setLogistics("联邦快递");
        mallSort.setType(0);
        mallSort.setSort(0);
        mallSort.setCreateTime(date);
        mallSort.setUpdateTime(date);


        Object o = JSONObject.toJSON(mallSort);
        System.out.println(o.toString());
    }
}
