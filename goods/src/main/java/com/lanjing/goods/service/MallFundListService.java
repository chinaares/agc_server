package com.lanjing.goods.service;

import com.lanjing.goods.model.MallFundList;
import com.lanjing.goods.model.MallFundListExample;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
public interface MallFundListService {


    long countByExample(MallFundListExample example);

    int deleteByExample(MallFundListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallFundList record);

    int insertSelective(MallFundList record);

    List<MallFundList> selectByExample(MallFundListExample example);

    MallFundList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(MallFundList record, MallFundListExample example);

    int updateByExample(MallFundList record, MallFundListExample example);

    int updateByPrimaryKeySelective(MallFundList record);

    int updateByPrimaryKey(MallFundList record);

}

