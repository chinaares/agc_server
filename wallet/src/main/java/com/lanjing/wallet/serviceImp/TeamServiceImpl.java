package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.enums.IncomeEnum;
import com.lanjing.wallet.model.Income;
import com.lanjing.wallet.model.Team;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("TeamService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    WelletsMapper welletsMapper;

    @Autowired
    AirdropRewardMapper airdropRewardMapper;

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    IncomeMapper incomeMapper;

    @Override
    public Team selectByUserId(Integer userId) {
        return teamMapper.selectByUserId(userId);
    }

    @Override
    @Transactional
    public void calculate(double release, String userKey) {
        Users user = usersMapper.selectByUserKey(userKey);
        Integer userId = user.getFid();
        Date date = new Date();

        //查询钱包信息
        Wellets wellets = welletsMapper.selectByUserandcoin(user.getKeyes(), ConfigConst.DZCC_COIN);

        //修改 钱包锁仓数量
        int result = welletsMapper.updateCoinNumByVersion(release, ConfigConst.DZCC_COIN, user.getKeyes(), wellets.getVersion());

        if (result < 1) {
            throw new RuntimeException("团队分红修改钱包锁仓数量失败");
        }

        //添加团队分红记录
        Income income = new Income();
        income.setUserId(userId);
        income.setAmount(release);
        income.setType(IncomeEnum.TEAM.getCode());
        income.setRemark(IncomeEnum.TEAM.getMsg());
        income.setCreateTime(date);
        result = incomeMapper.insert(income);

        if (result < 1) {
            throw new RuntimeException("添加团队分红记录失败");
        }

    }
}
