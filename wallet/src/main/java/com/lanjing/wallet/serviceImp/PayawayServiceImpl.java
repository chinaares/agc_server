package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.dao.PayawayMapper;
import com.lanjing.wallet.model.Payaway;
import com.lanjing.wallet.model.PayawayExample;
import com.lanjing.wallet.service.PayawayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service("PayawayService")
@RestController
public class PayawayServiceImpl implements PayawayService {

    @Autowired
    private PayawayMapper payawayMapper;

    @Override
    public int insertSelective(Payaway record) {
        return payawayMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Payaway record) {
        return payawayMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Payaway> selectByUser(String userkey,Integer type) {
        PayawayExample payawayExample = new PayawayExample();
        PayawayExample.Criteria criteria = payawayExample.createCriteria();
        criteria.andUseridEqualTo(Integer.parseInt(userkey));
        if(type != null){
            criteria.andTypeEqualTo(type);
        }
        return payawayMapper.selectByExample(payawayExample);
    }

}
