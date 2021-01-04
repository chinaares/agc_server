package com.lanjing.wallet.dao;

import java.util.List;

import com.lanjing.wallet.model.SystemConfig;

public interface SystemConfigMapper {

	public SystemConfig findById(int id);
	public List<SystemConfig> findAll();
	public  int updateValueById(int id,String value);
}
