package com.lanjing.wallet.model;

public class WelletsuptLog {
    private Integer fid;

    private String userkey;

    private String username;

    private Double titanago;

    private Double titanafter;

    private Double titanshiftago;

    private Double titanshiftafter;

    private Double titancago;

    private Double titancafter;

    private Double usd1ago;

    private Double usd1after;

    private Double usd2ago;

    private Double usd2after;

    private String auname;

    private Integer createtime;

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
        this.userkey = userkey == null ? "" : userkey.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? "" : username.trim();
    }

    public Double getTitanago() {
        return titanago;
    }

    public void setTitanago(Double titanago) {
        this.titanago = titanago;
    }

    public Double getTitanafter() {
        return titanafter;
    }

    public void setTitanafter(Double titanafter) {
        this.titanafter = titanafter;
    }

    public Double getTitanshiftago() {
        return titanshiftago;
    }

    public void setTitanshiftago(Double titanshiftago) {
        this.titanshiftago = titanshiftago;
    }

    public Double getTitanshiftafter() {
        return titanshiftafter;
    }

    public void setTitanshiftafter(Double titanshiftafter) {
        this.titanshiftafter = titanshiftafter;
    }

    public Double getTitancago() {
        return titancago;
    }

    public void setTitancago(Double titancago) {
        this.titancago = titancago;
    }

    public Double getTitancafter() {
        return titancafter;
    }

    public void setTitancafter(Double titancafter) {
        this.titancafter = titancafter;
    }

    public Double getUsd1ago() {
        return usd1ago;
    }

    public void setUsd1ago(Double usd1ago) {
        this.usd1ago = usd1ago;
    }

    public Double getUsd1after() {
        return usd1after;
    }

    public void setUsd1after(Double usd1after) {
        this.usd1after = usd1after;
    }

    public Double getUsd2ago() {
        return usd2ago;
    }

    public void setUsd2ago(Double usd2ago) {
        this.usd2ago = usd2ago;
    }

    public Double getUsd2after() {
        return usd2after;
    }

    public void setUsd2after(Double usd2after) {
        this.usd2after = usd2after;
    }

    public String getAuname() {
        return auname;
    }

    public void setAuname(String auname) {
        this.auname = auname == null ? "" : auname.trim();
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }
}