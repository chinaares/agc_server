package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.ChangelogService;
import com.lanjing.wallet.dao.ChangelogMapper;
import com.lanjing.wallet.model.Changelog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChangelogService")
public class ChangelogServiceImpl implements ChangelogService {

    @Autowired
    private ChangelogMapper changelogMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return changelogMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Changelog record) {
        return changelogMapper.insert(record);
    }

    @Override
    public int insertSelective(Changelog record) {
        return changelogMapper.insertSelective(record);
    }

    @Override
    public Changelog selectByPrimaryKey(Integer fid) {
        return changelogMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByPrimaryKeySelective(Changelog record) {
        return changelogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Changelog record) {
        return changelogMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Changelog> selectBy(Changelog record, Integer page, Integer size) {
        if(page == null){
            page = 1;
        }
        if(size == null){
            size = 10;
        }
        return changelogMapper.selectBy(record,(page-1)*size,page*size);
    }

    @Override
    public List<Changelog> selectByUser(String userkey, Integer page, Integer size) {
        return changelogMapper.selectByUser(userkey,(page-1)*size,size);
    }

    @Override
    public double userTitanKT1(String userkey) {
        return changelogMapper.userTitanKT1(userkey);
    }
}
