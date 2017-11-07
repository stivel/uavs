package com.uavs.common.message;

import com.uavs.common.util.NgearUtil;

public class Message {

    public static final String MESSAGE_FILE = "message.properties";

    /* 成功值 */
    public static final int SUCCESS_VALUE = 1;
    /* 失败值 */
    public static final int FAILURE_VALUE = 0;

    /**
     * 获得消息内容
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String getMessage(String key) throws Exception {
        return NgearUtil.getPropertiesByName(MESSAGE_FILE, key);
    }

    public static String getMessage(int value) throws Exception {
        if (value == Message.SUCCESS_VALUE) {
            return NgearUtil.getPropertiesByName(MESSAGE_FILE,
                    "message.success_value.key");
        } else if (value == Message.FAILURE_VALUE) {
            return NgearUtil.getPropertiesByName(MESSAGE_FILE,
                    "message.failure_value.key");
        } else {
            return NgearUtil.getPropertiesByName(MESSAGE_FILE,
                    "message.untreated_value.key");
        }
    }

}
