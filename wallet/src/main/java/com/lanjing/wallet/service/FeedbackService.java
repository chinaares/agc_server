package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Feedback;

import java.util.List;

public interface FeedbackService {

    int deleteByPrimaryKey(Integer fid);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKeyWithBLOBs(Feedback record);

    int updateByPrimaryKey(Feedback record);

    List<Feedback> selectByUserKey(String userkey,Integer page ,Integer size);

}
