package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.OrdersMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.model.Orders;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.WellethistoryStateService;
import com.lanjing.wallet.dao.WellethistoryStateMapper;
import com.lanjing.wallet.model.WellethistoryState;
import com.lanjing.wallet.util.EthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("WellethistoryStateService")
@RestController
public class WellethistoryStateServiceImpl implements WellethistoryStateService {

    @Autowired
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    public int insert(WellethistoryState record) {
        return 0;
    }

    @Override
    public int insertSelective(WellethistoryState record) {
        return wellethistoryStateMapper.insertSelective(record);
    }

    @RequestMapping("/zuul/insertWellethistoryState")
    public String insertWellethistoryState(@RequestBody JSONObject param) {
        String userkey = param.getString("userkey");
        Integer coin = param.getInteger("coin");
        double num = param.getDoubleValue("num");
        Integer type = param.getInteger("type");
        Wellets wellet = welletsMapper.selectByUserandcoin(userkey, coin);
        WellethistoryState wellethistoryState = new WellethistoryState();
        wellethistoryState.setWelletid(wellet.getFid());
        if (type == 7) {
            wellethistoryState.setChangenum(BigDecimal.valueOf(num));
            wellethistoryState.setType(type);
            wellethistoryState.setRemark("otc买入");
        } else if (type == 8) {
            wellethistoryState.setChangenum(BigDecimal.valueOf(-num));
            wellethistoryState.setType(type);
            wellethistoryState.setRemark("otc卖出");
        } else if (type == 9) {
            wellethistoryState.setChangenum(BigDecimal.valueOf(num));
            wellethistoryState.setType(type);
            wellethistoryState.setRemark("otc退回");
        }
        Date time = new Date();
        wellethistoryState.setBalance(wellet.getCoinnum());
        wellethistoryState.setCreatetime(time);
        wellethistoryState.setUpdatetime(time);
        wellethistoryState.setState(1);
        wellethistoryState.setCointype(1);
        wellethistoryState.setUserid(Integer.valueOf(userkey));
        int i = wellethistoryStateMapper.insertSelective(wellethistoryState);
        if (i > 0) {
            return JSONObject.toJSONString(new ResultDTO(200, "添加成功！"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "添加失败！"));
    }

    @Override
    public WellethistoryState selectByPrimaryKey(Integer fid) {
        return wellethistoryStateMapper.selectByPrimaryKey(fid);
    }

    @Override
    public WellethistoryState selectByKey(String keyes) {
        return null;
    }

    @Override
    public List<WellethistoryState> selectByWelletId(Integer welletId, Integer type, Integer page, Integer size) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 10;
        }
        List<WellethistoryState> list = wellethistoryStateMapper.selectByWelletId(welletId, type, (page - 1) * size, page * size);
        Set<String> set = new HashSet<>();
        for (WellethistoryState wellethistoryState : list) {
            if (wellethistoryState.getType() == 2 && wellethistoryState.getKeyes() != null && !"".equals(wellethistoryState.getKeyes()) && wellethistoryState.getState() == 0) {
                set.add(wellethistoryState.getKeyes());
            }
        }
        for (String s : set) {
            //查询订单
            Orders queryorderId = ordersMapper.queryorderId(s);
            if (queryorderId != null) {
                boolean isconfirm = EthUtil.confirm(queryorderId.getBuyadress());
                if (isconfirm) {
                    List<WellethistoryState> wellethistoryStates = wellethistoryStateMapper.selectByKey2(s);
                    WellethistoryState wellethistoryState1 = wellethistoryStates.get(0);
                    WellethistoryState wellethistoryState2 = wellethistoryStates.get(1);
                    wellethistoryState1.setState(1);
                    wellethistoryState2.setState(1);
                    wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState1);
                    wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState2);
                    Orders orders = ordersMapper.queryorderId(s);
                    orders.setState(1);
                    ordersMapper.updateByPrimaryKeySelective(orders);
                }
            }
        }
        return wellethistoryStateMapper.selectByWelletId(welletId, type, (page - 1) * size, page * size);
    }

    @Override
    public int updateByPrimaryKeySelective(WellethistoryState record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(WellethistoryState record) {
        return 0;
    }

    @Override
    public List<WellethistoryState> selectBycztx(Integer welletId, Integer page, Integer size) {
        return wellethistoryStateMapper.selectBycztx(welletId, (page - 1) * size, size);
    }

    @Override
    public List<WellethistoryState> selectBytransfer(Integer welletId, Integer page, Integer size) {
        return wellethistoryStateMapper.selectBytransfer(welletId, (page - 1) * size, size);
    }

    @Override
    public int querywithdraw(Integer userid, Integer coinid) {
        return wellethistoryStateMapper.querywithdraw(userid, coinid);
    }
}
