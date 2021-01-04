package com.lanjing.otc.service.impl;

import com.lanjing.otc.dao.PayWayMapper;
import com.lanjing.otc.model.PayWay;
import com.lanjing.otc.model.PayWayExample;
import com.lanjing.otc.service.PayWayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 15:10
 */
@Service
public class PayWayServiceImpl implements PayWayService {

    @Resource
    private PayWayMapper payWayMapper;

    @Override
    public long countByExample(PayWayExample example) {
        return payWayMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(PayWayExample example) {
        return payWayMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return payWayMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PayWay record) {
        return payWayMapper.insert(record);
    }

    @Override
    public int insertSelective(PayWay record) {
        return payWayMapper.insertSelective(record);
    }

    @Override
    public List<PayWay> selectByExample(PayWayExample example) {
        return payWayMapper.selectByExample(example);
    }

    @Override
    public PayWay selectByPrimaryKey(Integer id) {
        return payWayMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(PayWay record, PayWayExample example) {
        return payWayMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(PayWay record, PayWayExample example) {
        return payWayMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(PayWay record) {
        return payWayMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PayWay record) {
        return payWayMapper.updateByPrimaryKey(record);
    }

    @Override
    public int countByUserId(Integer userId) {
        return payWayMapper.countByUserid(userId);
    }
}


