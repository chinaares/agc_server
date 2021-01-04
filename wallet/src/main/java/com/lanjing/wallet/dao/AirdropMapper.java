package com.lanjing.wallet.dao;

import com.lanjing.wallet.model.Airdrop;
import com.lanjing.wallet.model.AirdropExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-14 14:39
 */
public interface AirdropMapper {
    long countByExample(AirdropExample example);

    int deleteByExample(AirdropExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Airdrop record);

    int insertSelective(Airdrop record);

    List<Airdrop> selectByExample(AirdropExample example);

    Airdrop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Airdrop record, @Param("example") AirdropExample example);

    int updateByExample(@Param("record") Airdrop record, @Param("example") AirdropExample example);

    int updateByPrimaryKeySelective(Airdrop record);

    int updateByPrimaryKey(Airdrop record);

    List<Airdrop> findByAll(Airdrop airdrop);

    int delByPrimaryKeys(String ids);

    List<Airdrop> findByNameLikeAndEnable(@Param("likeName") String name, @Param("enable") Integer enable);

    int updateByIdAndEnable(@Param("updated") Airdrop updated, @Param("id") Integer id, @Param("enable") Integer enable);

    int operateAirdrop(@Param("id") Integer id, @Param("enable") Integer enable);

    List<Airdrop> findAll();
}