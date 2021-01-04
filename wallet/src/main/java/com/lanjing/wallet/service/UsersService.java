package com.lanjing.wallet.service;

import com.lanjing.wallet.agc.vo.InviteUserVO;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.po.UserTeam;
import com.lanjing.wallet.model.po.Verified;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UsersService {

    Users insertSelective(Users record);

    Users register(Users record,String help);

    Users selectByPrimaryKey(Integer fid);

    Map selectIdentityByPrimaryKey(Integer fid);

    int userreal(Integer fid,String realname,String identityId,String identityImg1,String identityImg2);

    int Reviewuserreal(Integer fid,Integer isreal);

    Users selectByLogin(Users record);

    int updateByPrimaryKeySelective(Users record);

    String selectByhelpKey(String help,String keyes);

    List<Users> selectByPrimaryKeys(List<String> fids);

    List<Users> selectBy(Users record, Integer page , Integer size);

    Users selectByUserKey(String userKey);

    int selectId();

    int selectByUsername(String username);

    List<String> selectHelpword();

    int selectByTodayCount();

    Users selectByPhone(String phone);

    /**
     * 注册用户数
     * @return
     */
    BigDecimal sumTotalUser();

    /**
     * 有效用户数
     * @return
     */
    BigDecimal sumEffectiveUser();

    /**
     * 今日新增用户
     * @param today
     * @param tomorrow
     * @return
     */
    BigDecimal sumTodayUser(Date today, Date tomorrow);

    /**
     * 获取所有用户
     * @return
     */
    List<Users> selectAll();

    /**
     * 获取所有用户和级别code
     * @return
     */
    List<UserTeam> selectUserCoverCode();

    List<Verified> selectByStatusAndKey(Integer status, String key);

    // 推广直推下级用户列表-分页{时间范围}
	List<InviteUserVO> getUserInviteDirectList(String userKey, String startTime, String endTime,Integer pageNum,Integer pageSize);

	// 推广间推下级用户列表-分页{时间范围}
	List<InviteUserVO> getUserInviteIndirectList(String userKey, String startTime, String endTime,Integer pageNum,Integer pageSize);
}
