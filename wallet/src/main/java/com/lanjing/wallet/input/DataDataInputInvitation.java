package com.lanjing.wallet.input;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.Recommend;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.service.RecommendService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataDataInputInvitation {

    @Resource
    UsersMapper usersMapper;

    @Resource
    private addree_userMapper addressDao;

    @Resource
    CoinsMapper coinsMapper;

    @Resource
    private WelletsMapper welletsMapper;

    @Resource
    private RecommendMapper recommendMapper;

    //获取全部用户
    List<Users> usersList;

    public void startInvitation() {
        //查询邀请关系
        String path = DataDataInput.class.getClassLoader().getResource("usersInvitation.json").getPath();
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        JSONObject jsonObject = jobj.getJSONObject("data");
        InvitationBean invitationBean = new InvitationBean();
        Long id = jsonObject.getLong("id");
        Long pid = jsonObject.getLong("pid");
        String name = jsonObject.getString("name");
        String title = jsonObject.getString("title");
        invitationBean.setId(id);
        invitationBean.setPid(pid);
        invitationBean.setName(name);
        invitationBean.setTitle(title);
        List<InvitationBean> invitationBeanList = new ArrayList<>();
        JSONArray childrenJsonArray = jsonObject.getJSONArray("children");
        if (childrenJsonArray.size() > 0) {
            for (int i = 0; i < childrenJsonArray.size(); i++) {
                invitationBeanList.add(get(childrenJsonArray.getJSONObject(i)));
            }
        }
        invitationBean.setChildren(invitationBeanList);

        /**
         * A 邀请 B  B注册人  A 邀请码
         */

        //获取全部用户
        usersList = usersMapper.selectAll();

        String userName = getUserName(invitationBean.getName());
        Users users = usersList.stream().filter(q -> q.getPhonenum().equals(userName)).findAny().get();

        for (InvitationBean invitationBean1 : invitationBean.getChildren()) {
            registerQuery(invitationBean1, users.getKeyes());
        }
    }

    private void registerQuery(InvitationBean invitationBean, String invitacode) {
        String userName = getUserName(invitationBean.getName());
        Optional<Users> any = usersList.stream().filter(q -> q.getPhonenum().equals(userName)).findAny();
        if (any.isPresent()) {
            Users users = any.get();
            register(users.getKeyes(), invitacode);
            List<InvitationBean> children = invitationBean.getChildren();
            if (children.size() > 0) {
                for (InvitationBean invitationBean2 : children) {
                    registerQuery(invitationBean2, users.getKeyes());
                }
            }
        }
    }

    private void register(String key, String invitacode) {
        Recommend recommend = new Recommend();
        recommend.setUserkey1(key);//注册的人
        recommend.setUserkey2(invitacode);//邀请码
        recommendMapper.insertSelective(recommend);
        System.out.println("注册人：" + key + "-" + "邀请码：" + invitacode);
    }

    public String getUserName(String name) {
        return name.substring(name.indexOf("(") + 1, name.indexOf(")"));
    }

    public InvitationBean get(JSONObject jsonObject) {
        InvitationBean invitationBean = new InvitationBean();
        Long id = jsonObject.getLong("id");
        Long pid = jsonObject.getLong("pid");
        String name = jsonObject.getString("name");
        String title = jsonObject.getString("title");
        invitationBean.setId(id);
        invitationBean.setPid(pid);
        invitationBean.setName(name);
        invitationBean.setTitle(title);

        List<InvitationBean> invitationBeanList = new ArrayList<>();
        JSONArray childrenJsonArray = jsonObject.getJSONArray("children");
        if (childrenJsonArray.size() > 0) {
            for (int i = 0; i < childrenJsonArray.size(); i++) {
                invitationBeanList.add(get(childrenJsonArray.getJSONObject(i)));
            }
        }
        invitationBean.setChildren(invitationBeanList);
        return invitationBean;
    }

    //读取json文件
    public String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
