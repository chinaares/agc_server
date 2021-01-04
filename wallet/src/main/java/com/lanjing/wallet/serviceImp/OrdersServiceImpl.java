package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.OrdersService;
import com.lanjing.wallet.dao.OrdersMapper;
import com.lanjing.wallet.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return ordersMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Orders record) {
        return ordersMapper.insert(record);
    }

    @Override
    public int insertSelective(Orders record) {
        return ordersMapper.insertSelective(record);
    }

    @Override
    public Orders selectByPrimaryKey(Integer fid) {
        return ordersMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByPrimaryKeySelective(Orders record) {
        return ordersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orders record) {
        return ordersMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Orders> selectBy(Orders record, Integer begin, Integer end) {
        return ordersMapper.selectBy(record,begin,end);
    }
}
