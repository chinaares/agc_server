package com.lanjing.goods.service.impl;

import com.lanjing.goods.dao.AdminMapper;
import com.lanjing.goods.model.Admin;
import com.lanjing.goods.model.AdminExample;
import com.lanjing.goods.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public long countByExample(AdminExample example) {
        return adminMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AdminExample example) {
        return adminMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return adminMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public List<Admin> selectByExample(AdminExample example) {
        return adminMapper.selectByExample(example);
    }

    @Override
    public Admin selectByPrimaryKey(Integer fid) {
        return adminMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByExampleSelective(Admin record, AdminExample example) {
        return adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Admin record, AdminExample example) {
        return adminMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public Admin findByAname(String phone) {
        return adminMapper.findByAname(phone);
    }

    @Override
    public Admin findByCode(Long code) {
        return adminMapper.findByCode(code);
    }
}


