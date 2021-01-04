package com.lanjing.wallet.agc.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 算力分佣记录对象 agc_cp_profit
 * 
 * @author live
 * @date 2020-10-26
 */
public class AgcCpProfit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    private String userId;

    /** 来自用户 */
    private String fromId;

    /** 算力 */
    private BigDecimal cp;

    /** 第几代 */
    private Long era;

    /** 激活状态（1激活 2未激活） */
    private String status;

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
    public void setCp(BigDecimal cp) 
    {
        this.cp = cp;
    }

    public BigDecimal getCp() 
    {
        return cp;
    }
    public void setEra(Long era) 
    {
        this.era = era;
    }

    public Long getEra() 
    {
        return era;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("fromId", getFromId())
            .append("cp", getCp())
            .append("era", getEra())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
