package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.service.PhoneCodeService;
import com.lanjing.wallet.util.SMSUtil;
import com.lanjing.wallet.dao.PhoneCodeMapper;
import com.lanjing.wallet.model.PhoneCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("PhoneCodeService")
public class PhoneCodeServiceImpl implements PhoneCodeService {

    @Autowired
    private PhoneCodeMapper phoneCodeMapper;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    public int insertSelective(PhoneCode record) {
        return 0;
    }

    @Override
    public int sendCode(String phonenum) {
        PhoneCode phoneCode = new PhoneCode();
        phoneCode.setPhonenum(phonenum);
        int code =(int)((Math.random()*(9999-1000+1))+1000);
        phoneCode.setCode(code+"");
        phoneCode.setCreatetime(new Date());
        phoneCode.setState(0);
        JSONObject json = null;
        /*if(phonenum.startsWith("86")){
            json = SMSUtil.sendCode(phonenum.substring(2),code+"");
        }else{
            json = SMSUtil.sendCode2(phonenum,code+"");
        }*/
        json = SMSUtil.ypsendCode(phonenum,code+"");
        if(json != null){
            if("0".equals(json.getString("code"))){
                return phoneCodeMapper.insertSelective(phoneCode);
            }else {
            	System.err.println("短信发送失败:"+json);
            }
        }
        System.err.println("短信发送失败:"+json);
        return 0;
    }

    @Override
    public int sendmsg(String phonenum, String text) {
        return 0;
    }

    @Override
    public PhoneCode selectByPrimaryKey(Integer fid) {
        return null;
    }

    @Override
    public PhoneCode selectByUserKey(Integer fid) {
        return null;
    }

    @Override
    public int selectByCode(String phonenum, String code) {
        PhoneCode phoneCode = phoneCodeMapper.selectByCode(phonenum,code);
        if(phoneCode == null){
            return 0;
        }else if((new Date().getTime())-phoneCode.getCreatetime().getTime()>=120000){
            return 0;
        }
        phoneCode.setState(1);
        phoneCodeMapper.updateByPrimaryKeySelective(phoneCode);
        return 1;
    }

    @Override
    public int checkByCode(String phonenum, String code) {
        PhoneCode phoneCode = phoneCodeMapper.selectByCode(phonenum,code);
        if(phoneCode == null){
            return 0;
        }else if((new Date().getTime())-phoneCode.getCreatetime().getTime()>=120000){
            return 0;
        }
        return 1;
    }

    @Override
    public int expireByCode(String phonenum, String code) {
        PhoneCode phoneCode = phoneCodeMapper.selectByCode(phonenum,code);
        phoneCode.setState(1);
       return phoneCodeMapper.updateByPrimaryKeySelective(phoneCode);
    }


    @Override
    public int updateByPrimaryKeySelective(PhoneCode record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PhoneCode record) {
        return 0;
    }

    @Override
    public int selectByPhone(String phonenum) {
        return phoneCodeMapper.selectByPhone(phonenum);
    }
}
