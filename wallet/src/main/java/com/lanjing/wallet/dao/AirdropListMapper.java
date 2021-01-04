package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.AirdropList;
import com.lanjing.wallet.model.AirdropListExample;
import com.lanjing.wallet.model.po.FinancialRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-14 14:39
 */
public interface AirdropListMapper {
    long countByExample(AirdropListExample example);

    int deleteByExample(AirdropListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AirdropList record);

    int insertSelective(AirdropList record);

    List<AirdropList> selectByExample(AirdropListExample example);

    AirdropList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AirdropList record, @Param("example") AirdropListExample example);

    int updateByExample(@Param("record") AirdropList record, @Param("example") AirdropListExample example);

    int updateByPrimaryKeySelective(AirdropList record);

    int updateByPrimaryKey(AirdropList record);

    List<AirdropList> findByUserId(@Param("userId") Integer userId,@Param("id") Integer id);

    List<AirdropList> findByStatus(@Param("status")Integer status);

    int updateDaysAndStatusById(@Param("status") Boolean status, @Param("id") Integer id,@Param("freedBtc") BigDecimal freedBtc,@Param("freedYyb") BigDecimal freedYyb);

    List<FinancialRecord> selectByKeyAndStatus(@Param("status")Integer status,@Param("key")String key);

    BigDecimal sumFreeYyb(@Param("userId") Integer userId, @Param("id") Integer id);

    BigDecimal sumFreeBtc(@Param("userId") Integer userId, @Param("id") Integer id);

    BigDecimal sumFreezeYyb(@Param("userId") Integer userId, @Param("id") Integer id);

    BigDecimal sumFreezeBtc(@Param("userId") Integer userId, @Param("id") Integer id);

    int minusFreeBtc(@Param("id") Integer id,@Param("amount") Double amount);

    int minusFreeYyb(@Param("id") Integer id,@Param("amount") Double amount);

    BigDecimal sumTotalCoinNum(@Param("today") Date today,@Param("tomorrow") Date tomorrow);

    Integer countMaturity(@Param("days") Integer days);

    List<FinancialRecord> maturityFinancialRecord(@Param("week") Integer week,@Param("status") Integer status,@Param("key") String key);
}