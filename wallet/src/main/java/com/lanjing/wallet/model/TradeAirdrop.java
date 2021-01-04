package com.lanjing.wallet.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.DoubleTwoUtil;

import java.util.Date;

public class TradeAirdrop {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Long code;

    private String title;

    private Integer type;

    private Integer cycle;

    private Integer periodsNum;

    private Integer status;

    @JsonSerialize(using = DoubleTwoUtil.class)
    private Double amount;

    @JsonSerialize(using = DoubleTwoUtil.class)
    private Double transaction;

    @JsonSerialize(using = DoubleTwoUtil.class)
    private Double lockBalance;

    @JsonSerialize(using = DoubleTwoUtil.class)
    private Double total;

    @JsonSerialize(using = DoubleTwoUtil.class)
    private Double coinTransaction;

    @JsonSerialize(using = DoubleTwoUtil.class)
    private Double dailyRelease;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Double getCoinTransaction() {
        return coinTransaction;
    }

    public void setCoinTransaction(Double coinTransaction) {
        this.coinTransaction = coinTransaction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getPeriodsNum() {
        return periodsNum;
    }

    public void setPeriodsNum(Integer periodsNum) {
        this.periodsNum = periodsNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTransaction() {
        return transaction;
    }

    public void setTransaction(Double transaction) {
        this.transaction = transaction;
    }

    public Double getLockBalance() {
        return lockBalance;
    }

    public void setLockBalance(Double lockBalance) {
        this.lockBalance = lockBalance;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDailyRelease() {
        return dailyRelease;
    }

    public void setDailyRelease(Double dailyRelease) {
        this.dailyRelease = dailyRelease;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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