package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.MappingTradeOrderService;
import com.lanjing.wallet.dao.MappingTradeOrderMapper;
import com.lanjing.wallet.model.MappingTradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MappingTradeOrderService")
public class MappingTradeOrderServiceImpl implements MappingTradeOrderService {

    @Autowired
    private MappingTradeOrderMapper mappingTradeOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    public int insert(MappingTradeOrder record) {
        return 0;
    }

    @Override
    public int insertSelective(MappingTradeOrder record) {
        return 0;
    }

    @Override
    public MappingTradeOrder selectByPrimaryKey(Integer fid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(MappingTradeOrder record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(MappingTradeOrder record) {
        return 0;
    }

    @Override
    public List<MappingTradeOrder> selectByUserKey(String user, Integer page, Integer size) {
        if(page == null){
            page = 1;
        }
        if(size == null){
            size = 5;
        }
        int begin = (page-1)*size;
        int end = page*size;
        return mappingTradeOrderMapper.selectByUserKey(4,user, begin,end);
    }

    @Override
    public List<MappingTradeOrder> selectBy(MappingTradeOrder record, Integer page, Integer size) {
        if(page == null){
            page = 1;
        }
        if(size == null){
            size = 5;
        }
        int begin = (page-1)*size;
        int end = page*size;
        return mappingTradeOrderMapper.selectBy(record, begin,end);
    }
}
