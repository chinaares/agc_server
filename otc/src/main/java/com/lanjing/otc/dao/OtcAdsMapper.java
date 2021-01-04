package com.lanjing.otc.dao;

import com.lanjing.otc.model.OtcAds;
import com.lanjing.otc.model.OtcAdsExample;
import com.lanjing.otc.model.po.AdminAds;
import com.lanjing.otc.model.po.Ads;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 16:45
 */
public interface OtcAdsMapper {
    long countByExample(OtcAdsExample example);

    int deleteByExample(OtcAdsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OtcAds record);

    int insertSelective(OtcAds record);

    List<OtcAds> selectByExample(OtcAdsExample example);

    OtcAds selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OtcAds record, @Param("example") OtcAdsExample example);

    int updateByExample(@Param("record") OtcAds record, @Param("example") OtcAdsExample example);

    int updateByPrimaryKeySelective(OtcAds record);

    int updateByPrimaryKey(OtcAds record);

    int updateByIdAndUseId(@Param("id") Integer id, @Param("userId") Integer userId);

    List<OtcAds> selectAll(@Param("userId") Integer userId, @Param("id") Integer id);

    List<Ads> selectAdsBuyOrSell(@Param("coinId") Integer coinId, @Param("type") Integer type);

    List<AdminAds> findAll(@Param("key") String key, @Param("status") Integer status, @Param("reviewStatus") Integer reviewStatus, @Param("type") Integer type, @Param("coinId") Integer coinId);
}