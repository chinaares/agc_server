package com.lanjing.goods.model;

import lombok.Data;

@Data
public class Parameters {
    private Integer fid;

    private Integer type;

    private String keyname;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
    
}