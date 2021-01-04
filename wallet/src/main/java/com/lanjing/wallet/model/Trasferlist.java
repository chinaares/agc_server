package com.lanjing.wallet.model;



public class Trasferlist {
    private Integer fid;

    private Integer coin;

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    private Integer tId;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    private String memo;

    private String txid;

    private Double tradenum;

    private Double feenum;

    private Integer type;

    private String description;

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
        this.memo = memo == null ? null : memo.trim();
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid == null ? null : txid.trim();
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
        this.description = description == null ? null : description.trim();
    }
}