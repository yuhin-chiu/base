package cn.yx.entity;

import java.util.Date;

public class JcBanner {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_banner.id
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_banner.title
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_banner.img_key
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private String imgKey;
    private String imgUrl;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_banner.url
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_banner.status
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_banner.create_time
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_banner.lang
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    private Integer lang;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_banner.id
     *
     * @return the value of jc_banner.id
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_banner.id
     *
     * @param id the value for jc_banner.id
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_banner.title
     *
     * @return the value of jc_banner.title
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_banner.title
     *
     * @param title the value for jc_banner.title
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_banner.img_key
     *
     * @return the value of jc_banner.img_key
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public String getImgKey() {
        return imgKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_banner.img_key
     *
     * @param imgKey the value for jc_banner.img_key
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_banner.url
     *
     * @return the value of jc_banner.url
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_banner.url
     *
     * @param url the value for jc_banner.url
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_banner.status
     *
     * @return the value of jc_banner.status
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_banner.status
     *
     * @param status the value for jc_banner.status
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_banner.create_time
     *
     * @return the value of jc_banner.create_time
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_banner.create_time
     *
     * @param createTime the value for jc_banner.create_time
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_banner.lang
     *
     * @return the value of jc_banner.lang
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public Integer getLang() {
        return lang;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_banner.lang
     *
     * @param lang the value for jc_banner.lang
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}