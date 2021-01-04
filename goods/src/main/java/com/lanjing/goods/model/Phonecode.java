package com.lanjing.goods.model;

import java.util.Date;
import lombok.Data;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-22 16:56
 * @version version 1.0.0
 */
@Data
public class Phonecode {
    private Integer fid;

    /**
    * 手机号
    */
    private String phonenum;

    /**
    * 验证码
    */
    private String code;

    /**
    * 消息内容
    */
    private String msg;

    /**
    * 短信类型
    */
    private Integer type;

    /**
    * 发送时间
    */
    private Date createtime;

    /**
    * 状态
    */
    private Integer state;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    
}