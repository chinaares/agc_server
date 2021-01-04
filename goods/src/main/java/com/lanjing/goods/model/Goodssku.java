package com.lanjing.goods.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Goodssku {
    private Integer id;

    /**
    * skucode
    */
    private String skucode;

    /**
    * 商品code
    */
    private String goodscode;

    /**
    * sku名称
    */
    private String skuname;

    /**
    * 缩略图
    */
    private String thumbnail;

    /**
    * 规格
    */
    private String specs;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 币种
    */
    private Integer coinid;

    /**
    * 币种名称
    */
    private String coinname;

    /**
    * 积分数量
    */
    private BigDecimal integralnum;

    /**
     * 积分价格
     */
    private BigDecimal integralprice;

    /**
    * 库存
    */
    private Integer sum;

    /**
    * 单位
    */
    private String unit;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 标签
     */
    private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkucode() {
		return skucode;
	}

	public void setSkucode(String skucode) {
		this.skucode = skucode;
	}

	public String getGoodscode() {
		return goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}

	public String getSkuname() {
		return skuname;
	}

	public void setSkuname(String skuname) {
		this.skuname = skuname;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCoinid() {
		return coinid;
	}

	public void setCoinid(Integer coinid) {
		this.coinid = coinid;
	}

	public String getCoinname() {
		return coinname;
	}

	public void setCoinname(String coinname) {
		this.coinname = coinname;
	}

	public BigDecimal getIntegralnum() {
		return integralnum;
	}

	public void setIntegralnum(BigDecimal integralnum) {
		this.integralnum = integralnum;
	}

	public BigDecimal getIntegralprice() {
		return integralprice;
	}

	public void setIntegralprice(BigDecimal integralprice) {
		this.integralprice = integralprice;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}