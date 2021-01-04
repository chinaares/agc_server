package com.lanjing.wallet.model;

import java.util.Date;

public class Registers {
    private Integer fid;

    private Date todaytime;

    private Integer usernum;

    private Date createtime;

    private String adminname;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Date getTodaytime() {
        return todaytime;
    }

    public void setTodaytime(Date todaytime) {
        this.todaytime = todaytime;
    }

    public Integer getUsernum() {
        return usernum;
    }

    public void setUsernum(Integer usernum) {
        this.usernum = usernum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }
}