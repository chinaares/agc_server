package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.enums.IncomeEnum;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.ParametersService;
import com.lanjing.wallet.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("PromotionService")
public class PromotionServiceImpl implements PromotionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    IncomeMapper incomeMapper;

    @Autowired
    RecommendMapper recommendMapper;

    @Autowired
    WelletsMapper welletsMapper;

    @Resource(name = "ParametersService")
    ParametersService parametersService;

    @Autowired
    private PromotionMapper promotionMapper;

    public int deleteByPrimaryKey(Integer id) {
        return this.promotionMapper.deleteByPrimaryKey(id);
    }

    public int insert(Promotion record) {
        return this.promotionMapper.insert(record);
    }

    public int insertSelective(Promotion record) {
        return this.promotionMapper.insertSelective(record);
    }

    public Promotion selectByPrimaryKey(Integer id) {
        return this.promotionMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Promotion record) {
        return this.promotionMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Promotion record) {
        return this.promotionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Promotion> selectAll() {
        return this.promotionMapper.selectAll();
    }

    @Override
    @Transactional
    public void calculate(Promotion promotion) {
        //用户id
        Integer userId = promotion.getUserId();
        //获取用户信息
        Users user = usersMapper.selectByPrimaryKey(userId);
        //用户key
        String key = user.getKeyes();

        ParametersWithBLOBs pRate = parametersService.selectByKey("push");
        ParametersWithBLOBs dRate = parametersService.selectByKey("direct_push");

        //间推比率
        //double pushRate = ConfigConst.PUSH / 100.0;
        double pushRate = Double.parseDouble(pRate.getKeyvalue()) / 100.0;
        //直推比率
        //double directPushRate = ConfigConst.DIRECT_PUSH / 100.0;
        double directPushRate = Double.parseDouble(dRate.getKeyvalue()) / 100.0;
        //币种id
        int coinId = ConfigConst.DZCC_COIN;

        Date date = new Date();

        //获取用户推荐人（直推）
        Recommend directPush = recommendMapper.selectByUserKey1(key);
        if (directPush != null) {
            Users directPushUser = usersMapper.selectByUserKey(directPush.getUserkey2());
            double directPushReward = promotion.getAmount() * directPushRate;
            //修改用户钱包
            Wellets directPushWallet = welletsMapper.selectByUserandcoin(directPushUser.getKeyes(), coinId);

            int result = welletsMapper.updateCoinNumByVersion(directPushReward, coinId, directPushUser.getKeyes(), directPushWallet.getVersion());
            if (result < 1) {
                throw new RuntimeException("修改直推用户钱包失败");
            }

            //添加分红记录
            Income income = new Income();
            income.setInvestId(promotion.getId());
            income.setUserId(directPushUser.getFid());
            income.setType(IncomeEnum.COMMUNITY.getCode());
            income.setAmount(directPushReward);
            income.setRemark(IncomeEnum.COMMUNITY.getMsg());
            income.setCreateTime(date);

            result = incomeMapper.insertSelective(income);
            if (result < 1) {
                throw new RuntimeException("添加直推用户分红记录失败");
            }

            //获取用户推荐人（间推）
            Recommend push = recommendMapper.selectByUserKey1(directPushUser.getKeyes());
            if (push != null) {
                Users pushUser = usersMapper.selectByUserKey(push.getUserkey2());
                double pushUserReward = promotion.getAmount() * pushRate;

                //修改用户钱包
                Wellets pushWallet = welletsMapper.selectByUserandcoin(directPushUser.getKeyes(), coinId);

                result = welletsMapper.updateCoinNumByVersion(pushUserReward, coinId, directPushUser.getKeyes(), pushWallet.getVersion());
                if (result < 1) {
                    throw new RuntimeException("修改间推用户钱包失败");
                }

                //添加分红记录
                income.setUserId(pushUser.getFid());
                income.setAmount(pushUserReward);

                result = incomeMapper.insertSelective(income);
                if (result < 1) {
                    throw new RuntimeException("添加间推用户分红记录失败");
                }

            }
        }

        Promotion updatePromotion = new Promotion();
        updatePromotion.setId(promotion.getId());
        updatePromotion.setStatus(2);

        int i = promotionMapper.updateByPrimaryKeySelective(updatePromotion);

        if (i < 1) {
            throw new RuntimeException("更新待分红信息状态失败");
        }


    }
}