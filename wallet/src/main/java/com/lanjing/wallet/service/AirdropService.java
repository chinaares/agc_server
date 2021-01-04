package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Airdrop;
import com.lanjing.wallet.model.AirdropExample;
import com.lanjing.wallet.model.Users;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 11:47
 */
public interface AirdropService {


    long countByExample(AirdropExample example);

    int deleteByExample(AirdropExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Airdrop record);

    int insertSelective(Airdrop record);

    List<Airdrop> selectByExample(AirdropExample example);

    Airdrop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Airdrop record, AirdropExample example);

    int updateByExample(Airdrop record, AirdropExample example);

    int updateByPrimaryKeySelective(Airdrop record);

    int updateByPrimaryKey(Airdrop record);

    List<Airdrop> findByAll(Airdrop airdrop);

    /**
     * 参加理财
     *
     * @param user
     * @param id
     * @param principal
     * @return
     */
    Object invest(Users user, Integer id, Double principal);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delByPrimaryKeys(String ids);

    List<Airdrop> findByNameLikeAndEnable(String name, Integer enable);

    int operateAirdrop(Integer id, Integer enable);

    List<Airdrop> findAll();
}




