package com.lanjing.wallet.model;

import java.util.ArrayList;
import java.util.List;

public class AirdropParameterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AirdropParameterExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andMinIsNull() {
            addCriterion("min is null");
            return (Criteria) this;
        }

        public Criteria andMinIsNotNull() {
            addCriterion("min is not null");
            return (Criteria) this;
        }

        public Criteria andMinEqualTo(Double value) {
            addCriterion("min =", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotEqualTo(Double value) {
            addCriterion("min <>", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinGreaterThan(Double value) {
            addCriterion("min >", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinGreaterThanOrEqualTo(Double value) {
            addCriterion("min >=", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinLessThan(Double value) {
            addCriterion("min <", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinLessThanOrEqualTo(Double value) {
            addCriterion("min <=", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinIn(List<Double> values) {
            addCriterion("min in", values, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotIn(List<Double> values) {
            addCriterion("min not in", values, "min");
            return (Criteria) this;
        }

        public Criteria andMinBetween(Double value1, Double value2) {
            addCriterion("min between", value1, value2, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotBetween(Double value1, Double value2) {
            addCriterion("min not between", value1, value2, "min");
            return (Criteria) this;
        }

        public Criteria andMaxIsNull() {
            addCriterion("max is null");
            return (Criteria) this;
        }

        public Criteria andMaxIsNotNull() {
            addCriterion("max is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEqualTo(Double value) {
            addCriterion("max =", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotEqualTo(Double value) {
            addCriterion("max <>", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxGreaterThan(Double value) {
            addCriterion("max >", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxGreaterThanOrEqualTo(Double value) {
            addCriterion("max >=", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxLessThan(Double value) {
            addCriterion("max <", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxLessThanOrEqualTo(Double value) {
            addCriterion("max <=", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxIn(List<Double> values) {
            addCriterion("max in", values, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotIn(List<Double> values) {
            addCriterion("max not in", values, "max");
            return (Criteria) this;
        }

        public Criteria andMaxBetween(Double value1, Double value2) {
            addCriterion("max between", value1, value2, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotBetween(Double value1, Double value2) {
            addCriterion("max not between", value1, value2, "max");
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

        public Criteria andAmountEqualTo(Double value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Double value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Double value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Double value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Double value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Double> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Double> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Double value1, Double value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Double value1, Double value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseIsNull() {
            addCriterion("daily_release is null");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseIsNotNull() {
            addCriterion("daily_release is not null");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseEqualTo(Double value) {
            addCriterion("daily_release =", value, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseNotEqualTo(Double value) {
            addCriterion("daily_release <>", value, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseGreaterThan(Double value) {
            addCriterion("daily_release >", value, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseGreaterThanOrEqualTo(Double value) {
            addCriterion("daily_release >=", value, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseLessThan(Double value) {
            addCriterion("daily_release <", value, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseLessThanOrEqualTo(Double value) {
            addCriterion("daily_release <=", value, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseIn(List<Double> values) {
            addCriterion("daily_release in", values, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseNotIn(List<Double> values) {
            addCriterion("daily_release not in", values, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseBetween(Double value1, Double value2) {
            addCriterion("daily_release between", value1, value2, "dailyRelease");
            return (Criteria) this;
        }

        public Criteria andDailyReleaseNotBetween(Double value1, Double value2) {
            addCriterion("daily_release not between", value1, value2, "dailyRelease");
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