package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.FeedbackMapper;
import com.lanjing.wallet.model.Feedback;
import com.lanjing.wallet.model.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*")
public class AdminFeedBackController {

	@Autowired
	private FeedbackMapper feedbackMapper;

	// 查询所有的问题，按时间降序分页返回
	@RequestMapping("/admin/findFeedBackByPage")
	public String findFeedBackByPage(@RequestBody Map<String, String> param) {
		try {
			int page = Integer.parseInt(param.get("page"));
			int pagesize = Integer.parseInt(param.get("pagesize"));
			String username = param.get("username");
			page = page <= 0 ? 0 : (page - 1) * pagesize;
			List<Feedback> feedbacks = feedbackMapper.findFeedbackByPage(username, page, pagesize);
			int count = feedbackMapper.findFeedbackByPageCount(username);
			Map<String, Object> map = new HashMap<String, Object>();
			int tatalpage = count%pagesize > 0 ? count/pagesize + 1 : count/pagesize;
			map.put("feedbacks", feedbacks);
			map.put("count", tatalpage);
			return JSONObject.toJSONString(new ResultDTO(200, "success", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(new ResultDTO(201, "error"));

	}

	// 回复问题
	@RequestMapping("/admin/replyBackByPage")
	public String replyBackByPage(@RequestBody Map<String, String> param) {
		try {
			int feedbackId = Integer.parseInt(param.get("feedbackId"));
			String reply = param.get("reply");
			int num = feedbackMapper.updateReplyById(reply, feedbackId);
			Feedback feedback = feedbackMapper.selectByPrimaryKey(feedbackId);
			feedback.setState(1);
			feedbackMapper.updateByPrimaryKeySelective(feedback);
			if (num > 0) {
				return JSONObject.toJSONString(new ResultDTO(200, "success",
						feedbackMapper.selectByPrimaryKey(feedbackId)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(new ResultDTO(201, "error"));

	}
}
