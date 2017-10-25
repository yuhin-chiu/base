package cn.yx.model;

import cn.yx.enums.DialogItemEnum;

/**
 * @author yuxuanjiao
 * @date 2017年10月24日 上午11:55:29
 * @version 1.0
 */

public class DialogItem {

    private String id;
    private String title;
    private String type = DialogItemEnum.TEXT.getStr(); // (text、textarea、editor、radio、checkbox、select)
    private String placeholder;

    private Integer minLength = 1;
    private Integer maxLength = 30;

    /**
     * 设置id,title,默认设置type,placeholder
     * @param id
     * @param title
     */
    public DialogItem(String id, String title) {
        super();
        this.id = id;
        this.title = title;
        this.placeholder = (minLength > 0 ? "不能为空且" : "") + "最大长度不超过" + maxLength;
    }
    public DialogItem(String id, String title, String type) {
        super();
        this.id = id;
        this.title = title;
        this.type = type;
        this.placeholder = (minLength > 0 ? "不能为空且" : "") + "最大长度不超过" + maxLength;
    }

    public DialogItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

}
