package com.lanjing.wallet.dao;


import com.lanjing.wallet.model.Changelog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChangelogMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Changelog record);

    int insertSelective(Changelog record);

    Changelog selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Changelog record);

    int updateByPrimaryKey(Changelog record);

    List<Changelog> selectBy(@Param("record") Changelog record, Integer begin, Integer end);

    double selectByUserYesterday(String userkey, Integer type);

    List<Map> selectAirdropByUserOrId1(String username, Integer begin , Integer end,String begintime,String endtime);

    List<Map> selectAirdropByUserOrId2(String username, Integer begin , Integer end ,Integer type,String begintime,String endtime);

    List<Map> selectTradeByUserOrId1(String username, Integer begin , Integer end,String begintime,String endtime);

    List<Map> selectTradeByUserOrId2(String username, Integer begin , Integer end ,Integer type,String begintime,String endtime);

    int selectAirdropCount1(@Param("username") String username,String begintime,String endtime);
    int selectAirdropCount2(@Param("username") String username ,Integer type,String begintime,String endtime);
    int selectTradeCount1(@Param("username") String username,String begintime,String endtime);
    int selectTradeCount2(@Param("username") String username ,Integer type,String begintime,String endtime);


    List<Changelog> selectByUser(String userkey,Integer begin, Integer end);

    double selectByreward(@Param("userkey") String userkey);

    double userUSDKT(@Param("userkey") String userkey);

    double userTitanKT(@Param("userkey") String userkey);


    double userTitanKT1(@Param("userkey") String userkey);


}