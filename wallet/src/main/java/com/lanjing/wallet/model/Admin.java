package com.lanjing.wallet.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 18:21
 */
@Data
public class Admin {
    private Integer fid;

    /**
     * code
     */
    private Long code;

    /**
     * 账号
     */
    private String aname;

    /**
     * 姓名
     */
    private String anick;

    /**
     * 登录密码
     */
    private String loginpassword;

    /**
     * 角色
     */
    private Integer arole;

    /**
     * 权限
     */
    private Integer purview;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态   0，禁用    1，启用
     */
    private String state;

    /**
     * token
     */
    private String token;

    /**
     * 最近登录时间
     */
    private Date logintime;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 操作密码
     */
    private String ationpassword;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAnick() {
		return anick;
	}

	public void setAnick(String anick) {
		this.anick = anick;
	}

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public Integer getArole() {
		return arole;
	}

	public void setArole(Integer arole) {
		this.arole = arole;
	}

	public Integer getPurview() {
		return purview;
	}

	public void setPurview(Integer purview) {
		this.purview = purview;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAtionpassword() {
		return ationpassword;
	}

	public void setAtionpassword(String ationpassword) {
		this.ationpassword = ationpassword;
	}
    
}