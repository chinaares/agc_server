package com.lanjing.wallet.agc.service;

import java.util.List;
import java.util.Map;

import com.lanjing.wallet.agc.domain.AgcMiningTotal;

/**
 * 平台总挖矿记录Service接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface IAgcMiningTotalService 
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
     * 批量删除平台总挖矿记录
     * 
     * @param ids 需要删除的平台总挖矿记录ID
     * @return 结果
     */
    public int deleteAgcMiningTotalByIds(Long[] ids);

    /**
     * 删除平台总挖矿记录信息
     * 
     * @param id 平台总挖矿记录ID
     * @return 结果
     */
    public int deleteAgcMiningTotalById(Long id);

	/**
	 * AGC凌晨进行挖矿
	 */
    public void miningAgc() throws Exception;

    /**
	 * 全网数据
	 * {全网算力，消费算力，邀请算力，已挖出，待挖出，今日产量}
	 */
	public Map selectInfo();
}
