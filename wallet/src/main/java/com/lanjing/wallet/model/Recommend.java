package com.lanjing.wallet.model;

import java.util.Date;

public class Recommend {
    private Integer fid;

    private Integer lev;

    private String userkey1;

    private String userkey2;

    private Integer num;

    private Integer isnode;

    public Integer getIsNode() {
        return isnode;
    }

    public void setIsNode(Integer isNode) {
        this.isnode = isNode;
    }

    private Integer usergrede;

    private String coverCode;

    private Double userlocknum;

    private Double yesterdayrelease;

    private Integer downlocknum;

    private Integer nodenum;

    private Double downrelease;

    private Double ztdownrelease;

    private Double teamreward;

    private Double ztreward;
    
    private Date createTime;

    public Double getZtdownrelease() {
        return ztdownrelease;
    }

    public void setZtdownrelease(Double ztdownrelease) {
        this.ztdownrelease = ztdownrelease;
    }

    public Double getTeamreward() {
        return teamreward;
    }

    public void setTeamreward(Double teamreward) {
        this.teamreward = teamreward;
    }

    public Double getZtreward() {
        return ztreward;
    }

    public void setZtreward(Double ztreward) {
        this.ztreward = ztreward;
    }

    public Integer getDownlocknum() {
        return downlocknum;
    }

    public void setDownlocknum(Integer downlocknum) {
        this.downlocknum = downlocknum;
    }

    public Integer getNodenum() {
        return nodenum;
    }

    public void setNodenum(Integer nodenum) {
        this.nodenum = nodenum;
    }

    public Double getDownrelease() {
        return downrelease;
    }

    public void setDownrelease(Double downrelease) {
        this.downrelease = downrelease;
    }

    public Double getUserlocknum() {
        return userlocknum;
    }

    public void setUserlocknum(Double userlocknum) {
        this.userlocknum = userlocknum;
    }

    public Double getYesterdayrelease() {
        return yesterdayrelease;
    }

    public void setYesterdayrelease(Double yesterdayrelease) {
        this.yesterdayrelease = yesterdayrelease;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public String getUserkey1() {
        return userkey1;
    }

    public void setUserkey1(String userkey1) {
        this.userkey1 = userkey1 == null ? null : userkey1.trim();
    }

    public String getUserkey2() {
        return userkey2;
    }

    public void setUserkey2(String userkey2) {
        this.userkey2 = userkey2 == null ? null : userkey2.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUsergrede() {
        if(usergrede == null){
            return 0;
        }
        return usergrede;
    }

    public void setUsergrede(Integer usergrede) {
        this.usergrede = usergrede;
    }

    public String getCoverCode() {
        return coverCode;
    }

    public void setCoverCode(String coverCode) {
        this.coverCode = coverCode == null ? null : coverCode.trim();
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}