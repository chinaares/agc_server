package com.lanjing.goods.model;

import java.util.Date;
import lombok.Data;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-23 16:35
 */
@Data
public class Goods {
    /**
     * id
     */
    private Integer id;

    /**
     * code关联属性等
     */
    private Long code;

    /**
     * 店铺code
     */
    private Long shopCode;

    /**
     * 商品分类code
     */
    private Long sortCode;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 价格
     */
    private Double price;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 轮播图
     */
    private String imgs;

    /**
     * 商品详情
     */
    private String more;

    /**
     * 排序 可设置 热门商品（预留）
     */
    private Integer serial;

    /**
     * 上下架（1上架，0下架，2删除）
     */
    private Integer status;

    /**
     * 规格参数（存json数据、预留字段）
     */
    private String standard;

    /**
     * 销售量
     */
    private Integer salesVolume;

    /**
     * 好评数量
     */
    private Integer praise;

    /**
     * 商品单位
     */
    private String unit;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 标签
     */
    private String remark;

	/**
	 * 商品类型
	 */
    private Integer goodsType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getShopCode() {
		return shopCode;
	}

	public void setShopCode(Long shopCode) {
		this.shopCode = shopCode;
	}

	public Long getSortCode() {
		return sortCode;
	}

	public void setSortCode(Long sortCode) {
		this.sortCode = sortCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
}