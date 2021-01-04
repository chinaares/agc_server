package com.lanjing.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.DoubleUtil;

import java.util.Date;

public class Invest {
    private Integer id;

    private Integer userId;

    private Integer airdropId;

    private String title;

    private Integer periodsNum;

    private Long code;

    private Integer status;

    private Double principal;

    private Double income;

    @JsonSerialize(using = DoubleUtil.class)
    private Double principalBalance;

    @JsonSerialize(using = DoubleUtil.class)
    private Double incomeBalance;

    @JsonSerialize(using = DoubleUtil.class)
    private Double total;

    private Integer fee;

    private Double dailyRelease;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Integer getAirdropId() {
        return airdropId;
    }

    public void setAirdropId(Integer airdropId) {
        this.airdropId = airdropId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPeriodsNum() {
        return periodsNum;
    }

    public void setPeriodsNum(Integer periodsNum) {
        this.periodsNum = periodsNum;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getPrincipal() {
        return principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getPrincipalBalance() {
        return principalBalance;
    }

    public void setPrincipalBalance(Double principalBalance) {
        this.principalBalance = principalBalance;
    }

    public Double getIncomeBalance() {
        return incomeBalance;
    }

    public void setIncomeBalance(Double incomeBalance) {
        this.incomeBalance = incomeBalance;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
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