package com.lanjing.goods.service;

import java.util.List;
import com.lanjing.goods.model.CoinsExample;
import com.lanjing.goods.model.Coins;
    /**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 * @version version 1.0.0
 */
public interface CoinsService{


    long countByExample(CoinsExample example);

    int deleteByExample(CoinsExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Coins record);

    int insertSelective(Coins record);

    List<Coins> selectByExample(CoinsExample example);

    Coins selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(Coins record,CoinsExample example);

    int updateByExample(Coins record,CoinsExample example);

    int updateByPrimaryKeySelective(Coins record);

    int updateByPrimaryKey(Coins record);

}
