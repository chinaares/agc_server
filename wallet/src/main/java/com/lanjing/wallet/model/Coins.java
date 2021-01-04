package com.lanjing.wallet.model;

import java.math.BigDecimal;

public class Coins {
    private Integer fid;

    private String coinname;

    private String coinshort;

    private double price;

    private String img;

    private String remark;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getCoinname() {
        return coinname;
    }

    public void setCoinname(String coinname) {
        this.coinname = coinname == null ? null : coinname.trim();
    }

    public String getCoinshort() {
        return coinshort;
    }

    public void setCoinshort(String coinshort) {
        this.coinshort = coinshort == null ? null : coinshort.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}