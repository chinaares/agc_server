package com.lanjing.otc.model;

import lombok.Data;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-11 10:46
 * @version version 1.0.0
 */
@Data
public class Parameters {
    /**
    * 
    */
    private Integer id;

    /**
    * 参数名称
    */
    private String keyName;

    /**
    * 参数内容
    */
    private String keyValue;

    /**
    * 说明
    */
    private String remark;

    /**
    * 类型   0，其他 1，中文banner图   2，英文banner图   
    */
    private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
}