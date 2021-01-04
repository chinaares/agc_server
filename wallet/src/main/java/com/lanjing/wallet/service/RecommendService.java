package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Recommend;

import java.util.List;
import java.util.Map;

public interface RecommendService {
    int deleteByPrimaryKey(Integer fid);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer fid);

    List<Recommend> selectByUserKey2 (Map map);

    int selectByUserKey2Count(String userkey);

    Recommend selectByUserKey1 (String userkey);

    int updateByPrimaryKeySelective(Recommend record);

    List<Map> selectupnode(Integer levnum);

    /**
     * 获取被邀请人
     * @param key
     * @return
     */
    List<Recommend> selectByUserKey(String key);

    /**
     * 查询key下线人数
     * @param code
     * @return
     */
    Integer sumLevelByKey(String code);

    List<Recommend> selectByUserKey21(String userkey);


    List<Recommend> queryBycode(Integer levs,String covercode);

    List<Recommend> queryBycodeAndLev(Integer levs,String covercode);

    List<Recommend> queryByNum(Integer num);

    List<Recommend> selectByLockNumAndDownLockNum(int currency, int user);

    void updateClear();

    double sumLockNum(String userkey);

    double sumRelease(int level, String userkey);

    List<Recommend> levelRecommend(String userKey, int level);

    List<Recommend> smallRecommend(String userKey, int level);

    List<Recommend> selectByLevDesc(int level);

    List<Recommend> selectUserAdvanced(String userKey);

    int updateDownRelease(Integer id,double release);
    
    List<Recommend> selectAll();

	int selectByUserKey2AndTimeCount(String userId, String startTime, String endTime);

	/**
	 * 获取一个用户的所有代下级
	 * 
	 * @param userList 所有用户集合
	 * @param userId   父ID
	 * @return
	 */
	List<String> selectUserSubordinateList(List<Recommend> userList, List<String> userIdList, String userId,String startTime,String endTime,List<String> userIdList2);

	List<Recommend> selectListByTime(String startTime, String endTime);
}
