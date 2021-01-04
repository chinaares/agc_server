package com.lanjing.wallet.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Feedback {
    private Integer fid;

    private String username;

    private String userkey;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private Integer state;

    private String content;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifytime;
    private String reply;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getReply() {
        return reply == null ? "":reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    private String title;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {

        this.userkey = userkey == null ? null : userkey.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content != null) {
            this.content = content.trim();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}