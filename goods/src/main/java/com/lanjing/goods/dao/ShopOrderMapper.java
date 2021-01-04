package com.lanjing.goods.dao;

import com.lanjing.goods.model.ShopOrder;
import com.lanjing.goods.model.OrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopOrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    ShopOrder selectByPrimaryKey(@Param("fid") String fid);

    List<ShopOrder> selectByExample(OrderExample example);

    List<Map> queryorderBystate(Integer userkey, Integer state, Integer begin, Integer end);

    int queryorderBystateCount(Integer userkey, Integer state);

    Map queryorderById(Integer fId);

    List<ShopOrder> queryByOrderid(@Param("orderId") String orderId);

    ShopOrder queryByOrderidAndGoodsskuId(@Param("orderId") String orderId, @Param("goodsskuId") Integer goodsskuId);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByOrderid(@Param("orderId") String orderId, @Param("state") Integer state);

    int updateLogisticsByOrderid(@Param("orderId") String orderId, @Param("logistics") String logistics);

    List<Map> selectOrderBypage(@Param("userkey") Integer userkey, @Param("state") Integer state, @Param("begin") Integer begin, @Param("size") Integer size);

    List<Map> selectOrderBypage1(@Param("shop_code") Long shop_code,
                                 @Param("state") Integer state,
                                 @Param("orderid") String orderid,
                                 @Param("userphone") String userphone,
                                 @Param("username") String username,
                                 @Param("begin") Integer begin, @Param("size") Integer size);

    int selectOrderBypageCount1(@Param("shop_code") Long shop_code,
                                @Param("state") Integer state,
                                @Param("orderid") String orderid,
                                @Param("username") String username,
                                @Param("userphone") String userphone);

    int selectOrderBypageCount(@Param("userkey") Integer userkey, @Param("state") Integer state);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") OrderExample example);
}