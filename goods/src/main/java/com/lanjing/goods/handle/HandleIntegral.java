package com.lanjing.goods.handle;

import com.lanjing.goods.dao.ParametersMapper;
import com.lanjing.goods.model.ParametersWithBLOBs;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

//处理购物积分的逻辑
@Component
public class HandleIntegral {

    @Resource
    ParametersMapper parametersMapper;

    public BigDecimal getIntegralNumber() {
        ParametersWithBLOBs shopIntegralNum = parametersMapper.selectByKey("shopIntegralNum");
        if (shopIntegralNum == null) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(shopIntegralNum.getKeyvalue());
        }
    }
}
