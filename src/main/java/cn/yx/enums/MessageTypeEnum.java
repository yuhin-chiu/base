package cn.yx.enums;

/**
 * @author yuxuanjiao
 * @date 2017年9月8日 下午4:46:49 
 * @version 1.0
 */

public enum MessageTypeEnum {
    
    FROM_CLUE(0, "系统消息"),
    FROM_NOTICE(1, "消息通知"),
    
    CLUE_APPEAL(1, "申诉成功退款"),
    CLUE_COIN_EXCHANGE(2, "兑换虚拟币成功"),
    CLUE_COIN_GIFT(3, "虚拟币买赠开启"),
    CLUE_EXPIRE(4, "推广到期"),
    CLUE_OTHER(5, "其他类型"),
    
    NOTICE_ALERT(6, "提醒"),
    NOTICE_MISSCALL(7, "未接来电"),
    NOTICE_MISSNOTE(8, "未读消息"),
    NOTICE_LEVELUP(9, "等级提升"),
    NOTICE_APPEAL(10, "申诉通知"),
    NOTICE_AUDIT(11, "审核通知");
    private Byte code;
    private String type;

    MessageTypeEnum(Integer code, String type) {
        this.code = code.byteValue();
        this.type = type;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
