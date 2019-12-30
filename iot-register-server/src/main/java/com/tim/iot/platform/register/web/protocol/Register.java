package com.tim.iot.platform.register.web.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import com.tim.iot.platform.register.web.protocol.base.BaseResult;
import com.tim.iot.platform.register.web.protocol.base.Respond;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Register {

    @Data
    public static class Param {
        @JSONField(name = "deviceId")
        private String deviceId;
        private String imei;
        private String mac;
        @JSONField(name = "timestamp")
        private Long timestamp;
        private String type;
    }


    @NoArgsConstructor
    public static class Result extends BaseResult {

        private static final Result NO_BIND_RESULT = new Result(Respond.BIND_NOT_EXIST.getCode(), Respond.DEVICE_NOT_EXIST.getData());

        public Result(String code, String data) {
            super(code, data);
        }

        public static Result buildNotBind() {
            return NO_BIND_RESULT;
        }
    }

}
