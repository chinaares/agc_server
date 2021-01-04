package com.lanjing.goods.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-22 15:57
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MallFundList {
    /**
     * id
     */
    private Integer id;

    /**
     * 店铺code
     */
    private Long code;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用户id(预留)
     */
    private Integer userId;

    /**
     * 币种id
     */
    private Integer coinId;

    /**
     * 币种名称
     */
    private String coinName;

    /**
     * 类型(1、收入，2转出，3其他)
     */
    private Integer type;

    /**
     * 数额
     */
    private BigDecimal amount;

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

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCoinId() {
		return coinId;
	}

	public void setCoinId(Integer coinId) {
		this.coinId = coinId;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}