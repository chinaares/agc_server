package com.lanjing.wallet.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SystemConfig {

	private int key;// int(8) NOT NULL AUTO_INCREMENT COMMENT '系统配置表id',
	private String remarks;// varchar(200) DEFAULT NULL COMMENT '备注',
	private String value;// varchar(200) DEFAULT NULL COMMENT '取值',
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// datetime DEFAULT NULL COMMENT '创建时间',
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;// datetime DEFAULT NULL COMMENT '修改时间',

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
