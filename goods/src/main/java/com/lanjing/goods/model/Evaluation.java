package com.lanjing.goods.model;

import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Evaluation {
    private Integer id;

    /**
    * 用户Id
    */
    private Integer userid;

    /**
    * 用户昵称
    */
    private String username;

    /**
    * 用户头像
    */
    private String userpicture;

    /**
    * 订单Id
    */
    private String orderid;

    /**
    * 商品id
    */
    private Integer goodsid;

    private Integer skuid;

    /**
    * 商品评分
    */
    private Double goodsevaluation;

    /**
    * 店铺评分
    */
    private Double shopevaluation;

    /**
    * 店铺评分
    */
    private Double logisticsevaluation;

    /**
    * 评论文字内容
    */
    private String content;

    /**
    * 图片内容
    */
    private String imgs;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createtime;

    /**
    * 回复内容
    */
    private String backcontent;

    /**
    * 回复图片
    */
    private String backimgs;

    /**
    * 回复时间
    */
    private Date upttime;

    /**
     * 状态    0，待回复    1，已回复
     */
    private Integer state;

    /**
     * 商铺code
     */
    private Long shopcode;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpicture() {
		return userpicture;
	}

	public void setUserpicture(String userpicture) {
		this.userpicture = userpicture;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public Double getGoodsevaluation() {
		return goodsevaluation;
	}

	public void setGoodsevaluation(Double goodsevaluation) {
		this.goodsevaluation = goodsevaluation;
	}

	public Double getShopevaluation() {
		return shopevaluation;
	}

	public void setShopevaluation(Double shopevaluation) {
		this.shopevaluation = shopevaluation;
	}

	public Double getLogisticsevaluation() {
		return logisticsevaluation;
	}

	public void setLogisticsevaluation(Double logisticsevaluation) {
		this.logisticsevaluation = logisticsevaluation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getBackcontent() {
		return backcontent;
	}

	public void setBackcontent(String backcontent) {
		this.backcontent = backcontent;
	}

	public String getBackimgs() {
		return backimgs;
	}

	public void setBackimgs(String backimgs) {
		this.backimgs = backimgs;
	}

	public Date getUpttime() {
		return upttime;
	}

	public void setUpttime(Date upttime) {
		this.upttime = upttime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getShopcode() {
		return shopcode;
	}

	public void setShopcode(Long shopcode) {
		this.shopcode = shopcode;
	}
    
}