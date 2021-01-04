package com.lanjing.wallet.agc.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 平台总挖矿记录对象 agc_mining_total
 * 
 * @author live
 * @date 2020-10-26
 */
public class AgcMiningTotal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 挖矿用户人数 */
    private Long userCount;

    /** 挖矿总算力=实际+虚拟 */
    private BigDecimal miningCp;

    /** 实际总算力 */
    private BigDecimal totalCp;

    /** 虚拟总算力 */
    private BigDecimal fictitiousCp;

    /** 消费金额 */
    private BigDecimal amount;

    /** 消费数量 */
    private BigDecimal number;

    /** 消费算力 */
    private BigDecimal consumeCp;

    /** 邀请算力 */
    private BigDecimal profitCp;

    /** 活动算力 */
    private BigDecimal activityCp;

    /** 产出币 */
    private BigDecimal produceAgc;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserCount(Long userCount) 
    {
        this.userCount = userCount;
    }

    public Long getUserCount() 
    {
        return userCount;
    }
    
    public BigDecimal getMiningCp() {
		return miningCp;
	}

	public void setMiningCp(BigDecimal miningCp) {
		this.miningCp = miningCp;
	}

	public void setTotalCp(BigDecimal totalCp) 
    {
        this.totalCp = totalCp;
    }

    public BigDecimal getTotalCp() 
    {
        return totalCp;
    }
    public void setFictitiousCp(BigDecimal fictitiousCp) 
    {
        this.fictitiousCp = fictitiousCp;
    }

    public BigDecimal getFictitiousCp() 
    {
        return fictitiousCp;
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
    public void setProduceAgc(BigDecimal produceAgc) 
    {
        this.produceAgc = produceAgc;
    }

    public BigDecimal getProduceAgc() 
    {
        return produceAgc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userCount", getUserCount())
            .append("totalCp", getTotalCp())
            .append("fictitiousCp", getFictitiousCp())
            .append("amount", getAmount())
            .append("number", getNumber())
            .append("consumeCp", getConsumeCp())
            .append("profitCp", getProfitCp())
            .append("activityCp", getActivityCp())
            .append("produceAgc", getProduceAgc())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
