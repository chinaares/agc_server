package com.lanjing.wallet.service;


import com.lanjing.wallet.model.WellethistoryState;

import java.util.List;

public interface WellethistoryStateService {
    int deleteByPrimaryKey(Integer fid);

    int insert(WellethistoryState record);

    int insertSelective(WellethistoryState record);

    WellethistoryState selectByPrimaryKey(Integer fid);

    WellethistoryState selectByKey(String keyes);

    List<WellethistoryState> selectByWelletId(Integer welletId,Integer type,Integer page,Integer size);

    int updateByPrimaryKeySelective(WellethistoryState record);

    int updateByPrimaryKey(WellethistoryState record);

    List<WellethistoryState> selectBycztx(Integer welletId,Integer page, Integer size);

    List<WellethistoryState> selectBytransfer(Integer welletId,Integer page, Integer size);

    int querywithdraw(Integer userid,Integer coinid);
}