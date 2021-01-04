package com.lanjing.otc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 11:58
 * @version version 1.0.0
 */
public class WalletsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WalletsExample() {
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

        public Criteria andFidIsNull() {
            addCriterion("fId is null");
            return (Criteria) this;
        }

        public Criteria andFidIsNotNull() {
            addCriterion("fId is not null");
            return (Criteria) this;
        }

        public Criteria andFidEqualTo(Integer value) {
            addCriterion("fId =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(Integer value) {
            addCriterion("fId <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(Integer value) {
            addCriterion("fId >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fId >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(Integer value) {
            addCriterion("fId <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(Integer value) {
            addCriterion("fId <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<Integer> values) {
            addCriterion("fId in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<Integer> values) {
            addCriterion("fId not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(Integer value1, Integer value2) {
            addCriterion("fId between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(Integer value1, Integer value2) {
            addCriterion("fId not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andUserkeyIsNull() {
            addCriterion("userkey is null");
            return (Criteria) this;
        }

        public Criteria andUserkeyIsNotNull() {
            addCriterion("userkey is not null");
            return (Criteria) this;
        }

        public Criteria andUserkeyEqualTo(String value) {
            addCriterion("userkey =", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotEqualTo(String value) {
            addCriterion("userkey <>", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyGreaterThan(String value) {
            addCriterion("userkey >", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyGreaterThanOrEqualTo(String value) {
            addCriterion("userkey >=", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyLessThan(String value) {
            addCriterion("userkey <", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyLessThanOrEqualTo(String value) {
            addCriterion("userkey <=", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyLike(String value) {
            addCriterion("userkey like", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotLike(String value) {
            addCriterion("userkey not like", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyIn(List<String> values) {
            addCriterion("userkey in", values, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotIn(List<String> values) {
            addCriterion("userkey not in", values, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyBetween(String value1, String value2) {
            addCriterion("userkey between", value1, value2, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotBetween(String value1, String value2) {
            addCriterion("userkey not between", value1, value2, "userkey");
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

        public Criteria andCoinidIsNull() {
            addCriterion("coinId is null");
            return (Criteria) this;
        }

        public Criteria andCoinidIsNotNull() {
            addCriterion("coinId is not null");
            return (Criteria) this;
        }

        public Criteria andCoinidEqualTo(Integer value) {
            addCriterion("coinId =", value, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidNotEqualTo(Integer value) {
            addCriterion("coinId <>", value, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidGreaterThan(Integer value) {
            addCriterion("coinId >", value, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidGreaterThanOrEqualTo(Integer value) {
            addCriterion("coinId >=", value, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidLessThan(Integer value) {
            addCriterion("coinId <", value, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidLessThanOrEqualTo(Integer value) {
            addCriterion("coinId <=", value, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidIn(List<Integer> values) {
            addCriterion("coinId in", values, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidNotIn(List<Integer> values) {
            addCriterion("coinId not in", values, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidBetween(Integer value1, Integer value2) {
            addCriterion("coinId between", value1, value2, "coinid");
            return (Criteria) this;
        }

        public Criteria andCoinidNotBetween(Integer value1, Integer value2) {
            addCriterion("coinId not between", value1, value2, "coinid");
            return (Criteria) this;
        }

        public Criteria andCointypeIsNull() {
            addCriterion("cointype is null");
            return (Criteria) this;
        }

        public Criteria andCointypeIsNotNull() {
            addCriterion("cointype is not null");
            return (Criteria) this;
        }

        public Criteria andCointypeEqualTo(String value) {
            addCriterion("cointype =", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeNotEqualTo(String value) {
            addCriterion("cointype <>", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeGreaterThan(String value) {
            addCriterion("cointype >", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeGreaterThanOrEqualTo(String value) {
            addCriterion("cointype >=", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeLessThan(String value) {
            addCriterion("cointype <", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeLessThanOrEqualTo(String value) {
            addCriterion("cointype <=", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeLike(String value) {
            addCriterion("cointype like", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeNotLike(String value) {
            addCriterion("cointype not like", value, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeIn(List<String> values) {
            addCriterion("cointype in", values, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeNotIn(List<String> values) {
            addCriterion("cointype not in", values, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeBetween(String value1, String value2) {
            addCriterion("cointype between", value1, value2, "cointype");
            return (Criteria) this;
        }

        public Criteria andCointypeNotBetween(String value1, String value2) {
            addCriterion("cointype not between", value1, value2, "cointype");
            return (Criteria) this;
        }

        public Criteria andCoinnumIsNull() {
            addCriterion("coinNum is null");
            return (Criteria) this;
        }

        public Criteria andCoinnumIsNotNull() {
            addCriterion("coinNum is not null");
            return (Criteria) this;
        }

        public Criteria andCoinnumEqualTo(Double value) {
            addCriterion("coinNum =", value, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumNotEqualTo(Double value) {
            addCriterion("coinNum <>", value, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumGreaterThan(Double value) {
            addCriterion("coinNum >", value, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumGreaterThanOrEqualTo(Double value) {
            addCriterion("coinNum >=", value, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumLessThan(Double value) {
            addCriterion("coinNum <", value, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumLessThanOrEqualTo(Double value) {
            addCriterion("coinNum <=", value, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumIn(List<Double> values) {
            addCriterion("coinNum in", values, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumNotIn(List<Double> values) {
            addCriterion("coinNum not in", values, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumBetween(Double value1, Double value2) {
            addCriterion("coinNum between", value1, value2, "coinnum");
            return (Criteria) this;
        }

        public Criteria andCoinnumNotBetween(Double value1, Double value2) {
            addCriterion("coinNum not between", value1, value2, "coinnum");
            return (Criteria) this;
        }

        public Criteria andFrozennumIsNull() {
            addCriterion("frozenNum is null");
            return (Criteria) this;
        }

        public Criteria andFrozennumIsNotNull() {
            addCriterion("frozenNum is not null");
            return (Criteria) this;
        }

        public Criteria andFrozennumEqualTo(Double value) {
            addCriterion("frozenNum =", value, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumNotEqualTo(Double value) {
            addCriterion("frozenNum <>", value, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumGreaterThan(Double value) {
            addCriterion("frozenNum >", value, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumGreaterThanOrEqualTo(Double value) {
            addCriterion("frozenNum >=", value, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumLessThan(Double value) {
            addCriterion("frozenNum <", value, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumLessThanOrEqualTo(Double value) {
            addCriterion("frozenNum <=", value, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumIn(List<Double> values) {
            addCriterion("frozenNum in", values, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumNotIn(List<Double> values) {
            addCriterion("frozenNum not in", values, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumBetween(Double value1, Double value2) {
            addCriterion("frozenNum between", value1, value2, "frozennum");
            return (Criteria) this;
        }

        public Criteria andFrozennumNotBetween(Double value1, Double value2) {
            addCriterion("frozenNum not between", value1, value2, "frozennum");
            return (Criteria) this;
        }

        public Criteria andLocknumIsNull() {
            addCriterion("lockNum is null");
            return (Criteria) this;
        }

        public Criteria andLocknumIsNotNull() {
            addCriterion("lockNum is not null");
            return (Criteria) this;
        }

        public Criteria andLocknumEqualTo(Double value) {
            addCriterion("lockNum =", value, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumNotEqualTo(Double value) {
            addCriterion("lockNum <>", value, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumGreaterThan(Double value) {
            addCriterion("lockNum >", value, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumGreaterThanOrEqualTo(Double value) {
            addCriterion("lockNum >=", value, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumLessThan(Double value) {
            addCriterion("lockNum <", value, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumLessThanOrEqualTo(Double value) {
            addCriterion("lockNum <=", value, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumIn(List<Double> values) {
            addCriterion("lockNum in", values, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumNotIn(List<Double> values) {
            addCriterion("lockNum not in", values, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumBetween(Double value1, Double value2) {
            addCriterion("lockNum between", value1, value2, "locknum");
            return (Criteria) this;
        }

        public Criteria andLocknumNotBetween(Double value1, Double value2) {
            addCriterion("lockNum not between", value1, value2, "locknum");
            return (Criteria) this;
        }

        public Criteria andShiftnumIsNull() {
            addCriterion("shiftNum is null");
            return (Criteria) this;
        }

        public Criteria andShiftnumIsNotNull() {
            addCriterion("shiftNum is not null");
            return (Criteria) this;
        }

        public Criteria andShiftnumEqualTo(Double value) {
            addCriterion("shiftNum =", value, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumNotEqualTo(Double value) {
            addCriterion("shiftNum <>", value, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumGreaterThan(Double value) {
            addCriterion("shiftNum >", value, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumGreaterThanOrEqualTo(Double value) {
            addCriterion("shiftNum >=", value, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumLessThan(Double value) {
            addCriterion("shiftNum <", value, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumLessThanOrEqualTo(Double value) {
            addCriterion("shiftNum <=", value, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumIn(List<Double> values) {
            addCriterion("shiftNum in", values, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumNotIn(List<Double> values) {
            addCriterion("shiftNum not in", values, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumBetween(Double value1, Double value2) {
            addCriterion("shiftNum between", value1, value2, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andShiftnumNotBetween(Double value1, Double value2) {
            addCriterion("shiftNum not between", value1, value2, "shiftnum");
            return (Criteria) this;
        }

        public Criteria andReleasenumIsNull() {
            addCriterion("releaseNum is null");
            return (Criteria) this;
        }

        public Criteria andReleasenumIsNotNull() {
            addCriterion("releaseNum is not null");
            return (Criteria) this;
        }

        public Criteria andReleasenumEqualTo(Double value) {
            addCriterion("releaseNum =", value, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumNotEqualTo(Double value) {
            addCriterion("releaseNum <>", value, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumGreaterThan(Double value) {
            addCriterion("releaseNum >", value, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumGreaterThanOrEqualTo(Double value) {
            addCriterion("releaseNum >=", value, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumLessThan(Double value) {
            addCriterion("releaseNum <", value, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumLessThanOrEqualTo(Double value) {
            addCriterion("releaseNum <=", value, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumIn(List<Double> values) {
            addCriterion("releaseNum in", values, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumNotIn(List<Double> values) {
            addCriterion("releaseNum not in", values, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumBetween(Double value1, Double value2) {
            addCriterion("releaseNum between", value1, value2, "releasenum");
            return (Criteria) this;
        }

        public Criteria andReleasenumNotBetween(Double value1, Double value2) {
            addCriterion("releaseNum not between", value1, value2, "releasenum");
            return (Criteria) this;
        }

        public Criteria andQuotanumIsNull() {
            addCriterion("quotaNum is null");
            return (Criteria) this;
        }

        public Criteria andQuotanumIsNotNull() {
            addCriterion("quotaNum is not null");
            return (Criteria) this;
        }

        public Criteria andQuotanumEqualTo(Double value) {
            addCriterion("quotaNum =", value, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumNotEqualTo(Double value) {
            addCriterion("quotaNum <>", value, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumGreaterThan(Double value) {
            addCriterion("quotaNum >", value, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumGreaterThanOrEqualTo(Double value) {
            addCriterion("quotaNum >=", value, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumLessThan(Double value) {
            addCriterion("quotaNum <", value, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumLessThanOrEqualTo(Double value) {
            addCriterion("quotaNum <=", value, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumIn(List<Double> values) {
            addCriterion("quotaNum in", values, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumNotIn(List<Double> values) {
            addCriterion("quotaNum not in", values, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumBetween(Double value1, Double value2) {
            addCriterion("quotaNum between", value1, value2, "quotanum");
            return (Criteria) this;
        }

        public Criteria andQuotanumNotBetween(Double value1, Double value2) {
            addCriterion("quotaNum not between", value1, value2, "quotanum");
            return (Criteria) this;
        }

        public Criteria andLockfinancesIsNull() {
            addCriterion("lockfinances is null");
            return (Criteria) this;
        }

        public Criteria andLockfinancesIsNotNull() {
            addCriterion("lockfinances is not null");
            return (Criteria) this;
        }

        public Criteria andLockfinancesEqualTo(Double value) {
            addCriterion("lockfinances =", value, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesNotEqualTo(Double value) {
            addCriterion("lockfinances <>", value, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesGreaterThan(Double value) {
            addCriterion("lockfinances >", value, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesGreaterThanOrEqualTo(Double value) {
            addCriterion("lockfinances >=", value, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesLessThan(Double value) {
            addCriterion("lockfinances <", value, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesLessThanOrEqualTo(Double value) {
            addCriterion("lockfinances <=", value, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesIn(List<Double> values) {
            addCriterion("lockfinances in", values, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesNotIn(List<Double> values) {
            addCriterion("lockfinances not in", values, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesBetween(Double value1, Double value2) {
            addCriterion("lockfinances between", value1, value2, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andLockfinancesNotBetween(Double value1, Double value2) {
            addCriterion("lockfinances not between", value1, value2, "lockfinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesIsNull() {
            addCriterion("releasefinances is null");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesIsNotNull() {
            addCriterion("releasefinances is not null");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesEqualTo(Double value) {
            addCriterion("releasefinances =", value, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesNotEqualTo(Double value) {
            addCriterion("releasefinances <>", value, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesGreaterThan(Double value) {
            addCriterion("releasefinances >", value, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesGreaterThanOrEqualTo(Double value) {
            addCriterion("releasefinances >=", value, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesLessThan(Double value) {
            addCriterion("releasefinances <", value, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesLessThanOrEqualTo(Double value) {
            addCriterion("releasefinances <=", value, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesIn(List<Double> values) {
            addCriterion("releasefinances in", values, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesNotIn(List<Double> values) {
            addCriterion("releasefinances not in", values, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesBetween(Double value1, Double value2) {
            addCriterion("releasefinances between", value1, value2, "releasefinances");
            return (Criteria) this;
        }

        public Criteria andReleasefinancesNotBetween(Double value1, Double value2) {
            addCriterion("releasefinances not between", value1, value2, "releasefinances");
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