package com.lanjing.wallet.agc.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户消费记录对象 agc_consume_record
 * 
 * @author live
 * @date 2020-10-26
 */
public class AgcConsumeRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    private String userId;

    /** 金额 */
    private BigDecimal amount;

    /** 数量 */
    private BigDecimal number;

    /** 消费算力 */
    private BigDecimal consumeCp;

    /** 活动算力 */
    private BigDecimal activityCp;

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
    public void setActivityCp(BigDecimal activityCp) 
    {
        this.activityCp = activityCp;
    }

    public BigDecimal getActivityCp() 
    {
        return activityCp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("amount", getAmount())
            .append("number", getNumber())
            .append("consumeCp", getConsumeCp())
            .append("activityCp", getActivityCp())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
