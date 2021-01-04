package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Map> withdrawlist(@Param("adress")String adress ,
                           @Param("likes") String likes ,
                           @Param("begin")  Integer begin ,
                           @Param("end") Integer end,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("coinType")String coinType);

    List<Map> rechargelist(@Param("adress")String adress ,@Param("likes") String likes ,
                           @Param("begin") Integer begin ,@Param("end") Integer end,
                           @Param("startTime")String startTime,@Param("endTime")String endTime,@Param("coinType")String coinType);

    int withdrawlistCount(@Param("adress")String adress ,@Param("likes") String likes,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("coinType")String coinType);

    int rechargelistCount(@Param("adress")String adress ,@Param("likes") String likes,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("coinType")String coinType);

    List<Orders> selectBy(@Param("record") Orders record, Integer begin , Integer end);

    Integer countByStateAndUsersell(@Param("state")Integer state,@Param("usersell")String usersell);

    List<Map> querytxid();

    List<Map> queryprocess();

    Orders queryorderId(String orderid);

    List<Map> querytxid1();

}