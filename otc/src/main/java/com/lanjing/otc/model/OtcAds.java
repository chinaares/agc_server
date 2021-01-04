package com.lanjing.otc.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.BigDecimalUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 16:45
 */
@Data
public class OtcAds {
    /**
     * id
     */
    private Integer id;

    /**
     * 类型 1 买入，2卖出
     */
    private Integer type;

    /**
     * 用户ID
     */
    private Integer userKey;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 总量
     */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal num;

    /**
     * 剩余量
     */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal numBalance;

    /**
     * 价格
     */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal price;

    /**
     * 币种Id
     */
    private Integer coinId;

    /**
     * 币种名称
     */
    private String coin;

    /**
     * 最小限额
     */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal minNum;

    /**
     * 最大限额
     */
    @JsonSerialize(using = BigDecimalUtil.class)
    private BigDecimal maxNum;

    /**
     * 留言信息
     */
    private String message;

    /**
     * 状态   1，上架    0，下架
     */
    private Integer status;

    /**
     * 0，待审核    1，审核通过    -1，审核不通过
     */
    private Integer reviewStatus;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserKey() {
		return userKey;
	}

	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public BigDecimal getNumBalance() {
		return numBalance;
	}

	public void setNumBalance(BigDecimal numBalance) {
		this.numBalance = numBalance;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCoinId() {
		return coinId;
	}

	public void setCoinId(Integer coinId) {
		this.coinId = coinId;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

	public BigDecimal getMinNum() {
		return minNum;
	}

	public void setMinNum(BigDecimal minNum) {
		this.minNum = minNum;
	}

	public BigDecimal getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(BigDecimal maxNum) {
		this.maxNum = maxNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}