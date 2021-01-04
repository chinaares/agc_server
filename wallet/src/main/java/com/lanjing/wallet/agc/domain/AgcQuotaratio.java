package com.lanjing.wallet.agc.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 消费额度比例配置对象 agc_quotaratio
 * 
 * @author live
 * @date 2020-10-26
 */
public class AgcQuotaratio extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 开始额度 */
    private BigDecimal startQuota;

    /** 结束额度 */
    private BigDecimal endQuota;

    /** 消费算力比例 */
    private BigDecimal cpRatio;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStartQuota(BigDecimal startQuota) 
    {
        this.startQuota = startQuota;
    }

    public BigDecimal getStartQuota() 
    {
        return startQuota;
    }
    public void setEndQuota(BigDecimal endQuota) 
    {
        this.endQuota = endQuota;
    }

    public BigDecimal getEndQuota() 
    {
        return endQuota;
    }
    public void setCpRatio(BigDecimal cpRatio) 
    {
        this.cpRatio = cpRatio;
    }

    public BigDecimal getCpRatio() 
    {
        return cpRatio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("startQuota", getStartQuota())
            .append("endQuota", getEndQuota())
            .append("cpRatio", getCpRatio())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
