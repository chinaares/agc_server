package com.lanjing.otc.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-10 16:23
 */
@Data
public class OtcOrder {
    /**
     *
     */
    private Integer id;

    /**
     * 广告id
     */
    private Integer adsId;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 广告发布者
     */
    private Integer adsUser;

    /**
     * 数量
     */
    private BigDecimal coinNum;

    /**
     * 交易用户
     */
    private Integer tradeUser;

    /**
     * 交易者联系手机号
     */
    private String tradePhone;

    /**
     * 支付方式
     */
    private Integer payaWay;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 交易金额
     */
    private BigDecimal cnyNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 广告发布者手机号
     */
    private String adphone;

    /**
     * 发布人姓名
     */
    private String adsUserName;

    /**
     * 1，买    2卖
     */
    private Integer direction;

    /**
     * 下单时间
     */
    private Date createTime;

    /**
     * 交易凭证
     */
    private String payPicture;

    /**
     * 驳回原因
     */
    private String reject;

    /**
     * 订单状态    0，待支付   1，待确认   2，交易完成     -1，取消
     */
    private Integer state;

    /**
     * 币种
     */
    private Integer coin;

    /**
     * 0，未申诉   1，申诉中   2，申诉通过    -1，申诉失败
     */
    private Integer appealStatus;

    /**
     * 申诉文字描述
     */
    private String appealText;

    /**
     * 申诉凭证
     */
    private String appealImg;

    /**
     * 申诉时间
     */
    private Date appealTime;

    /**
     *
     */
    private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdsId() {
		return adsId;
	}

	public void setAdsId(Integer adsId) {
		this.adsId = adsId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getAdsUser() {
		return adsUser;
	}

	public void setAdsUser(Integer adsUser) {
		this.adsUser = adsUser;
	}

	public BigDecimal getCoinNum() {
		return coinNum;
	}

	public void setCoinNum(BigDecimal coinNum) {
		this.coinNum = coinNum;
	}

	public Integer getTradeUser() {
		return tradeUser;
	}

	public void setTradeUser(Integer tradeUser) {
		this.tradeUser = tradeUser;
	}

	public String getTradePhone() {
		return tradePhone;
	}

	public void setTradePhone(String tradePhone) {
		this.tradePhone = tradePhone;
	}

	public Integer getPayaWay() {
		return payaWay;
	}

	public void setPayaWay(Integer payaWay) {
		this.payaWay = payaWay;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCnyNum() {
		return cnyNum;
	}

	public void setCnyNum(BigDecimal cnyNum) {
		this.cnyNum = cnyNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAdphone() {
		return adphone;
	}

	public void setAdphone(String adphone) {
		this.adphone = adphone;
	}

	public String getAdsUserName() {
		return adsUserName;
	}

	public void setAdsUserName(String adsUserName) {
		this.adsUserName = adsUserName;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPayPicture() {
		return payPicture;
	}

	public void setPayPicture(String payPicture) {
		this.payPicture = payPicture;
	}

	public String getReject() {
		return reject;
	}

	public void setReject(String reject) {
		this.reject = reject;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCoin() {
		return coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public Integer getAppealStatus() {
		return appealStatus;
	}

	public void setAppealStatus(Integer appealStatus) {
		this.appealStatus = appealStatus;
	}

	public String getAppealText() {
		return appealText;
	}

	public void setAppealText(String appealText) {
		this.appealText = appealText;
	}

	public String getAppealImg() {
		return appealImg;
	}

	public void setAppealImg(String appealImg) {
		this.appealImg = appealImg;
	}

	public Date getAppealTime() {
		return appealTime;
	}

	public void setAppealTime(Date appealTime) {
		this.appealTime = appealTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}