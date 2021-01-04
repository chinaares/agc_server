package com.lanjing.goods.model;

import lombok.Data;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 * @version version 1.0.0
 */
@Data
public class Coins {
    private Integer fid;

    /**
    * 币种名称
    */
    private String coinname;

    /**
    * 英文简称
    */
    private String coinshort;

    /**
    * 价格
    */
    private Double price;

    /**
    * 币种图片
    */
    private String img;

    /**
    * 说明
    */
    private String remark;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getCoinname() {
		return coinname;
	}

	public void setCoinname(String coinname) {
		this.coinname = coinname;
	}

	public String getCoinshort() {
		return coinshort;
	}

	public void setCoinshort(String coinshort) {
		this.coinshort = coinshort;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}