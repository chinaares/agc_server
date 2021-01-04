package com.lanjing.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AirdropReward {
    private Integer id;

    private Integer userId;

    private Integer airdropId;

    private Long code;

    private Integer status;

    private Integer type;

    private Double bonus;

    private Double dailyRelease;

    private Double total;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getDailyRelease() {
        return dailyRelease;
    }

    public void setDailyRelease(Double dailyRelease) {
        this.dailyRelease = dailyRelease;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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