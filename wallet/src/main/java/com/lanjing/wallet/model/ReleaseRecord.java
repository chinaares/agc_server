package com.lanjing.wallet.model;

public class ReleaseRecord {
    private Long id;

    private String userKey;

    private Integer releaseFromCoin;

    private Double releaseFromAmount;

    private Double releaseRate;

    private Integer releaseToCoin;

    private Double releaseToAmount;

    private Integer releaseTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey == null ? null : userKey.trim();
    }

    public Integer getReleaseFromCoin() {
        return releaseFromCoin;
    }

    public void setReleaseFromCoin(Integer releaseFromCoin) {
        this.releaseFromCoin = releaseFromCoin;
    }

    public Double getReleaseFromAmount() {
        return releaseFromAmount;
    }

    public void setReleaseFromAmount(Double releaseFromAmount) {
        this.releaseFromAmount = releaseFromAmount;
    }

    public Double getReleaseRate() {
        return releaseRate;
    }

    public void setReleaseRate(Double releaseRate) {
        this.releaseRate = releaseRate;
    }

    public Integer getReleaseToCoin() {
        return releaseToCoin;
    }

    public void setReleaseToCoin(Integer releaseToCoin) {
        this.releaseToCoin = releaseToCoin;
    }

    public Double getReleaseToAmount() {
        return releaseToAmount;
    }

    public void setReleaseToAmount(Double releaseToAmount) {
        this.releaseToAmount = releaseToAmount;
    }

    public Integer getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Integer releaseTime) {
        this.releaseTime = releaseTime;
    }
}