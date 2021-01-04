package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-08-14 14:39
 * @version version 1.0.0
 */
public class AirdropListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AirdropListExample() {
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

        public Criteria andAIdIsNull() {
            addCriterion("a_id is null");
            return (Criteria) this;
        }

        public Criteria andAIdIsNotNull() {
            addCriterion("a_id is not null");
            return (Criteria) this;
        }

        public Criteria andAIdEqualTo(Integer value) {
            addCriterion("a_id =", value, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdNotEqualTo(Integer value) {
            addCriterion("a_id <>", value, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdGreaterThan(Integer value) {
            addCriterion("a_id >", value, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a_id >=", value, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdLessThan(Integer value) {
            addCriterion("a_id <", value, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdLessThanOrEqualTo(Integer value) {
            addCriterion("a_id <=", value, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdIn(List<Integer> values) {
            addCriterion("a_id in", values, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdNotIn(List<Integer> values) {
            addCriterion("a_id not in", values, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdBetween(Integer value1, Integer value2) {
            addCriterion("a_id between", value1, value2, "aId");
            return (Criteria) this;
        }

        public Criteria andAIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a_id not between", value1, value2, "aId");
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

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDaysIsNull() {
            addCriterion("`days` is null");
            return (Criteria) this;
        }

        public Criteria andDaysIsNotNull() {
            addCriterion("`days` is not null");
            return (Criteria) this;
        }

        public Criteria andDaysEqualTo(Integer value) {
            addCriterion("`days` =", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotEqualTo(Integer value) {
            addCriterion("`days` <>", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThan(Integer value) {
            addCriterion("`days` >", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("`days` >=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThan(Integer value) {
            addCriterion("`days` <", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThanOrEqualTo(Integer value) {
            addCriterion("`days` <=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysIn(List<Integer> values) {
            addCriterion("`days` in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotIn(List<Integer> values) {
            addCriterion("`days` not in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysBetween(Integer value1, Integer value2) {
            addCriterion("`days` between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("`days` not between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andCycleIsNull() {
            addCriterion("`cycle` is null");
            return (Criteria) this;
        }

        public Criteria andCycleIsNotNull() {
            addCriterion("`cycle` is not null");
            return (Criteria) this;
        }

        public Criteria andCycleEqualTo(Integer value) {
            addCriterion("`cycle` =", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotEqualTo(Integer value) {
            addCriterion("`cycle` <>", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThan(Integer value) {
            addCriterion("`cycle` >", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThanOrEqualTo(Integer value) {
            addCriterion("`cycle` >=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThan(Integer value) {
            addCriterion("`cycle` <", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThanOrEqualTo(Integer value) {
            addCriterion("`cycle` <=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleIn(List<Integer> values) {
            addCriterion("`cycle` in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotIn(List<Integer> values) {
            addCriterion("`cycle` not in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleBetween(Integer value1, Integer value2) {
            addCriterion("`cycle` between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotBetween(Integer value1, Integer value2) {
            addCriterion("`cycle` not between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcIsNull() {
            addCriterion("freeze_btc is null");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcIsNotNull() {
            addCriterion("freeze_btc is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcEqualTo(BigDecimal value) {
            addCriterion("freeze_btc =", value, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcNotEqualTo(BigDecimal value) {
            addCriterion("freeze_btc <>", value, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcGreaterThan(BigDecimal value) {
            addCriterion("freeze_btc >", value, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_btc >=", value, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcLessThan(BigDecimal value) {
            addCriterion("freeze_btc <", value, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_btc <=", value, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcIn(List<BigDecimal> values) {
            addCriterion("freeze_btc in", values, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcNotIn(List<BigDecimal> values) {
            addCriterion("freeze_btc not in", values, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_btc between", value1, value2, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeBtcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_btc not between", value1, value2, "freezeBtc");
            return (Criteria) this;
        }

        public Criteria andFreezeYybIsNull() {
            addCriterion("freeze_yyb is null");
            return (Criteria) this;
        }

        public Criteria andFreezeYybIsNotNull() {
            addCriterion("freeze_yyb is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeYybEqualTo(BigDecimal value) {
            addCriterion("freeze_yyb =", value, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybNotEqualTo(BigDecimal value) {
            addCriterion("freeze_yyb <>", value, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybGreaterThan(BigDecimal value) {
            addCriterion("freeze_yyb >", value, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_yyb >=", value, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybLessThan(BigDecimal value) {
            addCriterion("freeze_yyb <", value, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_yyb <=", value, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybIn(List<BigDecimal> values) {
            addCriterion("freeze_yyb in", values, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybNotIn(List<BigDecimal> values) {
            addCriterion("freeze_yyb not in", values, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_yyb between", value1, value2, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreezeYybNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_yyb not between", value1, value2, "freezeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeBtcIsNull() {
            addCriterion("free_btc is null");
            return (Criteria) this;
        }

        public Criteria andFreeBtcIsNotNull() {
            addCriterion("free_btc is not null");
            return (Criteria) this;
        }

        public Criteria andFreeBtcEqualTo(BigDecimal value) {
            addCriterion("free_btc =", value, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcNotEqualTo(BigDecimal value) {
            addCriterion("free_btc <>", value, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcGreaterThan(BigDecimal value) {
            addCriterion("free_btc >", value, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("free_btc >=", value, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcLessThan(BigDecimal value) {
            addCriterion("free_btc <", value, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("free_btc <=", value, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcIn(List<BigDecimal> values) {
            addCriterion("free_btc in", values, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcNotIn(List<BigDecimal> values) {
            addCriterion("free_btc not in", values, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("free_btc between", value1, value2, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeBtcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("free_btc not between", value1, value2, "freeBtc");
            return (Criteria) this;
        }

        public Criteria andFreeYybIsNull() {
            addCriterion("free_yyb is null");
            return (Criteria) this;
        }

        public Criteria andFreeYybIsNotNull() {
            addCriterion("free_yyb is not null");
            return (Criteria) this;
        }

        public Criteria andFreeYybEqualTo(BigDecimal value) {
            addCriterion("free_yyb =", value, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybNotEqualTo(BigDecimal value) {
            addCriterion("free_yyb <>", value, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybGreaterThan(BigDecimal value) {
            addCriterion("free_yyb >", value, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("free_yyb >=", value, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybLessThan(BigDecimal value) {
            addCriterion("free_yyb <", value, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybLessThanOrEqualTo(BigDecimal value) {
            addCriterion("free_yyb <=", value, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybIn(List<BigDecimal> values) {
            addCriterion("free_yyb in", values, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybNotIn(List<BigDecimal> values) {
            addCriterion("free_yyb not in", values, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("free_yyb between", value1, value2, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreeYybNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("free_yyb not between", value1, value2, "freeYyb");
            return (Criteria) this;
        }

        public Criteria andFreedBtcIsNull() {
            addCriterion("freed_btc is null");
            return (Criteria) this;
        }

        public Criteria andFreedBtcIsNotNull() {
            addCriterion("freed_btc is not null");
            return (Criteria) this;
        }

        public Criteria andFreedBtcEqualTo(BigDecimal value) {
            addCriterion("freed_btc =", value, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcNotEqualTo(BigDecimal value) {
            addCriterion("freed_btc <>", value, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcGreaterThan(BigDecimal value) {
            addCriterion("freed_btc >", value, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freed_btc >=", value, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcLessThan(BigDecimal value) {
            addCriterion("freed_btc <", value, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freed_btc <=", value, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcIn(List<BigDecimal> values) {
            addCriterion("freed_btc in", values, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcNotIn(List<BigDecimal> values) {
            addCriterion("freed_btc not in", values, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freed_btc between", value1, value2, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedBtcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freed_btc not between", value1, value2, "freedBtc");
            return (Criteria) this;
        }

        public Criteria andFreedYybIsNull() {
            addCriterion("freed_yyb is null");
            return (Criteria) this;
        }

        public Criteria andFreedYybIsNotNull() {
            addCriterion("freed_yyb is not null");
            return (Criteria) this;
        }

        public Criteria andFreedYybEqualTo(BigDecimal value) {
            addCriterion("freed_yyb =", value, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybNotEqualTo(BigDecimal value) {
            addCriterion("freed_yyb <>", value, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybGreaterThan(BigDecimal value) {
            addCriterion("freed_yyb >", value, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freed_yyb >=", value, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybLessThan(BigDecimal value) {
            addCriterion("freed_yyb <", value, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freed_yyb <=", value, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybIn(List<BigDecimal> values) {
            addCriterion("freed_yyb in", values, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybNotIn(List<BigDecimal> values) {
            addCriterion("freed_yyb not in", values, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freed_yyb between", value1, value2, "freedYyb");
            return (Criteria) this;
        }

        public Criteria andFreedYybNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freed_yyb not between", value1, value2, "freedYyb");
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