package com.lanjing.wallet.model.po;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminReward {
    private Integer id;
    /** 手机号码 */
    private String phone;
    /** 注册奖励 */
    private Double regGift;
    /** 首次ETH理财*/
    private Double principal;
    /** 理财赠送 */
    private Double income;
    /** 直推人数 */
    private Integer spreads;
    /** 推广奖励 */
    private Double bonus;
    /** 团队人数 */
    private Integer teams;
    /** 团队奖励 */
    private Double prize;
    /** 累计释放 */
    private Double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRegGift() {
        return regGift == null ? 0d : regGift;
    }

    public void setRegGift(Double regGift) {
        this.regGift = regGift;
    }

    public Double getPrincipal() {
        return principal == null ? 0d : principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public Double getIncome() {
        return income == null ? 0d : income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getSpreads() {
        return spreads;
    }

    public void setSpreads(Integer spreads) {
        this.spreads = spreads;
    }

    public Double getBonus() {
        return bonus == null ? 0d : bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Integer getTeams() {
        return teams;
    }

    public void setTeams(Integer teams) {
        this.teams = teams > 0 ? teams - 1 : 0;
    }

    public Double getPrize() {
        return prize == null ? 0d : prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public Double getTotal() {
        return total == null ? 0d : total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

