package cn.yx.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuxuanjiao
 * @date 2017年9月11日 下午2:14:26
 * @version 1.0
 */

public enum PushEnum {

    PUSH_ANDROID_APP_KEY_NEW("new_app", "f01886fa530f9d9a2c16df63"), PUSH_ANDROID_MASTER_SECRET_NEW("new_master",
            "d4dde92fd5dfdb1907394678"),

    PUSH_ANDROID_APP_KEY_OLD("old_app", "2865458cbffbadac79d3945c"), PUSH_ANDROID_MASTER_SECRET_OLD("old_master",
            "e80870a1e06d9f0a9738f866"),

    PUSH_ANDROID_APP_KEY_ADMIN("admin_app",
            "7295170c0ea2acfc7818a46d"), PUSH_ANDROID_MASTER_SECRET_ADMIN("admin_master", "4dbbbc27a84a0075d67680ab"),

    IOS_CERT_DEV("dev", "apns_dev.p12"), IOS_CERT_PRO("pro", "apns_pro.p12");

    private String key;
    private String content;

    private static Map<String, String> PUSH_MAP = new HashMap<>();

    static {
        for (PushEnum pe : PushEnum.values()) {
            PUSH_MAP.put(pe.key, pe.content);
        }
    }

    PushEnum(String key, String content) {
        this.key = key;
        this.content = content;
    }

    public static String getAndroid(String key) {
        return PUSH_MAP.get(key);
    }

    public static String getIOSCert(String edition, String ver) {
        return "classpath:ios/" + edition + "/" + PUSH_MAP.get(ver);
    }
}
