package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.WithdrawLogMapper;
import com.lanjing.wallet.model.WithdrawLog;
import com.lanjing.wallet.model.WithdrawLogExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.lanjing.wallet.service.WithdrawLogService;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-24 17:14
 */
@Service
public class WithdrawLogServiceImpl implements WithdrawLogService {

    @Resource
    private WithdrawLogMapper withdrawLogMapper;

    @Override
    public long countByExample(WithdrawLogExample example) {
        return withdrawLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(WithdrawLogExample example) {
        return withdrawLogMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return withdrawLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WithdrawLog record) {
        return withdrawLogMapper.insert(record);
    }

    @Override
    public int insertSelective(WithdrawLog record) {
        return withdrawLogMapper.insertSelective(record);
    }

    @Override
    public List<WithdrawLog> selectByExample(WithdrawLogExample example) {
        return withdrawLogMapper.selectByExample(example);
    }

    @Override
    public WithdrawLog selectByPrimaryKey(Integer id) {
        return withdrawLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(WithdrawLog record, WithdrawLogExample example) {
        return withdrawLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(WithdrawLog record, WithdrawLogExample example) {
        return withdrawLogMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(WithdrawLog record) {
        return withdrawLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WithdrawLog record) {
        return withdrawLogMapper.updateByPrimaryKey(record);
    }

}


