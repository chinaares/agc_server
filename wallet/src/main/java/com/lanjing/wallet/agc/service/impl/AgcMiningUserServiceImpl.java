package com.lanjing.wallet.agc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lanjing.wallet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.lanjing.wallet.agc.mapper.AgcMiningUserMapper;
import com.lanjing.wallet.agc.domain.AgcMiningUser;
import com.lanjing.wallet.agc.service.IAgcMiningUserService;

/**
 * 用户挖矿记录Service业务层处理
 * 
 * @author live
 * @date 2020-10-26
 */
@Service
@Primary
public class AgcMiningUserServiceImpl implements IAgcMiningUserService 
{
    @Autowired
    private AgcMiningUserMapper agcMiningUserMapper;

    /**
     * 查询用户挖矿记录
     * 
     * @param id 用户挖矿记录ID
     * @return 用户挖矿记录
     */
    @Override
    public AgcMiningUser selectAgcMiningUserById(Long id)
    {
        return agcMiningUserMapper.selectAgcMiningUserById(id);
    }

    /**
     * 查询用户挖矿记录列表
     * 
     * @param agcMiningUser 用户挖矿记录
     * @return 用户挖矿记录
     */
    @Override
    public List<AgcMiningUser> selectAgcMiningUserList(AgcMiningUser agcMiningUser)
    {
        return agcMiningUserMapper.selectAgcMiningUserList(agcMiningUser);
    }

    /**
     * 新增用户挖矿记录
     * 
     * @param agcMiningUser 用户挖矿记录
     * @return 结果
     */
    @Override
    public int insertAgcMiningUser(AgcMiningUser agcMiningUser)
    {
        agcMiningUser.setCreateTime(DateUtils.getNowDate());
        return agcMiningUserMapper.insertAgcMiningUser(agcMiningUser);
    }

    /**
     * 修改用户挖矿记录
     * 
     * @param agcMiningUser 用户挖矿记录
     * @return 结果
     */
    @Override
    public int updateAgcMiningUser(AgcMiningUser agcMiningUser)
    {
        agcMiningUser.setUpdateTime(DateUtils.getNowDate());
        return agcMiningUserMapper.updateAgcMiningUser(agcMiningUser);
    }

    /**
     * 批量删除用户挖矿记录
     * 
     * @param ids 需要删除的用户挖矿记录ID
     * @return 结果
     */
    @Override
    public int deleteAgcMiningUserByIds(Long[] ids)
    {
        return agcMiningUserMapper.deleteAgcMiningUserByIds(ids);
    }

    /**
     * 删除用户挖矿记录信息
     * 
     * @param id 用户挖矿记录ID
     * @return 结果
     */
    @Override
    public int deleteAgcMiningUserById(Long id)
    {
        return agcMiningUserMapper.deleteAgcMiningUserById(id);
    }

    /**
     * 批量插入记录
     * @param userList
     */
	@Override
	public void insertAgcMiningUserList(List<AgcMiningUser> userList) {
		agcMiningUserMapper.insertAgcMiningUserList(userList);
	}

	/**
	 * 我的挖矿收益明细-分页{每天挖矿的数量}
	 */
	@Override
	public List<AgcMiningUser> selectMiningUserListByUserId(String userId) {
		return agcMiningUserMapper.selectMiningUserListByUserId(userId);
	}

	/**今日收益*/
	@Override
	public BigDecimal selectDayIncomeByNowAndUserId(Date now, String userId) {
		return agcMiningUserMapper.selectDayIncomeByNowAndUserId(now,userId);
	}

	/**累计收益*/
	@Override
	public BigDecimal selectDayIncomeByUserId(String userId) {
		return agcMiningUserMapper.selectDayIncomeByUserId(userId);
	}
}
