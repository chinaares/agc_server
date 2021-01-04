package com.lanjing.wallet.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lanjing.wallet.dao.SystemConfigMapper;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.model.SystemConfig;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@CrossOrigin(value = "*")
public class SystemConfigController {
	@Resource
	private SystemConfigMapper systemConfigMapper;

	@RequestMapping("/sys/getSystemConfig")
	public String getSystemConfig(@RequestBody Map<String, String> param) {
		try {
			List<SystemConfig> configList = systemConfigMapper.findAll();
			return JSONObject.toJSONString(new ResultDTO(200, "success", configList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(new ResultDTO(201, "error"));
	}

	@RequestMapping("/sys/updateSystemConfig")
	public String updateSystemConfig(@RequestBody Map<String, String> param) {
		try {
			int configId = Integer.parseInt(param.get("configId"));
			String value = param.get("value");
			int num = systemConfigMapper.updateValueById(configId, value);
			if (num > 0) {
				return JSONObject.toJSONString(new ResultDTO(200, "success"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(new ResultDTO(201, "error"));
	}
}
