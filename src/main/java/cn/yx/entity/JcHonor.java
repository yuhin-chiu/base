package cn.yx.entity;

import java.util.Date;

public class JcHonor {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_honor.id
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_honor.img_key
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private String imgKey;
    private String imgUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_honor.status
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_honor.create_time
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Date createTime;
    private String createTimeStr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_honor.lang
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Integer lang;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_honor.content
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_honor.id
     *
     * @return the value of jc_honor.id
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_honor.id
     *
     * @param id the value for jc_honor.id
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_honor.img_key
     *
     * @return the value of jc_honor.img_key
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public String getImgKey() {
        return imgKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_honor.img_key
     *
     * @param imgKey the value for jc_honor.img_key
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_honor.status
     *
     * @return the value of jc_honor.status
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_honor.status
     *
     * @param status the value for jc_honor.status
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_honor.create_time
     *
     * @return the value of jc_honor.create_time
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_honor.create_time
     *
     * @param createTime the value for jc_honor.create_time
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_honor.lang
     *
     * @return the value of jc_honor.lang
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Integer getLang() {
        return lang;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_honor.lang
     *
     * @param lang the value for jc_honor.lang
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setLang(Integer lang) {
        this.lang = lang;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_honor.content
     *
     * @return the value of jc_honor.content
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_honor.content
     *
     * @param content the value for jc_honor.content
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}