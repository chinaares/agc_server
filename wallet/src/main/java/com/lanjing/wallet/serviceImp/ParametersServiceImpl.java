package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.service.ParametersService;
import com.lanjing.wallet.util.RedisDao;
import com.lanjing.wallet.dao.ParametersMapper;
import com.lanjing.wallet.model.Parameters;
import com.lanjing.wallet.model.ParametersWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ParametersService")
public class ParametersServiceImpl implements ParametersService {

    @Autowired
    private ParametersMapper parametersMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return parametersMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(ParametersWithBLOBs record) {
        return 0;
    }

    @Override
    public int insertSelective(ParametersWithBLOBs record) {
        return parametersMapper.insertSelective(record);
    }

    @Override
    public ParametersWithBLOBs selectByPrimaryKey(Integer fid) {
        return parametersMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByPrimaryKeySelective(ParametersWithBLOBs record) {
        return parametersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ParametersWithBLOBs record) {
        return 0;
    }

    @Override
    public ParametersWithBLOBs selectByKey(String keyname) {
        String json = redisDao.getValue(keyname);
        if(json != null && !"".equals(json)){
            return JSONObject.parseObject(json,ParametersWithBLOBs.class);
        }
        Parameters parameters = parametersMapper.selectByKey(keyname);
        redisDao.setKey(keyname, JSON.toJSONString(parameters));
        return parametersMapper.selectByKey(keyname);
    }

    @Override
    public ParametersWithBLOBs findByKey(String key) {
        return parametersMapper.selectByKey(key);
    }

    @Override
    public int updateByPrimaryKey(Parameters record) {
        return 0;
    }

    @Override
    public List<Parameters> selectByType(Integer type) {
        return parametersMapper.selectByType(type);
    }

    @Override
    public int updateByKeyName(String bannerKey, String filePath) {
        return parametersMapper.updateByKeyName(bannerKey,filePath);
    }
}
