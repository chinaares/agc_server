package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.Date;

public class Changelog {
    private Integer fid;

    private String userkey;

    private Integer usergrede;

    private Integer cointype1;

    private BigDecimal coinnumago;

    private BigDecimal coinnumafter;

    private BigDecimal buynum;

    private BigDecimal coinnum1;

    private Integer cointype2;

    private BigDecimal coinnum2;

    private Date craetetime;

    private Integer type;

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

    public Integer getUsergrede() {
        return usergrede;
    }

    public void setUsergrede(Integer usergrede) {
        this.usergrede = usergrede;
    }

    public Integer getCointype1() {
        return cointype1;
    }

    public void setCointype1(Integer cointype1) {
        this.cointype1 = cointype1;
    }

    public BigDecimal getCoinnumago() {
        return coinnumago;
    }

    public void setCoinnumago(BigDecimal coinnumago) {
        this.coinnumago = coinnumago;
    }

    public BigDecimal getCoinnumafter() {
        return coinnumafter;
    }

    public void setCoinnumafter(BigDecimal coinnumafter) {
        this.coinnumafter = coinnumafter;
    }

    public BigDecimal getBuynum() {
        return buynum;
    }

    public void setBuynum(BigDecimal buynum) {
        this.buynum = buynum;
    }

    public BigDecimal getCoinnum1() {
        return coinnum1;
    }

    public void setCoinnum1(BigDecimal coinnum1) {
        this.coinnum1 = coinnum1;
    }

    public Integer getCointype2() {
        return cointype2;
    }

    public void setCointype2(Integer cointype2) {
        this.cointype2 = cointype2;
    }

    public BigDecimal getCoinnum2() {
        return coinnum2;
    }

    public void setCoinnum2(BigDecimal coinnum2) {
        this.coinnum2 = coinnum2;
    }

    public Date getCraetetime() {
        return craetetime;
    }

    public void setCraetetime(Date craetetime) {
        this.craetetime = craetetime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}