package com.lanjing.otc.model;

import lombok.Data;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 11:58
 * @version version 1.0.0
 */
@Data
public class Wallets {
    /**
    * 
    */
    private Integer fid;

    /**
    * 所属用户
    */
    private String userkey;

    /**
    * 地址
    */
    private String address;

    /**
    * 币种id
    */
    private Integer coinid;

    /**
    * 币种
    */
    private String cointype;

    /**
    * 余额数量
    */
    private Double coinnum;

    /**
    * 冻结数量
    */
    private Double frozennum;

    /**
    * 空投锁仓数量 
    */
    private Double locknum;

    /**
    * 转入未交易
    */
    private Double shiftnum;

    /**
    * 空投已释放数量
    */
    private Double releasenum;

    /**
    * 空投配额
    */
    private Double quotanum;

    /**
    * 理财锁仓
    */
    private Double lockfinances;

    /**
    * 理财释放
    */
    private Double releasefinances;

    /**
    * 版本号
    */
    private Integer version;

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
		this.userkey = userkey;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCoinid() {
		return coinid;
	}

	public void setCoinid(Integer coinid) {
		this.coinid = coinid;
	}

	public String getCointype() {
		return cointype;
	}

	public void setCointype(String cointype) {
		this.cointype = cointype;
	}

	public Double getCoinnum() {
		return coinnum;
	}

	public void setCoinnum(Double coinnum) {
		this.coinnum = coinnum;
	}

	public Double getFrozennum() {
		return frozennum;
	}

	public void setFrozennum(Double frozennum) {
		this.frozennum = frozennum;
	}

	public Double getLocknum() {
		return locknum;
	}

	public void setLocknum(Double locknum) {
		this.locknum = locknum;
	}

	public Double getShiftnum() {
		return shiftnum;
	}

	public void setShiftnum(Double shiftnum) {
		this.shiftnum = shiftnum;
	}

	public Double getReleasenum() {
		return releasenum;
	}

	public void setReleasenum(Double releasenum) {
		this.releasenum = releasenum;
	}

	public Double getQuotanum() {
		return quotanum;
	}

	public void setQuotanum(Double quotanum) {
		this.quotanum = quotanum;
	}

	public Double getLockfinances() {
		return lockfinances;
	}

	public void setLockfinances(Double lockfinances) {
		this.lockfinances = lockfinances;
	}

	public Double getReleasefinances() {
		return releasefinances;
	}

	public void setReleasefinances(Double releasefinances) {
		this.releasefinances = releasefinances;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
    
}