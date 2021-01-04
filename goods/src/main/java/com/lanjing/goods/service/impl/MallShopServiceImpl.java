package com.lanjing.goods.service.impl;

import com.jester.util.utils.UUIDUtil;
import com.lanjing.goods.config.ConfigConst;
import com.lanjing.goods.dao.*;
import com.lanjing.goods.model.*;
import com.lanjing.goods.model.po.MallShops;
import com.lanjing.goods.model.po.Merchant;
import com.lanjing.goods.model.po.Shop;
import com.lanjing.goods.service.MallShopService;
import com.lanjing.goods.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
@Service
public class MallShopServiceImpl implements MallShopService {

    @Autowired
    private MallShopMapper mallShopMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private MallWalletMapper mallWalletMapper;

    @Resource
    private GoodsMapper goodsMapper;

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
    public int updateByExampleSelective(MallShop record, MallShopExample example) {
        return mallShopMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(MallShop record, MallShopExample example) {
        return mallShopMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(MallShop record) {
        return mallShopMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MallShop record) {
        return mallShopMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public int addMallShop(MallShop mallShop) {
        //创建admin用户，初始化权限
        String phone = mallShop.getContactNumber();

        Date date = new Date();
        long code = UUIDUtil.nextId();
        mallShop.setCreateTime(date);
        mallShop.setCode(code);

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        int password = threadLocalRandom.nextInt(100000, 999999);

        Admin admin = new Admin();
        admin.setAname(phone);
        admin.setAnick(phone);
        admin.setCode(code);
        admin.setArole(0);
        admin.setLoginpassword(String.valueOf(password));
        admin.setPurview(ConfigConst.INITIALIZATION_AUTHORITY);
        admin.setCreatetime(date);

        int result = adminMapper.insertSelective(admin);

        if (result < 1) {
            throw new RuntimeException("创建出错");
        }

        //ETH钱包地址
        String ethAddress = EthUtilHuang.createEthAddress(MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + code))));
        //创建店铺钱包
        List<Coins> coins = coinsMapper.findAllByFid(null);
        for (Coins coin : coins) {
            MallWallet wallet = new MallWallet();
            wallet.setAddress(ethAddress);
            wallet.setCode(code);
            wallet.setCoinId(coin.getFid());
            wallet.setCoinType(coin.getCoinshort());
            result = mallWalletMapper.insertSelective(wallet);

            if (result < 1) {
                throw new RuntimeException("初始化钱包失败");
            }
        }

        //创建店铺信息
        result = mallShopMapper.insertSelective(mallShop);
        if (result < 1) {
            throw new RuntimeException("创建店铺信息失败");
        }

        SMSUtil.createStore(phone, password);

        return result;
    }

    @Override
    public List<Merchant> getMerchant(String key) {
        List<Merchant> merchantList = mallShopMapper.getMerchant(key);
        for (Merchant merchant : merchantList) {
            List<Goods> goodsList = goodsMapper.selectByShopCode(merchant.getCode());
            merchant.setGoodsList(goodsList);
        }
        return merchantList;
    }

    @Override
    public MallShop findByCode(Long code) {
        return mallShopMapper.findByCode(code);
    }

    @Override
    @Transactional
    public int operateMerchant(Admin updateAdmin, MallShop updateMallShop) {
        int result = adminMapper.updateByPrimaryKeySelective(updateAdmin);
        if (result < 0) {
            throw new RuntimeException("处理账户信息失败");
        }
        result = mallShopMapper.updateByPrimaryKeySelective(updateMallShop);
        if (result < 0) {
            throw new RuntimeException("处理店铺信息失败");
        }
        return result;
    }

    @Override
    public MallShops findByCodes(Long code) {
        return mallShopMapper.findByCodes(code);
    }

    @Override
    public List<Shop> getShop(String key) {
        return mallShopMapper.getShop(key);
    }

    @Override
    public List<Shop> findBySort() {
        return mallShopMapper.findBySort();
    }

    @Override
    public Shop selectByCode(Long code) {
        return mallShopMapper.selectByCode(code);
    }

    @Override
    public MallShop findByPhone(String phone) {
        return mallShopMapper.findByPhone(phone);
    }
}



