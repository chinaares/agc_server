package com.lanjing.wallet.agc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AGC基础配置对象 agc_config
 * 
 * @author live
 * @date 2020-10-26
 */
public class AgcConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 日产量 */
    private BigDecimal yield;

    /** 全网真实算力 */
    private BigDecimal realCp;

    /** 全网虚拟算力 */
    private BigDecimal fictitiousCp;

    /** AGC价格 */
    private BigDecimal agcPrice;

    /** AGC涨跌幅 */
    private BigDecimal agcChg;

    /** 算力额度比 */
    private BigDecimal agcCnyCp;

    /** 提现手续费比例 */
    private BigDecimal withdrawRatio;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setYield(BigDecimal yield) 
    {
        this.yield = yield;
    }

    public BigDecimal getYield() 
    {
        return yield;
    }
    public void setRealCp(BigDecimal realCp) 
    {
        this.realCp = realCp;
    }

    public BigDecimal getRealCp() 
    {
        return realCp;
    }
    public void setFictitiousCp(BigDecimal fictitiousCp) 
    {
        this.fictitiousCp = fictitiousCp;
    }

    public BigDecimal getFictitiousCp() 
    {
        return fictitiousCp;
    }
    public void setAgcPrice(BigDecimal agcPrice) 
    {
        this.agcPrice = agcPrice;
    }

    public BigDecimal getAgcPrice() 
    {
        return agcPrice;
    }
    public void setAgcChg(BigDecimal agcChg) 
    {
        this.agcChg = agcChg;
    }

    public BigDecimal getAgcChg() 
    {
        return agcChg;
    }
    public void setAgcCnyCp(BigDecimal agcCnyCp) 
    {
        this.agcCnyCp = agcCnyCp;
    }

    public BigDecimal getAgcCnyCp() 
    {
        return agcCnyCp;
    }
    public void setWithdrawRatio(BigDecimal withdrawRatio) 
    {
        this.withdrawRatio = withdrawRatio;
    }

    public BigDecimal getWithdrawRatio() 
    {
        return withdrawRatio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("yield", getYield())
            .append("realCp", getRealCp())
            .append("fictitiousCp", getFictitiousCp())
            .append("agcPrice", getAgcPrice())
            .append("agcChg", getAgcChg())
            .append("agcCnyCp", getAgcCnyCp())
            .append("withdrawRatio", getWithdrawRatio())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
