package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.AirdropRewardMapper;
import com.lanjing.wallet.dao.IncomeMapper;
import com.lanjing.wallet.dao.UsersMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.enums.AirdropRewardEnum;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.AirdropRewardService;
import com.lanjing.wallet.util.IncomeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("AirdropRewardService")
public class AirdropRewardServiceImpl implements AirdropRewardService {

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    WelletsMapper welletsMapper;

    @Autowired
    IncomeMapper incomeMapper;

    @Autowired
    AirdropRewardMapper airdropRewardMapper;


    @Override
    public List<AirdropReward> selectAllOrByPrimaryKeyOrStatusOrUserIdOrType(Integer id, Integer status, Integer userId, Integer type) {
        AirdropRewardExample airdropRewardExample = new AirdropRewardExample();
        AirdropRewardExample.Criteria rewardExampleCriteria = airdropRewardExample.createCriteria();
        if (id != null) {
            rewardExampleCriteria.andIdEqualTo(id);
        }
        if (status != null) {
            rewardExampleCriteria.andStatusEqualTo(status);
        }
        if (userId != null) {
            rewardExampleCriteria.andUserIdEqualTo(userId);
        }
        if (type != null) {
            rewardExampleCriteria.andTypeEqualTo(type);
        }
        return airdropRewardMapper.selectByExample(airdropRewardExample);
    }

    @Override
    @Transactional
    public void rewardRelease(double todayRelease, AirdropReward airdropReward, boolean status) {
        Users users = usersMapper.selectByPrimaryKey(airdropReward.getUserId());
        Wellets wellets = welletsMapper.selectByUserandcoin(users.getKeyes(), ConfigConst.EXC_COIN);

        Date date = new Date();
        Integer rewardId = airdropReward.getId();

        Double lockNum = Double.valueOf(wellets.getLocknum().toString()) - todayRelease;
        Double releaseNum = Double.valueOf(wellets.getReleasenum().toString()) + todayRelease;

        //修改钱包
        int result = welletsMapper.updateRewardRelease(lockNum, releaseNum, ConfigConst.EXC_COIN, users.getKeyes());

        if (result < 1) {
            throw new RuntimeException("修改钱包失败,奖励记录id:" + rewardId);
        }

        //修改奖励记录数据和状态
        AirdropReward updateAirdropReward = new AirdropReward();
        updateAirdropReward.setId(rewardId);
        updateAirdropReward.setTotal(airdropReward.getTotal() + todayRelease);
        if (status) {
            updateAirdropReward.setStatus(AirdropRewardEnum.COMPLETE.getVal());
        }
        result = airdropRewardMapper.updateByPrimaryKeySelective(updateAirdropReward);
        if (result < 1) {
            throw new RuntimeException("修改奖励记录失败,奖励记录id:" + rewardId);
        }

        //添加释放记录
        Income income = new Income();
        income.setInvestId(rewardId);
        income.setUserId(users.getFid());
        income.setAmount(todayRelease);
        income.setType(IncomeUtil.getType(airdropReward.getType()));
        income.setRemark(IncomeUtil.getMsg(airdropReward.getType()));
        income.setCreateTime(date);
        result = incomeMapper.insertSelective(income);

        if (result < 1) {
            throw new RuntimeException("添加释放记录出错,奖励记录id:" + rewardId);
        }
    }

    @Override
    public Double sumTotalRewards(String key) {
        return airdropRewardMapper.sumTotalRewards(key);
    }
}
