package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.Date;

public class MappingTrade {
    private Integer fid;

    private String fuser;

    private Integer coinid;

    private String cointype;

    private Integer type;

    private BigDecimal price;

    private BigDecimal coinnum;

    private BigDecimal rest;

    private BigDecimal tradenum;

    private BigDecimal fee;

    private BigDecimal releasenum;

    private Date createtime;

    private Date updatetime;

    private Integer state;

    private Integer isreal;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser == null ? null : fuser.trim();
    }

    public Integer getCoinid() {
        return coinid;
    }

    public void setCoinid(Integer coinid) {
        this.coinid = coinid;
    }

    public String getCointype() {
        return cointype;
    }

    public void setCointype(String cointype) {
        this.cointype = cointype == null ? null : cointype.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCoinnum() {
        return coinnum;
    }

    public void setCoinnum(BigDecimal coinnum) {
        this.coinnum = coinnum;
    }

    public BigDecimal getRest() {
        return rest;
    }

    public void setRest(BigDecimal rest) {
        this.rest = rest;
    }

    public BigDecimal getTradenum() {
        return tradenum;
    }

    public void setTradenum(BigDecimal tradenum) {
        this.tradenum = tradenum;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getReleasenum() {
        return releasenum;
    }

    public void setReleasenum(BigDecimal releasenum) {
        this.releasenum = releasenum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsreal() {
        return isreal;
    }

    public void setIsreal(Integer isreal) {
        this.isreal = isreal;
    }
}