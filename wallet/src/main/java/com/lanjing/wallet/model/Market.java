package com.lanjing.wallet.model;

import java.math.BigDecimal;

public class Market {
    private Integer fid;

    private String symbol;

    private BigDecimal amount;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal price;

    private BigDecimal cnyprice;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCnyprice() {
        return cnyprice;
    }

    public void setCnyprice(BigDecimal cnyprice) {
        this.cnyprice = cnyprice;
    }
}