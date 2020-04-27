package cn.edu.xupt.help.framework.domain;

import java.util.ArrayList;
import java.util.List;

public class TbSwiperExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public TbSwiperExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
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

        public Criteria andSwiperIdIsNull() {
            addCriterion("swiper_id is null");
            return (Criteria) this;
        }

        public Criteria andSwiperIdIsNotNull() {
            addCriterion("swiper_id is not null");
            return (Criteria) this;
        }

        public Criteria andSwiperIdEqualTo(Long value) {
            addCriterion("swiper_id =", value, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdNotEqualTo(Long value) {
            addCriterion("swiper_id <>", value, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdGreaterThan(Long value) {
            addCriterion("swiper_id >", value, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("swiper_id >=", value, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdLessThan(Long value) {
            addCriterion("swiper_id <", value, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdLessThanOrEqualTo(Long value) {
            addCriterion("swiper_id <=", value, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdIn(List<Long> values) {
            addCriterion("swiper_id in", values, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdNotIn(List<Long> values) {
            addCriterion("swiper_id not in", values, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdBetween(Long value1, Long value2) {
            addCriterion("swiper_id between", value1, value2, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperIdNotBetween(Long value1, Long value2) {
            addCriterion("swiper_id not between", value1, value2, "swiperId");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlIsNull() {
            addCriterion("swiper_image_url is null");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlIsNotNull() {
            addCriterion("swiper_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlEqualTo(String value) {
            addCriterion("swiper_image_url =", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlNotEqualTo(String value) {
            addCriterion("swiper_image_url <>", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlGreaterThan(String value) {
            addCriterion("swiper_image_url >", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("swiper_image_url >=", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlLessThan(String value) {
            addCriterion("swiper_image_url <", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlLessThanOrEqualTo(String value) {
            addCriterion("swiper_image_url <=", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlLike(String value) {
            addCriterion("swiper_image_url like", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlNotLike(String value) {
            addCriterion("swiper_image_url not like", value, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlIn(List<String> values) {
            addCriterion("swiper_image_url in", values, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlNotIn(List<String> values) {
            addCriterion("swiper_image_url not in", values, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlBetween(String value1, String value2) {
            addCriterion("swiper_image_url between", value1, value2, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andSwiperImageUrlNotBetween(String value1, String value2) {
            addCriterion("swiper_image_url not between", value1, value2, "swiperImageUrl");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_swiper
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_swiper
     *
     * @mbggenerated
     */
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