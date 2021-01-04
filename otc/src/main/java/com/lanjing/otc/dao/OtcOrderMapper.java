package com.lanjing.otc.dao;

import com.lanjing.otc.model.OtcOrder;
import com.lanjing.otc.model.OtcOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 16:23
 */
public interface OtcOrderMapper {
    long countByExample(OtcOrderExample example);

    int deleteByExample(OtcOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OtcOrder record);

    int insertSelective(OtcOrder record);

    List<OtcOrder> selectByExample(OtcOrderExample example);

    OtcOrder selectByPrimaryKey(Integer id);

    List<OtcOrder> selectByuser(Integer trade_user,Integer state,Integer begin ,Integer pagesize);

    List<OtcOrder> selectByuser1(Integer trade_user,Integer state,Integer begin ,Integer pagesize);

    int selectByuserCount(Integer trade_user, Integer state);

    int updateByExampleSelective(@Param("record") OtcOrder record, @Param("example") OtcOrderExample example);

    int updateByExample(@Param("record") OtcOrder record, @Param("example") OtcOrderExample example);

    int updateByPrimaryKeySelective(OtcOrder record);

    int updateByPrimaryKey(OtcOrder record);

    int selectUnfinished(@Param("userkey") Integer userkey);
}