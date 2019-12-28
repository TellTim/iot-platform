package com.tim.iot.platform.register.web.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

public class Register {

    @Data
    public static class Param{
        @JSONField(name = "deviceId")
        private String deviceId;
        private String imei;
        private String mac;
        @JSONField(name = "timestamp")
        private Long timestamp;
        private String type;
    }

}
