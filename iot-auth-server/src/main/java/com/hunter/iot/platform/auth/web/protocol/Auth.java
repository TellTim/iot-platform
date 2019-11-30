package com.hunter.iot.platform.auth.web.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Auth {
    @Data
    public static  class Param{
        @JSONField(name = "deviceId")
        private String deviceId;
        private String account;
    }

    @Data
    @NoArgsConstructor
    public static class Result{
        private String code;
        private String data;

        public Result(String code, String data) {
            this.code = code;
            this.data = data;
        }

        public static Result buildOk() {
            return new Result("200","success");
        }
    }
}
