package com.tim.iot.platform.register.web.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import com.tim.iot.platform.register.web.protocol.account.AccountInfo;
import com.tim.iot.platform.register.web.protocol.base.BaseResult;
import com.tim.iot.platform.register.web.protocol.base.BaseResult;
import com.tim.iot.platform.register.web.protocol.qrcode.QrCodeInfo;
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
        @JSONField(name = "qrCodeInfo")
        private QrCodeInfo qrCodeInfo;
        @JSONField(name = "accountInfo")
        private AccountInfo accountInfo;
    }
}
