package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Information;
import com.lanjing.wallet.model.po.AdminReward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);

    List<Information> selectBy(@Param("record") Information record, Integer begin, Integer end);

    List<Information> selectByEdition(Integer edition);

    List<Information> findInformationByPage(int page, int pagesize, String title, int type);

    int findInformationByPageCount(String title, int type);

    int deleteByIds(String[] ids);

    List<AdminReward> getRewardList(@Param("phone") String phone);

    List<Information> selectByTypeOrTitle(@Param("id") Integer id,@Param("type") Integer type,@Param("title") String title);

    List<Information> findByTypeAndEdition(@Param("type")Integer type,@Param("edition")Integer edition);
}