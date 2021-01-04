package com.lanjing.goods.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.BigDecimalUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 * @version version 1.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MallWallet {
    /**
    * id
    */
    private Integer id;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * code
    */
    private Long code;

    /**
    * 地址
    */
    private String address;

    /**
    * 币种id
    */
    private Integer coinId;

    /**
    * 币种
    */
    private String coinType;

    /**
    * 余额数量
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal coinNum;

    /**
    * 冻结数量
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal frozenNum;

    /**
    * 空投锁仓数量 
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal lockNum;

    /**
    * 转入未交易
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal shiftNum;

    /**
    * 空投已释放数量
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal releaseNum;

    /**
    * 空投配额
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal quotaNum;

    /**
    * 理财锁仓
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal lockFinances;

    /**
    * 理财释放
    */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal releaseFinances;

    /**
    * 版本号
    */
    private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCoinId() {
		return coinId;
	}

	public void setCoinId(Integer coinId) {
		this.coinId = coinId;
	}

	public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	public BigDecimal getCoinNum() {
		return coinNum;
	}

	public void setCoinNum(BigDecimal coinNum) {
		this.coinNum = coinNum;
	}

	public BigDecimal getFrozenNum() {
		return frozenNum;
	}

	public void setFrozenNum(BigDecimal frozenNum) {
		this.frozenNum = frozenNum;
	}

	public BigDecimal getLockNum() {
		return lockNum;
	}

	public void setLockNum(BigDecimal lockNum) {
		this.lockNum = lockNum;
	}

	public BigDecimal getShiftNum() {
		return shiftNum;
	}

	public void setShiftNum(BigDecimal shiftNum) {
		this.shiftNum = shiftNum;
	}

	public BigDecimal getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(BigDecimal releaseNum) {
		this.releaseNum = releaseNum;
	}

	public BigDecimal getQuotaNum() {
		return quotaNum;
	}

	public void setQuotaNum(BigDecimal quotaNum) {
		this.quotaNum = quotaNum;
	}

	public BigDecimal getLockFinances() {
		return lockFinances;
	}

	public void setLockFinances(BigDecimal lockFinances) {
		this.lockFinances = lockFinances;
	}

	public BigDecimal getReleaseFinances() {
		return releaseFinances;
	}

	public void setReleaseFinances(BigDecimal releaseFinances) {
		this.releaseFinances = releaseFinances;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
    
}