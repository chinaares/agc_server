package com.lanjing.wallet.model;

import java.util.Date;

public class Wellethistory {
    private Integer fid;

    private Integer welletid;

    private Long changenum;

    private Integer type;

    private String remark;

    private Date create;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getWelletid() {
        return welletid;
    }

    public void setWelletid(Integer welletid) {
        this.welletid = welletid;
    }

    public Long getChangenum() {
        return changenum;
    }

    public void setChangenum(Long changenum) {
        this.changenum = changenum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}