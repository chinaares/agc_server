package com.lanjing.goods.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-09-09 17:22
 * @version version 1.0.0
 */
public class AdminExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdminExample() {
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

        public Criteria andAnameIsNull() {
            addCriterion("Aname is null");
            return (Criteria) this;
        }

        public Criteria andAnameIsNotNull() {
            addCriterion("Aname is not null");
            return (Criteria) this;
        }

        public Criteria andAnameEqualTo(String value) {
            addCriterion("Aname =", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotEqualTo(String value) {
            addCriterion("Aname <>", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThan(String value) {
            addCriterion("Aname >", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThanOrEqualTo(String value) {
            addCriterion("Aname >=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThan(String value) {
            addCriterion("Aname <", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThanOrEqualTo(String value) {
            addCriterion("Aname <=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLike(String value) {
            addCriterion("Aname like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotLike(String value) {
            addCriterion("Aname not like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameIn(List<String> values) {
            addCriterion("Aname in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotIn(List<String> values) {
            addCriterion("Aname not in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameBetween(String value1, String value2) {
            addCriterion("Aname between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotBetween(String value1, String value2) {
            addCriterion("Aname not between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andAnickIsNull() {
            addCriterion("Anick is null");
            return (Criteria) this;
        }

        public Criteria andAnickIsNotNull() {
            addCriterion("Anick is not null");
            return (Criteria) this;
        }

        public Criteria andAnickEqualTo(String value) {
            addCriterion("Anick =", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickNotEqualTo(String value) {
            addCriterion("Anick <>", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickGreaterThan(String value) {
            addCriterion("Anick >", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickGreaterThanOrEqualTo(String value) {
            addCriterion("Anick >=", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickLessThan(String value) {
            addCriterion("Anick <", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickLessThanOrEqualTo(String value) {
            addCriterion("Anick <=", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickLike(String value) {
            addCriterion("Anick like", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickNotLike(String value) {
            addCriterion("Anick not like", value, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickIn(List<String> values) {
            addCriterion("Anick in", values, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickNotIn(List<String> values) {
            addCriterion("Anick not in", values, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickBetween(String value1, String value2) {
            addCriterion("Anick between", value1, value2, "anick");
            return (Criteria) this;
        }

        public Criteria andAnickNotBetween(String value1, String value2) {
            addCriterion("Anick not between", value1, value2, "anick");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordIsNull() {
            addCriterion("loginpassword is null");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordIsNotNull() {
            addCriterion("loginpassword is not null");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordEqualTo(String value) {
            addCriterion("loginpassword =", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotEqualTo(String value) {
            addCriterion("loginpassword <>", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordGreaterThan(String value) {
            addCriterion("loginpassword >", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("loginpassword >=", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordLessThan(String value) {
            addCriterion("loginpassword <", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordLessThanOrEqualTo(String value) {
            addCriterion("loginpassword <=", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordLike(String value) {
            addCriterion("loginpassword like", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotLike(String value) {
            addCriterion("loginpassword not like", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordIn(List<String> values) {
            addCriterion("loginpassword in", values, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotIn(List<String> values) {
            addCriterion("loginpassword not in", values, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordBetween(String value1, String value2) {
            addCriterion("loginpassword between", value1, value2, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotBetween(String value1, String value2) {
            addCriterion("loginpassword not between", value1, value2, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordIsNull() {
            addCriterion("ationpassword is null");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordIsNotNull() {
            addCriterion("ationpassword is not null");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordEqualTo(String value) {
            addCriterion("ationpassword =", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordNotEqualTo(String value) {
            addCriterion("ationpassword <>", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordGreaterThan(String value) {
            addCriterion("ationpassword >", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("ationpassword >=", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordLessThan(String value) {
            addCriterion("ationpassword <", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordLessThanOrEqualTo(String value) {
            addCriterion("ationpassword <=", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordLike(String value) {
            addCriterion("ationpassword like", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordNotLike(String value) {
            addCriterion("ationpassword not like", value, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordIn(List<String> values) {
            addCriterion("ationpassword in", values, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordNotIn(List<String> values) {
            addCriterion("ationpassword not in", values, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordBetween(String value1, String value2) {
            addCriterion("ationpassword between", value1, value2, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAtionpasswordNotBetween(String value1, String value2) {
            addCriterion("ationpassword not between", value1, value2, "ationpassword");
            return (Criteria) this;
        }

        public Criteria andAroleIsNull() {
            addCriterion("ARole is null");
            return (Criteria) this;
        }

        public Criteria andAroleIsNotNull() {
            addCriterion("ARole is not null");
            return (Criteria) this;
        }

        public Criteria andAroleEqualTo(Integer value) {
            addCriterion("ARole =", value, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleNotEqualTo(Integer value) {
            addCriterion("ARole <>", value, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleGreaterThan(Integer value) {
            addCriterion("ARole >", value, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleGreaterThanOrEqualTo(Integer value) {
            addCriterion("ARole >=", value, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleLessThan(Integer value) {
            addCriterion("ARole <", value, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleLessThanOrEqualTo(Integer value) {
            addCriterion("ARole <=", value, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleIn(List<Integer> values) {
            addCriterion("ARole in", values, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleNotIn(List<Integer> values) {
            addCriterion("ARole not in", values, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleBetween(Integer value1, Integer value2) {
            addCriterion("ARole between", value1, value2, "arole");
            return (Criteria) this;
        }

        public Criteria andAroleNotBetween(Integer value1, Integer value2) {
            addCriterion("ARole not between", value1, value2, "arole");
            return (Criteria) this;
        }

        public Criteria andPurviewIsNull() {
            addCriterion("purview is null");
            return (Criteria) this;
        }

        public Criteria andPurviewIsNotNull() {
            addCriterion("purview is not null");
            return (Criteria) this;
        }

        public Criteria andPurviewEqualTo(Integer value) {
            addCriterion("purview =", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewNotEqualTo(Integer value) {
            addCriterion("purview <>", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewGreaterThan(Integer value) {
            addCriterion("purview >", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewGreaterThanOrEqualTo(Integer value) {
            addCriterion("purview >=", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewLessThan(Integer value) {
            addCriterion("purview <", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewLessThanOrEqualTo(Integer value) {
            addCriterion("purview <=", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewIn(List<Integer> values) {
            addCriterion("purview in", values, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewNotIn(List<Integer> values) {
            addCriterion("purview not in", values, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewBetween(Integer value1, Integer value2) {
            addCriterion("purview between", value1, value2, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewNotBetween(Integer value1, Integer value2) {
            addCriterion("purview not between", value1, value2, "purview");
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("`state` like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("`state` not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andLogintimeIsNull() {
            addCriterion("logintime is null");
            return (Criteria) this;
        }

        public Criteria andLogintimeIsNotNull() {
            addCriterion("logintime is not null");
            return (Criteria) this;
        }

        public Criteria andLogintimeEqualTo(Date value) {
            addCriterion("logintime =", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotEqualTo(Date value) {
            addCriterion("logintime <>", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThan(Date value) {
            addCriterion("logintime >", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("logintime >=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThan(Date value) {
            addCriterion("logintime <", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThanOrEqualTo(Date value) {
            addCriterion("logintime <=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeIn(List<Date> values) {
            addCriterion("logintime in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotIn(List<Date> values) {
            addCriterion("logintime not in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeBetween(Date value1, Date value2) {
            addCriterion("logintime between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotBetween(Date value1, Date value2) {
            addCriterion("logintime not between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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