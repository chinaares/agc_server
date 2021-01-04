package com.lanjing.goods.service.impl;

import com.jester.util.utils.UUIDUtil;
import com.lanjing.goods.dao.CoinsMapper;
import com.lanjing.goods.dao.MallFundListMapper;
import com.lanjing.goods.dao.MallWalletMapper;
import com.lanjing.goods.dao.OrdersMapper;
import com.lanjing.goods.model.MallFundList;
import com.lanjing.goods.model.MallWallet;
import com.lanjing.goods.model.MallWalletExample;
import com.lanjing.goods.model.Orders;
import com.lanjing.goods.service.MallWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 */
@Service
public class MallWalletServiceImpl implements MallWalletService {

    @Resource
    private MallWalletMapper mallWalletMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private MallFundListMapper mallFundListMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Override
    public long countByExample(MallWalletExample example) {
        return mallWalletMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(MallWalletExample example) {
        return mallWalletMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mallWalletMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MallWallet record) {
        return mallWalletMapper.insert(record);
    }

    @Override
    public int insertSelective(MallWallet record) {
        return mallWalletMapper.insertSelective(record);
    }

    @Override
    public List<MallWallet> selectByExample(MallWalletExample example) {
        return mallWalletMapper.selectByExample(example);
    }

    @Override
    public MallWallet selectByPrimaryKey(Integer id) {
        return mallWalletMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(MallWallet record, MallWalletExample example) {
        return mallWalletMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(MallWallet record, MallWalletExample example) {
        return mallWalletMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(MallWallet record) {
        return mallWalletMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MallWallet record) {
        return mallWalletMapper.updateByPrimaryKey(record);
    }

    @Override
    public MallWallet findByCodeAndCoinId(Long code, Integer coinId) {
        return mallWalletMapper.findByCodeAndCoinId(code, coinId);
    }

    @Override
    public int updateByCodeAndCoinIdAndVersion(BigDecimal amount, Long code, Integer coinId, Integer version) {
        return mallWalletMapper.updateByCodeAndCoinIdAndVersion(amount, code, coinId, version);
    }


    @Override
    @Transactional
    public int withdraw1(Long mallcode, int coin, double amount) {
        MallWallet wellet = mallWalletMapper.findByCodeAndCoinId(mallcode,coin);
        double gas = 0;
        wellet.setCoinNum(BigDecimal.valueOf(wellet.getCoinNum().doubleValue()-(amount+gas)));
        Long orderid = null;
        orderid = UUIDUtil.nextId();
        mallWalletMapper.updateByPrimaryKeySelective(wellet);

        MallFundList mallFundList = new MallFundList();
        mallFundList.setAmount(BigDecimal.valueOf(amount+gas));
        mallFundList.setCode(mallcode);
        mallFundList.setCoinId(coin);
        mallFundList.setOrderId(orderid);
        mallFundList.setType(2);
        mallFundList.setCoinName("AGC");
        mallFundList.setCreateTime(new Date());
        mallFundListMapper.insertSelective(mallFundList);

        Orders order = new Orders();
        order.setOrderid(String.valueOf(orderid));
        order.setTradecoin(coin+"");
        order.setTradecoinid(coin);
        order.setCreatetime(new Date());
        order.setConfirmtime(new Date());
        order.setBuyadress(wellet.getAddress());
        order.setState(-1);
        order.setUserbuy("");
        order.setTradenum(BigDecimal.valueOf(amount));
        order.setUsersell(String.valueOf(mallcode));
        order.setFee(BigDecimal.valueOf(gas/ 21000 * 1000000000000000000l));
        order.setSelladress(wellet.getAddress());
        ordersMapper.insertSelective(order);
        return 1;
    }
}
