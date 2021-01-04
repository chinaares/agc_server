package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-24 17:16
 */
@Data
public class WithdrawLog {
    private Integer id;

    /**
     * 交易id
     */
    private String orderId;

    /**
     * 交易hash
     */
    private String txId;

    /**
     * 提币地址
     */
    private String sellAddress;

    private Integer sellUser;

    /**
     * 数量
     */
    private BigDecimal num;

    /**
     * 创建时间
     */
    private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getSellAddress() {
		return sellAddress;
	}

	public void setSellAddress(String sellAddress) {
		this.sellAddress = sellAddress;
	}

	public Integer getSellUser() {
		return sellUser;
	}

	public void setSellUser(Integer sellUser) {
		this.sellUser = sellUser;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}