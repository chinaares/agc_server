package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.lanjing.wallet.agc.vo.InviteUserVO;
import com.lanjing.wallet.agc.vo.PageDomain;
import com.lanjing.wallet.agc.vo.TableSupport;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.model.po.UserTeam;
import com.lanjing.wallet.model.po.Verified;
import com.lanjing.wallet.service.RecommendService;
import com.lanjing.wallet.service.UsersService;
import com.lanjing.wallet.util.BTCUtilHuang;
import com.lanjing.wallet.util.EthUtil;
import com.lanjing.wallet.util.EthUtilHuang;
import com.lanjing.wallet.util.MD5Util;
import com.lanjing.wallet.util.RedisDao;
import com.lanjing.wallet.util.SqlUtil;
import com.lanjing.wallet.util.StringUtils;
import com.lanjing.wallet.dao.CoinsMapper;
import com.lanjing.wallet.dao.UsersMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.dao.addree_userMapper;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.model.addree_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

@Service("UsersService")
@RestController
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private addree_userMapper addressDao;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private RedisDao redisDao;
    
    @Resource(name = "RecommendService")
    private RecommendService recommendService;

    @Override
    @Transactional
    public Users insertSelective(Users record) {
        String test = UUID.randomUUID().toString().replace("-", "");
        usersMapper.insertSelective(record);
        Users user = usersMapper.selectByUserKey(record.getKeyes());
        user.setKeyes(user.getFid() + "");
        addree_user address = new addree_user();
        String adress = EthUtil.getethAdress(record.getTranspassword());
        address.setUserkey(user.getKeyes());
        address.setAdress(adress);
        address.setKeyes(test);
        int i = usersMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            if (addressDao.insertSelective(address) > 0) {
                List<Coins> list = coinsMapper.selectAll();
                int flag = 0;
                for (Coins coin : list) {
                    flag++;
                    Wellets wellet = new Wellets();
                    wellet.setAddress(address.getAdress());
                    wellet.setUserkey(user.getKeyes());
                    wellet.setCoinid(coin.getFid());
                    wellet.setCointype(coin.getCoinshort());
                    welletsMapper.insertSelective(wellet);
                }
                if (flag == list.size()) {
                    return user;
                }
            }
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public Users register(Users record, String help) {
        String test = UUID.randomUUID().toString().replace("-", "");
        usersMapper.insertSelective(record);
        Users user = usersMapper.selectByUserKey(record.getKeyes());
        user.setKeyes(user.getFid() + "");
        addree_user address = new addree_user();
        String adress = EthUtilHuang.createEthAddress(MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + user.getFid()))));
        address.setUserkey(user.getKeyes());
        address.setAdress(adress);
        address.setKeyes(test);
        address.setHelp(help);
        int i = usersMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            if (addressDao.insertSelective(address) > 0) {
                List<Coins> list = coinsMapper.selectAll();
                for (Coins coin : list) {
                    Wellets wellet = new Wellets();
                    wellet.setAddress(address.getAdress());
                    wellet.setUserkey(user.getKeyes());
                    wellet.setCoinid(coin.getFid());
                    wellet.setCointype(coin.getCoinshort());
                    welletsMapper.insertSelective(wellet);
                }
            }
            return user;
        }
        return null;
    }

    @Override
    public Users selectByPrimaryKey(Integer fid) {
        return usersMapper.selectByPrimaryKey(fid);
    }

    @Override
    public Map selectIdentityByPrimaryKey(Integer fid) {
        return usersMapper.selectIdentityByPrimaryKey(fid);
    }

    @Override
    public int userreal(Integer fid, String realname, String identityId, String identityImg1, String identityImg2) {
        return usersMapper.userreal(fid, realname, identityId, identityImg1, identityImg2);
    }

    @Override
    public int Reviewuserreal(Integer fid, Integer isreal) {
        return usersMapper.Reviewuserreal(fid, isreal);
    }

    @Override
    public Users selectByLogin(Users record) {
        List<Users> users = usersMapper.selectByLogin(record);
        if (users.size() < 1) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        if(record.getKeyes() !=null && !"".equals(record.getKeyes())){
            redisDao.setKey(record.getKeyes(), JSON.toJSONString(record),600);
            redisDao.remove("zuser"+record.getKeyes());
        }
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public String selectByhelpKey(String help, String keyes) {
        addree_user address = new addree_user();
        address.setHelp(help);
        address.setKeyes(keyes);
        Users user = usersMapper.selectByUserKey(addressDao.selectByKey(address).getUserkey());
        if (user != null) {
            return String.valueOf(user.getFid());
        }
        return null;
    }

    @Override
    public List<Users> selectByPrimaryKeys(List<String> fids) {
        StringBuilder builder = new StringBuilder("'0'");
        List<Users> list = new ArrayList<>();
        for (String fid : fids) {
            Users user = usersMapper.selectByUserKey(fid);
            if (user != null) {
                user.setLoginpassword(null);
                user.setTranspassword(null);
                user.setKeyes(null);
                user.setToken(null);
                list.add(user);
                builder.append("," + "'" + fid + "'");
            }
        }
        return list;
    }

    @Override
    public List<Users> selectBy(Users record, Integer page, Integer size) {
        if (page == null || size == null) {
            return usersMapper.selectBy(record, null, null);
        }
        int begin = (page - 1) * size;
        int end = page * size;
        return usersMapper.selectBy(record, begin, end);
    }

    @RequestMapping("/zuul/getUser")
    public Users selectByUserKey1(String userKey) {
        String userjson = null;
        try {
            userjson = redisDao.getValue("zuser" + userKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userjson != null && !"".equals(userjson)) {
            return JSONObject.parseObject(userjson, Users.class);
        }
        Users user = usersMapper.selectByPrimaryKey(Integer.valueOf(userKey));
        redisDao.setKey("zuser" + userKey, JSON.toJSONString(user), 30);
        return user;
    }

    @Override
    public Users selectByUserKey(String userKey) {
        String userjson = null;
        try {
            userjson = redisDao.getValue(userKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userjson != null && !"".equals(userjson)) {
            return JSONObject.parseObject(redisDao.getValue(userKey), Users.class);
        }
        Users user = usersMapper.selectByUserKey(userKey);
        user.setTranspassword(null);
        user.setLoginpassword(null);
        redisDao.setKey(userKey, JSON.toJSONString(user), 30);
        return user;
    }

    @Override
    public int selectId() {
        return usersMapper.selectId();
    }

    @Override
    public int selectByUsername(String username) {
        return usersMapper.selectByUsername(username);
    }

    @Override
    public List<String> selectHelpword() {
        List<String> list = null;
        String helpword = null;
        try {
            helpword = redisDao.getValue("helpword");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (helpword != null && !"".equals(helpword)) {
            list = JSONObject.parseObject(helpword, List.class);
        } else {
            list = usersMapper.selectHelpword();
            redisDao.setKey1("helpword", JSON.toJSONString(list));
        }
        return list;
    }

    @Override
    public int selectByTodayCount() {
        return usersMapper.selectBytodayCount();
    }

    @Override
    public Users selectByPhone(String phone) {
        return usersMapper.selectByPhone(phone);
    }

    @Override
    public BigDecimal sumTotalUser() {
        return usersMapper.sumTotalUser();
    }

    @Override
    public BigDecimal sumEffectiveUser() {
        return usersMapper.sumEffectiveUser();
    }

    @Override
    public BigDecimal sumTodayUser(Date startTime, Date endTime) {
        return usersMapper.sumTodayUser(startTime, endTime);
    }

    @Override
    public List<Users> selectAll() {
        return usersMapper.selectAll();
    }

    @Override
    public List<UserTeam> selectUserCoverCode() {
        return usersMapper.selectUserCoverCode();
    }

    @Override
    public List<Verified> selectByStatusAndKey(Integer status, String key) {
        return usersMapper.selectByStatusAndKey(status, key);
    }

    // 推广直推下级用户列表-分页{时间范围}
	@Override
	public List<InviteUserVO> getUserInviteDirectList(String userKey, String startTime, String endTime,Integer pageNum,Integer pageSize) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
		return usersMapper.getUserInviteDirectList(userKey,startTime,endTime);
	}

	// 推广间推下级用户列表-分页{时间范围}
	@Override
	public List<InviteUserVO> getUserInviteIndirectList(String userKey, String startTime, String endTime,Integer pageNum,Integer pageSize) {
		
		List<Recommend> reList = recommendService.selectAll();
		List<String> userIdList = new ArrayList<String>();
		List<String> userIdList2 = new ArrayList<String>();
		userIdList = recommendService.selectUserSubordinateList(reList, userIdList, userKey ,startTime, endTime,userIdList2);
		List<InviteUserVO> listVo = usersMapper.getUserInviteDirectList(userKey,startTime,endTime);
		for (int j = 0; j < listVo.size(); j++) {
			userIdList2.remove(listVo.get(j).getUserId());
		}
		List<InviteUserVO> list = new ArrayList<InviteUserVO>();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
        if (StringUtils.isNotEmpty(userIdList2)) {
        	list = usersMapper.getUserInviteIndirectList(userIdList2,startTime,endTime);
		}
		
		return list;
	}
}
