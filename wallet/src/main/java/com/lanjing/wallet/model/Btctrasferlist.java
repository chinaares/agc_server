package com.lanjing.wallet.model;

import lombok.Data;

@Data
public class Btctrasferlist {
    private Integer fid;

    /**
    * 标签备注（钱包标识）
    */
    private String memo;

    /**
    * 交易txid
    */
    private String txid;

    /**
    * 交易数量
    */
    private Double tradenum;

    /**
    * 手续费
    */
    private Double feenum;

    /**
    * 1，提币   2，充币
    */
    private Integer type;

    /**
    * 备注
    */
    private String description;

    /**
    * 状态   是否进行处理   0，未处理   1，已处理
    */
    private Integer state;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public Double getTradenum() {
		return tradenum;
	}

	public void setTradenum(Double tradenum) {
		this.tradenum = tradenum;
	}

	public Double getFeenum() {
		return feenum;
	}

	public void setFeenum(Double feenum) {
		this.feenum = feenum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    
}