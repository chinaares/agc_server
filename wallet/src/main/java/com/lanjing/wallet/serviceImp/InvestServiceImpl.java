package com.lanjing.wallet.serviceImp;

import com.jester.util.utils.Result;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.AirdropRewardMapper;
import com.lanjing.wallet.dao.InvestMapper;
import com.lanjing.wallet.dao.UsersMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.enums.InvestEnum;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.model.po.IncomeInterest;
import com.lanjing.wallet.model.po.InvestCheck;
import com.lanjing.wallet.model.po.InvestReward;
import com.lanjing.wallet.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("InvestService")
public class InvestServiceImpl implements InvestService {
    @Autowired
    InvestMapper investMapper;
    @Autowired
    AirdropRewardMapper airdropRewardMapper;
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    WelletsMapper welletsMapper;

    @Override
    public List<Invest> selectAllOrByPrimaryKeyOrStatusOrUserId(Integer id, Integer status, Integer userId) {
        InvestExample investExample = new InvestExample();
        InvestExample.Criteria criteria = investExample.createCriteria();
        if (id != null) {
            criteria.andIdEqualTo(id);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        return investMapper.selectByExample(investExample);
    }

    @Override
    public List<Invest> selectAllOrByPrimaryKeyOrStatusesOrUserIds(Integer id, List<Integer> status, List<Integer> userId) {
        InvestExample investExample = new InvestExample();
        InvestExample.Criteria criteria = investExample.createCriteria();
        if (id != null) {
            criteria.andIdEqualTo(id);
        }
        if (status != null) {
            criteria.andStatusIn(status);
        }
        if (userId != null) {
            criteria.andUserIdIn(userId);
        }
        return investMapper.selectByExample(investExample);
    }

    @Override
    public List<InvestReward> selectByUserId(Integer userId) {
        return investMapper.selectByUserId(userId);
    }

    @Override
    public List<IncomeInterest> selectIncomeInterest(){
        return investMapper.selectIncomeInterest();
    }

    @Override
    public List<InvestCheck> getInvestCheck(String phone, Integer status) {
        return investMapper.getInvestCheck(phone,status);
    }

    @Override
    public List<InvestCheck> getInvest(String phone) {
        return investMapper.getInvest(phone);
    }

    @Override
    @Transactional
    public Object investCheckReview(Integer id, Boolean status) {
        InvestExample investExample=new InvestExample();
        InvestExample.Criteria criteria = investExample.createCriteria();
        criteria.andIdEqualTo(id);

        Invest updateInvest=new Invest();
        updateInvest.setId(id);
        if (status){
            updateInvest.setStatus(InvestEnum.PASS.getVal());
        }else{
            updateInvest.setStatus(InvestEnum.REFUSE.getVal());
        }

        int result = investMapper.updateByExampleSelective(updateInvest, investExample);

        if (result< 1) {
            throw new RuntimeException("审核失败");
        }


        if(status){
            Invest invest = investMapper.selectByPrimaryKey(id);
            Users user = usersMapper.selectByPrimaryKey(invest.getUserId());

            //判断是否是首次理财
            AirdropRewardExample airdropRewardExample=new AirdropRewardExample();
            AirdropRewardExample.Criteria rewardExampleCriteria = airdropRewardExample.createCriteria();
            rewardExampleCriteria.andCodeEqualTo(invest.getCode());
            rewardExampleCriteria.andUserIdEqualTo(user.getFid());
            rewardExampleCriteria.andAirdropIdEqualTo(invest.getAirdropId());

            List<AirdropReward> airdropRewards = airdropRewardMapper.selectByExample(airdropRewardExample);
            if (airdropRewards.size()>0) {
                //取消首次理财EXC奖励
                AirdropReward airdropReward = airdropRewards.get(0);
                if (airdropReward.getStatus()== InvestEnum.INPUT.getVal()) {
                    AirdropReward reward = new AirdropReward();
                    reward.setId(airdropReward.getId());
                    reward.setStatus(InvestEnum.ABORT.getVal());
                    result= airdropRewardMapper.updateByPrimaryKeySelective(reward);
                    if (result<1){
                        throw new RuntimeException("取消理财EXC奖励失败");
                    }

                    //取消待释放EXC
                    Wellets wellets = welletsMapper.selectByUserandcoin(user.getKeyes(), ConfigConst.EXC_COIN);
                    double remainder = airdropReward.getBonus() - airdropReward.getTotal();

                    //修改 releaseNum 待释放数量
                    result=welletsMapper.uptWelletRelease(Double.valueOf(wellets.getReleasenum().toString())-remainder,ConfigConst.EXC_COIN,user.getKeyes());

                    if (result<1){
                        throw new RuntimeException("取消待释放EXC失败");
                    }
                }
            }

            //FIXME 取消 推广奖励，团队奖励
            Wellets wellets = welletsMapper.selectByUserandcoin(user.getKeyes(), ConfigConst.ETH_COIN);

            result = welletsMapper.uptWelletByuserandcoin(Double.valueOf(wellets.getCoinnum().toString())+invest.getPrincipalBalance(), ConfigConst.ETH_COIN, user.getKeyes());
            if (result<1){
                throw new RuntimeException("ETH返还失败");
            }
        }

        return Result.success();
    }

    @Override
    public BigDecimal sumPrincipal() {
        return investMapper.sumPrincipal();
    }

    @Override
    public BigDecimal sumRevenue() {
        return investMapper.sumRevenue();
    }

    @Override
    public BigDecimal sumTotalRelease() {
        return investMapper.sumTotalRelease();
    }

    @Override
    public BigDecimal sumTodayPrincipal(Date today, Date tomorrow) {
        return investMapper.sumTodayPrincipal(today,tomorrow);
    }
}
