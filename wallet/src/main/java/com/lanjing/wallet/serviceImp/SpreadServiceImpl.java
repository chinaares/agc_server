package com.lanjing.wallet.serviceImp;

import com.jester.util.utils.UUIDUtil;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.AirdropRewardMapper;
import com.lanjing.wallet.dao.SpreadMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.enums.RewardEnum;
import com.lanjing.wallet.model.AirdropReward;
import com.lanjing.wallet.model.Spread;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.SpreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("SpreadService")
public class SpreadServiceImpl implements SpreadService {

    @Autowired
    SpreadMapper spreadMapper;

    @Autowired
    WelletsMapper welletsMapper;

    @Autowired
    AirdropRewardMapper airdropRewardMapper;

    @Override
    public Spread selectByUserId(Integer userId) {
        return spreadMapper.selectByUserId(userId);
    }

    @Override
    @Transactional
    public void calculate(int person, double bonus, Users user) {
        Integer userId = user.getFid();
        Date date = new Date();
        Boolean flag = false;
        int result;
        long nextId;
        //获取该用户上次是否已经添加奖励
        Spread spread = this.selectByUserId(userId);

        if (spread == null) {
            nextId = UUIDUtil.nextId();
            spread=new Spread();
            spread.setCode(nextId);
            spread.setUserId(userId);
            spread.setBonus(bonus);
            spread.setPerson(person);
            spread.setCreateTime(date);
            spread.setUpdateTime(date);
            //添加Spread记录
            result = spreadMapper.insert(spread);

            if (result < 1) {
                throw new RuntimeException("添加Spread记录失败");
            }
            flag = true;
        } else {
            //存在Spread记录,获取其奖励的人数和EXC数量
            Double beforeBonus = spread.getBonus();
            Integer beforePerson = spread.getPerson();
            nextId=spread.getCode();

            if (beforeBonus < bonus && beforePerson < person) {
                spread.setPerson(person);
                spread.setBonus(bonus);
                result = spreadMapper.updateByPrimaryKey(spread);

                if (result < 1) {
                    throw new RuntimeException("添加Spread记录失败");
                }
                bonus = bonus - beforeBonus;
                flag = true;
            }
        }

        if (flag) {
            //添加EXC奖励记录
            AirdropReward airdropReward = new AirdropReward();
            airdropReward.setUserId(userId);
            airdropReward.setCode(nextId);
            airdropReward.setStatus(1);
            airdropReward.setType(RewardEnum.PROMOTION.getVal());
            airdropReward.setBonus(bonus);
            airdropReward.setDailyRelease(ConfigConst.SPREAD_RELEASE);
            airdropReward.setTotal(0d);
            airdropReward.setCreateTime(date);
            airdropReward.setUpdateTime(date);

            result = airdropRewardMapper.insert(airdropReward);

            if (result < 1) {
                throw new RuntimeException("添加EXC奖励记录失败");
            }
            //查询钱包信息
            Wellets wellets = welletsMapper.selectByUserandcoin(user.getKeyes(), ConfigConst.EXC_COIN);
            double total = Double.valueOf(wellets.getLocknum().toString()) + bonus;

            //修改 钱包锁仓数量
            result = welletsMapper.uptWelletLockNum(total, ConfigConst.EXC_COIN, user.getKeyes());

            if (result < 1) {
                throw new RuntimeException("修改钱包锁仓数量失败");
            }
        }
    }
}
