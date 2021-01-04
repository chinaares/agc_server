package com.lanjing.wallet.util;

public class Quotation {

    /** 日K线 开盘价  */
    private double open;
    /** 日K线 收盘价  */
    private double close;
    /** 日K线 最低价  */
    private double low;
    /** 日K线 最高价  */
    private double high;
    /** 24小时成交量  */
    private double amount;
    /** 24小时成交笔数  */
    private int count;
    /** 24小时成交额  */
    private double vol;
    /** 交易对  btcusdt   ethusdt   eosusdt  */
    private String symbol;

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Quotation() {
    }
}
