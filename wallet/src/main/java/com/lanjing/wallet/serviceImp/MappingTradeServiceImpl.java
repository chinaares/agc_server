package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.service.MappingTradeService;
import com.lanjing.wallet.dao.MappingTradeMapper;
import com.lanjing.wallet.model.MappingTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MappingTradeService")
public class MappingTradeServiceImpl implements MappingTradeService {

    @Autowired
    private MappingTradeMapper mappingTradeMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    public int insert(MappingTrade record) {
        return 0;
    }

    @Override
    public int insertSelective(MappingTrade record) {
        return mappingTradeMapper.insertSelective(record);
    }

    @Override
    public MappingTrade selectByPrimaryKey(Integer fid) {
        return mappingTradeMapper.selectByPrimaryKey(fid);
    }

    @Override
    public List<MappingTrade> selectBy(MappingTrade record,Integer page,Integer size) {
        if(page == null){
            page = 1;
        }
        if(size == null){
            size = 1;
        }
        int begin = (page-1)*size;
        return mappingTradeMapper.selectBy(record,begin,size);
    }

    @Override
    public int updateByPrimaryKeySelective(MappingTrade record) {
        return mappingTradeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MappingTrade record) {
        return 0;
    }

    @Override
    public List<MappingTrade> selectByUserKey(Integer symbol, String user, Integer page, Integer size) {
        if(page == null){
            page = 1;
        }
        if(size == null){
            size = 5;
        }
        int begin = (page-1)*size;
        return mappingTradeMapper.selectByUserKey(1,user,begin,size);
    }
}
