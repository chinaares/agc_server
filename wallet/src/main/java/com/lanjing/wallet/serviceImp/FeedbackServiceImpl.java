package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.FeedbackService;
import com.lanjing.wallet.dao.FeedbackMapper;
import com.lanjing.wallet.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FeedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return feedbackMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Feedback record) {
        return feedbackMapper.insert(record);
    }

    @Override
    public int insertSelective(Feedback record) {
        return feedbackMapper.insertSelective(record);
    }

    @Override
    public Feedback selectByPrimaryKey(Integer fid) {
        return feedbackMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByPrimaryKeySelective(Feedback record) {
        return feedbackMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Feedback record) {
        return feedbackMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Feedback record) {
        return feedbackMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Feedback> selectByUserKey(String userkey,Integer page ,Integer size) {
        return feedbackMapper.selectByUserKey(userkey,(page-1)*size , size);
    }

}
