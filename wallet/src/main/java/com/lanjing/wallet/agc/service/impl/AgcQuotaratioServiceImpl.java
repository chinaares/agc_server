package com.lanjing.wallet.agc.service.impl;

import java.util.List;
import com.lanjing.wallet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.lanjing.wallet.agc.mapper.AgcQuotaratioMapper;
import com.lanjing.wallet.agc.domain.AgcQuotaratio;
import com.lanjing.wallet.agc.service.IAgcQuotaratioService;

/**
 * 消费额度比例配置Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcQuotaratioServiceImpl implements IAgcQuotaratioService 
{
    @Autowired
    private AgcQuotaratioMapper agcQuotaratioMapper;

    /**
     * 查询消费额度比例配置
     * 
     * @param id 消费额度比例配置ID
     * @return 消费额度比例配置
     */
    @Override
    public AgcQuotaratio selectAgcQuotaratioById(Long id)
    {
        return agcQuotaratioMapper.selectAgcQuotaratioById(id);
    }

    /**
     * 查询消费额度比例配置列表
     * 
     * @param agcQuotaratio 消费额度比例配置
     * @return 消费额度比例配置
     */
    @Override
    public List<AgcQuotaratio> selectAgcQuotaratioList(AgcQuotaratio agcQuotaratio)
    {
        return agcQuotaratioMapper.selectAgcQuotaratioList(agcQuotaratio);
    }

    /**
     * 新增消费额度比例配置
     * 
     * @param agcQuotaratio 消费额度比例配置
     * @return 结果
     */
    @Override
    public int insertAgcQuotaratio(AgcQuotaratio agcQuotaratio)
    {
        agcQuotaratio.setCreateTime(DateUtils.getNowDate());
        return agcQuotaratioMapper.insertAgcQuotaratio(agcQuotaratio);
    }

    /**
     * 修改消费额度比例配置
     * 
     * @param agcQuotaratio 消费额度比例配置
     * @return 结果
     */
    @Override
    public int updateAgcQuotaratio(AgcQuotaratio agcQuotaratio)
    {
        agcQuotaratio.setUpdateTime(DateUtils.getNowDate());
        return agcQuotaratioMapper.updateAgcQuotaratio(agcQuotaratio);
    }

    /**
     * 批量删除消费额度比例配置
     * 
     * @param ids 需要删除的消费额度比例配置ID
     * @return 结果
     */
    @Override
    public int deleteAgcQuotaratioByIds(Long[] ids)
    {
        return agcQuotaratioMapper.deleteAgcQuotaratioByIds(ids);
    }

    /**
     * 删除消费额度比例配置信息
     * 
     * @param id 消费额度比例配置ID
     * @return 结果
     */
    @Override
    public int deleteAgcQuotaratioById(Long id)
    {
        return agcQuotaratioMapper.deleteAgcQuotaratioById(id);
    }
}
