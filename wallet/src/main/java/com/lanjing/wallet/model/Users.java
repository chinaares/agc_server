package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.Date;

public class Users {
    private Integer fid;

    private String keyes;

    private String username;

    private String loginpassword;

    private String transpassword;

    private String nickname;

    private String picture;

    private String phonenum;

    private String token;


    private Date createtime;

    private Date updatetime;

    private String device;

    private String welletname;


    private Integer state;


    private Integer ways;

    private Integer isReal;

    private String realname;

    private String identityId;

    private String identityImg1;

    private String identityImg2;

    public Integer getIsReal() {
        return isReal;
    }

    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

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
        this.keyes = keyes == null ? null : keyes.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword == null ? null : loginpassword.trim();
    }

    public String getTranspassword() {
        return transpassword;
    }

    public void setTranspassword(String transpassword) {
        this.transpassword = transpassword == null ? null : transpassword.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? "" : phonenum.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }


    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    public String getWelletname() {
        return welletname;
    }

    public void setWelletname(String welletname) {
        this.welletname = welletname == null ? null : welletname.trim();
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


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIdentityImg1() {
        return identityImg1;
    }

    public void setIdentityImg1(String identityImg1) {
        this.identityImg1 = identityImg1;
    }

    public String getIdentityImg2() {
        return identityImg2;
    }

    public void setIdentityImg2(String identityImg2) {
        this.identityImg2 = identityImg2;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Users{" +
                "fid=" + fid +
                ", keyes='" + keyes + '\'' +
                ", username='" + username + '\'' +
                ", loginpassword='" + loginpassword + '\'' +
                ", transpassword='" + transpassword + '\'' +
                ", nickname='" + nickname + '\'' +
                ", picture='" + picture + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", token='" + token + '\'' +
                ", createtime=" + createtime +
                ", device='" + device + '\'' +
                ", welletname='" + welletname + '\'' +
                ", state=" + state +
                ", ways=" + ways +
                '}';
    }
}