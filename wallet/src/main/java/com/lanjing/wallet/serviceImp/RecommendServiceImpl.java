package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.service.RecommendService;
import com.lanjing.wallet.systemService.systemUtil;
import com.lanjing.wallet.util.DateUtils;
import com.lanjing.wallet.util.RedisDao;
import com.lanjing.wallet.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("RecommendService")
public class RecommendServiceImpl implements RecommendService {

    @Resource(name = "systemUtil")
    private systemUtil systemutil;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private AddUserMapper addUserMapper;

    @Autowired
    private ChangelogMapper changelogMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(Recommend record) {
        Users user = usersMapper.selectByUserKey(record.getUserkey2());
        if(user!=null){
            record.setUserkey2(user.getKeyes());
            Recommend recommend1 = recommendMapper.selectByUserKey1(user.getKeyes());
            int num = 0;
            if ("".equals(recommend1.getCoverCode())){
                num = recommendMapper.selectByLev1(1).size()+1;
            }else{
                num = recommendMapper.selectByUserKey2Count(record.getUserkey2()) + 1;
            }
            String cover_code = "0000"+num;
            if(!"99999999".equals(user.getKeyes())){
                Recommend recommend = recommendMapper.selectByUserKey1(user.getKeyes());
                cover_code = recommend.getCoverCode()+cover_code.substring(cover_code.length()-4);
                record.setLev(recommend.getLev()+1);
                if(num>=20 && recommend.getUsergrede()<1){
                    recommend.setUsergrede(1);
                    redisDao.sendmsg("test",recommend.getUserkey1());
                }
                recommend.setNum(num);
                //获得Usd待理财
                //getAirdrop(record.getUserkey1(),user);
                //修改上级
                recommendMapper.updateByPrimaryKeySelective(recommend);
            }else {
                cover_code = cover_code.substring(cover_code.length()-4);
                record.setLev(1);
            }
            record.setCoverCode(cover_code);
            addUserMapper.updateByUserKey(record.getUserkey1());
            return recommendMapper.insertSelective(record);
        }else{
            recommendMapper.insertSelective(record);
        }
        return 0;
    }

    @Override
    public Recommend selectByPrimaryKey(Integer fid) {
        return recommendMapper.selectByPrimaryKey(fid);
    }

    @Override
    public List<Recommend> selectByUserKey2(Map map) {
        return recommendMapper.selectByUserKey2(map);
    }

    @Override
    public int selectByUserKey2Count(String userkey) {
        return recommendMapper.selectByUserKey2Count(userkey);
    }

    @Override
    public Recommend selectByUserKey1(String userkey) {
        return recommendMapper.selectByUserKey1(userkey);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Recommend record) {
        //判断上级是否激活
        Recommend uprecommend = recommendMapper.selectByUserKey1(record.getUserkey2());
        if(uprecommend == null || "".equals(uprecommend.getUserkey2())){
            return 0;
        }else if(uprecommend.getUserkey2() != null && !"".equals(uprecommend.getUserkey2()) ){
            Users user = usersMapper.selectByUserKey(record.getUserkey2());
            if(user!=null){
                record.setUserkey2(user.getKeyes());
                Recommend recommend1 = recommendMapper.selectByUserKey1(user.getKeyes());
                int num = 0;
                if ("".equals(recommend1.getCoverCode())){
                    num = recommendMapper.selectByLev1(1).size()+1;
                }else{
                    num = recommendMapper.selectByUserKey2Count(record.getUserkey2()) + 1;
                }
                String cover_code = "0000"+num;
                if(!"99999999".equals(user.getKeyes())){
                    Recommend recommend = recommendMapper.selectByUserKey1(user.getKeyes());
                    cover_code = recommend.getCoverCode()+cover_code.substring(cover_code.length()-4);
                    record.setLev(recommend.getLev()+1);
                    recommend.setNum(num);
                    if(num>=20 && recommend.getUsergrede()<1){
                        recommend.setUsergrede(1);
                        redisDao.sendmsg("test",recommend.getUserkey1());
                    }
                    //获得Usd待理财
                    //getAirdrop(record.getUserkey1(),user);
                    //修改上级
                    recommendMapper.updateByPrimaryKeySelective(recommend);
                }else {
                    cover_code = cover_code.substring(cover_code.length()-4);
                    record.setLev(1);
                }
                record.setCoverCode(cover_code);
                addUserMapper.updateByUserKey(record.getUserkey1());
                return recommendMapper.updateByPrimaryKeySelective(record);
            }
        }
        return 0;
    }

    @Override
    public List<Map> selectupnode(Integer levnum) {
        return recommendMapper.selectupnode(levnum);
    }

    @Override
    public List<Recommend> selectByUserKey(String key) {
        return recommendMapper.selectByUserKey(key);
    }

    @Override
    public Integer sumLevelByKey(String code) {
        return recommendMapper.sumLevelByKey(code);
    }
    @Override
    public List<Recommend> selectByUserKey21(String userkey) {
        return recommendMapper.selectByUserKey21(userkey);
    }

