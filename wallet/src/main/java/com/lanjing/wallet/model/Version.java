package com.lanjing.wallet.model;

public class Version {
    private int id;// int(8) NOT NULL AUTO_INCREMENT COMMENT '版本号',
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    private String remarks;// varchar(255) DEFAULT NULL COMMENT '备注',
    private String url;// text COMMENT '下载地址',
    private String versionname;// varchar(255) DEFAULT NULL COMMENT '版本名',
    private String versioncode;// varchar(255) DEFAULT NULL COMMENT '版本号',


}
