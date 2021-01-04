package com.lanjing.wallet.model;

import java.util.Date;

public class DaySettlement {
    private Integer fid;

    private Integer lev;

    private String userkey;

    private Integer grede;

    private Double dayairdrop;

    private Double daytrade;

    private Double dayteamairdrop;

    private Double dayteamtrade;

    private Double weightairdrop;

    private Double weighttrade;

    private Date createtime;

    private String coverCode;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey == null ? null : userkey.trim();
    }

    public Integer getGrede() {
        return grede;
    }

    public void setGrede(Integer grede) {
        this.grede = grede;
    }

    public Double getDayairdrop() {
        return dayairdrop;
    }

    public void setDayairdrop(Double dayairdrop) {
        this.dayairdrop = dayairdrop;
    }

    public Double getDaytrade() {
        return daytrade;
    }

    public void setDaytrade(Double daytrade) {
        this.daytrade = daytrade;
    }

    public Double getDayteamairdrop() {
        return dayteamairdrop;
    }

    public void setDayteamairdrop(Double dayteamairdrop) {
        this.dayteamairdrop = dayteamairdrop;
    }

    public Double getDayteamtrade() {
        return dayteamtrade;
    }

    public void setDayteamtrade(Double dayteamtrade) {
        this.dayteamtrade = dayteamtrade;
    }

    public Double getWeightairdrop() {
        return weightairdrop;
    }

    public void setWeightairdrop(Double weightairdrop) {
        this.weightairdrop = weightairdrop;
    }

    public Double getWeighttrade() {
        return weighttrade;
    }

    public void setWeighttrade(Double weighttrade) {
        this.weighttrade = weighttrade;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCoverCode() {
        return coverCode;
    }

    public void setCoverCode(String coverCode) {
        this.coverCode = coverCode == null ? null : coverCode.trim();
    }
}