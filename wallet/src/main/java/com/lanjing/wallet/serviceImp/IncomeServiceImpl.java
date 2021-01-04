package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.IncomeMapper;
import com.lanjing.wallet.enums.IncomeEnum;
import com.lanjing.wallet.model.Income;
import com.lanjing.wallet.model.IncomeExample;
import com.lanjing.wallet.model.po.EmptyRecord;
import com.lanjing.wallet.model.po.RewardBonus;
import com.lanjing.wallet.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("IncomeService")
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    IncomeMapper incomeMapper;

    @Override
    public List<Income> selectByInvestId(Integer investId, Integer type) {
        IncomeExample incomeExample = new IncomeExample();
        incomeExample.setOrderByClause("id desc");
        IncomeExample.Criteria criteria = incomeExample.createCriteria();
        criteria.andInvestIdEqualTo(investId);
        if (type != null) {
            criteria.andTypeEqualTo(IncomeEnum.RELEASE.getCode());
        }
        return incomeMapper.selectByExample(incomeExample);
    }

    @Override
    public Double reward(int type) {
        return incomeMapper.sumReward(type);
    }

    @Override
    public List<Income> selectAllOrByPrimaryKeyOrStatusOrUserId(Integer id, Integer type, Integer userId) {
        IncomeExample incomeExample = new IncomeExample();
        IncomeExample.Criteria criteria = incomeExample.createCriteria();
        if (id != null) {
            criteria.andIdEqualTo(id);
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        return incomeMapper.selectByExample(incomeExample);
    }

    @Override
    public List<RewardBonus> selectFinancialReward(String key) {
        return incomeMapper.selectFinancialReward(key);
    }

    @Override
    public List<RewardBonus> selectPromotionReward(String key) {
        return incomeMapper.selectPromotionReward(key);
    }

    @Override
    public List<EmptyRecord> selectEmptyRecord(String key, Integer type, Integer count, Date start, Date end) {
        return incomeMapper.selectEmptyRecord(key, type, count, start, end);
    }
}
