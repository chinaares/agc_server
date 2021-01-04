package com.lanjing.goods.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Orders {
    private Integer fid;

    /**
    * 订单编号/交易标识
    */
    private String orderid;

    /**
    * 交易的主币种
    */
    private Integer tradecoinid;

    /**
    * 交易主币种名称     
    */
    private String tradecoin;

    /**
    * 买方
    */
    private String userbuy;

    /**
    * 买方地址
    */
    private String buyadress;

    /**
    * 卖方
    */
    private String usersell;

    /**
    * 卖方地址
    */
    private String selladress;

    /**
    * 手续费
    */
    private BigDecimal fee;

    /**
    * 交易数量
    */
    private BigDecimal tradenum;

    /**
    * 交易创建时间
    */
    private Date createtime;

    /**
    * 完成交易时间
    */
    private Date confirmtime;

    /**
    * 处理时间
    */
    private Date processtime;

    /**
    * 0，进行中    1，已完成    2，失败
    */
    private Integer state;

    /**
    * 处理状态
    */
    private Integer processstate;

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
		this.orderid = orderid;
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
		this.tradecoin = tradecoin;
	}

	public String getUserbuy() {
		return userbuy;
	}

	public void setUserbuy(String userbuy) {
		this.userbuy = userbuy;
	}

	public String getBuyadress() {
		return buyadress;
	}

	public void setBuyadress(String buyadress) {
		this.buyadress = buyadress;
	}

	public String getUsersell() {
		return usersell;
	}

	public void setUsersell(String usersell) {
		this.usersell = usersell;
	}

	public String getSelladress() {
		return selladress;
	}

	public void setSelladress(String selladress) {
		this.selladress = selladress;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
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

	public Date getProcesstime() {
		return processtime;
	}

	public void setProcesstime(Date processtime) {
		this.processtime = processtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getProcessstate() {
		return processstate;
	}

	public void setProcessstate(Integer processstate) {
		this.processstate = processstate;
	}
    
}