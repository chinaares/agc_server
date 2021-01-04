package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.RegistersService;
import com.lanjing.wallet.dao.RegistersMapper;
import com.lanjing.wallet.model.Registers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RegistersService")
public class RegistersServiceImpl implements RegistersService {

    @Autowired
    private RegistersMapper registersMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    public int insert(Registers record) {
        return 0;
    }

    @Override
    public int insertSelective(Registers record) {
        return 0;
    }

    @Override
    public Registers selectByPrimaryKey(Integer fid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Registers record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Registers record) {
        return 0;
    }

    @Override
    public Registers selectByTime(String time) {
        return registersMapper.selectByTime(time);
    }
}
