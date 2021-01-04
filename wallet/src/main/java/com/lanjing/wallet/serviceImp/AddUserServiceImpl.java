package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.AddUserService;
import com.lanjing.wallet.dao.AddUserMapper;
import com.lanjing.wallet.model.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AddUserService")
public class AddUserServiceImpl implements AddUserService {

    @Autowired
    private AddUserMapper addUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return addUserMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(AddUser record) {
        return addUserMapper.insert(record);
    }

    @Override
    public int insertSelective(AddUser record) {
        return addUserMapper.insertSelective(record);
    }

    @Override
    public AddUser selectByPrimaryKey(Integer fid) {
        return addUserMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByPrimaryKeySelective(AddUser record) {
        return addUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AddUser record) {
        return addUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByUserKey(String userkey) {
        return addUserMapper.updateByUserKey(userkey);
    }
}
