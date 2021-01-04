package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Information;
import com.lanjing.wallet.model.po.AdminReward;

import java.util.List;

public interface InformationService {

    int deleteByPrimaryKey(Integer fid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);

    List<Information> selectBy(Information record, Integer page, Integer size);

    List<Information> selectByEdition(Integer edition);

    List<AdminReward> getRewardList(String phone);

    List<Information> selectByTypeOrTitle(Integer id, Integer type, String title);

    List<Information> findByTypeAndEdition(Integer type, Integer edition);
}
