package com.lanjing.goods.model;

import lombok.Data;

@Data
public class Shopcat {
    private Integer id;

    /**
    * 用户id
    */
    private Integer userid;

    /**
    * 商铺code
    */
    private Long shopCode;

    /**
    * 商品Id
    */
    private Integer goodsid;

    /**
    * 规格id
    */
    private Integer goodsskuid;

    /**
     * 数量
     */
    private Integer num;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Long getShopCode() {
		return shopCode;
	}

	public void setShopCode(Long shopCode) {
		this.shopCode = shopCode;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getGoodsskuid() {
		return goodsskuid;
	}

	public void setGoodsskuid(Integer goodsskuid) {
		this.goodsskuid = goodsskuid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
    
}