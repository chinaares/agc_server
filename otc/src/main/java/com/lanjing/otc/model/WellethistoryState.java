package com.lanjing.otc.model;

import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@ToString
public class WellethistoryState {
    private Integer fid;

    private Integer userid;

    private Integer welletid;

    private Integer cointype;

    private String withdraw;

    private String recharge;

    private BigDecimal changenum;

    private Integer type;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private String keyes;

    private Integer state;

    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getWelletid() {
        return welletid;
    }

    public void setWelletid(Integer welletid) {
        this.welletid = welletid;
    }

    public Integer getCointype() {
        return cointype;
    }

    public void setCointype(Integer cointype) {
        this.cointype = cointype;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw == null ? null : withdraw.trim();
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge == null ? null : recharge.trim();
    }

    public BigDecimal getChangenum() {
        return changenum;
    }

    public void setChangenum(BigDecimal changenum) {
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

    public String getKeyes() {
        return keyes;
    }

    public void setKeyes(String keyes) {
        this.keyes = keyes == null ? null : keyes.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}