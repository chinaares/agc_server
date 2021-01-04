package com.lanjing.wallet.model;

import java.util.Date;

public class Autolist {
    private Integer fid;

    private String createuser;

    private int taktTime;

    private Date beginTime;

    private Double sum;

    private Double coinMin;

    private Double coinMax;

    private Integer type;

    private String adminName;

    private Date createtime;

    private int Isstart;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public int getTaktTime() {
        return taktTime;
    }

    public void setTaktTime(Integer taktTime) {
        this.taktTime = taktTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getCoinMin() {
        return coinMin;
    }

    public void setCoinMin(Double coinMin) {
        this.coinMin = coinMin;
    }

    public Double getCoinMax() {
        return coinMax;
    }

    public void setCoinMax(Double coinMax) {
        this.coinMax = coinMax;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setTaktTime(int taktTime) {
        this.taktTime = taktTime;
    }

    public int getIsstart() {
        return Isstart;
    }

    public void setIsstart(int isstart) {
        Isstart = isstart;
    }
}