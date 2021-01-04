package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.IntegralListMapper;
import com.lanjing.wallet.model.IntegralList;
import com.lanjing.wallet.model.IntegralListExample;
import com.lanjing.wallet.service.IntegralListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-19 11:40
 * @version version 1.0.0
 */
@Service("IntegralListService")
public class IntegralListServiceImpl implements IntegralListService{

    @Resource
    private IntegralListMapper integralListMapper;

    @Override
    public long countByExample(IntegralListExample example) {
        return integralListMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(IntegralListExample example) {
        return integralListMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return integralListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(IntegralList record) {
        return integralListMapper.insert(record);
    }

    @Override
    public int insertSelective(IntegralList record) {
        return integralListMapper.insertSelective(record);
    }

    @Override
    public List<IntegralList> selectByExample(IntegralListExample example) {
        return integralListMapper.selectByExample(example);
    }

    @Override
    public IntegralList selectByPrimaryKey(Integer id) {
        return integralListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(IntegralList record,IntegralListExample example) {
        return integralListMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(IntegralList record,IntegralListExample example) {
        return integralListMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(IntegralList record) {
        return integralListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(IntegralList record) {
        return integralListMapper.updateByPrimaryKey(record);
    }

    @Override
    public long countByUserIdAndIId(Integer id, Integer userId) {
        IntegralListExample integralListExample=new IntegralListExample();
        IntegralListExample.Criteria criteria = integralListExample.createCriteria();
        criteria.andIIdEqualTo(id);
        criteria.andUserIdEqualTo(userId);
        return integralListMapper.countByExample(integralListExample);
    }
}
