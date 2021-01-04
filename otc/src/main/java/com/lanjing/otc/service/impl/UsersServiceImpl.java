package com.lanjing.otc.service.impl;

import com.lanjing.otc.dao.UsersMapper;
import com.lanjing.otc.model.Users;
import com.lanjing.otc.model.UsersExample;
import com.lanjing.otc.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 11:46
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public long countByExample(UsersExample example) {
        return usersMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UsersExample example) {
        return usersMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return usersMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public List<Users> selectByExample(UsersExample example) {
        return usersMapper.selectByExample(example);
    }

    @Override
    public Users selectByPrimaryKey(Integer fid) {
        return usersMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByExampleSelective(Users record, UsersExample example) {
        return usersMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Users record, UsersExample example) {
        return usersMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }

}