    @Override
    public List<Recommend> queryBycode(Integer levs, String covercode) {
        return recommendMapper.queryBycode(levs,covercode);
    }

    @Override
    public List<Recommend> queryBycodeAndLev(Integer levs, String covercode) {
        return recommendMapper.queryBycodeAndLev(levs,covercode);
    }

    @Override
    public List<Recommend> queryByNum(Integer num) {
        return recommendMapper.queryByNum(num);
    }

    @Override
    public List<Recommend> selectByLockNumAndDownLockNum(int currency, int user) {
        return recommendMapper.selectByLockNumAndDownLockNum(currency,user);
    }

    @Override
    public void updateClear() {
        recommendMapper.updateClear();
    }

    @Override
    public double sumLockNum(String userkey) {
        return recommendMapper.sumLockNum(userkey);
    }

    @Override
    public double sumRelease(int level, String userkey) {
        return recommendMapper.sumRelease(level, userkey);
    }

    @Override
    public List<Recommend> levelRecommend(String userKey, int level) {
        return recommendMapper.levelRecommend(userKey, level);
    }

    @Override
    public List<Recommend> smallRecommend(String userKey, int level) {
        return recommendMapper.smallRecommend(userKey, level);
    }

    @Override
    public List<Recommend> selectByLevDesc(int level) {
        return recommendMapper.selectByLevDesc(level);
    }

    @Override
    public List<Recommend> selectUserAdvanced(String userKey) {
        return recommendMapper.selectUserAdvanced(userKey);
    }

    @Override
    public int updateDownRelease(Integer id, double release) {
        return recommendMapper.updateDownRelease(id, release);
    }
    
    @Override
    public List<Recommend> selectAll() {
        return recommendMapper.selectAll();
    }

	@Override
	public int selectByUserKey2AndTimeCount(String userId, String startTime, String endTime) {
		return recommendMapper.selectByUserKey2AndTimeCount(userId, startTime, endTime);
	}
	
	
	/**
	 * 获取一个用户的所有代下级
	 * 
	 * @param userList 所有用户集合
	 * @param userId   父ID
	 * @return
	 */
	@Override
	public List<String> selectUserSubordinateList(List<Recommend> userList, List<String> userIdList, String userId,String startTime,String endTime,List<String> userIdList2) {
		List<String> nowUserIdList = new ArrayList<String>();
		for (Recommend user : userList) {
			if (userId.equals(user.getUserkey2())) {// 等于父ID
				nowUserIdList.add(user.getUserkey1());
				if (StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)&&StringUtils.isNotNull(user.getCreateTime())) {
					if (DateUtils.isEffectiveDate(user.getCreateTime(), DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, startTime), DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, endTime))) {
						userIdList2.add(user.getUserkey1());
					}
					
				}
			}
		}

		userIdList.addAll(nowUserIdList);

		if (StringUtils.isNotEmpty(nowUserIdList) && nowUserIdList.size() > 0) {
			for (String uid : nowUserIdList) {
				selectUserSubordinateList(userList, userIdList, uid,startTime,endTime,userIdList2);
			}
		}

		return userIdList;
	}

	@Override
	public List<Recommend> selectListByTime(String startTime, String endTime) {
		return recommendMapper.selectListByTime(startTime,endTime);
	}

    /*public void getAirdrop(String userkey1,Users user2){
        double airdrop = 0;
        if(user2.getIsnode() == 1){
            airdrop = user2.getNodenum().doubleValue();
        }
        if(airdrop == 0){
            Recommend recommend = recommendMapper.selectByUserKey1(user2.getKeyes());
            List<Map> mapList = recommendMapper.selectupnode(recommend.getLev());
            for (Map map : mapList){
                if(recommend.getCoverCode().startsWith(map.get("cover_code")+"")){
                    airdrop = usersMapper.selectByUserKey(map.get("userkey_1")+"").getNodenum().doubleValue();
                    break;
                }
            }
        }
        Wellets wellet = welletsMapper.selectByUserandcoin(userkey1,4);
        wellet.setCoinnum(BigDecimal.valueOf(airdrop));
        if(airdrop > 0){
            Changelog changelog = new Changelog();
            changelog.setUserkey(userkey1);
            changelog.setCointype1(4);
            changelog.setCoinnum1(BigDecimal.valueOf(airdrop));
            changelog.setCoinnumago(BigDecimal.valueOf(0));
            changelog.setCoinnumafter(BigDecimal.valueOf(airdrop));
            changelog.setCointype2(4);
            changelog.setCoinnum2(BigDecimal.valueOf(airdrop));
            changelog.setType(0);
            changelog.setCraetetime(new Date());
            changelogMapper.insertSelective(changelog);
        }
        systemutil.addcoinLog(userkey1,4,airdrop,40,(int) (System.currentTimeMillis() / 1000),"理财配比",null);
        welletsMapper.updateByPrimaryKeySelective(wellet);
    }*/
}
