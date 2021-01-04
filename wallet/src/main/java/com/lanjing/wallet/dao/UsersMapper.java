package com.lanjing.wallet.dao;

import com.lanjing.wallet.agc.vo.InviteUserVO;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.po.UserTeam;
import com.lanjing.wallet.model.po.Verified;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer fid);

    Map selectIdentityByPrimaryKey(Integer fid);

    int userreal(Integer fid,String realname,String identityId,String identityImg1,String identityImg2);

    int Reviewuserreal(Integer fid,Integer isreal);

    Users selectByUserKey(String keyes);

    List<Users> selectByLogin(Users record);

    List<Users> selectByPrimaryKeys(String fids);

    List<Users> selectBy(@Param("record") Users record, Integer begin , Integer end);

    int selectId();

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKeySelective1(@Param("record") Users record,String admin);

    int updateByPrimaryKey(Users record);

    int updateNodenum();

    int selectByUsername(String username);

    List<String> selectHelpword();

    List<Users> selectByNameorId(String username, Integer begin , Integer end);

    List<Map> selectByNameorId2(String username, Integer begin , Integer end,@Param("startTime")String startTime,@Param("endTime")String endTime);

    List<Map> selectByNameorId1(String username, Integer begin , Integer end);

    int selectCount(@Param("username") String username);

    int selectCount2(@Param("username") String username,@Param("startTime")String startTime,@Param("endTime")String endTime);

    List<Map> selectByNode(Integer begin , Integer end ,String likes ,Integer state);

    int selectByNodeCount(String likes ,Integer state);

    int updateByUserkey(Users record);

    int updateByfids(List<String> list,Integer state);

    int Resetloginpassword(List<String> list);

    int Resettradepassword(List<String> list);

    int uptphone(List<String> list,String phonenum);

    int selectALLCount();
    int selectUserCount();
    int selectBystateCount();

    int selectBytodayCount();

    int selectByNodeOpen();

    int selectByNodeClose();

    int selectByTodayCount();

    Users selectByPhone(@Param("phone") String phone);

    BigDecimal sumTotalUser();

    BigDecimal sumEffectiveUser();

    BigDecimal sumTodayUser(@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    List<Users> selectAll();

    List<UserTeam> selectUserCoverCode();

    List<Verified> selectByStatusAndKey(@Param("status") Integer status, @Param("key") String key);

    int selectTodayUserCount();

    long queryYesterdayNewUserList();

    long queryYesterdayLoginList();

    // 推广直推+间推下级用户列表-分页{时间范围}
	List<InviteUserVO> getUserInviteDirectList(@Param("userId") String userId,@Param("startTime") String startTime,@Param("endTime") String endTime);

	// 推广间推下级用户列表-分页{时间范围}
	List<InviteUserVO> getUserInviteIndirectList(@Param("userIdList") List<String> userIdList,@Param("startTime") String startTime,@Param("endTime") String endTime);
}