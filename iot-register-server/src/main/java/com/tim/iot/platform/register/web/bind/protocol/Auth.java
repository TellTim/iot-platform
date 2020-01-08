package com.tim.iot.platform.register.web.bind.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Tell.Tim
 * @date : 2020/1/6 20:54
 * @fileName : Auth
 */
public class Auth {

    @Data
    public static  class Param{
        @JSONField(name = "deviceId")
        private String deviceId;
        private String account;
        @JSONField(name = "authAt")
        private Long authAt;
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
