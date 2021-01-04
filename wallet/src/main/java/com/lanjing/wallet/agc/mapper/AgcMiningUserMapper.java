package com.lanjing.wallet.agc.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanjing.wallet.agc.domain.AgcMiningUser;

/**
 * 用户挖矿记录Mapper接口
 * 
 * @author live
 * @date 2020-10-26
 */
public interface AgcMiningUserMapper 
{
    /**
     * 查询用户挖矿记录
     * 
     * @param id 用户挖矿记录ID
     * @return 用户挖矿记录
     */
    public AgcMiningUser selectAgcMiningUserById(Long id);

    /**
     * 查询用户挖矿记录列表
     * 
     * @param agcMiningUser 用户挖矿记录
     * @return 用户挖矿记录集合
     */
    public List<AgcMiningUser> selectAgcMiningUserList(AgcMiningUser agcMiningUser);

    /**
     * 新增用户挖矿记录
     * 
     * @param agcMiningUser 用户挖矿记录
     * @return 结果
     */
    public int insertAgcMiningUser(AgcMiningUser agcMiningUser);

    /**
     * 修改用户挖矿记录
     * 
     * @param agcMiningUser 用户挖矿记录
     * @return 结果
     */
    public int updateAgcMiningUser(AgcMiningUser agcMiningUser);

    /**
     * 删除用户挖矿记录
     * 
     * @param id 用户挖矿记录ID
     * @return 结果
     */
    public int deleteAgcMiningUserById(Long id);

    /**
     * 批量删除用户挖矿记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgcMiningUserByIds(Long[] ids);

    /**
     * 批量插入记录
     * @param userList
     */
	public void insertAgcMiningUserList(@Param("userList") List<AgcMiningUser> userList);

	/**
	 * 我的挖矿收益明细-分页{每天挖矿的数量}
	 */
	public List<AgcMiningUser> selectMiningUserListByUserId(String userId);

	/**今日收益*/
	public BigDecimal selectDayIncomeByNowAndUserId(Date now, String userId);

	/**累计收益*/
	public BigDecimal selectDayIncomeByUserId(String userId);
}
