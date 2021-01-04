package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.AvailMapper;
import com.lanjing.wallet.model.Avail;
import com.lanjing.wallet.model.AvailExample;
import com.lanjing.wallet.service.AvailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 19:04
 */
@Service("AvailService")
public class AvailServiceImpl implements AvailService {

    @Resource
    private AvailMapper availMapper;

    @Override
    public long countByExample(AvailExample example) {
        return availMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AvailExample example) {
        return availMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return availMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Avail record) {
        return availMapper.insert(record);
    }

    @Override
    public int insertSelective(Avail record) {
        return availMapper.insertSelective(record);
    }

    @Override
    public List<Avail> selectByExample(AvailExample example) {
        return availMapper.selectByExample(example);
    }

    @Override
    public Avail selectByPrimaryKey(Integer id) {
        return availMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Avail record, AvailExample example) {
        return availMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Avail record, AvailExample example) {
        return availMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Avail record) {
        return availMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Avail record) {
        return availMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Avail> findByAIdAndUserIdAndType(Integer id, Integer userId, Integer type) {
        return availMapper.findByAIdAndUserIdAndType(id, userId, type);
    }
}


