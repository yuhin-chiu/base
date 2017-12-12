package cn.yx.constant;

/**
 * @author yuxuanjiao
 * @date 2017年9月11日 上午11:29:46
 * @version 1.0
 */

public class MessageConstant {

    public static final int MSG_SEND_SUCCESS = 1; // 推送成功
    public static final int MSG_SEND_FAIL = 2; // 推送失败
    public static final int EXT_INFO_MAX_LEN = 255; // 最大信息长度
    public static final int EXT_INFO_HALF_LEN = 127;
    public static final int EXT_INFO_IOS_LEN = 31;
    public static final int EXT_INFO_ANDROID_LEN = 96;

    public static final String MSG_TITLE = "焦点卖房";
    public static final String MSG_TYPE = "text";

    public static final int DEVICE_NONE = 0; // none
    public static final int DEVICE_ANDROID = 1; // 安卓
    public static final int DEVICE_IOS = 2; // IOS

}
