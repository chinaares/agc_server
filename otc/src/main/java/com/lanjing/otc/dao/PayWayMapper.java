package com.lanjing.otc.dao;

import com.lanjing.otc.model.PayWay;
import com.lanjing.otc.model.PayWayExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 16:08
 */
public interface PayWayMapper {
    long countByExample(PayWayExample example);

    int deleteByExample(PayWayExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(PayWay record);

    int insertSelective(PayWay record);

    List<PayWay> selectByExample(PayWayExample example);

    PayWay selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") PayWay record, @Param("example") PayWayExample example);

    int updateByExample(@Param("record") PayWay record, @Param("example") PayWayExample example);

    int updateByPrimaryKeySelective(PayWay record);

    int updateByPrimaryKey(PayWay record);

    Integer countByUserid(@Param("userid")Integer userid);


}