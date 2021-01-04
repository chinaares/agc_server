package com.lanjing.otc.model;

import java.math.BigDecimal;

public class Wellets {
    private Integer fid;

    private String userkey;

    private String address;

    private Integer coinid;

    private String cointype;

    private BigDecimal coinnum;

    private BigDecimal frozennum;

    private BigDecimal locknum;

    private BigDecimal shiftnum;

    private BigDecimal releasenum;

    private BigDecimal quotanum;

    private BigDecimal lockfinances;

    private BigDecimal releasefinances;

    private Integer version;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey == null ? null : userkey.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public BigDecimal getCoinnum() {
        return coinnum;
    }

    public void setCoinnum(BigDecimal coinnum) {
        this.coinnum = coinnum;
    }

    public BigDecimal getFrozennum() {
        return frozennum;
    }

    public void setFrozennum(BigDecimal frozennum) {
        this.frozennum = frozennum;
    }

    public BigDecimal getLocknum() {
        return locknum;
    }

    public void setLocknum(BigDecimal locknum) {
        this.locknum = locknum;
    }

    public BigDecimal getShiftnum() {
        return shiftnum;
    }

    public void setShiftnum(BigDecimal shiftnum) {
        this.shiftnum = shiftnum;
    }

    public BigDecimal getReleasenum() {
        return releasenum;
    }

    public void setReleasenum(BigDecimal releasenum) {
        this.releasenum = releasenum;
    }

    public BigDecimal getQuotanum() {
        return quotanum;
    }

    public void setQuotanum(BigDecimal quotanum) {
        this.quotanum = quotanum;
    }

    public BigDecimal getLockfinances() {
        return lockfinances;
    }

    public void setLockfinances(BigDecimal lockfinances) {
        this.lockfinances = lockfinances;
    }

    public BigDecimal getReleasefinances() {
        return releasefinances;
    }

    public void setReleasefinances(BigDecimal releasefinances) {
        this.releasefinances = releasefinances;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}