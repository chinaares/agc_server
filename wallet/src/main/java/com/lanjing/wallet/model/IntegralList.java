package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-19 11:40
 * @version version 1.0.0
 */
@Data
public class IntegralList {
    private Integer id;

    /**
    * 活动积分id
    */
    private Integer iId;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 0 用户参加活动获取积分
    */
    private Integer type;

    /**
    * 积分数
    */
    private BigDecimal amount;

    /**
    * 备注
    */
    private String remark;

    /**
    * 创建时间
    */
    private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getiId() {
		return iId;
	}

	public void setiId(Integer iId) {
		this.iId = iId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}