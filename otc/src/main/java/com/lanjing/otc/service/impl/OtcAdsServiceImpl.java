package com.lanjing.otc.service.impl;

import com.lanjing.otc.dao.OtcAdsMapper;
import com.lanjing.otc.dao.UsersMapper;
import com.lanjing.otc.dao.WalletsMapper;
import com.lanjing.otc.dao.WellethistoryStateMapper;
import com.lanjing.otc.enums.ReviewEnum;
import com.lanjing.otc.ex.CheckEx;
import com.lanjing.otc.model.*;
import com.lanjing.otc.model.po.AdminAds;
import com.lanjing.otc.model.po.Ads;
import com.lanjing.otc.service.OtcAdsService;
import com.lanjing.otc.util.GetInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 14:35
 */
@Service
public class OtcAdsServiceImpl implements OtcAdsService {

    @Resource
    private OtcAdsMapper otcAdsMapper;

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private WalletsMapper walletsMapper;

    @Resource
    GetInformation getInformation;

    @Resource
    WellethistoryStateMapper wellethistoryStateMapper;

    @Override
    public long countByExample(OtcAdsExample example) {
        return otcAdsMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(OtcAdsExample example) {
        return otcAdsMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return otcAdsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OtcAds record) {
        return otcAdsMapper.insert(record);
    }

    @Override
    public int insertSelective(OtcAds record) {
        return otcAdsMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int addAds(OtcAds otcAds, Integer userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        CheckEx.isNull(user, "获取用户信息失败");

        Wallets wallet = walletsMapper.selectByUserKeyAndCoinId(user.getKeyes(), otcAds.getCoinId());
        CheckEx.isNull(wallet, "获取用户钱包失败");

        BigDecimal num = otcAds.getNum();
        int result;
        if (otcAds.getType() == 2) {
            //卖出，冻结币种余额
            CheckEx.check(wallet.getCoinnum() < Double.parseDouble(num.toString()) || wallet.getCoinnum() <= 0, "余额不足");

            //冻结用户钱包
            result = walletsMapper.updateByVersionAndFreeze(num, wallet.getFid(), wallet.getVersion());
            CheckEx.db(result, "修改用户钱包失败");

            //添加日志
            getInformation.insertwellethistory(user.getKeyes(), wallet.getCoinid(), Double.parseDouble(num.toString()), 8);
        }

        //添加广告记录
        Date date = new Date();
        otcAds.setId(null);
        otcAds.setUserName(user.getRealname());
        otcAds.setUserKey(userId);
        otcAds.setUpdateTime(date);
        otcAds.setCreateTime(date);
        otcAds.setNumBalance(num);
        otcAds.setReviewStatus(ReviewEnum.PASS.getCode());

        result = this.insertSelective(otcAds);
        CheckEx.db(result, "插入失败");
        return result;
    }

    @Override
    public List<OtcAds> selectByExample(OtcAdsExample example) {
        return otcAdsMapper.selectByExample(example);
    }

    @Override
    public OtcAds selectByPrimaryKey(Integer id) {
        return otcAdsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(OtcAds record, OtcAdsExample example) {
        return otcAdsMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(OtcAds record, OtcAdsExample example) {
        return otcAdsMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(OtcAds record) {
        return otcAdsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OtcAds record) {
        return otcAdsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByIdAndUseId(Integer id, Integer userId) {
        return otcAdsMapper.updateByIdAndUseId(id, userId);
    }

    @Override
    public List<OtcAds> selectAll(Integer userId, Integer id) {
        return otcAdsMapper.selectAll(userId, id);
    }

    @Override
    @Transactional
    public int cancelAds(Integer id, Integer userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);

        CheckEx.isNull(user, "获取用户信息失败");

        int result = this.updateByIdAndUseId(id, userId);
        CheckEx.db(result, "取消失败");

        OtcAds ads = this.selectByPrimaryKey(id);
        CheckEx.isNull(ads, "未获取到广告");

        Wallets wallet = walletsMapper.selectByUserKeyAndCoinId(user.getKeyes(), ads.getCoinId());
        CheckEx.isNull(wallet, "获取用户钱包失败");

        BigDecimal num = ads.getNumBalance();

        if (ads.getType() == 2) {
            //修改用户钱包冻结
            CheckEx.check(wallet.getFrozennum() < num.doubleValue(), "修改冻结会出现负数！");
            //保存一条回退的交易记录
            WellethistoryState wellethistoryState = new WellethistoryState();
            wellethistoryState.setCreatetime(new Date());
            wellethistoryState.setRemark("OTC取消订单");
            wellethistoryState.setUpdatetime(new Date());
            wellethistoryState.setState(1);
            wellethistoryState.setType(13);//新的一个状态，app那边要做相应的修改
            wellethistoryState.setChangenum(num);
            wellethistoryState.setKeyes(UUID.randomUUID().toString());
            wellethistoryState.setUserid(user.getFid());
            wellethistoryState.setRecharge(wallet.getAddress());
            wellethistoryState.setWithdraw(wallet.getAddress());
            wellethistoryState.setWelletid(wallet.getFid());
            wellethistoryState.setCointype(wallet.getCoinid());
            CheckEx.db(wellethistoryStateMapper.insertSelective(wellethistoryState));

            result = walletsMapper.updateByVersionAndCoinNum(num, wallet.getFid(), wallet.getVersion());
            CheckEx.db(result, "修改用户钱包失败");
        }
        return result;
    }

    @Override
    @Transactional
    public int adsReview(OtcAds ads) {
        OtcAds oldAds = this.selectByPrimaryKey(ads.getId());
        CheckEx.isNull(oldAds, "未获取到广告");
        CheckEx.check(oldAds.getReviewStatus() != 0, "已经审核");

        Integer userId = oldAds.getUserKey();
        Users user = usersMapper.selectByPrimaryKey(userId);
        CheckEx.isNull(user, "获取用户信息失败");

        int result = this.updateByPrimaryKeySelective(ads);
        CheckEx.db(result, "取消失败");

        if (oldAds.getType() == 2) {
            if (ads.getStatus() == ReviewEnum.FAIL.getCode()) {
                Wallets wallet = walletsMapper.selectByUserKeyAndCoinId(user.getKeyes(), ads.getCoinId());
                CheckEx.isNull(wallet, "获取用户钱包失败");

                BigDecimal num = oldAds.getNumBalance();

                CheckEx.check(wallet.getFrozennum() < num.doubleValue(), "修改冻结会出现负数~");

                //修改用户钱包冻结
                result = walletsMapper.updateByVersionAndCoinNum(num, wallet.getFid(), wallet.getVersion());
                CheckEx.db(result, "修改用户钱包失败");
            }
        }
        return result;
    }

    @Override
    public List<Ads> selectAdsBuyOrSell(Integer coinId, Integer type) {
        return otcAdsMapper.selectAdsBuyOrSell(coinId, type);
    }

    @Override
    public List<AdminAds> findAll(String key, Integer status, Integer reviewStatus, Integer type, Integer coinId) {
        return otcAdsMapper.findAll(key, status, reviewStatus, type, coinId);
    }
}

