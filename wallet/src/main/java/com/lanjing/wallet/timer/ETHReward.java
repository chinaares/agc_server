package com.lanjing.wallet.timer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.IncomeMapper;
import com.lanjing.wallet.model.Income;
import com.lanjing.wallet.model.Invest;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.service.IncomeService;
import com.lanjing.wallet.service.InvestService;
import com.lanjing.wallet.service.RecommendService;
import com.lanjing.wallet.util.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Component
//@RestController
public class ETHReward {

    private static Logger logger = LoggerFactory.getLogger(ETHReward.class);

    @Resource(name = "RecommendService")
    private RecommendService recommendService;

    @Resource(name = "InvestService")
    private InvestService investService;

    @Resource(name = "IncomeService")
    private IncomeService incomeService;

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private RedisDao redisDao;

    /**
     * 获取直推总业绩
     * */
    public double getztyj (String userkey){
        List<Recommend> recommendList = recommendService.selectByUserKey21(userkey);
        List<Integer> userIds = new ArrayList<>();
        for (Recommend recommend : recommendList){
            userIds.add(Integer.parseInt(recommend.getUserkey1()));
        }
        List<Integer> states = new ArrayList<>();
        states.add(1);
        states.add(2);
        states.add(3);
        states.add(5);
        List<Invest> list = investService.selectAllOrByPrimaryKeyOrStatusesOrUserIds(null,states,userIds);
        double num = 0;
        for (Invest invest :list){
            num = num + invest.getPrincipal();
        }
        return num;
    }

    /**
     * 获取下线总业绩
     * */
    public double getsqyj(String key,String userkey){
        Recommend recommend = recommendService.selectByUserKey1(userkey);
        List<Recommend> recommendList = recommendService.queryBycode(recommend.getLev(),recommend.getCoverCode());
        List<Integer> userIds = new ArrayList<>();
        for (Recommend recommend1: recommendList){
            userIds.add(Integer.parseInt(recommend1.getUserkey1()));
        }
        if(key != null && !"".equals(key)){
            redisDao.setKey1(key+userkey, JSON.toJSONString(userIds));
        }
        List<Integer> states = new ArrayList<>();
        states.add(1);
        states.add(2);
        states.add(3);
        states.add(5);
        List<Invest> list = investService.selectAllOrByPrimaryKeyOrStatusesOrUserIds(null,states,userIds);
        double num = 0;
        for (Invest invest :list){
            num = num + invest.getPrincipal();
        }
        return num;
    }

    /**
     * 获取个人总业绩
     * */
    public double getpersonyj(String userkey){
        List<Integer> userIds = new ArrayList<>();
        userIds.add(Integer.parseInt(userkey));
        List<Integer> states = new ArrayList<>();
        states.add(1);
        states.add(2);
        states.add(3);
        states.add(5);
        List<Invest> list = investService.selectAllOrByPrimaryKeyOrStatusesOrUserIds(null,states,userIds);
        double num = 0;
        for (Invest invest :list){
            num = num + invest.getPrincipal();
        }
        return num;
    }

    public int check1(String userkey){
        List<Recommend> recommendList = recommendService.selectByUserKey21(userkey);
        int i = 0;
        if(recommendList.size()>=4){
            for (Recommend recommend1 : recommendList){
                double sum = getpersonyj(recommend1.getUserkey1());
                if (sum >= 29.8){
                    i++;
                }
            }
        }
        return i;
    }

    public double cjcheck(String userkey){
        List<Recommend> recommendList = recommendService.selectByUserKey21(userkey);
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        if(recommendList.size()>=6){
            for (Recommend recommend1 : recommendList){
                double sum = getsqyj(null,recommend1.getUserkey1());
                if (sum >= 1785.7){
                    i1++;
                }
                if (sum >= 5357.1){
                    i2++;
                }
                if (sum >= 10714.2){
                    i3++;
                }
                if (sum >= 16071.4){
                    i4++;
                }
                if (sum >= 21428.5){
                    i5++;
                }if (sum >= 29761.9){
                    i6++;
                }

            }
        }
        if(i6 >=6){
            return 0.15;
        }else if(i5 >=6){
            return 0.14;
        }else if(i4 >=6){
            return 0.13;
        }else if(i3 >=6){
            return 0.12;
        }else if(i2 >=6){
            return 0.11;
        }else if(i1 >=6){
            return 0.10;
        }
        return 0;
    }

    public double sqcheck(String key,String userkey){
        int num = check1(userkey);
        double ratio = 0;
        if(num >= 4){
            double yjsum = getsqyj(key,userkey);
            if(yjsum >= 29761.9 && num >= 25){
                ratio = 0.1;
            }else if(yjsum >= 21428.5 && num >= 20){
                ratio = 0.09;
            }else if(yjsum >= 16071.4 && num >= 16){
                ratio = 0.08;
            }else if(yjsum >= 10714.2 && num >= 12){
                ratio = 0.07;
            }else if(yjsum >= 5357.1 && num >= 8){
                ratio = 0.06;
            }else if(yjsum >= 1785.7 && num >= 4){
                ratio = 0.05;
            }
        }
        return ratio;
    }

