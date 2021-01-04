package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanjing.wallet.dao.CoinsMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.AddressService;
import com.lanjing.wallet.dao.UsersMapper;
import com.lanjing.wallet.dao.addree_userMapper;
import com.lanjing.wallet.model.addree_user;
import com.lanjing.wallet.util.EthUtil;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private addree_userMapper addressDao;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return addressDao.deleteByPrimaryKey(fid);
    }

    @Override
    public int insertSelective(addree_user record) {
        return addressDao.insertSelective(record);
    }

    @Override
    public addree_user selectByPrimaryKey(Integer fid) {
        return addressDao.selectByPrimaryKey(fid);
    }

    @Override
    public addree_user selectByUserKey(String userkey) {
        return addressDao.selectByUserKey(userkey);
    }

    @Override
    public int updateByPrimaryKeySelective(addree_user record) {
        return addressDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByUserKeySelective(addree_user record) {
        return addressDao.updateByUserKeySelective(record);
    }


    public addree_user updateByUserIdSelective(String userid,String help) {
        try{
            addree_user address = addressDao.selectByUserKey(usersMapper.selectByUserKey(userid).getKeyes());
            address.setHelp(help);
            int i = addressDao.updateByUserKeySelective(address);
            if(i>0){
                return address;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public addree_user selectByhelpkey(String help, String key) {
        addree_user addree = new addree_user();
        addree.setHelp(help);
        addree.setKeyes(key);
        return addressDao.selectByKey(addree);
    }

    @Override
    @Transactional
    public addree_user createWellet(String userkey, String helps) {
        String helps1 = redisDao.getValue("helps"+userkey);
        String pwd = usersMapper.selectByUserKey(userkey).getTranspassword();
        if(helps1 != null){
            JSONArray set1 = JSON.parseArray(helps1);
            StringBuilder strstringList = new StringBuilder();
            for (Object str : set1){
                String s = str+" ";
                strstringList.append(s);
            }
            if(helps.endsWith(" ")){
                helps = helps.substring(0,helps.length()-1);
            }
            if(strstringList.toString().substring(0,strstringList.toString().length()-1).equals(helps)){
                addree_user address = new addree_user();
                String adress = EthUtil.getethAdress(pwd);
                address.setUserkey(userkey);
                address.setAdress(adress);
                address.setKeyes(UUID.randomUUID().toString().replace("-",""));
                address.setHelp(helps);
                addressDao.insertSelective(address);
                List<Coins> list = coinsMapper.selectAll();
                int flag = 0;
                for(Coins coin : list){
                    flag++;
                    Wellets wellet = new Wellets();
                    wellet.setAddress(address.getAdress());
                    wellet.setUserkey(address.getUserkey());
                    wellet.setCoinid(coin.getFid());
                    wellet.setCointype(coin.getCoinshort());
                    welletsMapper.insertSelective(wellet);
                }
                if (flag == list.size()){
                    redisDao.remove("helps"+userkey);
                    return address;
                }
                return null;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public addree_user importwellet(String userkey, String helps) {
        if(helps.endsWith(" ")){
            helps = helps.substring(0,helps.length()-1);
        }
        addree_user address = new addree_user();
        address.setHelp(helps);
        addree_user addree_user = addressDao.selectByHelps(address);
        if (addree_user != null){
            addree_user.setUserkey(userkey);
            List<Wellets> wellets = welletsMapper.queryWelletByAdress(addree_user.getAdress());
            for (Wellets wellets1 : wellets){
                wellets1.setUserkey(userkey);
                welletsMapper.updateByPrimaryKeySelective(wellets1);
            }
            addressDao.updateByPrimaryKeySelective(addree_user);
            return addree_user;
        }
        return null;
    }

    @Override
    public addree_user getaddress(String userkey, String tradepwd) {
        String tradepwd1 = usersMapper.selectByPrimaryKey(Integer.valueOf(userkey)).getTranspassword();
        if(tradepwd1.equals(tradepwd)){
            return addressDao.selectByUserKey(userkey);
        }
        return null;
    }

}
