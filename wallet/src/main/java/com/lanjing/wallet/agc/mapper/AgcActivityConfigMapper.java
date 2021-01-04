package com.lanjing.wallet.agc.mapper;

import java.util.List;
import com.lanjing.wallet.agc.domain.AgcActivityConfig;

/**
 * 活动配置Mapper接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface AgcActivityConfigMapper 
{
    /**
     * 查询活动配置
     * 
     * @param id 活动配置ID
     * @return 活动配置
     */
    public AgcActivityConfig selectAgcActivityConfigById(Long id);

    /**
     * 查询活动配置列表
     * 
     * @param agcActivityConfig 活动配置
     * @return 活动配置集合
     */
    public List<AgcActivityConfig> selectAgcActivityConfigList(AgcActivityConfig agcActivityConfig);

    /**
     * 新增活动配置
     * 
     * @param agcActivityConfig 活动配置
     * @return 结果
     */
    public int insertAgcActivityConfig(AgcActivityConfig agcActivityConfig);

    /**
     * 修改活动配置
     * 
     * @param agcActivityConfig 活动配置
     * @return 结果
     */
    public int updateAgcActivityConfig(AgcActivityConfig agcActivityConfig);

    /**
     * 删除活动配置
     * 
     * @param id 活动配置ID
     * @return 结果
     */
    public int deleteAgcActivityConfigById(Long id);

    /**
     * 批量删除活动配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgcActivityConfigByIds(Long[] ids);
}
