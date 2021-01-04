package com.lanjing.otc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 16:23
 * @version version 1.0.0
 */
public class OtcOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OtcOrderExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAdsIdIsNull() {
            addCriterion("ads_id is null");
            return (Criteria) this;
        }

        public Criteria andAdsIdIsNotNull() {
            addCriterion("ads_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdsIdEqualTo(Integer value) {
            addCriterion("ads_id =", value, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdNotEqualTo(Integer value) {
            addCriterion("ads_id <>", value, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdGreaterThan(Integer value) {
            addCriterion("ads_id >", value, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ads_id >=", value, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdLessThan(Integer value) {
            addCriterion("ads_id <", value, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdLessThanOrEqualTo(Integer value) {
            addCriterion("ads_id <=", value, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdIn(List<Integer> values) {
            addCriterion("ads_id in", values, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdNotIn(List<Integer> values) {
            addCriterion("ads_id not in", values, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdBetween(Integer value1, Integer value2) {
            addCriterion("ads_id between", value1, value2, "adsId");
            return (Criteria) this;
        }

        public Criteria andAdsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ads_id not between", value1, value2, "adsId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andAdsUserIsNull() {
            addCriterion("ads_user is null");
            return (Criteria) this;
        }

        public Criteria andAdsUserIsNotNull() {
            addCriterion("ads_user is not null");
            return (Criteria) this;
        }

        public Criteria andAdsUserEqualTo(Integer value) {
            addCriterion("ads_user =", value, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserNotEqualTo(Integer value) {
            addCriterion("ads_user <>", value, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserGreaterThan(Integer value) {
            addCriterion("ads_user >", value, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("ads_user >=", value, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserLessThan(Integer value) {
            addCriterion("ads_user <", value, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserLessThanOrEqualTo(Integer value) {
            addCriterion("ads_user <=", value, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserIn(List<Integer> values) {
            addCriterion("ads_user in", values, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserNotIn(List<Integer> values) {
            addCriterion("ads_user not in", values, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserBetween(Integer value1, Integer value2) {
            addCriterion("ads_user between", value1, value2, "adsUser");
            return (Criteria) this;
        }

        public Criteria andAdsUserNotBetween(Integer value1, Integer value2) {
            addCriterion("ads_user not between", value1, value2, "adsUser");
            return (Criteria) this;
        }

        public Criteria andCoinNumIsNull() {
            addCriterion("coin_num is null");
            return (Criteria) this;
        }

        public Criteria andCoinNumIsNotNull() {
            addCriterion("coin_num is not null");
            return (Criteria) this;
        }

        public Criteria andCoinNumEqualTo(BigDecimal value) {
            addCriterion("coin_num =", value, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumNotEqualTo(BigDecimal value) {
            addCriterion("coin_num <>", value, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumGreaterThan(BigDecimal value) {
            addCriterion("coin_num >", value, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_num >=", value, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumLessThan(BigDecimal value) {
            addCriterion("coin_num <", value, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_num <=", value, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumIn(List<BigDecimal> values) {
            addCriterion("coin_num in", values, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumNotIn(List<BigDecimal> values) {
            addCriterion("coin_num not in", values, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_num between", value1, value2, "coinNum");
            return (Criteria) this;
        }

        public Criteria andCoinNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_num not between", value1, value2, "coinNum");
            return (Criteria) this;
        }

        public Criteria andTradeUserIsNull() {
            addCriterion("trade_user is null");
            return (Criteria) this;
        }

        public Criteria andTradeUserIsNotNull() {
            addCriterion("trade_user is not null");
            return (Criteria) this;
        }

        public Criteria andTradeUserEqualTo(Integer value) {
            addCriterion("trade_user =", value, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserNotEqualTo(Integer value) {
            addCriterion("trade_user <>", value, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserGreaterThan(Integer value) {
            addCriterion("trade_user >", value, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_user >=", value, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserLessThan(Integer value) {
            addCriterion("trade_user <", value, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserLessThanOrEqualTo(Integer value) {
            addCriterion("trade_user <=", value, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserIn(List<Integer> values) {
            addCriterion("trade_user in", values, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserNotIn(List<Integer> values) {
            addCriterion("trade_user not in", values, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserBetween(Integer value1, Integer value2) {
            addCriterion("trade_user between", value1, value2, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradeUserNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_user not between", value1, value2, "tradeUser");
            return (Criteria) this;
        }

        public Criteria andTradePhoneIsNull() {
            addCriterion("tradephone is null");
            return (Criteria) this;
        }

        public Criteria andTradePhoneIsNotNull() {
            addCriterion("tradephone is not null");
            return (Criteria) this;
        }

        public Criteria andTradePhoneEqualTo(String value) {
            addCriterion("tradephone =", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneNotEqualTo(String value) {
            addCriterion("tradephone <>", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneGreaterThan(String value) {
            addCriterion("tradephone >", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("tradephone >=", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneLessThan(String value) {
            addCriterion("tradephone <", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneLessThanOrEqualTo(String value) {
            addCriterion("tradephone <=", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneLike(String value) {
            addCriterion("tradephone like", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneNotLike(String value) {
            addCriterion("tradephone not like", value, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneIn(List<String> values) {
            addCriterion("tradephone in", values, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneNotIn(List<String> values) {
            addCriterion("tradephone not in", values, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneBetween(String value1, String value2) {
            addCriterion("tradephone between", value1, value2, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andTradePhoneNotBetween(String value1, String value2) {
            addCriterion("tradephone not between", value1, value2, "tradePhone");
            return (Criteria) this;
        }

        public Criteria andPayaWayIsNull() {
            addCriterion("paya_way is null");
            return (Criteria) this;
        }

        public Criteria andPayaWayIsNotNull() {
            addCriterion("paya_way is not null");
            return (Criteria) this;
        }

        public Criteria andPayaWayEqualTo(Integer value) {
            addCriterion("paya_way =", value, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayNotEqualTo(Integer value) {
            addCriterion("paya_way <>", value, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayGreaterThan(Integer value) {
            addCriterion("paya_way >", value, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("paya_way >=", value, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayLessThan(Integer value) {
            addCriterion("paya_way <", value, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayLessThanOrEqualTo(Integer value) {
            addCriterion("paya_way <=", value, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayIn(List<Integer> values) {
            addCriterion("paya_way in", values, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayNotIn(List<Integer> values) {
            addCriterion("paya_way not in", values, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayBetween(Integer value1, Integer value2) {
            addCriterion("paya_way between", value1, value2, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPayaWayNotBetween(Integer value1, Integer value2) {
            addCriterion("paya_way not between", value1, value2, "payaWay");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCnyNumIsNull() {
            addCriterion("cny_num is null");
            return (Criteria) this;
        }

        public Criteria andCnyNumIsNotNull() {
            addCriterion("cny_num is not null");
            return (Criteria) this;
        }

        public Criteria andCnyNumEqualTo(BigDecimal value) {
            addCriterion("cny_num =", value, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumNotEqualTo(BigDecimal value) {
            addCriterion("cny_num <>", value, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumGreaterThan(BigDecimal value) {
            addCriterion("cny_num >", value, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cny_num >=", value, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumLessThan(BigDecimal value) {
            addCriterion("cny_num <", value, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cny_num <=", value, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumIn(List<BigDecimal> values) {
            addCriterion("cny_num in", values, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumNotIn(List<BigDecimal> values) {
            addCriterion("cny_num not in", values, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cny_num between", value1, value2, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andCnyNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cny_num not between", value1, value2, "cnyNum");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andAdphoneIsNull() {
            addCriterion("adphone is null");
            return (Criteria) this;
        }

        public Criteria andAdphoneIsNotNull() {
            addCriterion("adphone is not null");
            return (Criteria) this;
        }

        public Criteria andAdphoneEqualTo(String value) {
            addCriterion("adphone =", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneNotEqualTo(String value) {
            addCriterion("adphone <>", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneGreaterThan(String value) {
            addCriterion("adphone >", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneGreaterThanOrEqualTo(String value) {
            addCriterion("adphone >=", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneLessThan(String value) {
            addCriterion("adphone <", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneLessThanOrEqualTo(String value) {
            addCriterion("adphone <=", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneLike(String value) {
            addCriterion("adphone like", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneNotLike(String value) {
            addCriterion("adphone not like", value, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneIn(List<String> values) {
            addCriterion("adphone in", values, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneNotIn(List<String> values) {
            addCriterion("adphone not in", values, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneBetween(String value1, String value2) {
            addCriterion("adphone between", value1, value2, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdphoneNotBetween(String value1, String value2) {
            addCriterion("adphone not between", value1, value2, "adphone");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameIsNull() {
            addCriterion("ads_user_name is null");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameIsNotNull() {
            addCriterion("ads_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameEqualTo(String value) {
            addCriterion("ads_user_name =", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameNotEqualTo(String value) {
            addCriterion("ads_user_name <>", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameGreaterThan(String value) {
            addCriterion("ads_user_name >", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("ads_user_name >=", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameLessThan(String value) {
            addCriterion("ads_user_name <", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameLessThanOrEqualTo(String value) {
            addCriterion("ads_user_name <=", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameLike(String value) {
            addCriterion("ads_user_name like", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameNotLike(String value) {
            addCriterion("ads_user_name not like", value, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameIn(List<String> values) {
            addCriterion("ads_user_name in", values, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameNotIn(List<String> values) {
            addCriterion("ads_user_name not in", values, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameBetween(String value1, String value2) {
            addCriterion("ads_user_name between", value1, value2, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andAdsUserNameNotBetween(String value1, String value2) {
            addCriterion("ads_user_name not between", value1, value2, "adsUserName");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNull() {
            addCriterion("direction is null");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNotNull() {
            addCriterion("direction is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionEqualTo(Integer value) {
            addCriterion("direction =", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotEqualTo(Integer value) {
            addCriterion("direction <>", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThan(Integer value) {
            addCriterion("direction >", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThanOrEqualTo(Integer value) {
            addCriterion("direction >=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThan(Integer value) {
            addCriterion("direction <", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThanOrEqualTo(Integer value) {
            addCriterion("direction <=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionIn(List<Integer> values) {
            addCriterion("direction in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotIn(List<Integer> values) {
            addCriterion("direction not in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionBetween(Integer value1, Integer value2) {
            addCriterion("direction between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotBetween(Integer value1, Integer value2) {
            addCriterion("direction not between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPayPictureIsNull() {
            addCriterion("pay_picture is null");
            return (Criteria) this;
        }

        public Criteria andPayPictureIsNotNull() {
            addCriterion("pay_picture is not null");
            return (Criteria) this;
        }

        public Criteria andPayPictureEqualTo(String value) {
            addCriterion("pay_picture =", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureNotEqualTo(String value) {
            addCriterion("pay_picture <>", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureGreaterThan(String value) {
            addCriterion("pay_picture >", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureGreaterThanOrEqualTo(String value) {
            addCriterion("pay_picture >=", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureLessThan(String value) {
            addCriterion("pay_picture <", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureLessThanOrEqualTo(String value) {
            addCriterion("pay_picture <=", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureLike(String value) {
            addCriterion("pay_picture like", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureNotLike(String value) {
            addCriterion("pay_picture not like", value, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureIn(List<String> values) {
            addCriterion("pay_picture in", values, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureNotIn(List<String> values) {
            addCriterion("pay_picture not in", values, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureBetween(String value1, String value2) {
            addCriterion("pay_picture between", value1, value2, "payPicture");
            return (Criteria) this;
        }

        public Criteria andPayPictureNotBetween(String value1, String value2) {
            addCriterion("pay_picture not between", value1, value2, "payPicture");
            return (Criteria) this;
        }

        public Criteria andAppealStatusIsNull() {
            addCriterion("appeal_status is null");
            return (Criteria) this;
        }

        public Criteria andAppealStatusIsNotNull() {
            addCriterion("appeal_status is not null");
            return (Criteria) this;
        }

        public Criteria andAppealStatusEqualTo(Integer value) {
            addCriterion("appeal_status =", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusNotEqualTo(Integer value) {
            addCriterion("appeal_status <>", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusGreaterThan(Integer value) {
            addCriterion("appeal_status >", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("appeal_status >=", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusLessThan(Integer value) {
            addCriterion("appeal_status <", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusLessThanOrEqualTo(Integer value) {
            addCriterion("appeal_status <=", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusIn(List<Integer> values) {
            addCriterion("appeal_status in", values, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusNotIn(List<Integer> values) {
            addCriterion("appeal_status not in", values, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusBetween(Integer value1, Integer value2) {
            addCriterion("appeal_status between", value1, value2, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("appeal_status not between", value1, value2, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealTextIsNull() {
            addCriterion("appeal_text is null");
            return (Criteria) this;
        }

        public Criteria andAppealTextIsNotNull() {
            addCriterion("appeal_text is not null");
            return (Criteria) this;
        }

        public Criteria andAppealTextEqualTo(String value) {
            addCriterion("appeal_text =", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextNotEqualTo(String value) {
            addCriterion("appeal_text <>", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextGreaterThan(String value) {
            addCriterion("appeal_text >", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_text >=", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextLessThan(String value) {
            addCriterion("appeal_text <", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextLessThanOrEqualTo(String value) {
            addCriterion("appeal_text <=", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextLike(String value) {
            addCriterion("appeal_text like", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextNotLike(String value) {
            addCriterion("appeal_text not like", value, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextIn(List<String> values) {
            addCriterion("appeal_text in", values, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextNotIn(List<String> values) {
            addCriterion("appeal_text not in", values, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextBetween(String value1, String value2) {
            addCriterion("appeal_text between", value1, value2, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealTextNotBetween(String value1, String value2) {
            addCriterion("appeal_text not between", value1, value2, "appealText");
            return (Criteria) this;
        }

        public Criteria andAppealImgIsNull() {
            addCriterion("appeal_img is null");
            return (Criteria) this;
        }

        public Criteria andAppealImgIsNotNull() {
            addCriterion("appeal_img is not null");
            return (Criteria) this;
        }

        public Criteria andAppealImgEqualTo(String value) {
            addCriterion("appeal_img =", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgNotEqualTo(String value) {
            addCriterion("appeal_img <>", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgGreaterThan(String value) {
            addCriterion("appeal_img >", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_img >=", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgLessThan(String value) {
            addCriterion("appeal_img <", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgLessThanOrEqualTo(String value) {
            addCriterion("appeal_img <=", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgLike(String value) {
            addCriterion("appeal_img like", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgNotLike(String value) {
            addCriterion("appeal_img not like", value, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgIn(List<String> values) {
            addCriterion("appeal_img in", values, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgNotIn(List<String> values) {
            addCriterion("appeal_img not in", values, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgBetween(String value1, String value2) {
            addCriterion("appeal_img between", value1, value2, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealImgNotBetween(String value1, String value2) {
            addCriterion("appeal_img not between", value1, value2, "appealImg");
            return (Criteria) this;
        }

        public Criteria andAppealTimeIsNull() {
            addCriterion("appeal_time is null");
            return (Criteria) this;
        }

        public Criteria andAppealTimeIsNotNull() {
            addCriterion("appeal_time is not null");
            return (Criteria) this;
        }

        public Criteria andAppealTimeEqualTo(Date value) {
            addCriterion("appeal_time =", value, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeNotEqualTo(Date value) {
            addCriterion("appeal_time <>", value, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeGreaterThan(Date value) {
            addCriterion("appeal_time >", value, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("appeal_time >=", value, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeLessThan(Date value) {
            addCriterion("appeal_time <", value, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeLessThanOrEqualTo(Date value) {
            addCriterion("appeal_time <=", value, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeIn(List<Date> values) {
            addCriterion("appeal_time in", values, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeNotIn(List<Date> values) {
            addCriterion("appeal_time not in", values, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeBetween(Date value1, Date value2) {
            addCriterion("appeal_time between", value1, value2, "appealTime");
            return (Criteria) this;
        }

        public Criteria andAppealTimeNotBetween(Date value1, Date value2) {
            addCriterion("appeal_time not between", value1, value2, "appealTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}