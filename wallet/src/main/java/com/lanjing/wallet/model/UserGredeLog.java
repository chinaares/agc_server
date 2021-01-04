package com.lanjing.wallet.model;

import java.util.Date;

public class UserGredeLog {
	private int id;// int(8) NOT NULL AUTO_INCREMENT COMMENT '用户等级修改记录id',
	private int userId;// int(8) DEFAULT NULL COMMENT '用户id',
	private String admin;// varchar(200) DEFAULT NULL COMMENT '管理员姓名',
	private int beforeGrede;// int(5) DEFAULT NULL COMMENT '修改之前的等级',
	private int afterGrede;// int(5) DEFAULT NULL COMMENT '修改以后的等级',
	private Date createTime;// datetime DEFAULT NULL COMMENT '创建时间',
	private Date modifyTime;// datetime DEFAULT NULL COMMENT '修改时间',
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public int getBeforeGrede() {
		return beforeGrede;
	}
	public void setBeforeGrede(int beforeGrede) {
		this.beforeGrede = beforeGrede;
	}
	public int getAfterGrede() {
		return afterGrede;
	}
	public void setAfterGrede(int afterGrede) {
		this.afterGrede = afterGrede;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	

}
