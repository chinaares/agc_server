package com.lanjing.wallet.agc.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户挖矿记录对象 agc_mining_user
 * 
 * @author live
 * @date 2020-10-26
 */
public class AgcMiningUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    private String userId;

    /** 总算力 */
    private BigDecimal totalCp;

    /** 金额 */
    private BigDecimal amount;

    /** 数量 */
    private BigDecimal number;

    /** 消费算力 */
    private BigDecimal consumeCp;

    /** 邀请算力 */
    private BigDecimal profitCp;

    /** 活动算力 */
    private BigDecimal activityCp;

    /** 获得币 */
    private BigDecimal getAgc;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setTotalCp(BigDecimal totalCp) 
    {
        this.totalCp = totalCp;
    }

    public BigDecimal getTotalCp() 
    {
        return totalCp;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setNumber(BigDecimal number) 
    {
        this.number = number;
    }

    public BigDecimal getNumber() 
    {
        return number;
    }
    public void setConsumeCp(BigDecimal consumeCp) 
    {
        this.consumeCp = consumeCp;
    }

    public BigDecimal getConsumeCp() 
    {
        return consumeCp;
    }
    public void setProfitCp(BigDecimal profitCp) 
    {
        this.profitCp = profitCp;
    }

    public BigDecimal getProfitCp() 
    {
        return profitCp;
    }
    public void setActivityCp(BigDecimal activityCp) 
    {
        this.activityCp = activityCp;
    }

    public BigDecimal getActivityCp() 
    {
        return activityCp;
    }
    public void setGetAgc(BigDecimal getAgc) 
    {
        this.getAgc = getAgc;
    }

    public BigDecimal getGetAgc() 
    {
        return getAgc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("totalCp", getTotalCp())
            .append("amount", getAmount())
            .append("number", getNumber())
            .append("consumeCp", getConsumeCp())
            .append("profitCp", getProfitCp())
            .append("activityCp", getActivityCp())
            .append("getAgc", getGetAgc())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
