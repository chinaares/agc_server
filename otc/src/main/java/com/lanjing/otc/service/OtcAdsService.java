package com.lanjing.otc.service;

import com.lanjing.otc.model.OtcAds;
import com.lanjing.otc.model.OtcAdsExample;
import com.lanjing.otc.model.po.AdminAds;
import com.lanjing.otc.model.po.Ads;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 14:35
 */
public interface OtcAdsService {


    long countByExample(OtcAdsExample example);

    int deleteByExample(OtcAdsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OtcAds record);

    int insertSelective(OtcAds record);

    List<OtcAds> selectByExample(OtcAdsExample example);

    OtcAds selectByPrimaryKey(Integer id);

    int updateByExampleSelective(OtcAds record, OtcAdsExample example);

    int updateByExample(OtcAds record, OtcAdsExample example);

    int updateByPrimaryKeySelective(OtcAds record);

    int updateByPrimaryKey(OtcAds record);

    int updateByIdAndUseId(Integer id, Integer userId);

    List<OtcAds> selectAll(Integer userId, Integer id);

    int addAds(OtcAds otcAds, Integer userId);

    int cancelAds(Integer id, Integer userId);

    int adsReview(OtcAds ads);

    List<Ads> selectAdsBuyOrSell(Integer coinId, Integer type);

    List<AdminAds> findAll(String key, Integer status, Integer reviewStatus, Integer type, Integer coinId);
}

