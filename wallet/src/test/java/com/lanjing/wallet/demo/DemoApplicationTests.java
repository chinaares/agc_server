//package com.lanjing.wallet.demo;
//
//import com.alibaba.fastjson.JSONObject;
//import com.jester.util.utils.PushUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DemoApplicationTests {
//    @Autowired
//    PushUtil pushUtil;
//
//    @Test
//    public void timer() {
//        //定时通知
//        Long timeStamp = System.currentTimeMillis()+1000*6;  //获取当前时间戳
//        pushUtil.timer( "测试通知", "通知1","hello",new Date(timeStamp));
//
//    }
//
//    @Test
//    public void aliasPush() {
//        //通过别名推送
//        JSONObject param=new JSONObject();
//        param.put("param","{'key','value'}");
//        param.put("type",1);
//        pushUtil.aliasPush( "json", "通知标题","通知内容",param.toJSONString());
//
//    }
//}
