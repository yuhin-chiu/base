//package cn.yx.service.push;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import cn.jiguang.common.ClientConfig;
//import cn.jiguang.common.resp.APIConnectionException;
//import cn.jiguang.common.resp.APIRequestException;
//import cn.jpush.api.JPushClient;
//import cn.jpush.api.push.PushResult;
//import cn.jpush.api.push.model.Message;
//import cn.jpush.api.push.model.Platform;
//import cn.jpush.api.push.model.PushPayload;
//import cn.jpush.api.push.model.audience.Audience;
//import cn.yx.constant.AppConstant;
//import cn.yx.constant.MessageConstant;
//import cn.yx.enums.PushEnum;
//import cn.yx.model.ApiResponse;
//import cn.yx.model.PushMessage;
//import cn.yx.util.JsonUtil;
//import cn.yx.util.ListUtil;
//import cn.yx.util.TimeUtil;
//
///**
// * @author yuxuanjiao
// * @date 2017年9月11日 上午11:37:29
// * @version 1.0
// */
//
//@Service
//public class AndroidPushService {
//
//    private Logger LOGGER = LoggerFactory.getLogger(AndroidPushService.class);
//    
//    @Value("${spring.profiles.active}")
//    private String RUN_ACTIVE;
//
//    private static PushPayload buildAndroidPushObject(List<String> alias, Message message) {
//
//        return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.alias(alias))
//                .setMessage(message).build();
//    }
//
//    private static Message buildMessage(String contentType, String pushMsg, String title) {
//
//        return Message.newBuilder().setContentType(contentType).setMsgContent(pushMsg).setTitle(title).build();
//    }
//
//    public ApiResponse sendMessage(List<String> alias, PushMessage pushMessage, String contentType, String edition) {
//
//        ApiResponse pushResult = new ApiResponse();
//
//        if (alias == null || alias.isEmpty()) {
//            pushResult.setStatus(false);
//            pushResult.setMsg("经纪人id集合为空，安卓消息推送失败");
//            return pushResult;
//        }
//
//        String pushMsg = JsonUtil.toJson(pushMessage);
//        System.out.println(pushMsg);
//        return jpush(alias, pushMsg, pushMessage.getTitle(), contentType, edition);
//    }
//
//    private ApiResponse jpush(List<String> alias, String pushMsg, String title, String contentType, String edition) {
//        ApiResponse result = new ApiResponse();
//        ClientConfig config = ClientConfig.getInstance();
//        config.setMaxRetryTimes(AppConstant.MAX_RETRY_TIMES);
//        config.setApnsProduction("prod".equals(RUN_ACTIVE));
//        // config.setSSLVersion("TLSv1.1");
//        config.setConnectionTimeout(10 * 1000);
//        config.setTimeToLive(TimeUtil.DAY_LONG_VALUE);
//        String appKey = PushEnum.getAndroid(edition + "_app");
//        String masterSecret = PushEnum.getAndroid(edition + "_master");
//
//        Collections.shuffle(alias);
//
//        String aliasStr = "ids:" + ListUtil.connect(alias, ",");
//
//        if (aliasStr.length() > MessageConstant.EXT_INFO_ANDROID_LEN) {
//            aliasStr = aliasStr.substring(0, MessageConstant.EXT_INFO_ANDROID_LEN);
//        }
//
//        JPushClient jPushClient = new JPushClient(masterSecret, appKey, null, config);
//        Message message = buildMessage(contentType, pushMsg, title);
//        PushPayload pushPayload = buildAndroidPushObject(alias, message);
//
//        try {
//            PushResult pushResult = jPushClient.sendPush(pushPayload);
//            if (!pushResult.isResultOK() || pushResult.msg_id <= 0) {
//                result.setStatus(false);
//                result.setMsg("推送失败");
//            } else {
//                result.setStatus(true);
//                result.setMsg(pushResult.getOriginalContent());
//            }
//        } catch (APIConnectionException e) {
//            LOGGER.warn("Connection error, should retry later", e);
//            result.setMsg(aliasStr);
//            result.setStatus(false);
//            return result;
//        } catch (APIRequestException e) {
//            LOGGER.warn("HTTP Status: " + e.getStatus());
//            LOGGER.warn("Error Code: " + e.getErrorCode());
//            LOGGER.warn("Error Message: " + e.getErrorMessage());
//            result.setMsg(aliasStr);
//            result.setStatus(false);
//            return result;
//        }
//
//        return result;
//    }
//}
