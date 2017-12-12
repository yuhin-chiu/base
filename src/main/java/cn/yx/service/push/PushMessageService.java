//package cn.yx.service.push;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import cn.yx.constant.MessageConstant;
//import cn.yx.enums.MessageTypeEnum;
//import cn.yx.model.ApiResponse;
//import cn.yx.model.PushMessage;
//import cn.yx.util.JsonUtil;
//
///**
// * @author yuxuanjiao
// * @date 2017年9月11日 上午11:24:22
// * @version 1.0
// */
//
//@Service
//public class PushMessageService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(PushMessageService.class);
//
//    @Resource
//    private AndroidPushService pushAndroidService;
//    
//    @Resource
//    private BrokerPushInfoMapper bpiMapper;
//
//    public Boolean push(BrokerMessage brokerMessage) {
//        
//        List<String> tokens = new ArrayList<>();
//        List<String> alias = new ArrayList<String>();
//        
//        BrokerPushInfo broker = bpiMapper.selectByPrimaryKey(brokerMessage.getBrokerId());
//        
//        if (brokerMessage.getBrokerId() == null) {
//            LOGGER.warn("经纪人id为空");
//            return false;
//        }
//        broker = bpiMapper.selectByPrimaryKey(brokerMessage.getBrokerId());
//
//        if (broker == null || broker.getIsPush() == 0) {
//            LOGGER.warn("经纪人不存在或者不在线");
//            return false;
//        }
//        
//        addDeviceForBroker(tokens, alias, broker);
//        PushMessage pushMessage = handleMessageContent(brokerMessage);
//        
//        LOGGER.info("####推送时间:{} 推送类型{}, alias:{}, tokens:{}####", new Date(), brokerMessage.getMessageType(), alias.toString(), tokens.toString());
//        ApiResponse resp =  sendMessage(alias, tokens, pushMessage, "new");
//        return resp.getStatus();
//    }
//
//    private ApiResponse sendMessage(List<String> alias, List<String> tokens, PushMessage pushMessage, String path) {
//
//        ApiResponse result = new ApiResponse();
//        result.setMsg("");
//        result.setStatus(false);
//
//        ApiResponse androidRes = new ApiResponse();
//        androidRes.setMsg("");
//        androidRes.setStatus(false);
//
//        ApiResponse iosRes = new ApiResponse();
//        iosRes.setMsg("");
//        iosRes.setStatus(false);
//
//        pushMessage.setTitle(MessageConstant.MSG_TITLE);
//
//        if (!alias.isEmpty()) {
//            androidRes = pushAndroidService.sendMessage(alias, pushMessage, MessageConstant.MSG_TYPE, path);
//        }
//
//        // if (!tokens.isEmpty()) {
//        // iosRes = iosPushService.sendMessage(tokens, pushMessage, path);
//        // }
//
//        if (iosRes.getStatus() || androidRes.getStatus()) {
//            result.setStatus(true);
//        }
//
//        return result;
//    }
//
//    private void addDeviceForBroker(List<String> tokens, List<String> alias, BrokerPushInfo broker) {
//        // android设备登录才推消息到极光
//        // ios设备登录，且push_token存在才推消息到苹果服务器
//        // 其它情况推了也没用，根据日志分析错误即可
//        if (broker.getAppType() == MessageConstant.DEVICE_ANDROID) {
////            alias.add(broker.getBrokerId() + "");
//            alias.add("55");
//        } else if (broker.getAppType() == MessageConstant.DEVICE_IOS &&
//                StringUtils.isNotBlank(broker.getDeviceToken())) {
//            tokens.add(broker.getDeviceToken());
//        } else {
//            LOGGER.info("推送数据添加失败, broker:{}", JsonUtil.toJson(broker));
//        }
//    }
//    
//    private PushMessage handleMessageContent(BrokerMessage message) {
//        PushMessage pushMessage = new PushMessage();
//        pushMessage.setId(message.getId());
//        pushMessage.setBrokerId(message.getBrokerId());
//        pushMessage.setType(message.getMessageType());
//        pushMessage.setFromType(message.getFromType());
//        pushMessage.setMessage(message.getContent());
//        
//        if(message.getMessageType() == MessageTypeEnum.NOTICE_ALERT.getCode()) {
//            pushMessage.setNoticeType(message.getNoticeType());
//        } else if (message.getMessageType() == MessageTypeEnum.NOTICE_APPEAL.getCode()
//                || message.getMessageType() == MessageTypeEnum.NOTICE_AUDIT.getCode()) {
//            pushMessage.setExtra(message.getPushStatus().intValue());
//        }
//        
//        
//        pushMessage.setType(MessageTypeEnum.NOTICE_ALERT.getCode());
//        return pushMessage;
//    }
//    
//}
