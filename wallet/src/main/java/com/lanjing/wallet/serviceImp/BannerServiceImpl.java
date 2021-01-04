package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.BannerMapper;
import com.lanjing.wallet.model.Banner;
import com.lanjing.wallet.service.BannerService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-26 10:14
 */
@Service("BannerService")
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> selectAll() {
        return bannerMapper.selectAllList();
    }

    @Override
    public int insertSelective(Banner banner) {
        return bannerMapper.insertSelective2(banner);
    }

    @Override
    public List<Banner> selectByTypeAndLanguage(Integer type, Integer language) {
        Example example = new Example(Banner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (language != null) {
            criteria.andEqualTo("language", language);
        }
        return bannerMapper.selectByExample(example);
    }

    @Override
    public Banner selectByPrimaryKey(Integer id) {
        return bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Banner banner) {
        return bannerMapper.updateByPrimaryKeySelective2(banner);
    }

    @Override
    public int updateStatusById(Integer status, Integer id) {
        return bannerMapper.updateStatusById(status, id);
    }

    @Override
    public int deleteBanner(Banner banner) {
        return bannerMapper.delete(banner);
    }
}


