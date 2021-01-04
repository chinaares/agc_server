package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private Integer fid;

    private String orderid;

    private Integer tradecoinid;

    private String tradecoin;

    private String userbuy;

    private String buyadress;

    private String usersell;

    private String selladress;

    private BigDecimal fee;

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    private BigDecimal tradenum;

    private Date createtime;

    private Date confirmtime;

    private Date processtime;

    private Integer state;

    private Integer processstate;

    public Integer getProcessstate() {
        return processstate;
    }

    public void setProcessstate(Integer processstate) {
        this.processstate = processstate;
    }

    public Date getProcesstime() {
        return processtime;
    }

    public void setProcesstime(Date processtime) {
        this.processtime = processtime;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getTradecoinid() {
        return tradecoinid;
    }

    public void setTradecoinid(Integer tradecoinid) {
        this.tradecoinid = tradecoinid;
    }

    public String getTradecoin() {
        return tradecoin;
    }

    public void setTradecoin(String tradecoin) {
        this.tradecoin = tradecoin == null ? null : tradecoin.trim();
    }

    public String getUserbuy() {
        return userbuy;
    }

    public void setUserbuy(String userbuy) {
        this.userbuy = userbuy == null ? null : userbuy.trim();
    }

    public String getBuyadress() {
        return buyadress;
    }

    public void setBuyadress(String buyadress) {
        this.buyadress = buyadress == null ? null : buyadress.trim();
    }

    public String getUsersell() {
        return usersell;
    }

    public void setUsersell(String usersell) {
        this.usersell = usersell == null ? null : usersell.trim();
    }

    public String getSelladress() {
        return selladress;
    }

    public void setSelladress(String selladress) {
        this.selladress = selladress == null ? null : selladress.trim();
    }

    public BigDecimal getTradenum() {
        return tradenum;
    }

    public void setTradenum(BigDecimal tradenum) {
        this.tradenum = tradenum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Date confirmtime) {
        this.confirmtime = confirmtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}