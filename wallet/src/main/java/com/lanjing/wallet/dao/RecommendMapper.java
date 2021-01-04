package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RecommendMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);

    int selectByUserKey2Count(String userkey);
    
    int selectByUserKey2AndTimeCount(@Param("userkey") String userkey,@Param("startTime") String startTime,@Param("endTime") String endTime);

    List<Recommend> selectByUserKey2(@Param("map") Map map);

    List<Recommend> selectByUserKey(String key);

    List<Recommend> selectByUserKey2list(String userkey2);

    List<Map> selectByUserKey2list1(String userkey2);

    int selectBycover_code(@Param("map") Map map);

    Recommend selectBycover_code1(String cover_code);

    Recommend selectByUserKey1(String userkey1);

    int selectMaxlev();

    List<Recommend> selectByLev(Integer lev);

    List<Map> selectByLev1(Integer lev);

    int selectBycode(@Param("code") String code);

    int setNode(@Param("userkey") String userkey);

    int colseNode(@Param("userkey") String userkey);

    List<Map> selectupnode(@Param("levnum") Integer levnum);

    List<Recommend> selectByUserKey21(@Param("userkey") String userkey);

    int selectByuserdouwnum(@Param("userkey") String userkey);


    Map selectByuserkey(String userkey);

    Integer sumLevelByKey(@Param("code") String code);

    List<Recommend> queryBycode(Integer levs,String covercode);

    List<Recommend> queryBycodeAndLev(Integer levs,String covercode);

    List<Recommend> queryByNum(Integer num);

    List<Recommend> selectByLockNumAndDownLockNum(@Param("currency") int currency,@Param("user") int user);

    void updateClear();

    Map<String,Double> queryBycodescnum(Integer levs,String covercode);

    int selectByUserKey22(@Param("userkey") String userkey);

    int selectByUserKey23(@Param("userkey") String userkey,@Param("usergrede") Integer usergrede);

    double sumLockNum(@Param("userkey") String userkey);

    double sumRelease(@Param("level") int level,@Param("userkey") String userkey);

    List<Recommend> levelRecommend(@Param("userkey") String userkey,@Param("level") int level);

    List<Recommend> smallRecommend(@Param("userkey") String userkey,@Param("level") int level);

    List<Recommend> selectByLevDesc(@Param("level") int level);

    List<Recommend> selectUserAdvanced(@Param("userKey") String userKey);

   int updateDownRelease(@Param("id") Integer id,@Param("release") double release);

    List<Recommend> selectAll();

	List<Recommend> selectListByTime(String startTime, String endTime);
}