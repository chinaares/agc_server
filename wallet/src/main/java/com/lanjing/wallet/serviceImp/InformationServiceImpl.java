package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.model.po.AdminReward;
import com.lanjing.wallet.service.InformationService;
import com.lanjing.wallet.dao.InformationMapper;
import com.lanjing.wallet.model.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("InformationService")
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return informationMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Information record) {
        return informationMapper.insert(record);
    }

    @Override
    public int insertSelective(Information record) {
        return informationMapper.insertSelective(record);
    }

    @Override
    public Information selectByPrimaryKey(Integer fid) {
        return informationMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByPrimaryKeySelective(Information record) {
        return informationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Information record) {
        return informationMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Information record) {
        return informationMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Information> selectBy(Information record, Integer page, Integer size) {
        if(page == null){
            page = 1;
        }
        if(size == null){
            size = 10;
        }
        return informationMapper.selectBy(record,(page-1)*size,page*size);
    }

    @Override
    public List<Information> selectByEdition(Integer edition) {
        return informationMapper.selectByEdition(edition);
    }

    @Override
    public List<AdminReward> getRewardList(String phone) {
        return informationMapper.getRewardList(phone);
    }

    @Override
    public List<Information> selectByTypeOrTitle(Integer id,Integer type, String title) {
        return informationMapper.selectByTypeOrTitle(id,type, title);
    }

    @Override
    public List<Information> findByTypeAndEdition(Integer type, Integer edition) {
        return informationMapper.findByTypeAndEdition(type, edition);
    }
}
