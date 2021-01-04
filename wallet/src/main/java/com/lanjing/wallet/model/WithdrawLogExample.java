package com.lanjing.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-24 17:16
 * @version version 1.0.0
 */
public class WithdrawLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WithdrawLogExample() {
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
            addCriterion("fId is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("fId is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("fId =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("fId <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("fId >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fId >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("fId <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("fId <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("fId in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("fId not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("fId between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fId not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("orderId =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("orderId <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("orderId >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("orderId >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("orderId <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("orderId <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("orderId like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("orderId not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("orderId in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("orderId not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("orderId between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("orderId not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andTxIdIsNull() {
            addCriterion("txId is null");
            return (Criteria) this;
        }

        public Criteria andTxIdIsNotNull() {
            addCriterion("txId is not null");
            return (Criteria) this;
        }

        public Criteria andTxIdEqualTo(String value) {
            addCriterion("txId =", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdNotEqualTo(String value) {
            addCriterion("txId <>", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdGreaterThan(String value) {
            addCriterion("txId >", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdGreaterThanOrEqualTo(String value) {
            addCriterion("txId >=", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdLessThan(String value) {
            addCriterion("txId <", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdLessThanOrEqualTo(String value) {
            addCriterion("txId <=", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdLike(String value) {
            addCriterion("txId like", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdNotLike(String value) {
            addCriterion("txId not like", value, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdIn(List<String> values) {
            addCriterion("txId in", values, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdNotIn(List<String> values) {
            addCriterion("txId not in", values, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdBetween(String value1, String value2) {
            addCriterion("txId between", value1, value2, "txId");
            return (Criteria) this;
        }

        public Criteria andTxIdNotBetween(String value1, String value2) {
            addCriterion("txId not between", value1, value2, "txId");
            return (Criteria) this;
        }

        public Criteria andSellAddressIsNull() {
            addCriterion("selladress is null");
            return (Criteria) this;
        }

        public Criteria andSellAddressIsNotNull() {
            addCriterion("selladress is not null");
            return (Criteria) this;
        }

        public Criteria andSellAddressEqualTo(String value) {
            addCriterion("selladress =", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotEqualTo(String value) {
            addCriterion("selladress <>", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressGreaterThan(String value) {
            addCriterion("selladress >", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressGreaterThanOrEqualTo(String value) {
            addCriterion("selladress >=", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLessThan(String value) {
            addCriterion("selladress <", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLessThanOrEqualTo(String value) {
            addCriterion("selladress <=", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressLike(String value) {
            addCriterion("selladress like", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotLike(String value) {
            addCriterion("selladress not like", value, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressIn(List<String> values) {
            addCriterion("selladress in", values, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotIn(List<String> values) {
            addCriterion("selladress not in", values, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressBetween(String value1, String value2) {
            addCriterion("selladress between", value1, value2, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellAddressNotBetween(String value1, String value2) {
            addCriterion("selladress not between", value1, value2, "sellAddress");
            return (Criteria) this;
        }

        public Criteria andSellUserIsNull() {
            addCriterion("selluser is null");
            return (Criteria) this;
        }

        public Criteria andSellUserIsNotNull() {
            addCriterion("selluser is not null");
            return (Criteria) this;
        }

        public Criteria andSellUserEqualTo(Integer value) {
            addCriterion("selluser =", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserNotEqualTo(Integer value) {
            addCriterion("selluser <>", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserGreaterThan(Integer value) {
            addCriterion("selluser >", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("selluser >=", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserLessThan(Integer value) {
            addCriterion("selluser <", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserLessThanOrEqualTo(Integer value) {
            addCriterion("selluser <=", value, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserIn(List<Integer> values) {
            addCriterion("selluser in", values, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserNotIn(List<Integer> values) {
            addCriterion("selluser not in", values, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserBetween(Integer value1, Integer value2) {
            addCriterion("selluser between", value1, value2, "sellUser");
            return (Criteria) this;
        }

        public Criteria andSellUserNotBetween(Integer value1, Integer value2) {
            addCriterion("selluser not between", value1, value2, "sellUser");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(BigDecimal value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(BigDecimal value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(BigDecimal value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(BigDecimal value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<BigDecimal> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<BigDecimal> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("num not between", value1, value2, "num");
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