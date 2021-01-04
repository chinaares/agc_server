package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Invest;
import com.lanjing.wallet.model.InvestExample;
import com.lanjing.wallet.model.po.FinancialRecord;
import com.lanjing.wallet.model.po.IncomeInterest;
import com.lanjing.wallet.model.po.InvestCheck;
import com.lanjing.wallet.model.po.InvestReward;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface InvestMapper {
    long countByExample(InvestExample example);

    int deleteByExample(InvestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Invest record);

    int insertSelective(Invest record);

    List<Invest> selectByExample(InvestExample example);

    Invest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Invest record, @Param("example") InvestExample example);

    int updateByExample(@Param("record") Invest record, @Param("example") InvestExample example);

    int updateByPrimaryKeySelective(Invest record);

    int updateByPrimaryKey(Invest record);

    List<IncomeInterest> selectIncomeInterest();

    List<InvestCheck> getInvestCheck(@Param("phone") String phone, @Param("status") Integer status);

    List<InvestCheck> getInvest(@Param("phone") String phone);

    BigDecimal sumPrincipal();

    BigDecimal sumRevenue();

    BigDecimal sumTotalRelease();

    BigDecimal sumTodayPrincipal(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<InvestReward> selectByUserId(Integer userId);

    Double sumPrincipalByUserID(@Param("user") Integer user);

    List<FinancialRecord> selectByKeyAndStatus(@Param("status") Integer status, @Param("key") String key);
}