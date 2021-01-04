package com.lanjing.wallet.model;

import java.util.Date;

public class MappingTradeOrder {
    private Integer fid;

    private Integer mappingid;

    private String user1;

    private String coin1;

    private Long coinnum1;

    private String user2;

    private String coin2;

    private Long coinnum2;

    private Date createtime;

    private Date updatetime;

    private Integer state;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getMappingid() {
        return mappingid;
    }

    public void setMappingid(Integer mappingid) {
        this.mappingid = mappingid;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1 == null ? null : user1.trim();
    }

    public String getCoin1() {
        return coin1;
    }

    public void setCoin1(String coin1) {
        this.coin1 = coin1 == null ? null : coin1.trim();
    }

    public Long getCoinnum1() {
        return coinnum1;
    }

    public void setCoinnum1(Long coinnum1) {
        this.coinnum1 = coinnum1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2 == null ? null : user2.trim();
    }

    public String getCoin2() {
        return coin2;
    }

    public void setCoin2(String coin2) {
        this.coin2 = coin2 == null ? null : coin2.trim();
    }

    public Long getCoinnum2() {
        return coinnum2;
    }

    public void setCoinnum2(Long coinnum2) {
        this.coinnum2 = coinnum2;
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
}