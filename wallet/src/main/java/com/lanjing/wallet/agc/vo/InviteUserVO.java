package com.lanjing.wallet.agc.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 推广用户VO类
 */
public class InviteUserVO {
	
	private String userId;
	
	private String phone;
	
	private String nickName;
	
    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
