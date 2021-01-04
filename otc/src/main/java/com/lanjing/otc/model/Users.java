package com.lanjing.otc.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 11:57
 * @version version 1.0.0
 */
@Data
public class Users {
    /**
     *id
     */
    private Integer fid;

    /**
     * 唯一key
     */
    private String keyes;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登陆密码
     */
    private String loginpassword;

    /**
     * 交易密码
     */
    private String transpassword;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String picture;

    /**
     * 手机号码
     */
    private String phonenum;

    /**
     * token
     */
    private String token;

    /**
     * 初始配比
     */
    private Double beginnodenum;

    /**
     * USD奖励空投设置
     */
    private BigDecimal nodenum;

    /**
     * 是否是节点   0，不是    1，是   2，节点关闭
     */
    private Integer isnode;

    /**
     * 节点操作管理员
     */
    private String nodeadmin;

    /**
     * 是否是委员会    1，是    0，不是
     */
    private Integer isvip;

    /**
     * 账号注册时间
     */
    private Date createtime;

    /**
     * 成为节点日期
     */
    private Date isnodetime;

    /**
     * 设备标识
     */
    private String device;

    /**
     * 钱包名称
     */
    private String welletname;

    /**
     * 是否开启智能交易   0，关闭  1，开启
     */
    private Integer isauto;

    /**
     * 状态     0，禁用     1，正常
     */
    private Integer state;

    /**
     * 渠道   1，前台     2，后台
     */
    private Integer ways;

    /**
     * 是否实名   0，未实名 1，实名审核中 2，实名成功 -1，实名未通过
     */
    private Integer isreal;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 身份证号
     */
    private String identityid;

    /**
     * 身份证号人像页
     */
    private String identityimg1;

    /**
     * 身份证号国徽页
     */
    private String identityimg2;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getKeyes() {
		return keyes;
	}

	public void setKeyes(String keyes) {
		this.keyes = keyes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}

	public String getTranspassword() {
		return transpassword;
	}

	public void setTranspassword(String transpassword) {
		this.transpassword = transpassword;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Double getBeginnodenum() {
		return beginnodenum;
	}

	public void setBeginnodenum(Double beginnodenum) {
		this.beginnodenum = beginnodenum;
	}

	public BigDecimal getNodenum() {
		return nodenum;
	}

	public void setNodenum(BigDecimal nodenum) {
		this.nodenum = nodenum;
	}

	public Integer getIsnode() {
		return isnode;
	}

	public void setIsnode(Integer isnode) {
		this.isnode = isnode;
	}

	public String getNodeadmin() {
		return nodeadmin;
	}

	public void setNodeadmin(String nodeadmin) {
		this.nodeadmin = nodeadmin;
	}

	public Integer getIsvip() {
		return isvip;
	}

	public void setIsvip(Integer isvip) {
		this.isvip = isvip;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getIsnodetime() {
		return isnodetime;
	}

	public void setIsnodetime(Date isnodetime) {
		this.isnodetime = isnodetime;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getWelletname() {
		return welletname;
	}

	public void setWelletname(String welletname) {
		this.welletname = welletname;
	}

	public Integer getIsauto() {
		return isauto;
	}

	public void setIsauto(Integer isauto) {
		this.isauto = isauto;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getWays() {
		return ways;
	}

	public void setWays(Integer ways) {
		this.ways = ways;
	}

	public Integer getIsreal() {
		return isreal;
	}

	public void setIsreal(Integer isreal) {
		this.isreal = isreal;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdentityid() {
		return identityid;
	}

	public void setIdentityid(String identityid) {
		this.identityid = identityid;
	}

	public String getIdentityimg1() {
		return identityimg1;
	}

	public void setIdentityimg1(String identityimg1) {
		this.identityimg1 = identityimg1;
	}

	public String getIdentityimg2() {
		return identityimg2;
	}

	public void setIdentityimg2(String identityimg2) {
		this.identityimg2 = identityimg2;
	}
    
}