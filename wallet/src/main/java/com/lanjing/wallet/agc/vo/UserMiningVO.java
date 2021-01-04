package com.lanjing.wallet.agc.vo;

import java.math.BigDecimal;

/**
 * 我的数据接口{今日收益，累计收益，我的消费算力，我的邀请算力}
 *
 */
public class UserMiningVO {
	
	/**今日收益*/
	private BigDecimal dayIncome;
	
	/**累计收益*/
	private BigDecimal totalIncome;
	
	/**我的消费算力*/
	private BigDecimal consumeCp;
	
	/**我的邀请算力*/
	private BigDecimal profitCp;

	public BigDecimal getDayIncome() {
		return dayIncome;
	}

	public void setDayIncome(BigDecimal dayIncome) {
		this.dayIncome = dayIncome;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getConsumeCp() {
		return consumeCp;
	}

	public void setConsumeCp(BigDecimal consumeCp) {
		this.consumeCp = consumeCp;
	}

	public BigDecimal getProfitCp() {
		return profitCp;
	}

	public void setProfitCp(BigDecimal profitCp) {
		this.profitCp = profitCp;
	}
	

}
