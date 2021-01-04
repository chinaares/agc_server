package com.lanjing.otc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @email jiansite@qq.com
 * @date 2019-07-10 11:57
 * @version version 1.0.0
 */
public class UsersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsersExample() {
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

        public Criteria andKeyesIsNull() {
            addCriterion("keyes is null");
            return (Criteria) this;
        }

        public Criteria andKeyesIsNotNull() {
            addCriterion("keyes is not null");
            return (Criteria) this;
        }

        public Criteria andKeyesEqualTo(String value) {
            addCriterion("keyes =", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesNotEqualTo(String value) {
            addCriterion("keyes <>", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesGreaterThan(String value) {
            addCriterion("keyes >", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesGreaterThanOrEqualTo(String value) {
            addCriterion("keyes >=", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesLessThan(String value) {
            addCriterion("keyes <", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesLessThanOrEqualTo(String value) {
            addCriterion("keyes <=", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesLike(String value) {
            addCriterion("keyes like", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesNotLike(String value) {
            addCriterion("keyes not like", value, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesIn(List<String> values) {
            addCriterion("keyes in", values, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesNotIn(List<String> values) {
            addCriterion("keyes not in", values, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesBetween(String value1, String value2) {
            addCriterion("keyes between", value1, value2, "keyes");
            return (Criteria) this;
        }

        public Criteria andKeyesNotBetween(String value1, String value2) {
            addCriterion("keyes not between", value1, value2, "keyes");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("userName =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("userName <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("userName >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("userName <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("userName like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("userName not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("userName in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("userName not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordIsNull() {
            addCriterion("loginPassword is null");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordIsNotNull() {
            addCriterion("loginPassword is not null");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordEqualTo(String value) {
            addCriterion("loginPassword =", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotEqualTo(String value) {
            addCriterion("loginPassword <>", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordGreaterThan(String value) {
            addCriterion("loginPassword >", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("loginPassword >=", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordLessThan(String value) {
            addCriterion("loginPassword <", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordLessThanOrEqualTo(String value) {
            addCriterion("loginPassword <=", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordLike(String value) {
            addCriterion("loginPassword like", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotLike(String value) {
            addCriterion("loginPassword not like", value, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordIn(List<String> values) {
            addCriterion("loginPassword in", values, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotIn(List<String> values) {
            addCriterion("loginPassword not in", values, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordBetween(String value1, String value2) {
            addCriterion("loginPassword between", value1, value2, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andLoginpasswordNotBetween(String value1, String value2) {
            addCriterion("loginPassword not between", value1, value2, "loginpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordIsNull() {
            addCriterion("transPassword is null");
            return (Criteria) this;
        }

        public Criteria andTranspasswordIsNotNull() {
            addCriterion("transPassword is not null");
            return (Criteria) this;
        }

        public Criteria andTranspasswordEqualTo(String value) {
            addCriterion("transPassword =", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordNotEqualTo(String value) {
            addCriterion("transPassword <>", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordGreaterThan(String value) {
            addCriterion("transPassword >", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordGreaterThanOrEqualTo(String value) {
            addCriterion("transPassword >=", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordLessThan(String value) {
            addCriterion("transPassword <", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordLessThanOrEqualTo(String value) {
            addCriterion("transPassword <=", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordLike(String value) {
            addCriterion("transPassword like", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordNotLike(String value) {
            addCriterion("transPassword not like", value, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordIn(List<String> values) {
            addCriterion("transPassword in", values, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordNotIn(List<String> values) {
            addCriterion("transPassword not in", values, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordBetween(String value1, String value2) {
            addCriterion("transPassword between", value1, value2, "transpassword");
            return (Criteria) this;
        }

        public Criteria andTranspasswordNotBetween(String value1, String value2) {
            addCriterion("transPassword not between", value1, value2, "transpassword");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("Nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("Nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("Nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("Nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("Nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("Nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("Nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("Nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("Nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("Nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("Nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("Nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("Nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("Nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andPictureIsNull() {
            addCriterion("picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("picture not between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPhonenumIsNull() {
            addCriterion("phoneNum is null");
            return (Criteria) this;
        }

        public Criteria andPhonenumIsNotNull() {
            addCriterion("phoneNum is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenumEqualTo(String value) {
            addCriterion("phoneNum =", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotEqualTo(String value) {
            addCriterion("phoneNum <>", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThan(String value) {
            addCriterion("phoneNum >", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThanOrEqualTo(String value) {
            addCriterion("phoneNum >=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThan(String value) {
            addCriterion("phoneNum <", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThanOrEqualTo(String value) {
            addCriterion("phoneNum <=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLike(String value) {
            addCriterion("phoneNum like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotLike(String value) {
            addCriterion("phoneNum not like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumIn(List<String> values) {
            addCriterion("phoneNum in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotIn(List<String> values) {
            addCriterion("phoneNum not in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumBetween(String value1, String value2) {
            addCriterion("phoneNum between", value1, value2, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotBetween(String value1, String value2) {
            addCriterion("phoneNum not between", value1, value2, "phonenum");
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

        public Criteria andBeginnodenumIsNull() {
            addCriterion("beginNodenum is null");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumIsNotNull() {
            addCriterion("beginNodenum is not null");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumEqualTo(Double value) {
            addCriterion("beginNodenum =", value, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumNotEqualTo(Double value) {
            addCriterion("beginNodenum <>", value, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumGreaterThan(Double value) {
            addCriterion("beginNodenum >", value, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumGreaterThanOrEqualTo(Double value) {
            addCriterion("beginNodenum >=", value, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumLessThan(Double value) {
            addCriterion("beginNodenum <", value, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumLessThanOrEqualTo(Double value) {
            addCriterion("beginNodenum <=", value, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumIn(List<Double> values) {
            addCriterion("beginNodenum in", values, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumNotIn(List<Double> values) {
            addCriterion("beginNodenum not in", values, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumBetween(Double value1, Double value2) {
            addCriterion("beginNodenum between", value1, value2, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andBeginnodenumNotBetween(Double value1, Double value2) {
            addCriterion("beginNodenum not between", value1, value2, "beginnodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumIsNull() {
            addCriterion("NodeNum is null");
            return (Criteria) this;
        }

        public Criteria andNodenumIsNotNull() {
            addCriterion("NodeNum is not null");
            return (Criteria) this;
        }

        public Criteria andNodenumEqualTo(BigDecimal value) {
            addCriterion("NodeNum =", value, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumNotEqualTo(BigDecimal value) {
            addCriterion("NodeNum <>", value, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumGreaterThan(BigDecimal value) {
            addCriterion("NodeNum >", value, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NodeNum >=", value, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumLessThan(BigDecimal value) {
            addCriterion("NodeNum <", value, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NodeNum <=", value, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumIn(List<BigDecimal> values) {
            addCriterion("NodeNum in", values, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumNotIn(List<BigDecimal> values) {
            addCriterion("NodeNum not in", values, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NodeNum between", value1, value2, "nodenum");
            return (Criteria) this;
        }

        public Criteria andNodenumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NodeNum not between", value1, value2, "nodenum");
            return (Criteria) this;
        }

        public Criteria andIsnodeIsNull() {
            addCriterion("IsNode is null");
            return (Criteria) this;
        }

        public Criteria andIsnodeIsNotNull() {
            addCriterion("IsNode is not null");
            return (Criteria) this;
        }

        public Criteria andIsnodeEqualTo(Integer value) {
            addCriterion("IsNode =", value, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeNotEqualTo(Integer value) {
            addCriterion("IsNode <>", value, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeGreaterThan(Integer value) {
            addCriterion("IsNode >", value, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsNode >=", value, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeLessThan(Integer value) {
            addCriterion("IsNode <", value, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeLessThanOrEqualTo(Integer value) {
            addCriterion("IsNode <=", value, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeIn(List<Integer> values) {
            addCriterion("IsNode in", values, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeNotIn(List<Integer> values) {
            addCriterion("IsNode not in", values, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeBetween(Integer value1, Integer value2) {
            addCriterion("IsNode between", value1, value2, "isnode");
            return (Criteria) this;
        }

        public Criteria andIsnodeNotBetween(Integer value1, Integer value2) {
            addCriterion("IsNode not between", value1, value2, "isnode");
            return (Criteria) this;
        }

        public Criteria andNodeadminIsNull() {
            addCriterion("Nodeadmin is null");
            return (Criteria) this;
        }

        public Criteria andNodeadminIsNotNull() {
            addCriterion("Nodeadmin is not null");
            return (Criteria) this;
        }

        public Criteria andNodeadminEqualTo(String value) {
            addCriterion("Nodeadmin =", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminNotEqualTo(String value) {
            addCriterion("Nodeadmin <>", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminGreaterThan(String value) {
            addCriterion("Nodeadmin >", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminGreaterThanOrEqualTo(String value) {
            addCriterion("Nodeadmin >=", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminLessThan(String value) {
            addCriterion("Nodeadmin <", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminLessThanOrEqualTo(String value) {
            addCriterion("Nodeadmin <=", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminLike(String value) {
            addCriterion("Nodeadmin like", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminNotLike(String value) {
            addCriterion("Nodeadmin not like", value, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminIn(List<String> values) {
            addCriterion("Nodeadmin in", values, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminNotIn(List<String> values) {
            addCriterion("Nodeadmin not in", values, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminBetween(String value1, String value2) {
            addCriterion("Nodeadmin between", value1, value2, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andNodeadminNotBetween(String value1, String value2) {
            addCriterion("Nodeadmin not between", value1, value2, "nodeadmin");
            return (Criteria) this;
        }

        public Criteria andIsvipIsNull() {
            addCriterion("IsVip is null");
            return (Criteria) this;
        }

        public Criteria andIsvipIsNotNull() {
            addCriterion("IsVip is not null");
            return (Criteria) this;
        }

        public Criteria andIsvipEqualTo(Integer value) {
            addCriterion("IsVip =", value, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipNotEqualTo(Integer value) {
            addCriterion("IsVip <>", value, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipGreaterThan(Integer value) {
            addCriterion("IsVip >", value, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsVip >=", value, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipLessThan(Integer value) {
            addCriterion("IsVip <", value, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipLessThanOrEqualTo(Integer value) {
            addCriterion("IsVip <=", value, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipIn(List<Integer> values) {
            addCriterion("IsVip in", values, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipNotIn(List<Integer> values) {
            addCriterion("IsVip not in", values, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipBetween(Integer value1, Integer value2) {
            addCriterion("IsVip between", value1, value2, "isvip");
            return (Criteria) this;
        }

        public Criteria andIsvipNotBetween(Integer value1, Integer value2) {
            addCriterion("IsVip not between", value1, value2, "isvip");
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

        public Criteria andIsnodetimeIsNull() {
            addCriterion("IsNodetime is null");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeIsNotNull() {
            addCriterion("IsNodetime is not null");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeEqualTo(Date value) {
            addCriterion("IsNodetime =", value, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeNotEqualTo(Date value) {
            addCriterion("IsNodetime <>", value, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeGreaterThan(Date value) {
            addCriterion("IsNodetime >", value, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("IsNodetime >=", value, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeLessThan(Date value) {
            addCriterion("IsNodetime <", value, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeLessThanOrEqualTo(Date value) {
            addCriterion("IsNodetime <=", value, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeIn(List<Date> values) {
            addCriterion("IsNodetime in", values, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeNotIn(List<Date> values) {
            addCriterion("IsNodetime not in", values, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeBetween(Date value1, Date value2) {
            addCriterion("IsNodetime between", value1, value2, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andIsnodetimeNotBetween(Date value1, Date value2) {
            addCriterion("IsNodetime not between", value1, value2, "isnodetime");
            return (Criteria) this;
        }

        public Criteria andDeviceIsNull() {
            addCriterion("device is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIsNotNull() {
            addCriterion("device is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceEqualTo(String value) {
            addCriterion("device =", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotEqualTo(String value) {
            addCriterion("device <>", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceGreaterThan(String value) {
            addCriterion("device >", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceGreaterThanOrEqualTo(String value) {
            addCriterion("device >=", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLessThan(String value) {
            addCriterion("device <", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLessThanOrEqualTo(String value) {
            addCriterion("device <=", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLike(String value) {
            addCriterion("device like", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotLike(String value) {
            addCriterion("device not like", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceIn(List<String> values) {
            addCriterion("device in", values, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotIn(List<String> values) {
            addCriterion("device not in", values, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceBetween(String value1, String value2) {
            addCriterion("device between", value1, value2, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotBetween(String value1, String value2) {
            addCriterion("device not between", value1, value2, "device");
            return (Criteria) this;
        }

        public Criteria andWelletnameIsNull() {
            addCriterion("welletname is null");
            return (Criteria) this;
        }

        public Criteria andWelletnameIsNotNull() {
            addCriterion("welletname is not null");
            return (Criteria) this;
        }

        public Criteria andWelletnameEqualTo(String value) {
            addCriterion("welletname =", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameNotEqualTo(String value) {
            addCriterion("welletname <>", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameGreaterThan(String value) {
            addCriterion("welletname >", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameGreaterThanOrEqualTo(String value) {
            addCriterion("welletname >=", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameLessThan(String value) {
            addCriterion("welletname <", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameLessThanOrEqualTo(String value) {
            addCriterion("welletname <=", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameLike(String value) {
            addCriterion("welletname like", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameNotLike(String value) {
            addCriterion("welletname not like", value, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameIn(List<String> values) {
            addCriterion("welletname in", values, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameNotIn(List<String> values) {
            addCriterion("welletname not in", values, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameBetween(String value1, String value2) {
            addCriterion("welletname between", value1, value2, "welletname");
            return (Criteria) this;
        }

        public Criteria andWelletnameNotBetween(String value1, String value2) {
            addCriterion("welletname not between", value1, value2, "welletname");
            return (Criteria) this;
        }

        public Criteria andIsautoIsNull() {
            addCriterion("Isauto is null");
            return (Criteria) this;
        }

        public Criteria andIsautoIsNotNull() {
            addCriterion("Isauto is not null");
            return (Criteria) this;
        }

        public Criteria andIsautoEqualTo(Integer value) {
            addCriterion("Isauto =", value, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoNotEqualTo(Integer value) {
            addCriterion("Isauto <>", value, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoGreaterThan(Integer value) {
            addCriterion("Isauto >", value, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Isauto >=", value, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoLessThan(Integer value) {
            addCriterion("Isauto <", value, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoLessThanOrEqualTo(Integer value) {
            addCriterion("Isauto <=", value, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoIn(List<Integer> values) {
            addCriterion("Isauto in", values, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoNotIn(List<Integer> values) {
            addCriterion("Isauto not in", values, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoBetween(Integer value1, Integer value2) {
            addCriterion("Isauto between", value1, value2, "isauto");
            return (Criteria) this;
        }

        public Criteria andIsautoNotBetween(Integer value1, Integer value2) {
            addCriterion("Isauto not between", value1, value2, "isauto");
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

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andWaysIsNull() {
            addCriterion("ways is null");
            return (Criteria) this;
        }

        public Criteria andWaysIsNotNull() {
            addCriterion("ways is not null");
            return (Criteria) this;
        }

        public Criteria andWaysEqualTo(Integer value) {
            addCriterion("ways =", value, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysNotEqualTo(Integer value) {
            addCriterion("ways <>", value, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysGreaterThan(Integer value) {
            addCriterion("ways >", value, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("ways >=", value, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysLessThan(Integer value) {
            addCriterion("ways <", value, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysLessThanOrEqualTo(Integer value) {
            addCriterion("ways <=", value, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysIn(List<Integer> values) {
            addCriterion("ways in", values, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysNotIn(List<Integer> values) {
            addCriterion("ways not in", values, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysBetween(Integer value1, Integer value2) {
            addCriterion("ways between", value1, value2, "ways");
            return (Criteria) this;
        }

        public Criteria andWaysNotBetween(Integer value1, Integer value2) {
            addCriterion("ways not between", value1, value2, "ways");
            return (Criteria) this;
        }

        public Criteria andIsrealIsNull() {
            addCriterion("IsReal is null");
            return (Criteria) this;
        }

        public Criteria andIsrealIsNotNull() {
            addCriterion("IsReal is not null");
            return (Criteria) this;
        }

        public Criteria andIsrealEqualTo(Integer value) {
            addCriterion("IsReal =", value, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealNotEqualTo(Integer value) {
            addCriterion("IsReal <>", value, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealGreaterThan(Integer value) {
            addCriterion("IsReal >", value, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsReal >=", value, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealLessThan(Integer value) {
            addCriterion("IsReal <", value, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealLessThanOrEqualTo(Integer value) {
            addCriterion("IsReal <=", value, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealIn(List<Integer> values) {
            addCriterion("IsReal in", values, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealNotIn(List<Integer> values) {
            addCriterion("IsReal not in", values, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealBetween(Integer value1, Integer value2) {
            addCriterion("IsReal between", value1, value2, "isreal");
            return (Criteria) this;
        }

        public Criteria andIsrealNotBetween(Integer value1, Integer value2) {
            addCriterion("IsReal not between", value1, value2, "isreal");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("Realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("Realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("Realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("Realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("Realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("Realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("Realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("Realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("Realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("Realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("Realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("Realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("Realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("Realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andIdentityidIsNull() {
            addCriterion("IdentityId is null");
            return (Criteria) this;
        }

        public Criteria andIdentityidIsNotNull() {
            addCriterion("IdentityId is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityidEqualTo(String value) {
            addCriterion("IdentityId =", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotEqualTo(String value) {
            addCriterion("IdentityId <>", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidGreaterThan(String value) {
            addCriterion("IdentityId >", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidGreaterThanOrEqualTo(String value) {
            addCriterion("IdentityId >=", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidLessThan(String value) {
            addCriterion("IdentityId <", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidLessThanOrEqualTo(String value) {
            addCriterion("IdentityId <=", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidLike(String value) {
            addCriterion("IdentityId like", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotLike(String value) {
            addCriterion("IdentityId not like", value, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidIn(List<String> values) {
            addCriterion("IdentityId in", values, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotIn(List<String> values) {
            addCriterion("IdentityId not in", values, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidBetween(String value1, String value2) {
            addCriterion("IdentityId between", value1, value2, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityidNotBetween(String value1, String value2) {
            addCriterion("IdentityId not between", value1, value2, "identityid");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1IsNull() {
            addCriterion("IdentityImg1 is null");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1IsNotNull() {
            addCriterion("IdentityImg1 is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1EqualTo(String value) {
            addCriterion("IdentityImg1 =", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1NotEqualTo(String value) {
            addCriterion("IdentityImg1 <>", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1GreaterThan(String value) {
            addCriterion("IdentityImg1 >", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1GreaterThanOrEqualTo(String value) {
            addCriterion("IdentityImg1 >=", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1LessThan(String value) {
            addCriterion("IdentityImg1 <", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1LessThanOrEqualTo(String value) {
            addCriterion("IdentityImg1 <=", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1Like(String value) {
            addCriterion("IdentityImg1 like", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1NotLike(String value) {
            addCriterion("IdentityImg1 not like", value, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1In(List<String> values) {
            addCriterion("IdentityImg1 in", values, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1NotIn(List<String> values) {
            addCriterion("IdentityImg1 not in", values, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1Between(String value1, String value2) {
            addCriterion("IdentityImg1 between", value1, value2, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg1NotBetween(String value1, String value2) {
            addCriterion("IdentityImg1 not between", value1, value2, "identityimg1");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2IsNull() {
            addCriterion("IdentityImg2 is null");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2IsNotNull() {
            addCriterion("IdentityImg2 is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2EqualTo(String value) {
            addCriterion("IdentityImg2 =", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2NotEqualTo(String value) {
            addCriterion("IdentityImg2 <>", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2GreaterThan(String value) {
            addCriterion("IdentityImg2 >", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2GreaterThanOrEqualTo(String value) {
            addCriterion("IdentityImg2 >=", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2LessThan(String value) {
            addCriterion("IdentityImg2 <", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2LessThanOrEqualTo(String value) {
            addCriterion("IdentityImg2 <=", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2Like(String value) {
            addCriterion("IdentityImg2 like", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2NotLike(String value) {
            addCriterion("IdentityImg2 not like", value, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2In(List<String> values) {
            addCriterion("IdentityImg2 in", values, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2NotIn(List<String> values) {
            addCriterion("IdentityImg2 not in", values, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2Between(String value1, String value2) {
            addCriterion("IdentityImg2 between", value1, value2, "identityimg2");
            return (Criteria) this;
        }

        public Criteria andIdentityimg2NotBetween(String value1, String value2) {
            addCriterion("IdentityImg2 not between", value1, value2, "identityimg2");
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