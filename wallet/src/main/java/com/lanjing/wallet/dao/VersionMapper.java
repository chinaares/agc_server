package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Version;

import java.util.List;

public interface VersionMapper {
     List<Version> findAll();

     Version findByid(Integer id);
}
