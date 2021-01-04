package com.lanjing.goods.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-21 16:34
 * @version version 1.0.0
 */
public class MallWalletExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MallWalletExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(Long value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Long value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Long value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Long value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Long value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Long> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Long> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Long value1, Long value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Long value1, Long value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCoinIdIsNull() {
            addCriterion("coin_id is null");
            return (Criteria) this;
        }

        public Criteria andCoinIdIsNotNull() {
            addCriterion("coin_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoinIdEqualTo(Integer value) {
            addCriterion("coin_id =", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdNotEqualTo(Integer value) {
            addCriterion("coin_id <>", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdGreaterThan(Integer value) {
            addCriterion("coin_id >", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coin_id >=", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdLessThan(Integer value) {
            addCriterion("coin_id <", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdLessThanOrEqualTo(Integer value) {
            addCriterion("coin_id <=", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdIn(List<Integer> values) {
            addCriterion("coin_id in", values, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdNotIn(List<Integer> values) {
            addCriterion("coin_id not in", values, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdBetween(Integer value1, Integer value2) {
            addCriterion("coin_id between", value1, value2, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coin_id not between", value1, value2, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinTypeIsNull() {
            addCriterion("coin_type is null");
            return (Criteria) this;
        }

        public Criteria andCoinTypeIsNotNull() {
            addCriterion("coin_type is not null");
            return (Criteria) this;
        }

        public Criteria andCoinTypeEqualTo(String value) {
            addCriterion("coin_type =", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeNotEqualTo(String value) {
            addCriterion("coin_type <>", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeGreaterThan(String value) {
            addCriterion("coin_type >", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeGreaterThanOrEqualTo(String value) {
            addCriterion("coin_type >=", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeLessThan(String value) {
            addCriterion("coin_type <", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeLessThanOrEqualTo(String value) {
            addCriterion("coin_type <=", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeLike(String value) {
            addCriterion("coin_type like", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeNotLike(String value) {
            addCriterion("coin_type not like", value, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeIn(List<String> values) {
            addCriterion("coin_type in", values, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeNotIn(List<String> values) {
            addCriterion("coin_type not in", values, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeBetween(String value1, String value2) {
            addCriterion("coin_type between", value1, value2, "coinType");
            return (Criteria) this;
        }

        public Criteria andCoinTypeNotBetween(String value1, String value2) {
            addCriterion("coin_type not between", value1, value2, "coinType");
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

        public Criteria andFrozenNumIsNull() {
            addCriterion("frozen_num is null");
            return (Criteria) this;
        }

        public Criteria andFrozenNumIsNotNull() {
            addCriterion("frozen_num is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenNumEqualTo(BigDecimal value) {
            addCriterion("frozen_num =", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumNotEqualTo(BigDecimal value) {
            addCriterion("frozen_num <>", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumGreaterThan(BigDecimal value) {
            addCriterion("frozen_num >", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_num >=", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumLessThan(BigDecimal value) {
            addCriterion("frozen_num <", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_num <=", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumIn(List<BigDecimal> values) {
            addCriterion("frozen_num in", values, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumNotIn(List<BigDecimal> values) {
            addCriterion("frozen_num not in", values, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_num between", value1, value2, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_num not between", value1, value2, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andLockNumIsNull() {
            addCriterion("lock_num is null");
            return (Criteria) this;
        }

        public Criteria andLockNumIsNotNull() {
            addCriterion("lock_num is not null");
            return (Criteria) this;
        }

        public Criteria andLockNumEqualTo(BigDecimal value) {
            addCriterion("lock_num =", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumNotEqualTo(BigDecimal value) {
            addCriterion("lock_num <>", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumGreaterThan(BigDecimal value) {
            addCriterion("lock_num >", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lock_num >=", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumLessThan(BigDecimal value) {
            addCriterion("lock_num <", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lock_num <=", value, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumIn(List<BigDecimal> values) {
            addCriterion("lock_num in", values, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumNotIn(List<BigDecimal> values) {
            addCriterion("lock_num not in", values, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lock_num between", value1, value2, "lockNum");
            return (Criteria) this;
        }

        public Criteria andLockNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lock_num not between", value1, value2, "lockNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumIsNull() {
            addCriterion("shift_num is null");
            return (Criteria) this;
        }

        public Criteria andShiftNumIsNotNull() {
            addCriterion("shift_num is not null");
            return (Criteria) this;
        }

        public Criteria andShiftNumEqualTo(BigDecimal value) {
            addCriterion("shift_num =", value, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumNotEqualTo(BigDecimal value) {
            addCriterion("shift_num <>", value, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumGreaterThan(BigDecimal value) {
            addCriterion("shift_num >", value, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shift_num >=", value, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumLessThan(BigDecimal value) {
            addCriterion("shift_num <", value, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shift_num <=", value, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumIn(List<BigDecimal> values) {
            addCriterion("shift_num in", values, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumNotIn(List<BigDecimal> values) {
            addCriterion("shift_num not in", values, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shift_num between", value1, value2, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andShiftNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shift_num not between", value1, value2, "shiftNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumIsNull() {
            addCriterion("release_num is null");
            return (Criteria) this;
        }

        public Criteria andReleaseNumIsNotNull() {
            addCriterion("release_num is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseNumEqualTo(BigDecimal value) {
            addCriterion("release_num =", value, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumNotEqualTo(BigDecimal value) {
            addCriterion("release_num <>", value, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumGreaterThan(BigDecimal value) {
            addCriterion("release_num >", value, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("release_num >=", value, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumLessThan(BigDecimal value) {
            addCriterion("release_num <", value, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("release_num <=", value, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumIn(List<BigDecimal> values) {
            addCriterion("release_num in", values, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumNotIn(List<BigDecimal> values) {
            addCriterion("release_num not in", values, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("release_num between", value1, value2, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andReleaseNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("release_num not between", value1, value2, "releaseNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumIsNull() {
            addCriterion("quota_num is null");
            return (Criteria) this;
        }

        public Criteria andQuotaNumIsNotNull() {
            addCriterion("quota_num is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaNumEqualTo(BigDecimal value) {
            addCriterion("quota_num =", value, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumNotEqualTo(BigDecimal value) {
            addCriterion("quota_num <>", value, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumGreaterThan(BigDecimal value) {
            addCriterion("quota_num >", value, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quota_num >=", value, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumLessThan(BigDecimal value) {
            addCriterion("quota_num <", value, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quota_num <=", value, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumIn(List<BigDecimal> values) {
            addCriterion("quota_num in", values, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumNotIn(List<BigDecimal> values) {
            addCriterion("quota_num not in", values, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quota_num between", value1, value2, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andQuotaNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quota_num not between", value1, value2, "quotaNum");
            return (Criteria) this;
        }

        public Criteria andLockFinancesIsNull() {
            addCriterion("lock_finances is null");
            return (Criteria) this;
        }

        public Criteria andLockFinancesIsNotNull() {
            addCriterion("lock_finances is not null");
            return (Criteria) this;
        }

        public Criteria andLockFinancesEqualTo(BigDecimal value) {
            addCriterion("lock_finances =", value, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesNotEqualTo(BigDecimal value) {
            addCriterion("lock_finances <>", value, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesGreaterThan(BigDecimal value) {
            addCriterion("lock_finances >", value, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lock_finances >=", value, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesLessThan(BigDecimal value) {
            addCriterion("lock_finances <", value, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lock_finances <=", value, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesIn(List<BigDecimal> values) {
            addCriterion("lock_finances in", values, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesNotIn(List<BigDecimal> values) {
            addCriterion("lock_finances not in", values, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lock_finances between", value1, value2, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andLockFinancesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lock_finances not between", value1, value2, "lockFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesIsNull() {
            addCriterion("release_finances is null");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesIsNotNull() {
            addCriterion("release_finances is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesEqualTo(BigDecimal value) {
            addCriterion("release_finances =", value, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesNotEqualTo(BigDecimal value) {
            addCriterion("release_finances <>", value, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesGreaterThan(BigDecimal value) {
            addCriterion("release_finances >", value, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("release_finances >=", value, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesLessThan(BigDecimal value) {
            addCriterion("release_finances <", value, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("release_finances <=", value, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesIn(List<BigDecimal> values) {
            addCriterion("release_finances in", values, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesNotIn(List<BigDecimal> values) {
            addCriterion("release_finances not in", values, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("release_finances between", value1, value2, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andReleaseFinancesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("release_finances not between", value1, value2, "releaseFinances");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
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