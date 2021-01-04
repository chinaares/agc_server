package com.lanjing.wallet.serviceImp;

import com.jester.util.utils.Result;
import com.jester.util.utils.UUIDUtil;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.AirdropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 11:47
 */
@Service("AirdropService")
public class AirdropServiceImpl implements AirdropService {

    @Resource
    AirdropMapper airdropMapper;
    @Autowired
    WelletsMapper welletsMapper;
    @Autowired
    AirdropRewardMapper airdropRewardMapper;
    @Autowired
    AirdropParameterMapper airdropParameterMapper;
    @Autowired
    WellethistoryStateMapper wellethistoryStateMapper;
    @Autowired
    ParametersMapper parametersMapper;
    @Autowired
    RecommendMapper recommendMapper;
    @Autowired
    IncomeMapper incomeMapper;
    @Autowired
    InvestMapper investMapper;

    @Override
    public long countByExample(AirdropExample example) {
        return airdropMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AirdropExample example) {
        return airdropMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return airdropMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Airdrop record) {
        return airdropMapper.insert(record);
    }

    @Override
    public int insertSelective(Airdrop record) {
        return airdropMapper.insertSelective(record);
    }

    @Override
    public List<Airdrop> selectByExample(AirdropExample example) {
        return airdropMapper.selectByExample(example);
    }

    @Override
    public Airdrop selectByPrimaryKey(Integer id) {
        return airdropMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Airdrop record, AirdropExample example) {
        return airdropMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Airdrop record, AirdropExample example) {
        return airdropMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Airdrop record) {
        return airdropMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Airdrop record) {
        return airdropMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Airdrop> findByAll(Airdrop airdrop) {
        return airdropMapper.findByAll(airdrop);
    }


    @Override
    public int delByPrimaryKeys(String ids) {
        return airdropMapper.delByPrimaryKeys(ids);
    }

    @Override
    @Transactional
    public Object invest(Users user, Integer id, Double principal) {
        Date date = new Date();
        long code = UUIDUtil.nextId();
        int result;

        //获取理财信息
        Airdrop airdrop = airdropMapper.selectByPrimaryKey(id);
        if (airdrop == null) {
            return Result.error("理财信息不存在");
        }

        Wellets wellets = welletsMapper.selectByUserandcoin(user.getKeyes(), ConfigConst.DZCC_COIN);

        if (wellets == null) {
            return Result.error("未找到DZCC钱包");
        }

        double balance = Double.valueOf(wellets.getCoinnum().toString()) - principal;
        if (balance < 0) {
            return Result.error("余额不足");

        }

        //修改钱包余额
        result = welletsMapper.upLockFinances(principal, ConfigConst.DZCC_COIN, user.getKeyes(), wellets.getVersion());

        if (result < 1) {
            throw new RuntimeException("修改钱包余额失败");
        }

        //修改推荐关系表用户财富锁仓数
        Recommend recommend = recommendMapper.selectByUserKey1(user.getKeyes());
        Recommend updateRe = new Recommend();
        recommend.setFid(recommend.getFid());
        recommend.setUserlocknum(recommend.getUserlocknum() + principal);

        result = recommendMapper.updateByPrimaryKeySelective(updateRe);
        if (result < 1) {
            throw new RuntimeException("更新用户财富锁仓失败");
        }

        //添加理财记录
        Invest invest = new Invest();
        invest.setUserId(user.getFid());
        invest.setAirdropId(id);
        //invest.setTitle(airdrop.getTitle());
        //invest.setPeriodsNum(airdrop.getPeriodsNum());
        //invest.setCode(code);
        //invest.setStatus(InvestEnum.INPUT.getVal());
        //invest.setPrincipal(principal);
        //invest.setIncome(airdrop.getMultiple());
        //invest.setPrincipalBalance(principal);
        //invest.setIncomeBalance(0d);
        //invest.setTotal(0d);
        //invest.setFee(airdrop.getPeriodsNum());
        //invest.setDailyRelease(airdrop.getDailyRelease());
        //invest.setEndTime(DateUtil.getDate(airdrop.getPeriodsNum()));
        invest.setCreateTime(date);
        invest.setUpdateTime(date);
        result = investMapper.insertSelective(invest);
        if (result < 1) {
            throw new RuntimeException("添加理财记录失败");
        }

        //添加钱包日志
        WellethistoryState state = new WellethistoryState();
        state.setWelletid(wellets.getFid());
        state.setUserid(user.getFid());
        state.setChangenum(BigDecimal.valueOf(principal));
        state.setType(4);
        state.setCointype(1);
        state.setRemark("用户理财");
        state.setCreatetime(date);
        state.setUpdatetime(date);
        state.setKeyes(user.getKeyes());
        state.setState(1);

        result = wellethistoryStateMapper.insertSelective(state);

        if (result < 1) {
            throw new RuntimeException(String.format("添加钱包日志错误:%s", state.toString()));
        }

        return Result.success();
    }

    @Override
    public List<Airdrop> findByNameLikeAndEnable(String name, Integer enable) {
        return airdropMapper.findByNameLikeAndEnable(name, enable);
    }

    @Override
    public int operateAirdrop(Integer id, Integer enable) {
        return airdropMapper.operateAirdrop(id, enable);
    }

    @Override
    public List<Airdrop> findAll() {
        return airdropMapper.findAll();
    }
}




