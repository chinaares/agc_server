package com.lanjing.goods.model.po;

import com.lanjing.goods.model.Goods;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-22 10:30
 */
@Data
public class Merchant {
    /**
     * id
     */
    private Integer id;

    /**
     * code
     */
    private Long code;

    /**
     * 管理员
     */
    private String admin;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 状态 0冻结，1启用
     */
    private Integer status;

    /**
     * 排行
     */
    private Integer sort;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 客服电话
     */
    private String tel;

    /**
     * 创建时间
     */
    private Date date;

    /**
     * 商品列表
     */
    private List<Goods> goodsList;
}
