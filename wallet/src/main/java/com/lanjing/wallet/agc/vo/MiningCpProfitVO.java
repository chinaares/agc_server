package com.lanjing.wallet.agc.vo;

import java.math.BigDecimal;

/**
 * 分佣算力
 */
public class MiningCpProfitVO {
	
	/**用户ID*/
	private String userId;
	
	/**邀请算力*/
	private BigDecimal cp;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getCp() {
		return cp;
	}

	public void setCp(BigDecimal cp) {
		this.cp = cp;
	}
	
}
