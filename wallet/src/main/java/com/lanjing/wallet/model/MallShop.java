package com.lanjing.wallet.model;

import java.util.Date;
import lombok.Data;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-09-04 16:46
 * @version version 1.0.0
 */
@Data
public class MallShop {
    /**
    * id
    */
    private Integer id;

    /**
    * code
    */
    private Long code;

    /**
    * 店铺名
    */
    private String name;

    /**
    * 简介
    */
    private String synopsis;

    /**
    * 缩略图
    */
    private String thumbnail;

    /**
    * 联系人
    */
    private String contact;

    /**
    * 联系人电话
    */
    private String contactNumber;

    /**
    * 客服电话
    */
    private String consumerHotline;

    /**
    * 发货城市
    */
    private String city;

    /**
    * 物流公司
    */
    private String logistics;

    /**
    * 0、普通，1、推荐店铺
    */
    private Integer popular;

    /**
    * 0、普通，1、精品店铺、-1冻结或注销
    */
    private Integer type;

    /**
    * 排行
    */
    private Integer sort;

    /**
    * 好评/百分比
    */
    private Double praise;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getConsumerHotline() {
		return consumerHotline;
	}

	public void setConsumerHotline(String consumerHotline) {
		this.consumerHotline = consumerHotline;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public Integer getPopular() {
		return popular;
	}

	public void setPopular(Integer popular) {
		this.popular = popular;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Double getPraise() {
		return praise;
	}

	public void setPraise(Double praise) {
		this.praise = praise;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}