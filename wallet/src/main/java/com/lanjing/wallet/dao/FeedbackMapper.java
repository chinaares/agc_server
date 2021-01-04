package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Feedback;

import java.util.List;

public interface FeedbackMapper {
	int deleteByPrimaryKey(Integer fid);

	int insert(Feedback record);

	int insertSelective(Feedback record);

	Feedback selectByPrimaryKey(Integer fid);

	int updateByPrimaryKeySelective(Feedback record);

	int updateByPrimaryKeyWithBLOBs(Feedback record);

	int updateByPrimaryKey(Feedback record);

	List<Feedback> selectAll(String likes, Integer state, Integer begin, Integer end);

	int selectAllCount(String likes, Integer state);

	List<Feedback> selectByUserKey(String userkey, Integer begin, Integer end);

	List<Feedback> findFeedbackByPage(String username, int page, int pagesize);

	int findFeedbackByPageCount(String username);

	int updateReplyById(String reply, int id);

}