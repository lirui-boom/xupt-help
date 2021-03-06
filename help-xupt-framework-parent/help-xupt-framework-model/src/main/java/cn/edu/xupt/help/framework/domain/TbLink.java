package cn.edu.xupt.help.framework.domain;

import java.util.Date;

public class TbLink {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_link.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_link.from_uid
     *
     * @mbggenerated
     */
    private Long fromUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_link.to_uid
     *
     * @mbggenerated
     */
    private Long toUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_link.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_link.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_link.id
     *
     * @return the value of tb_link.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_link.id
     *
     * @param id the value for tb_link.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_link.from_uid
     *
     * @return the value of tb_link.from_uid
     *
     * @mbggenerated
     */
    public Long getFromUid() {
        return fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_link.from_uid
     *
     * @param fromUid the value for tb_link.from_uid
     *
     * @mbggenerated
     */
    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_link.to_uid
     *
     * @return the value of tb_link.to_uid
     *
     * @mbggenerated
     */
    public Long getToUid() {
        return toUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_link.to_uid
     *
     * @param toUid the value for tb_link.to_uid
     *
     * @mbggenerated
     */
    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_link.content
     *
     * @return the value of tb_link.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_link.content
     *
     * @param content the value for tb_link.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_link.create_time
     *
     * @return the value of tb_link.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_link.create_time
     *
     * @param createTime the value for tb_link.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}