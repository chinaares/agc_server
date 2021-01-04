package com.lanjing.wallet.agc.vo;

import java.math.BigDecimal;

/**
 * 邀请数据VO类
 * 
 */
public class InviteVO {
	
	/**邀请人数*/
	private int userTotal;
	
	/**推广获得算力*/
	private BigDecimal inviteCp;
	
	/**推广获得Agc*/
	private BigDecimal inviteAgc;

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public BigDecimal getInviteCp() {
		return inviteCp;
	}

	public void setInviteCp(BigDecimal inviteCp) {
		this.inviteCp = inviteCp;
	}

	public BigDecimal getInviteAgc() {
		return inviteAgc;
	}

	public void setInviteAgc(BigDecimal inviteAgc) {
		this.inviteAgc = inviteAgc;
	}

}
