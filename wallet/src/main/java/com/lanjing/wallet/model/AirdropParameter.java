package com.lanjing.wallet.model;

public class AirdropParameter {
    private Integer id;

    private Double min;

    private Double max;

    private Double amount;

    private Double dailyRelease;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDailyRelease() {
        return dailyRelease;
    }

    public void setDailyRelease(Double dailyRelease) {
        this.dailyRelease = dailyRelease;
    }
}