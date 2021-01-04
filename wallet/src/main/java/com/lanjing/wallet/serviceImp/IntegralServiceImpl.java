package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.IntegralListMapper;
import com.lanjing.wallet.dao.IntegralMapper;
import com.lanjing.wallet.dao.WellethistoryStateMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.IntegralService;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2019-08-19 10:20
 */
@Slf4j
@Service("IntegralService")
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IntegralMapper integralMapper;

    @Resource
    private IntegralListMapper integralListMapper;

    @Resource
    private WelletsMapper welletsMapper;

    @Resource
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Override
    public long countByExample(IntegralExample example) {
        return integralMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(IntegralExample example) {
        return integralMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return integralMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Integral record) {
        return integralMapper.insert(record);
    }

    @Override
    public int insertSelective(Integral record) {
        return integralMapper.insertSelective(record);
    }

    @Override
    public List<Integral> selectByExample(IntegralExample example) {
        return integralMapper.selectByExample(example);
    }

    @Override
    public Integral selectByPrimaryKey(Integer id) {
        return integralMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Integral record, IntegralExample example) {
        return integralMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Integral record, IntegralExample example) {
        return integralMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Integral record) {
        return integralMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Integral record) {
        return integralMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Integral> findAllByCode(Long code) {
        return integralMapper.findAllByCode(code);
    }

    @Override
    public int updateStatusById(Integer status, Integer id) {
        return integralMapper.updateStatusById(status, id);
    }

    @Override
    public Integral findByCode(Long code) {
        return integralMapper.findByCode(code);
    }

    @Override
    @Transactional
    public int earnPoints(Integral integral, Integer userId) {
        Date date = new Date();
        Integer amount = integral.getAmount();
        IntegralList integralList = new IntegralList();
        integralList.setId(integral.getId());
        integralList.setUserId(userId);
        integralList.setAmount(BigDecimal.valueOf(amount));
        integralList.setRemark(integral.getName());
        integralList.setCreateTime(date);

        int result = integralListMapper.insertSelective(integralList);

        if (result < 1) {
           // log.error("扫码获取积分添加记录失败,userId:{}", userId);
            throw new RuntimeException("Please try again later");
        }

        //修改用户钱包
        Wellets wallet = welletsMapper.selectByUserandcoin(userId.toString(), ConfigConst.INTEGRAL_ID);

        if (wallet == null) {
            throw new RuntimeException("Wallet is abnormal");
        }

        result = welletsMapper.updateCoinNumByVersion(amount.doubleValue(), ConfigConst.INTEGRAL_ID, userId.toString(), wallet.getVersion());

        if (result < 1) {
            //log.error("扫码获取积分修改钱包失败,userId:{}", userId);
            throw new RuntimeException("Modify wallet failed");
        }

        //添加钱包日志
        WellethistoryState state = new WellethistoryState();
        state.setWelletid(wallet.getFid());
        state.setUserid(userId);
        state.setChangenum(BigDecimal.valueOf(amount));
        state.setType(11);
        state.setCointype(4);
        state.setRemark("扫码获取积分");
        state.setCreatetime(date);
        state.setUpdatetime(date);
        state.setKeyes(userId.toString());
        state.setState(1);

        result = wellethistoryStateMapper.insertSelective(state);
        if (result < 1) {
           // log.error("扫码获取积分添加日志失败,userId:{}", userId);
        }

        return 1;
    }
}


