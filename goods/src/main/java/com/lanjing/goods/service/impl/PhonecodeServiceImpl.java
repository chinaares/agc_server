package com.lanjing.goods.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.lanjing.goods.model.Phonecode;
import com.lanjing.goods.dao.PhonecodeMapper;
import com.lanjing.goods.model.PhonecodeExample;
import com.lanjing.goods.service.PhonecodeService;
/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-22 16:56
 * @version version 1.0.0
 */
@Service
public class PhonecodeServiceImpl implements PhonecodeService{

    @Resource
    private PhonecodeMapper phonecodeMapper;

    @Override
    public long countByExample(PhonecodeExample example) {
        return phonecodeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(PhonecodeExample example) {
        return phonecodeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return phonecodeMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int insert(Phonecode record) {
        return phonecodeMapper.insert(record);
    }

    @Override
    public int insertSelective(Phonecode record) {
        return phonecodeMapper.insertSelective(record);
    }

    @Override
    public List<Phonecode> selectByExample(PhonecodeExample example) {
        return phonecodeMapper.selectByExample(example);
    }

    @Override
    public Phonecode selectByPrimaryKey(Integer fid) {
        return phonecodeMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int updateByExampleSelective(Phonecode record,PhonecodeExample example) {
        return phonecodeMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Phonecode record,PhonecodeExample example) {
        return phonecodeMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Phonecode record) {
        return phonecodeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Phonecode record) {
        return phonecodeMapper.updateByPrimaryKey(record);
    }
    @Override
    public int selectByCode(String phonenum, String code) {
        Phonecode phoneCode = phonecodeMapper.selectByCode(phonenum,code);
        if(phoneCode == null){
            return 0;
        }else if((new Date().getTime())-phoneCode.getCreatetime().getTime()>=120000){
            return 0;
        }
        phoneCode.setState(1);
        phonecodeMapper.updateByPrimaryKeySelective(phoneCode);
        return 1;
    }

}
