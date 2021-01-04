package com.lanjing.wallet.agc.mapper;

import java.util.List;
import com.lanjing.wallet.agc.domain.AgcQuotaratio;

/**
 * 消费额度比例配置Mapper接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface AgcQuotaratioMapper 
{
    /**
     * 查询消费额度比例配置
     * 
     * @param id 消费额度比例配置ID
     * @return 消费额度比例配置
     */
    public AgcQuotaratio selectAgcQuotaratioById(Long id);

    /**
     * 查询消费额度比例配置列表
     * 
     * @param agcQuotaratio 消费额度比例配置
     * @return 消费额度比例配置集合
     */
    public List<AgcQuotaratio> selectAgcQuotaratioList(AgcQuotaratio agcQuotaratio);

    /**
     * 新增消费额度比例配置
     * 
     * @param agcQuotaratio 消费额度比例配置
     * @return 结果
     */
    public int insertAgcQuotaratio(AgcQuotaratio agcQuotaratio);

    /**
     * 修改消费额度比例配置
     * 
     * @param agcQuotaratio 消费额度比例配置
     * @return 结果
     */
    public int updateAgcQuotaratio(AgcQuotaratio agcQuotaratio);

    /**
     * 删除消费额度比例配置
     * 
     * @param id 消费额度比例配置ID
     * @return 结果
     */
    public int deleteAgcQuotaratioById(Long id);

    /**
     * 批量删除消费额度比例配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgcQuotaratioByIds(Long[] ids);
}
