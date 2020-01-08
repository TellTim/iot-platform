package com.tim.iot.platform.register.web.bind.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Tell.Tim
 * @date : 2020/1/6 20:37
 * @fileName : Bind
 */
public class Bind {

    @Data
    public static class Param {
        @JSONField(name = "traceId")
        private String traceId;
        @JSONField(name = "deviceId")
        private String deviceId;
        private String account;
        @JSONField(name = "authAt")
        private Long authAt;
    }

    @Data
    @NoArgsConstructor
    public static class Result {
        public static final Result DEVICE_NOT_EXIST_RESULT = new Result("90000", "Device Not Exist");
        private String code;
        private String data;

        public Result(String code, String data) {
            this.code = code;
            this.data = data;
        }
    }
}
