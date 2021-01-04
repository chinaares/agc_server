package com.lanjing.wallet.service;

import com.lanjing.wallet.model.Banner;

import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-26 10:14
 */
public interface BannerService {
    List<Banner> selectAll();

    int insertSelective(Banner banner);

    List<Banner> selectByTypeAndLanguage(Integer type, Integer language);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner banner);

    int updateStatusById(Integer status, Integer id);

    int deleteBanner(Banner banner);
}


