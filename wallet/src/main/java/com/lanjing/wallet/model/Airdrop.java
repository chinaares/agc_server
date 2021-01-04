package com.lanjing.wallet.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.BigDecimalUtil;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Airdrop {
    /**
     * id
     */
    private Integer id;

    /**
     * 项目介绍
     */
    private String introduction;

    /**
     * 冻结规则
     */
    private String rule;

    /**
     * 服务协议
     */
    private String protocol;

    /**
     * 简介
     */
    private String summary;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 理财金额
     */
    @JsonSerialize(using = BigDecimalUtil.class)
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
     * 冻结周期/天
     */
    private Integer cycle;

    /**
     * 1 启用 0 下架 -1删除
     */
    private Integer enable;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 发布者
     */
    private String publisher;

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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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