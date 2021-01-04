package com.lanjing.wallet.agc.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lanjing.wallet.agc.domain.AgcMiningTotal;

/**
 * 平台总挖矿记录Mapper接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface AgcMiningTotalMapper 
{
    /**
     * 查询平台总挖矿记录
     * 
     * @param id 平台总挖矿记录ID
     * @return 平台总挖矿记录
     */
    public AgcMiningTotal selectAgcMiningTotalById(Long id);

    /**
     * 查询平台总挖矿记录列表
     * 
     * @param agcMiningTotal 平台总挖矿记录
     * @return 平台总挖矿记录集合
     */
    public List<AgcMiningTotal> selectAgcMiningTotalList(AgcMiningTotal agcMiningTotal);

    /**
     * 新增平台总挖矿记录
     * 
     * @param agcMiningTotal 平台总挖矿记录
     * @return 结果
     */
    public int insertAgcMiningTotal(AgcMiningTotal agcMiningTotal);

    /**
     * 修改平台总挖矿记录
     * 
     * @param agcMiningTotal 平台总挖矿记录
     * @return 结果
     */
    public int updateAgcMiningTotal(AgcMiningTotal agcMiningTotal);

    /**
     * 删除平台总挖矿记录
     * 
     * @param id 平台总挖矿记录ID
     * @return 结果
     */
    public int deleteAgcMiningTotalById(Long id);

    /**
     * 批量删除平台总挖矿记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgcMiningTotalByIds(Long[] ids);

    /**
     * 已挖出 多少量
     */
	public BigDecimal selectDigOut();

	/**
	 * 判断今天是否已经挖矿
	 */
	public AgcMiningTotal selectAgcMiningTotalByDate(Date date);
}
