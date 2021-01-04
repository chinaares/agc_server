package com.lanjing.wallet.util;

import java.util.List;

public class ResultVO {

    private String status;
    private long ts;
    private List<Quotation> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public List<Quotation> getData() {
        return data;
    }

    public void setData(List<Quotation> data) {
        this.data = data;
    }

    public ResultVO() {
    }
}
