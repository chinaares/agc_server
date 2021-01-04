package com.lanjing.wallet.agc.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户消费分佣记录对象 agc_consume_profit
 * 
 * @author live
 * @date 2020-10-26
 */
public class AgcConsumeProfit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    private String userId;

    /** 来自用户 */
    private String fromId;

    /** 第几代 */
    private Long era;

    /** 数量 */
    private BigDecimal number;

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
    public void setFromId(String fromId) 
    {
        this.fromId = fromId;
    }

    public String getFromId() 
    {
        return fromId;
    }
    public void setEra(Long era) 
    {
        this.era = era;
    }

    public Long getEra() 
    {
        return era;
    }
    public void setNumber(BigDecimal number) 
    {
        this.number = number;
    }

    public BigDecimal getNumber() 
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("fromId", getFromId())
            .append("era", getEra())
            .append("number", getNumber())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
