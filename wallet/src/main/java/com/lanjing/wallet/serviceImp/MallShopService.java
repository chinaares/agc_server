package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.model.MallShopExample;
import java.util.List;
import com.lanjing.wallet.model.MallShop;
    /**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-09-04 16:46
 * @version version 1.0.0
 */
public interface MallShopService{


    long countByExample(MallShopExample example);

    int deleteByExample(MallShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallShop record);

    int insertSelective(MallShop record);

    List<MallShop> selectByExample(MallShopExample example);

    MallShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(MallShop record,MallShopExample example);

    int updateByExample(MallShop record,MallShopExample example);

    int updateByPrimaryKeySelective(MallShop record);

    int updateByPrimaryKey(MallShop record);

}
