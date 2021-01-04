package com.lanjing.wallet.timer;

import com.lanjing.wallet.dao.RecommendMapper;
import com.lanjing.wallet.model.Recommend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RestController
public class NodeCalculation {
    private static Logger logger = LoggerFactory.getLogger(ETHReward.class);

    @Autowired
    private RecommendMapper recommendMapper;

    public void upnode(int usergrede){
        double sc = 200000;
        int down = 20;
        if(usergrede == 1){
            sc = 10000;
            down = 10;
        }else if(usergrede == 2){
            sc = 50000;
            down = 10;
        }else if(usergrede == 3){
            sc = 200000;
            down = 20;
        }
        int maxlev = recommendMapper.selectMaxlev();
        for (int i =1;i<=maxlev;i++){
            List<Recommend> recommendList = recommendMapper.selectByLev(i);
            for(Recommend recommend : recommendList){
                if(recommend.getUserlocknum() >= sc){
                   if(getztscnum(recommend.getUserkey1()) >= down ){
                       int nodenum = getnodenum(recommend.getUserkey1(),usergrede);
                       if(usergrede == 1){
                           if(i == 1){
                               Map<String, Double> getdownsc = getdownsc(recommend.getCoverCode(),recommend.getLev());
                               recommend.setDownrelease(getdownsc.get("releasenum"));
                           }
                           double small = checksmall(recommend.getUserkey1());
                           if(small >= 410000){
                               recommend.setDownlocknum(down);
                               recommend.setUsergrede(usergrede);
                               recommend.setNodenum(nodenum);
                               recommendMapper.updateByPrimaryKeySelective(recommend);
                            }
                       }else {
                           if (nodenum >= 3){
                               recommend.setDownlocknum(down);
                               recommend.setUsergrede(usergrede);
                               recommend.setNodenum(nodenum);
                               recommendMapper.updateByPrimaryKeySelective(recommend);
                           }
                       }
                   }
                }
            }
        }
    }

    public int getztscnum(String userkey){
        int num = recommendMapper.selectByUserKey22(userkey);
        return num;
    }

    public int getnodenum(String userkey,int usergrede){
        int num = recommendMapper.selectByUserKey23(userkey,usergrede-1);
        return num;
    }

    public double checksmall(String userkey){
        double num = 0;
        List<Recommend> recommendList1 = recommendMapper.selectByUserKey21(userkey);
        List<Double> list = new ArrayList<>();
        double ztrelease = 0;
        for (Recommend recommend : recommendList1){
            ztrelease = ztrelease + recommend.getYesterdayrelease();
            Map<String, Double> sc = getdownsc(recommend.getCoverCode(),recommend.getLev());
            recommend.setDownrelease(sc.get("releasenum"));
            list.add(sc.get("locknum"));
            recommendMapper.updateByPrimaryKeySelective(recommend);
        }
        Recommend recommend = recommendMapper.selectByUserKey1(userkey);
        recommend.setZtdownrelease(ztrelease);
        recommendMapper.updateByPrimaryKeySelective(recommend);
        list.remove(getmax(list));
        for (Double b : list){
            num = num + b;
        }
        return num;
    }

    public Map<String, Double> getdownsc(String covercode, int lev){
        return recommendMapper.queryBycodescnum(lev,covercode);
    }

    public double getmax(List<Double> list){
        double a = 0;
        for (Double d :list){
            if(d>a){
                a = d;
            }
        }
        return a;
    }

    @RequestMapping("/sys/upnode")
    public void start(){
        upnode(1);
        upnode(2);
        upnode(3);
    }

}
