package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.ReleaseRecordService;
import com.lanjing.wallet.dao.ReleaseRecordMapper;
import com.lanjing.wallet.model.ReleaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ReleaseRecordService")
public class ReleaseRecordServiceImpl implements ReleaseRecordService {

    @Autowired
    private ReleaseRecordMapper releaseRecordMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return releaseRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ReleaseRecord record) {
        return releaseRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(ReleaseRecord record) {
        return releaseRecordMapper.insertSelective(record);
    }

    @Override
    public ReleaseRecord selectByPrimaryKey(Long id) {
        return releaseRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ReleaseRecord record) {
        return updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ReleaseRecord record) {
        return updateByPrimaryKey(record);
    }
}
