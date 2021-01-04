package com.lanjing.wallet.model;

public class addree_user {
    private Integer fid;

    private String userkey;

    private String help;

    private String adress;

    private String keyes;

    private Integer wid;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey == null ? null : userkey.trim();
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help == null ? null : help.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getKeyes() {
        return keyes;
    }

    public void setKeyes(String keyes) {
        this.keyes = keyes == null ? null : keyes.trim();
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }
}