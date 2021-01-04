package com.lanjing.wallet.agc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lanjing.wallet.agc.domain.AgcActivityConfig;
import com.lanjing.wallet.agc.mapper.AgcActivityConfigMapper;
import com.lanjing.wallet.agc.service.IAgcActivityConfigService;
import com.lanjing.wallet.util.DateUtils;

/**
 * 活动配置Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcActivityConfigServiceImpl implements IAgcActivityConfigService 
{
    @Autowired
    private AgcActivityConfigMapper agcActivityConfigMapper;

    /**
     * 查询活动配置
     * 
     * @param id 活动配置ID
     * @return 活动配置
     */
    @Override
    public AgcActivityConfig selectAgcActivityConfigById(Long id)
    {
        return agcActivityConfigMapper.selectAgcActivityConfigById(id);
    }

    /**
     * 查询活动配置列表
     * 
     * @param agcActivityConfig 活动配置
     * @return 活动配置
     */
    @Override
    public List<AgcActivityConfig> selectAgcActivityConfigList(AgcActivityConfig agcActivityConfig)
    {
        return agcActivityConfigMapper.selectAgcActivityConfigList(agcActivityConfig);
    }

    /**
     * 新增活动配置
     * 
     * @param agcActivityConfig 活动配置
     * @return 结果
     */
    @Override
    public int insertAgcActivityConfig(AgcActivityConfig agcActivityConfig)
    {
        agcActivityConfig.setCreateTime(DateUtils.getNowDate());
        return agcActivityConfigMapper.insertAgcActivityConfig(agcActivityConfig);
    }

    /**
     * 修改活动配置
     * 
     * @param agcActivityConfig 活动配置
     * @return 结果
     */
    @Override
    public int updateAgcActivityConfig(AgcActivityConfig agcActivityConfig)
    {
        agcActivityConfig.setUpdateTime(DateUtils.getNowDate());
        return agcActivityConfigMapper.updateAgcActivityConfig(agcActivityConfig);
    }

    /**
     * 批量删除活动配置
     * 
     * @param ids 需要删除的活动配置ID
     * @return 结果
     */
    @Override
    public int deleteAgcActivityConfigByIds(Long[] ids)
    {
        return agcActivityConfigMapper.deleteAgcActivityConfigByIds(ids);
    }

    /**
     * 删除活动配置信息
     * 
     * @param id 活动配置ID
     * @return 结果
     */
    @Override
    public int deleteAgcActivityConfigById(Long id)
    {
        return agcActivityConfigMapper.deleteAgcActivityConfigById(id);
    }
}
