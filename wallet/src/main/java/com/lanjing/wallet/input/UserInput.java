package com.lanjing.wallet.input;

import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.CoinsMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.dao.addree_userMapper;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.model.addree_user;
import com.lanjing.wallet.service.UsersService;
import com.lanjing.wallet.util.EthUtilHuang;
import com.lanjing.wallet.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserInput {

    @Resource
    private WelletsMapper welletsMapper;

    @Autowired
    private addree_userMapper addressDao;

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource
    private CoinsMapper coinsMapper;

    public void aa() {
        List<String> userKeyList = new ArrayList<>();
        userKeyList.add("2022036");
        userKeyList.add("2022037");
        userKeyList.add("2022038");

        for (String userKey : userKeyList) {
            String adress = EthUtilHuang.createEthAddress(MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + userKey))));
            addree_user addree_user = addressDao.selectByUserKey(userKey);
            addree_user.setAdress(adress);
            addressDao.updateByPrimaryKeySelective(addree_user);
            List<Wellets> welletsList = welletsMapper.selectByUserKey(userKey);
            for (Wellets wellets : welletsList) {
                wellets.setAddress(adress);
                welletsMapper.updateByPrimaryKeySelective(wellets);
            }
        }
    }

    public static void main(String[] args) {
        String adress = EthUtilHuang.createEthAddress(MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + "2022015"))));
        System.out.println(adress);
    }

    public void bb() {
        List<addree_user> addreeUserList = addressDao.selectAll();
        System.out.println("开始");
        for (addree_user addreeUser : addreeUserList) {
            double ethBalance = EthUtilHuang.getEthBalance(addreeUser.getAdress());
            double tokenBalance = EthUtilHuang.getTokenBalance(addreeUser.getAdress(), ConfigConst.PLATFORM_ADDRESS1);
            if (ethBalance > 0 || tokenBalance > 0) {
                System.out.println(addreeUser.getUserkey() + ": " + ethBalance + "-" + tokenBalance);
            }
        }
        System.out.println("结束");
    }

    public void cc() {
        List<Coins> coinsList = coinsMapper.selectAll();
        List<Users> usersList = usersService.selectAll();
        for (Users users : usersList) {
            List<Wellets> welletsList = welletsMapper.selectByUserKey(users.getKeyes());
            List<Wellets> collect = welletsList.stream().filter(q -> q.getCoinid().equals(1)).collect(Collectors.toList());
            if (collect.size() > 1) {
                System.out.println(users.getKeyes());
            }
        }
    }
}
