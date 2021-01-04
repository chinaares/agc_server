package com.lanjing.wallet.model;

public class Trademapping {
    private Integer fid;

    private Integer tradecoin1;

    private Long coinprice1;

    private Integer tradecoin2;

    private Long coinprice2;

    private Double proportion;

    private Double fee;

    private Integer isstartup;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getTradecoin1() {
        return tradecoin1;
    }

    public void setTradecoin1(Integer tradecoin1) {
        this.tradecoin1 = tradecoin1;
    }

    public Long getCoinprice1() {
        return coinprice1;
    }

    public void setCoinprice1(Long coinprice1) {
        this.coinprice1 = coinprice1;
    }

    public Integer getTradecoin2() {
        return tradecoin2;
    }

    public void setTradecoin2(Integer tradecoin2) {
        this.tradecoin2 = tradecoin2;
    }

    public Long getCoinprice2() {
        return coinprice2;
    }

    public void setCoinprice2(Long coinprice2) {
        this.coinprice2 = coinprice2;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getIsstartup() {
        return isstartup;
    }

    public void setIsstartup(Integer isstartup) {
        this.isstartup = isstartup;
    }
}