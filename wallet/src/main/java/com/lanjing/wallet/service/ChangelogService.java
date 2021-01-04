package com.lanjing.wallet.service;


import com.lanjing.wallet.model.Changelog;

import java.util.List;

public interface ChangelogService {
    int deleteByPrimaryKey(Integer fid);

    int insert(Changelog record);

    int insertSelective(Changelog record);

    Changelog selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Changelog record);

    int updateByPrimaryKey(Changelog record);

    List<Changelog> selectBy(Changelog record, Integer page, Integer size);


    List<Changelog> selectByUser(String userkey,Integer page, Integer size);

    double userTitanKT1(String userkey);

}