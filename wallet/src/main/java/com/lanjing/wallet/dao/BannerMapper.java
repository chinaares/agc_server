package com.lanjing.wallet.dao;
import org.apache.ibatis.annotations.Param;

import com.lanjing.wallet.model.Banner;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerMapper extends Mapper<Banner> {
    int updateStatusById(@Param("updatedStatus")Integer updatedStatus,@Param("id")Integer id);

    List<Banner> selectAllList();

    int insertSelective2(Banner banner);
    
    int updateByPrimaryKeySelective2(Banner banner);
}