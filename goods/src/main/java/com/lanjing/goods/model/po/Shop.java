package com.lanjing.goods.model.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.DoubleTwoUtil;
import lombok.Data;

/**
 * 店铺简略信息
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-23 09:44
 */
@Data
public class Shop {

    /**
     * id
     */
    private Integer id;

    /**
     * code
     */
    private Long code;

    /**
     * 好评
     */
    @JsonSerialize(using = DoubleTwoUtil.class)
    private Double praise;

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

	public Double getPraise() {
		return praise;
	}

	public void setPraise(Double praise) {
		this.praise = praise;
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
    
}
