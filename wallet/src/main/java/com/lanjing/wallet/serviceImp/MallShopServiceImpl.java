package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.MallShopMapper;
import com.lanjing.wallet.model.MallShop;
import com.lanjing.wallet.model.MallShopExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-09-04 16:46
 * @version version 1.0.0
 */
@Service("MallShopService")
public class MallShopServiceImpl implements MallShopService{

    @Resource
    private MallShopMapper mallShopMapper;

    @Override
    public long countByExample(MallShopExample example) {
        return mallShopMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(MallShopExample example) {
        return mallShopMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mallShopMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MallShop record) {
        return mallShopMapper.insert(record);
    }

    @Override
    public int insertSelective(MallShop record) {
        return mallShopMapper.insertSelective(record);
    }

    @Override
    public List<MallShop> selectByExample(MallShopExample example) {
        return mallShopMapper.selectByExample(example);
    }

    @Override
    public MallShop selectByPrimaryKey(Integer id) {
        return mallShopMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(MallShop record,MallShopExample example) {
        return mallShopMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(MallShop record,MallShopExample example) {
        return mallShopMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(MallShop record) {
        return mallShopMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MallShop record) {
        return mallShopMapper.updateByPrimaryKey(record);
    }

}
