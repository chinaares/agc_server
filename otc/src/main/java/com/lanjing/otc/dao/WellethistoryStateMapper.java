package com.lanjing.otc.dao;


import com.lanjing.otc.model.WellethistoryState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WellethistoryStateMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(WellethistoryState record);

    int insertSelective(WellethistoryState record);

    WellethistoryState selectByPrimaryKey(Integer fid);

    WellethistoryState selectByKey(String keyes);

    List<WellethistoryState> selectByKey2(String keyes);

    List<WellethistoryState> selectByWelletId(@Param("welletid") Integer welletid, @Param("type") Integer type, @Param("begin") Integer begin, @Param("end") Integer end);

    int updateByPrimaryKeySelective(WellethistoryState record);

    int updateByPrimaryKey(WellethistoryState record);

    List<WellethistoryState> selectBycztx(Integer welletId, Integer begin, Integer end);

    List<WellethistoryState> selectBytransfer(Integer welletId, Integer begin, Integer end);

    List<WellethistoryState> queryAlltransfer(Integer state, Integer begin, Integer end);

    List<WellethistoryState> reward(List list, Integer begin, Integer end);

    int gettodaybuyNum(Integer welletId);

    Map queryByKeyes(String keyes);

    int uptByKeyes(String keyes, Integer state);


    int querywithdraw(Integer userid, Integer coinid);

    @Select("select ifnull(sum(changeNum),0) from wellethistorystate where state=1 and cointype=#{coinId} and type=#{type} and updatetime between #{today} and #{tomorrow} ")
    BigDecimal sumTotalCoinNum(@Param("today") Date today, @Param("tomorrow") Date tomorrow, @Param("coinId") int coinId, @Param("type") Integer type);

    double selectAllETHIn();

    double selectAllBTCIn();

    double selectAllYYBIn();

    double selectTodayETHIn();

    double selectTodayBTCIn();

    double selectTodayYYBIn();

    double selectAllETHOut();

    double selectAllBTCOut();

    double selectAllYYBOut();

    double selectTodayETHOut();

    double selectTodayBTCOut();

    double selectTodayYYBOut();

    double selectAllIntegralOut();
    double selectAllIntegralIn();

}