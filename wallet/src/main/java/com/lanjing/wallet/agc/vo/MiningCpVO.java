package com.lanjing.wallet.agc.vo;

import java.math.BigDecimal;

/**
 * 基础算力挖矿VO
 */
public class MiningCpVO {
	
	/**用户ID*/
	private String userId;
	
	/**消费金额*/
	private BigDecimal amount;

	/**消费数量*/
	private BigDecimal number;
	
	/**基础算力*/
	private BigDecimal consumeCp;

	/**活动算力*/
	private BigDecimal activityCp;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getConsumeCp() {
		return consumeCp;
	}

	public void setConsumeCp(BigDecimal consumeCp) {
		this.consumeCp = consumeCp;
	}

	public BigDecimal getActivityCp() {
		return activityCp;
	}

	public void setActivityCp(BigDecimal activityCp) {
		this.activityCp = activityCp;
	}
	
}
