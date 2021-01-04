package com.lanjing.goods.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShopOrder {
    private String fid;

    private Integer userkey;

    private String userphone;

    private String orderid;

    private Integer goodsid;

    private String phone;

    private String username;

    private String address;

    private String standard;

    private Integer num;

    private BigDecimal cnyprice;

    private String coin;

    private BigDecimal coinprive;

    private Date paytime;

    private Date createtime;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    private String logistics;

    private Integer state;

    private Long shop_code;

    private Integer goodsskuId;

    private BigDecimal integralnum;

    private String remark;

    public Long getShop_code() {
        return shop_code;
    }

    public void setShop_code(Long shop_code) {
        this.shop_code = shop_code;
    }

    public Integer getGoodsskuId() {
        return goodsskuId;
    }

    public void setGoodsskuId(Integer goodsskuId) {
        this.goodsskuId = goodsskuId;
    }

    public BigDecimal getIntegralnum() {
        return integralnum;
    }

    public void setIntegralnum(BigDecimal integralnum) {
        this.integralnum = integralnum;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Integer getUserkey() {
        return userkey;
    }

    public void setUserkey(Integer userkey) {
        this.userkey = userkey;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getCnyprice() {
        return cnyprice;
    }

    public void setCnyprice(BigDecimal cnyprice) {
        this.cnyprice = cnyprice;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin == null ? null : coin.trim();
    }

    public BigDecimal getCoinprive() {
        return coinprive;
    }

    public void setCoinprive(BigDecimal coinprive) {
        this.coinprive = coinprive;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}