    public double getsy(String key,String userkey){
        double sy = 0;
        if(key != null && !"".equals(key)){
            List list = JSONObject.parseObject(redisDao.getValue(key+userkey),List.class);
            redisDao.remove(key+userkey);
            for (Object obj : list){
                int uid = (int) obj;
                List<Income> incomes = incomeMapper.queryBytodayAndUser(uid,1);
                for (Income income :incomes){
                    sy = sy + income.getAmount();
                }
            }
        }else{
            Recommend recommend = recommendService.selectByUserKey1(userkey);
            List<Recommend> recommendList = recommendService.queryBycode(recommend.getLev(),recommend.getCoverCode());
            for (Recommend recommend1 : recommendList){
                List<Income> incomes = incomeMapper.queryBytodayAndUser(Integer.parseInt(recommend1.getUserkey1()),1);
                for (Income income :incomes){
                    sy = sy + income.getAmount();
                }
            }
        }
        return sy;
    }

    /**
     * 社区奖励结算
     * */
    public void sqReward(){
        List<Recommend> list = recommendService.queryByNum(4);
        for (Recommend recommend : list){
            double ratio = sqcheck("sq",recommend.getUserkey1());
            if(ratio>0){
                double sy = getsy("sq",recommend.getUserkey1());
                if(sy*ratio >0){
                    Income income = new Income();
                    income.setType(5);
                    income.setAmount(sy*ratio);
                    income.setRemark("社区奖励");
                    income.setUserId(Integer.valueOf(recommend.getUserkey1()));
                    income.setCreateTime(new Date());
                    incomeMapper.insert(income);
                }
            }
        }
    }

    /**
     * 超级奖励结算
     * */
    public void cjReward(){
        List<Recommend> list = recommendService.queryByNum(6);
        for (Recommend recommend : list){
            double ratio = cjcheck(recommend.getUserkey1());
            if(ratio>0){
                double sy = getsy(null,recommend.getUserkey1());
                if(sy*ratio >0){
                    Income income = new Income();
                    income.setType(6);
                    income.setAmount(sy*ratio);
                    income.setRemark("超级奖励");
                    income.setUserId(Integer.valueOf(recommend.getUserkey1()));
                    income.setCreateTime(new Date());
                    incomeMapper.insert(income);
                }
            }
        }
    }

    /**
     * 分享奖励结算
     * */
    public void fxReward(){
        List<Recommend> list = recommendService.queryByNum(1);
        for (Recommend recommend : list){
            double ztyj = getztyj(recommend.getUserkey1());
            int ds = fxdscheck(ztyj);
            int num = ds>20?20:ds;
            double sy = getztsy(num,recommend.getCoverCode(),recommend.getLev());
            if(sy >0){
                Income income = new Income();
                income.setType(4);
                income.setAmount(sy);
                income.setRemark("分享奖励");
                income.setUserId(Integer.valueOf(recommend.getUserkey1()));
                income.setCreateTime(new Date());
                incomeMapper.insert(income);
            }
        }
    }

    public int fxdscheck(double num){
        if(num >=5.9 && num <= 11.8){
            return (int) (num%5.9);
        }else if (num > 11.8 && num <35.8){
            return (int) ((num-11.8)%6+2);
        }else if(num>=35.8 && num <41.6){
            return 6;
        }else if(num >=41.6){
            return (int) ((num-41.6)%6+7);
        }
        return 0;
    }

    public double getztsy(int ds,String covercode,int levs){
        double sy = 0;
        for (int i =1 ;i<=ds;i++){
            List<Recommend> list = recommendService.queryBycodeAndLev(levs+1,covercode);
            double sy1 = 0;
            for (Recommend recommend1 : list){
                List<Income> incomes = incomeMapper.queryBytodayAndUser(Integer.parseInt(recommend1.getUserkey1()),1);
                for (Income income :incomes){
                    sy1 = sy1 + income.getAmount();
                }
            }
            if(i<=10){
                sy = sy + sy1*(0.2-(i-1)*0.02);
            }else if (i>10){
                sy = sy + sy1*0.01;
            }
        }
        return sy;
    }

    /**
     * 每天零晨两点执行
     */
    @Scheduled(cron = "0 0 2 * * ? ")
    public void start(){
        fxReward();
        sqReward();
        cjReward();
    }

    @RequestMapping("/sys/fxstart")
    public void fxstart(){
        fxReward();
    }

    @RequestMapping("/sys/sqstart")
    public void sqstart(){
        sqReward();
    }

    @RequestMapping("/sys/cjstart")
    public void cjstart(){
        cjReward();
    }


}
