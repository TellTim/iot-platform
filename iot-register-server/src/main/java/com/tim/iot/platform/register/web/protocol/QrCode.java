package com.tim.iot.platform.register.web.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import com.tim.iot.platform.register.web.protocol.base.BaseResult;
import com.tim.iot.platform.register.web.protocol.base.Respond;
import com.tim.iot.platform.register.web.protocol.qrcode.QrCodeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

public class QrCode {
    @Data
    public static class Param {
        @JSONField(name = "deviceId")
        private String deviceId;
        @JSONField(name = "timestamp")
        private Long timestamp;
        private String type;
    }

    @Data
    @NoArgsConstructor
    public static class Result extends BaseResult {

        private static final Result DEVICE_NO_EXIT_RESULT = new Result(Respond.DEVICE_NOT_EXIST.getCode(), Respond.DEVICE_NOT_EXIST.getData());

        @JSONField(name = "qrCodeInfo")
        private QrCodeInfo qrCodeInfo;

        public Result(String code, String data) {
            super(code, data);
        }


        public QrCodeInfo getQrCodeInfo() {
            return qrCodeInfo;
        }

        public void setQrCodeInfo(QrCodeInfo qrCodeInfo) {
            this.qrCodeInfo = qrCodeInfo;
        }

        public static Result noDeviceBuild() {
            return DEVICE_NO_EXIT_RESULT;
        }
    }

}
