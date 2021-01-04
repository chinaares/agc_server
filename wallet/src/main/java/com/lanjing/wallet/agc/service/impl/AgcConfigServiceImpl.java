package com.lanjing.wallet.agc.service.impl;

import java.util.List;
import com.lanjing.wallet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.lanjing.wallet.agc.mapper.AgcConfigMapper;
import com.lanjing.wallet.agc.domain.AgcConfig;
import com.lanjing.wallet.agc.service.IAgcConfigService;

/**
 * AGC基础配置Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcConfigServiceImpl implements IAgcConfigService 
{
    @Autowired
    private AgcConfigMapper agcConfigMapper;

    /**
     * 查询AGC基础配置
     * 
     * @param id AGC基础配置ID
     * @return AGC基础配置
     */
    @Override
    public AgcConfig selectAgcConfigById(Long id)
    {
        return agcConfigMapper.selectAgcConfigById(id);
    }

    /**
     * 查询AGC基础配置列表
     * 
     * @param agcConfig AGC基础配置
     * @return AGC基础配置
     */
    @Override
    public List<AgcConfig> selectAgcConfigList(AgcConfig agcConfig)
    {
        return agcConfigMapper.selectAgcConfigList(agcConfig);
    }

    /**
     * 新增AGC基础配置
     * 
     * @param agcConfig AGC基础配置
     * @return 结果
     */
    @Override
    public int insertAgcConfig(AgcConfig agcConfig)
    {
        agcConfig.setCreateTime(DateUtils.getNowDate());
        return agcConfigMapper.insertAgcConfig(agcConfig);
    }

    /**
     * 修改AGC基础配置
     * 
     * @param agcConfig AGC基础配置
     * @return 结果
     */
    @Override
    public int updateAgcConfig(AgcConfig agcConfig)
    {
        agcConfig.setUpdateTime(DateUtils.getNowDate());
        return agcConfigMapper.updateAgcConfig(agcConfig);
    }

    /**
     * 批量删除AGC基础配置
     * 
     * @param ids 需要删除的AGC基础配置ID
     * @return 结果
     */
    @Override
    public int deleteAgcConfigByIds(Long[] ids)
    {
        return agcConfigMapper.deleteAgcConfigByIds(ids);
    }

    /**
     * 删除AGC基础配置信息
     * 
     * @param id AGC基础配置ID
     * @return 结果
     */
    @Override
    public int deleteAgcConfigById(Long id)
    {
        return agcConfigMapper.deleteAgcConfigById(id);
    }
}
