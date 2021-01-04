package com.lanjing.wallet.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-14 14:39
 */
@Data
public class AirdropList {
    /**
     * id
     */
    private Integer id;

    /**
     * 理财id
     */
    private Integer aId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 1待释放，2释放完成，-1 删除
     */
    private Integer status;

    /**
     * 已释放天数
     */
    private Integer days;

    /**
     * 总释放天数
     */
    private Integer cycle;

    /**
     * 理财金额
     */
    private BigDecimal amount;

    /**
     * 冻结BTC金额
     */
    private BigDecimal freezeBtc;

    /**
     * 冻结YYB金额
     */
    private BigDecimal freezeYyb;

    /**
     * 自由BTC金额
     */
    private BigDecimal freeBtc;

    /**
     * 自由YYB金额
     */
    private BigDecimal freeYyb;

    /**
     * 每日释放BTC
     */
    private BigDecimal freedBtc;

    /**
     * 每日释放YYB
     */
    private BigDecimal freedYyb;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFreezeBtc() {
		return freezeBtc;
	}

	public void setFreezeBtc(BigDecimal freezeBtc) {
		this.freezeBtc = freezeBtc;
	}

	public BigDecimal getFreezeYyb() {
		return freezeYyb;
	}

	public void setFreezeYyb(BigDecimal freezeYyb) {
		this.freezeYyb = freezeYyb;
	}

	public BigDecimal getFreeBtc() {
		return freeBtc;
	}

	public void setFreeBtc(BigDecimal freeBtc) {
		this.freeBtc = freeBtc;
	}

	public BigDecimal getFreeYyb() {
		return freeYyb;
	}

	public void setFreeYyb(BigDecimal freeYyb) {
		this.freeYyb = freeYyb;
	}

	public BigDecimal getFreedBtc() {
		return freedBtc;
	}

	public void setFreedBtc(BigDecimal freedBtc) {
		this.freedBtc = freedBtc;
	}

	public BigDecimal getFreedYyb() {
		return freedYyb;
	}

	public void setFreedYyb(BigDecimal freedYyb) {
		this.freedYyb = freedYyb;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}