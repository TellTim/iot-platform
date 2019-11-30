package com.hunter.iot.platform.register.web.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import com.hunter.iot.platform.register.web.protocol.base.BaseResult;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Data
    @NoArgsConstructor
    public static class Result extends BaseResult {
        @JSONField(name = "qrCode")
        private String qrCode;
        @JSONField(name = "expireIn")
        private int expireIn;
    }
}
