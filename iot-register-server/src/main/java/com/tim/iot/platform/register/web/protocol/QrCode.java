package com.tim.iot.platform.register.web.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import com.tim.iot.platform.register.web.protocol.base.DeviceResult;
import lombok.Data;

public class QrCode {
    @Data
    public static class Param{
        @JSONField(name = "deviceId")
        private String deviceId;
        @JSONField(name = "timestamp")
        private Long timestamp;
        private String type;
    }

}
